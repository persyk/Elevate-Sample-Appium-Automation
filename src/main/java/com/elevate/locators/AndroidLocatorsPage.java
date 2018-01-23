package com.elevate.locators;

public class AndroidLocatorsPage extends LocatorsPage {

	public AndroidLocatorsPage() {

		super.alertTitle = "alertTitle";
		super.alertMessage = "message";
		super.headerTitle = "navigationTitle";	
		
		/** Login Screen **/
		super.emailloginScreenTitle = "welcomeTitle";
		super.loginEmail = "inputUserEmail";
		super.loginEmailNextButton = "emailNextBtn";
		super.passwordloginScreenTitle = "welcomeTitle";
		super.loginPassword = "inputPassword";
		super.loginButton = "logInButton";

		/** Onboarding Screen **/
		super.onboarding_notNowButton = "notNowButton";
		super.permissionsTitleTextlabel = "permissionsTitle";
		super.alertPopup = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.LinearLayout[@resource-id='android:id/parentPanel']";
		super.cancelButton = "button2";
		super.locationOnboardingScreenTitle = "permissionsTitle";
		super.bluetoothOnboardingScreenTitle = "permissionsTitle";

		/** Home Screen **/
		super.welcomeMessage = "feedContentView";
		super.hamburgerIcon = "openDrawerBtn";
		super.serviceTitle = "service_title";

	}
}
