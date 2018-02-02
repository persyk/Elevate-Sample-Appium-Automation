package com.elevate.pages.cafe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * CafeDetailsPage consists of all the methods related to cafe details page
 */

public class CafeDetailsPage extends BaseAppPage {

	// Element locators for Menu Page.
		private String detailsTitle;
		private String addToCartButton;
								
	public CafeDetailsPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.detailsTitle = locatorPage.detailsTitle;
		this.addToCartButton =  locatorPage.addToCartButton;
		
	}
	
	public Boolean isDetailsTitleDisplayed() {
		return isElementPresent(By.xpath(detailsTitle));
	}
	
	public void clickOnFullOrHalfPlate(String plateOption) {
		String plateValue = "//android.widget.RadioButton[@text='"+plateOption+"']";		
		findElementByXpath(plateValue).click();		
	}
	
	public CafeCheckoutPage clickOnAddToCart() {
		findElementById(addToCartButton).click();
		return new CafeCheckoutPage(getDriver());
	}
	
	public Boolean isSelectedItemAndAmountDisplayed(String item, String price) {
		return findElementById("title").getText().equalsIgnoreCase(item) && findElementById("price").getText().equalsIgnoreCase(price);
	}
	
}
