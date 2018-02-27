package generics;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	
	public static String getFormatedDateTime(){
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return simpleDate.format(new Date());
	}
	
	public static String getScreenShot(WebDriver driver, String imageFolderPath){
		String imagePath=imageFolderPath+"/"+getFormatedDateTime()+".png";
		TakesScreenshot page=(TakesScreenshot) driver;
		try{
			FileUtils.copyFile(page.getScreenshotAs(OutputType.FILE), new File(imagePath));
		}catch(Exception e){
			
		}
		return imagePath;
		
	}
	
	public static String getScreenShot(String imageFolderPath){
		String imagePath=imageFolderPath+"/"+getFormatedDateTime()+".png";
		
		try{
			Dimension desktopSize=Toolkit.getDefaultToolkit().getScreenSize();
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(desktopSize));
			ImageIO.write(image, "png", new File(imagePath));
		}
		catch(Exception e){
		}

		return imagePath;
		
	}
	public static String getPropertyValue(String filePath,String key)
	{
		String value="";
		Properties ppt=new Properties();
		try{
			ppt.load(new FileInputStream(filePath));
			value=ppt.getProperty(key);
		}
		catch(Exception e){
		}
		return value;
	}
	public static int getExcelRowCount(String path,String sheet)
	{
		int r=0;
		try{
			
			r=WorkbookFactory.create(new FileInputStream(path)).getSheet(sheet).getLastRowNum();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return r;
	}
	
	public static String getExcelCellValue(String path,String sheet,int r,int c)
	{
		String v="";
		try{
			
			v=WorkbookFactory.create(new FileInputStream(path)).getSheet(sheet).getRow(r).getCell(c).toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return v;
	}
	
	public static boolean verifyElementIsPresent(WebDriverWait wait,WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception E) {
			return false;
		}
	}

	public static boolean verifyElementIsPresent(WebDriverWait wait, By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public static boolean verifyElementIsNotPresent(WebDriverWait wait,WebElement element) {
		try {
			wait.until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean verifyElementIsNotPresent(WebDriverWait wait, By locator) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			
			return false;
		}
	}
	public static void clickUsingJS(WebDriver driver, WebElement e) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].click()", e);
													
	}
	public static ArrayList<String> getAllTextFromListBox(WebElement listBox, int startIndex) {
		Select select = new Select(listBox);
		List<WebElement> allOptions = select.getOptions();
		ArrayList<String> allText = new ArrayList<String>();
		for (int i = startIndex; i < allOptions.size(); i++) {
			String text = allOptions.get(i).getText();
			allText.add(text);
		}
		return allText;
	}
	public static boolean checkArrayListHasNoDuplicate(ArrayList<String> allText) {
		HashSet<String> clone = new HashSet<String>();
		for (String s : allText) {
			if (clone.add(s)) {
				System.out.println(s + "->Not Duplicate");
			} else {
				System.out.println(s + "->Is Duplicate");
				return false;// means it has duplicate
			}
		}
		return true; // means it has no duplicate
	}

	

	public static boolean checkArrayListIsSorted(ArrayList<String> allText) {
		ArrayList<String> clone = new ArrayList<String>(allText);
		Collections.sort(clone, String.CASE_INSENSITIVE_ORDER);
		// for debugging purpose
		System.out.println("------------------------------------");
		for (int i = 0; i < clone.size(); i++) {
			System.out.println(allText.get(i) + "==" + clone.get(i));
		}
		System.out.println("------------------------------------");
		return allText.equals(clone);
	}

	public static boolean switchBrowser(WebDriver driver, String eTitle) { 
	String currentWH = "";
		try 
		{
			currentWH = driver.getWindowHandle();
		} catch (Exception e)
		{
		}
	Set<String> allWH = driver.getWindowHandles();
	for (String wh : allWH) {
	String title = driver.switchTo().window(wh).getTitle();
		if (title.equals(eTitle)) {
			System.out.println("Browser Found");
			return true;
		}
	}
	driver.switchTo().window(currentWH);
	return false;
}
	
	public static boolean switchBrowser(String eURL, WebDriver driver) {
	String currentWH = "";
		try {
			currentWH = driver.getWindowHandle();
		} 
		catch (Exception e) 
		{
		}
		Set<String> allWH = driver.getWindowHandles();
		for (String wh : allWH) {
			String url = driver.switchTo().window(wh).getCurrentUrl();
				if (url.contains(eURL)) {
					System.out.println("Browser Found");
					return true;
				}
		}
		driver.switchTo().window(currentWH);
		return false;
	}
	
	
	
	public static void moveToElement(WebDriver driver, WebElement element) {
		Actions ac = new Actions(driver);
		ac.moveToElement(element).perform();
	}

	public static boolean verifyURLhas(WebDriverWait wait,String expectedPartialUrl) {
		try{
			wait.until(ExpectedConditions.urlContains(expectedPartialUrl));
			System.out.println("URL contains:"+expectedPartialUrl);
			return true;
		}
		catch(Exception e){
			System.out.println("URL doest not contains:"+expectedPartialUrl);
			return false;
		}
		}

	public static boolean verifyURLIs(WebDriverWait wait,String expectedCompleteUrl) {
			try{
				wait.until(ExpectedConditions.urlToBe(expectedCompleteUrl));
				System.out.println("URL is:"+expectedCompleteUrl);
				return true;
			}
			catch(Exception e){
				System.out.println("URL is not:"+expectedCompleteUrl);
				return false;
			}
		}

	
}
