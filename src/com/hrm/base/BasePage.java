package com.hrm.base;

import generics.Utility;
import org.testng.Assert;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	protected Logger log = Logger.getLogger(this.getClass());
	protected long timeout = Long.parseLong(Utility.getPropertyValue(AutomationConstants.CONFIG_PATH, "EXPLICIT"));
	protected WebDriver driver;
	protected WebDriverWait wait;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, timeout);
	}

	protected void checkListBoxIsSorted(WebElement listBox, int startIndex) {
		ArrayList<String> allText = Utility.getAllTextFromListBox(listBox, startIndex);
		boolean sorted = Utility.checkArrayListIsSorted(allText);
		Assert.assertTrue(sorted, "FAIL:ListBox is Not Sorted");
		log.info("PASS:ListBox is Sorted");
	}

	protected void checkListBoxHasNoDuplicate(WebElement listBox, int startIndex) {
		ArrayList<String> allText = Utility.getAllTextFromListBox(listBox, startIndex);
		boolean noDuplicate = Utility.checkArrayListHasNoDuplicate(allText);
		Assert.assertTrue(noDuplicate, "FAIL:ListBox has duplicate");
		log.info("PASS:ListBox has no duplicate");
	}

	protected void checkElementIsPresent(WebElement element) {
		boolean present = Utility.verifyElementIsPresent(wait, element);
		Assert.assertTrue(present, "FAIL:Element is not present");
		log.info("PASS:Element is present");
	}
	protected void checkElementIsPresent(By locator) {
		boolean present = Utility.verifyElementIsPresent(wait, locator);
		Assert.assertTrue(present, "FAIL:Element is not present");
		log.info("PASS: Element is present");
	}
	
	protected void checkElementIsNotPresent(WebElement element) {
		boolean present = Utility.verifyElementIsPresent(wait, element);
		Assert.assertFalse(present, "FAIL:Element is present");
		log.info("PASS: Element is Not present");
	}
	
	protected void checkElementIsNotPresent(By locator) {
		boolean present = Utility.verifyElementIsPresent(wait, locator);
		Assert.assertFalse(present, "FAIL:Element is present");
		log.info("PASS: Element is Not present");
	}

}
