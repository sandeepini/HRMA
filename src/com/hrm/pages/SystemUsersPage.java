package com.hrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hrm.base.HomePage;

public class SystemUsersPage extends HomePage{
	public SystemUsersPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="searchSystemUser_userType")
	private WebElement userRoleListBox;
	
	public void checkUserRoleListBoxIsSorted(){
		checkListBoxIsSorted(userRoleListBox,1);
		log.info("PASS:User Role ListBox is Sorted");
	}
}
