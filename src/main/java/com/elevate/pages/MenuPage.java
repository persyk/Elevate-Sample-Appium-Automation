package com.elevate.pages;

import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;

/**
 * @author QASource MenuPage consists of all the methods related to menu page
 */

public class MenuPage extends BaseAppPage {

	// Element locators for Menu Page.
	private String settingsOption;

	public MenuPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		this.settingsOption = locatorPage.settingsOption;
	}
	
	public SettingsPage clickOnSettingsOption() {
		findElementById(settingsOption).click();
		return new SettingsPage(getDriver());
	}

}
