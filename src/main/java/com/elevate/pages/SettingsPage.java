package com.elevate.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;

/**
 * @author QASource SettingsPage consists of all the methods related to settings
 *         page
 */

public class SettingsPage extends BaseAppPage {

	private String settingsTitle;
	private String logoutButton;
	private String changePasswordOption;

	public SettingsPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();	
		this.settingsTitle = locatorPage.settingsTitle;
		this.logoutButton = locatorPage.logoutButton;
	}

	public Boolean isSettingsTitleDisplayed() {
		return isElementPresent(By.xpath(settingsTitle));
	}

	public void clickOnLogoutButton() {
		findElementById(logoutButton).click();
	}

	public void clickOnChangePasswordOption() {
		findElementById(changePasswordOption).click();
	}

}
