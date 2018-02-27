package com.hrm.scripts;

import org.testng.annotations.Test;
import com.hrm.base.BaseTest;
import com.hrm.pages.DashboardPage;
import com.hrm.pages.EmpInfoPage;

public class TestSearchButton extends BaseTest{

	@Test(priority=2)
	public void testSearchButton(){
		//click on PIM menu
		DashboardPage dbPage=new DashboardPage(driver);
		dbPage.clickPIM_Menu();
		//verify Search Button is Present
		EmpInfoPage eiPage=new EmpInfoPage(driver);
		eiPage.checkSearchButtonIsPresent();
	}
}
