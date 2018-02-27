package com.hrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hrm.base.HomePage;

public class EmpInfoPage extends HomePage{


	public EmpInfoPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="searchBtn")
	private WebElement searchBtn;
	
	@FindBy(id="btnDelete")
	private WebElement btnDelete;
	
	@FindBy(id="dialogDeleteBtn")
	private WebElement btnOK;
	
	@FindBy(id="empsearch_termination")
	private WebElement includeListBox;
	
	public void checkSearchButtonIsPresent(){
		checkElementIsPresent(searchBtn);
	}
	
	public void selectEmpCheckBox(String eID){
		String xp="//a[.='"+eID+"']/../..//input";
		driver.findElement(By.xpath(xp)).click();
	}
	
	public void clickDelete(){
		btnDelete.click();
	}
	
	public void clickOK(){
		btnOK.click();
	}
	
	public void checkEmpIsDeleted(String eID){
		String xp="//a[.='"+eID+"']";
		checkElementIsNotPresent(By.xpath(xp));
		log.info("PASS:Emp:"+eID+" is Deleted");
	}
	
	public void checkIncludeListBoxIsSorted(){
		checkListBoxIsSorted(includeListBox, 0);
	}
	
	public void checkIncludeListBoxHasNoDuplicate(){
		checkListBoxHasNoDuplicate(includeListBox,0);
	}
}
