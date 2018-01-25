package com.elevate.locators;

public class IOSLocatorsPage extends LocatorsPage {

	public IOSLocatorsPage() {

		super.alertTitle = "Error"; // Name
		super.alertMessage = "Incorrect username or password";
		super.headerTitle = "";

		/** Login Screen **/
		super.applePayPopUpOkButton = "OK";
		super.emailloginScreenTitle = "Email";
		super.loginEmail = "inputUserEmail";
		super.loginEmailNextButton = "emailNextBtn";
		super.passwordloginScreenTitle = "Password";
		super.loginPassword = "inputPassword";
		super.loginButton = "Log In";
		super.incorrectFormatPasswordMessage = "Hint: your password must be at least 8 characters with 1 uppercase letter and 1 number.";

		/** Onboarding Screen **/
		super.notificationOnboardingScreenTitle = "Notifications";
		super.locationOnboardingScreenTitle = "Location Services";
		super.bluetoothOnboardingScreenTitle = "Bluetooth";
		super.enableNotificationButton = "Enable Notifications";
		super.enableLocationServiceButton = "Enable Location Services";
		super.onboarding_notNowButton = "Not Now";
		super.onboarding_OkButton = "OK";

		/** Home Screen **/
		super.welcomeMessage = "//XCUIElementTypeStaticText[contains(@name,'WELCOME')]"; // Xpath
		super.hamburgerIcon = "menu";
		super.serviceTitle = "";

		/** Menu Screen **/		
		super.settingsOption= "Settings";
		
		/** Home Screen **/
		super.logoutButton = "optionLogout";
	}
}
