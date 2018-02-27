package com.hrm.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.hrm.base.BaseTest;
import com.hrm.pages.LoginPage;

public class TestLoginButtonIsPresent extends BaseTest{
	@BeforeClass
	public void disableAutoLoginLogout(){
		loginlogoutRequired=false;
	}
	
	@Test(priority=3,enabled=false)
	public void  testLoginButtonIsPresent(){
		LoginPage lPage=new LoginPage(driver);
		lPage.checkLoginButtonIsPresent();
	}
}
