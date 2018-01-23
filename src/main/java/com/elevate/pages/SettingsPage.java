package com.elevate.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * @author QASource 
 * SettingsPage consists of all the methods related to settings page
 */

public class SettingsPage extends BaseAppPage {
	
	private String settingsTitle;
	private String logoutButton;
	private String changePasswordOption;
	private String currentPasswordTextfield;
	private String newPasswordTextfield;
	private String newPasswordConfirmationTextfield;
	private String changePasswordButton;
	
	public SettingsPage(WebDriver driver) {
		super(driver);
		this.settingsTitle = loadLocatorFile().getProperty("settingsTitle");
		this.logoutButton = loadLocatorFile().getProperty("logoutButton");
		this.changePasswordOption = loadLocatorFile().getProperty("changePasswordOption");
		this.currentPasswordTextfield = loadLocatorFile().getProperty("currentPasswordTextfield");
		this.newPasswordTextfield = loadLocatorFile().getProperty("newPasswordTextfield");
		this.newPasswordConfirmationTextfield = loadLocatorFile().getProperty("newPasswordConfirmationTextfield");
		this.changePasswordButton = loadLocatorFile().getProperty("changePasswordButton");
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
