package com.hrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.hrm.base.HomePage;

public class AddJobTitlePage extends HomePage {

	public AddJobTitlePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="jobTitle_jobTitle")
	private WebElement jobTitle;
	
	@FindBy(id="btnSave")
	private WebElement btnSave;
	
	
	public void setJobTitle(String title){
		jobTitle.sendKeys(title);
	}
	
	public void clickSave(){
		btnSave.click();
	}
	
	
}
