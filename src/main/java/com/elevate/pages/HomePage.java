package com.elevate.pages;

//import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;

/**
 * @author QASource 
 * HomePage consists of all the methods related to home page
 */

public class HomePage extends BaseAppPage {
	
	// Element locators for Home Page.
	private String welcomeMessage;
	private String hamburgerIcon;
	private String serviceTitle;

		
	public HomePage(WebDriver driver) {
		super(driver);
		
		LocatorsPage locatorPage = getLocatorsPage();	
		this.welcomeMessage = locatorPage.welcomeMessage;
		this.hamburgerIcon = locatorPage.hamburgerIcon;
		this.serviceTitle = locatorPage.serviceTitle;
	}	
	
	public Boolean isWelcomeMessageDisplayed() {
		if(getDriverType().equalsIgnoreCase("android")){
		return findElementById(welcomeMessage).getText().contains("WELCOME");
		}
		else{
			return isElementPresent(By.xpath("//XCUIElementTypeStaticText[contains(@name,'WELCOME')]"));
		}
	}
	
	public Boolean isHamburgerIconDisplayed() {
		return isElementPresent(By.id(hamburgerIcon));
	}

	public MenuPage clickOnHamburgerIcon() {
		findElementById(hamburgerIcon).click();
		return new MenuPage(getDriver());
	}
	
}
