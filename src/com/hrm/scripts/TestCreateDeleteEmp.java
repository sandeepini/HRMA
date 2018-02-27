package com.hrm.scripts;

import generics.Utility;

import org.testng.annotations.Test;

import com.hrm.base.BaseTest;
import com.hrm.pages.AddEmpPage;
import com.hrm.pages.DashboardPage;
import com.hrm.pages.EmpInfoPage;
import com.hrm.pages.PersonalDetailsPage;

public class TestCreateDeleteEmp extends BaseTest{

	@Test(priority=4,enabled=false)
	public void testCreateDeleteEmp(){
		int rc=Utility.getExcelRowCount(INPUT_PATH, "Emp");
		for(int i=1;i<=rc;i++){
		String fn=Utility.getExcelCellValue(INPUT_PATH, "Emp",i,0);
		String ln=Utility.getExcelCellValue(INPUT_PATH, "Emp",i,1);
		// go to PIM->add employee
		DashboardPage dbPage=new DashboardPage(driver);
		dbPage.clickPIM_Menu();
		dbPage.click_AddEmp_Menu();
		//enter Fn, LN & Save;
		AddEmpPage aePage=new AddEmpPage(driver);
		aePage.setFirstName(fn);
		aePage.setLastName(ln);
		aePage.clickSave();
		//compare first name
		PersonalDetailsPage pdPage=new PersonalDetailsPage(driver);
		pdPage.checkFirstName(fn);
		//get new emp id
		String eID = pdPage.getEmpID();
		//click emp list menu
		pdPage.clickEmpList_Menu();
		//select check box of new emp id
		EmpInfoPage eiPage=new EmpInfoPage(driver);
		eiPage.selectEmpCheckBox(eID);
		//click delete
		eiPage.clickDelete();
		//click OK
		eiPage.clickOK();
		//verify deleted emp not present
		eiPage.checkEmpIsDeleted(eID);
		}
	}
}
