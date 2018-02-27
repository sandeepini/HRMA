package com.hrm.scripts;

import org.testng.annotations.Test;
import com.hrm.base.BaseTest;
import com.hrm.pages.DashboardPage;
import com.hrm.pages.EmpInfoPage;

public class TestIncludeListBox extends BaseTest{

	@Test(priority=5,enabled=false)
	public void testIncludeListBox(){
		//click PIM menu
		DashboardPage dbPage=new DashboardPage(driver);
		dbPage.clickPIM_Menu();
		
		EmpInfoPage eiPage=new EmpInfoPage(driver);
		//check Include LB has no duplicate
		eiPage.checkIncludeListBoxHasNoDuplicate();
				
		//check Include LB is sorted
		eiPage.checkIncludeListBoxIsSorted();
		
	}
	
}
