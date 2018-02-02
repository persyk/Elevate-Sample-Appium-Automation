package com.elevate.pages.cafe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * CafeOrderConfirmationPage consists of all the methods related to cafe order confirmation page
 */

public class CafeOrderConfirmationPage extends BaseAppPage {

	// Element locators for Menu Page.		
	private String orderConfirmedMessage;

	public final static String ORDER_CONFIRMATION_MESSAGE = "Your Order Has Been Placed";

	public CafeOrderConfirmationPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();

		this.orderConfirmedMessage = locatorPage.orderConfirmedMessage;


	}

	public Boolean isOrderConfirmationMessageDisplayed() {
		return findElementById(orderConfirmedMessage).getText().equalsIgnoreCase(ORDER_CONFIRMATION_MESSAGE);
	}


}
