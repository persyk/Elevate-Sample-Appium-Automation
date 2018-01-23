package com.elevate.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;

/**
 * @author QASource 
 * OnBoardingPage consists of all the methods related to onBoarding page
 */

public class OnBoardingPage extends BaseAppPage {
		
	private String notificationOnboardingScreenTitle;
	private String enableNotificationButton;
	private String locationOnboardingScreenTitle;
	private String enableLocationServiceButton;
	private String bluetoothOnboardingScreenTitle;
	private String onboarding_notNowButton;
	private String onboarding_OkButton;
	private String permissionsTitleTextlabel;
	private String alertPopup;
	private String cancelButton;
	
	// Data.
		public final static String LOCATION_SERVICES_TITLE = "Location Services";
		public final static String BLUETOOTH_SERVICES_TITLE = "Bluetooth";
		public final static String NOTIFICATION_SERVICES_TITLE = "Notifications";
	
	public OnBoardingPage(WebDriver driver) {
		super(driver);
		
		LocatorsPage locatorPage = getLocatorsPage();		
		this.notificationOnboardingScreenTitle = locatorPage.notificationOnboardingScreenTitle;
		this.enableNotificationButton = locatorPage.enableNotificationButton;
		this.locationOnboardingScreenTitle = locatorPage.locationOnboardingScreenTitle;
		this.enableLocationServiceButton = locatorPage.enableLocationServiceButton;
		this.bluetoothOnboardingScreenTitle = locatorPage.bluetoothOnboardingScreenTitle;
		this.onboarding_notNowButton = locatorPage.onboarding_notNowButton;
		this.onboarding_OkButton = locatorPage.onboarding_OkButton;
		this.permissionsTitleTextlabel = locatorPage.permissionsTitleTextlabel;
		this.alertPopup = locatorPage.alertPopup;
		this.cancelButton = locatorPage.cancelButton;
		}
	
	public Boolean isLocationServicesTitleDisplayed() {
		if (isElementPresent(By.id(locationOnboardingScreenTitle))) {
			return findElementById(locationOnboardingScreenTitle).getText().equalsIgnoreCase(LOCATION_SERVICES_TITLE);
		} else
			return false;
	}

	public Boolean isBluetoothServicesTitleDisplayed() {
		if (isElementPresent(By.id(bluetoothOnboardingScreenTitle))) {
			return findElementById(bluetoothOnboardingScreenTitle).getText().equalsIgnoreCase(BLUETOOTH_SERVICES_TITLE);
		} else
			return false;
	}

	public Boolean isNotifcationServicesTitleDisplayed() {
		if (isElementPresent(By.id(notificationOnboardingScreenTitle))) {
			return findElementById(notificationOnboardingScreenTitle).getText().equalsIgnoreCase(NOTIFICATION_SERVICES_TITLE);
		} else
			return false;
	}
	
	public void clickOnNotNowButton() {
		findElementById(onboarding_notNowButton).click();
	}
	
	public void skipLocationServices() {
		if (isLocationServicesTitleDisplayed()) {
			waitForElement(By.id(onboarding_notNowButton), "wait", 10);
			clickOnNotNowButton();
			getLogger().info("tapped Not Now button on Location Onboarding screen");
		}
	}
	
	public void skipBluetoothServices() {
		if (isBluetoothServicesTitleDisplayed()) {
			if (getDriverType().equalsIgnoreCase("android")) {
				clickOnNotNowButton();
			} else {
				waitForElement(By.id(onboarding_OkButton), "wait", 10);
				tapOkOnBluetoothOnboardingScreen();
				getLogger().info("tapped OK button on Bluetooth Onboarding screen");
			}
		}
	}
	
	public void clickOnCancelButton() {
		findElementById(cancelButton).click();
	}
	
	public Boolean isAlertPopupMessageDisplayed() {
		return isElementPresent(By.xpath(alertPopup));
	}
	
	public void rejectAlertForLocationsSettings() {
		//waitForElement(By.id(alertPopup), "Popup takes some time to appears.", 10);
		if(isAlertPopupMessageDisplayed()) {
			clickOnCancelButton();
		}
	}
	
	public void tapNotNowButton(){
		findElementById(onboarding_notNowButton).click();
	}
	
	public void skipNotificationsOnboardingScreen() {
		if (isNotifcationServicesTitleDisplayed())
			waitForElement(By.id(onboarding_notNowButton), "wait", 10);
			tapNotNowButton();
		getLogger().info("tapped Not Now button on Notifications Onboarding screen");
	}
	
	public void tapOkOnBluetoothOnboardingScreen(){
		if(isBluetoothServicesTitleDisplayed())
		findElementById(onboarding_OkButton).click();
	}		
	
	public HomePage skipAllOnboardingScreens(){		
		if (getDriverType().equalsIgnoreCase("android")) {
			skipLocationServices();
			skipBluetoothServices();
			rejectAlertForLocationsSettings();
			return new HomePage(getDriver());
		} else {
			skipNotificationsOnboardingScreen();
			skipLocationServices();
			skipBluetoothServices();
			return new HomePage(getDriver());
		}	
	}
	
}
