package com.hrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import generics.Utility;

public abstract class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@id='welcome']")
	private WebElement welcome;

	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement logout;

	@FindBy(id = "menu_pim_viewPimModule")
	private WebElement pim_Menu;

	@FindBy(id = "menu_admin_viewAdminModule")
	private WebElement adminModule;

	@FindBy(id = "menu_admin_Job")
	private WebElement admin_Job_Menu;

	@FindBy(id = "menu_pim_addEmployee")
	private WebElement addEmp_Menu;

	@FindBy(id = "menu_admin_employmentStatus")
	private WebElement admin_EmploymentStatus;

	@FindBy(id = "menu_pim_viewEmployeeList")
	private WebElement empList_Menu;

	@FindBy(id = "menu_admin_Configuration")
	private WebElement configuration_Menu;

	@FindBy(id = "menu_admin_localization")
	private WebElement admin_localization_Menu;

	@FindBy(id = "menu_pim_Configuration")
	private WebElement pimConfigurationMenu;

	@FindBy(id = "menu_admin_pimCsvImport")
	private WebElement pimConfigurationCSVImport;

	@FindBy(id = "menu_admin_UserManagement")
	private WebElement userManagementMenu;

	@FindBy(id = "menu_admin_viewSystemUsers")
	private WebElement userMenu;
	
	@FindBy(id="menu_leave_viewLeaveModule")
	private WebElement leaveMenu;
	
	@FindBy(id="menu_leave_assignLeave")
	private WebElement assignLeaveMenu;
	
	@FindBy(id="menu_time_viewTimeModule")
	private WebElement timeMenu;
	
	@FindBy(id="menu_recruitment_viewRecruitmentModule")
	private WebElement recruitmentMenu;
	
	@FindBy(id="menu_recruitment_viewJobVacancy")
	private WebElement recruitmentVacanciesMenu;
	
	@FindBy(id="menu_admin_Job")
	private WebElement adminJobMenu;
	
	@FindBy(id="menu_admin_workShift")
	private WebElement adminJobWorkshifts;
	
	@FindBy(id="menu_admin_viewJobTitleList")
	private WebElement jobTitleMenu;
	
	
	public void logout() {
		Utility.clickUsingJS(driver, welcome);
		Utility.clickUsingJS(driver, logout);
	}
	
	public void clickPIM_Menu(){
		pim_Menu.click();
	}
	
	public void click_AddEmp_Menu(){
		addEmp_Menu.click();
	}
	
	public void clickEmpList_Menu(){
		empList_Menu.click();
	}
	
	
	public void clickAdminModule(){
		adminModule.click();
	}
	
	public void moveToJobMenu(){
		Utility.moveToElement(driver, admin_Job_Menu);
	}
	
	public void clickJobTitleMenu(){
		jobTitleMenu.click();
	}
	
	public void moveToUserManagementMenu(){
		Utility.moveToElement(driver,userManagementMenu);
	}
	
	public void clickUsersMenu(){
		userMenu.click();
	}
	
	public void clickLeaveMenu(){
		leaveMenu.click();
	}
	
	public void moveToAdmin(){
		Utility.moveToElement(driver,adminModule);
	}
}
