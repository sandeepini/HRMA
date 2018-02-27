package com.hrm.scripts;

import org.testng.annotations.Test;

import com.hrm.base.BaseTest;
import com.hrm.pages.DashboardPage;
import com.hrm.pages.SystemUsersPage;

public class TestUserRoleListBox extends BaseTest{

	@Test(priority=7,enabled=false)
	public void testUserRoleListBox(){
		DashboardPage dbPage=new DashboardPage(driver);
		dbPage.clickAdminModule();
		dbPage.moveToUserManagementMenu();
		dbPage.clickUsersMenu();
		
		SystemUsersPage suPage=new SystemUsersPage(driver);
		suPage.checkUserRoleListBoxIsSorted();
	}
}
