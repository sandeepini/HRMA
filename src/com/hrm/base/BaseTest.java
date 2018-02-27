package com.hrm.base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.hrm.pages.DashboardPage;
import com.hrm.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generics.TestListener;
import generics.Utility;

@Listeners(TestListener.class)
public abstract class BaseTest implements AutomationConstants{
	
	public WebDriver driver;
	public Logger log;
	public boolean loginlogoutRequired;
	
	public static ExtentTest eTest;
	public static ExtentReports eReport;
	
	public static String url;
	public static String un;
	public static String pw;
	public static long iTimeout;
	public static long eTimeout;
	public static String reportFile;
	
	public BaseTest(){
		log=Logger.getLogger(this.getClass());
		loginlogoutRequired=true;
	}
	
	@BeforeSuite
	public void initFrameWork()
	{
		log.info("Initializing ExtentReport");
		String now=Utility.getFormatedDateTime();
		reportFile=REPORT_PATH+now+".html";
		eReport=new ExtentReports(reportFile);
		
		log.info("Initialize Global Variables");
		url=Utility.getPropertyValue(CONFIG_PATH,"URL");
		un=Utility.getPropertyValue(CONFIG_PATH,"UN");
		pw=Utility.getPropertyValue(CONFIG_PATH,"PW");
		iTimeout=Long.parseLong(Utility.getPropertyValue(CONFIG_PATH,"IMPLICIT"));
		eTimeout=Long.parseLong(Utility.getPropertyValue(CONFIG_PATH,"EXPLICIT"));
	
	}
	
	@AfterSuite
	public void publishReport()
	{
		log.info("Publishing ExtentReport:"+reportFile);
		eReport.flush();
	}
	
	@BeforeTest
	public void initGlobalVar(){
		System.setProperty(CHROME_KEY,CHROME_VALUE);
		System.setProperty(GECKO_KEY,GECKO_VALUE);
	}
	
	@Parameters({"browser"})
	@BeforeClass
	public void initApplication(@Optional("chrome")String browser){
		log.info("Opening Browser:"+browser);
		if(browser.equals("chrome")){
			driver=new ChromeDriver();
		}
		else{
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(iTimeout,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void closeApplication(){
		log.info("Closing Browser");
		driver.quit();
	}
	
	@BeforeMethod
	public void preCondition(Method method){
		driver.get(url);
		if(loginlogoutRequired){
			log.info("Auto login");
			new LoginPage(driver).login(un,pw);
		}
		eTest=eReport.startTest(method.getName());
		log.info("Started executing test:"+method.getName());
	}
	
	@AfterMethod
	public void postCondition(ITestResult testNGTestResult){
		if(testNGTestResult.getStatus()==ITestResult.FAILURE)
		{
			String imgPath = Utility.getScreenShot(driver,REPORT_PATH);
			String path = eTest.addScreenCapture("."+imgPath);
			eTest.log(LogStatus.FAIL,"Check log for details",path);
			log.error("Test is FAILED");
		}
		else
		{
			eTest.log(LogStatus.PASS,"Script executed successfully");
			log.info("Test is PASSED");
		}
		if(loginlogoutRequired){
			log.info("Auto logout");
			new DashboardPage(driver).logout();
		}
		eReport.endTest(eTest);
	}
}





