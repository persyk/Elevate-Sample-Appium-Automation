package com.elevate.pages.cafe;

import java.beans.Visibility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.elevate.driver.webdriver.ExpectedConditions;
import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * CafeCheckoutPage consists of all the methods related to cafe checkout page
 */

public class CafeCheckoutPage extends BaseAppPage {

	// Element locators for Menu Page.
		private String cartTitle;
		private String placeOrderButton;
		private String orderConfirmationMessage;
								
	public CafeCheckoutPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.cartTitle = locatorPage.cartTitle;
		this.placeOrderButton = locatorPage.placeOrderButton;
		this.orderConfirmationMessage = locatorPage.orderConfirmedMessage;
	}
	
	public Boolean isCartTitleDisplayed() {
		return isElementPresent(By.xpath(cartTitle));
	}
	
	public CafeOrderConfirmationPage clickOnPlaceOrderButton() {
		waitForElement(findElementById("cardTextView"), "wait", 30);
		findElementById(placeOrderButton).click();
	    waitForElement(By.id(orderConfirmationMessage), "wait", 30);		
		return new CafeOrderConfirmationPage(getDriver());
	}
		
}
