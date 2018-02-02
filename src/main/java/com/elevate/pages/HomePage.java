package com.elevate.pages;

//import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.cafe.CafePage;

/**
 * @author QASource HomePage consists of all the methods related to home page
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
		if (getDriverType().equalsIgnoreCase("android")) {
			return findElementById(welcomeMessage).getText().contains("WELCOME");
		} else {
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

	public CafePage clickOnServiceTitle(String serviceTitle) {
		String service = "//android.widget.TextView[@text='"+serviceTitle+"']";
		if (isElementPresent(By.xpath(service))) {
			findElementByXpath(service).click();
			return new CafePage(getDriver());				
		}
		else
		{
			int start_x = findElementByXpath("//android.widget.TextView[@text='RESERVE A ROOM']").getLocation().getX();
			int start_y = findElementByXpath("//android.widget.TextView[@text='RESERVE A ROOM']").getLocation().getY();
			swipe(start_x, start_y, 0, -1480);
			findElementByXpath(service).click();
		    return new CafePage(getDriver());
		}
	}
	
}
