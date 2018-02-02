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
		super.incorrectFormatPasswordMessage = "passwordIncorrectMessage";

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
		
		/** Menu Screen **/
		super.settingsOption = "settingsBtn";
		
		/** Settings Screen **/
		super.settingsTitle = "//android.widget.TextView[contains(@text,'settings')]";
		super.logoutButton = "optionLogout";
		
		/** Cafe Screen **/
        super.cafeTitle = "//android.widget.TextView[contains(@text,'Food & Drink')]";
        super.orderConfirmedMessage = "titleView";
        
        /** Cafe Menu Screen **/
        super.navigationBar = "navigationBar";
        
        /** Cafe Details Screen **/
        super.detailsTitle =  "//android.widget.TextView[@text='Details']";
        super.addToCartButton = "addToCart";
        
        /** Cafe Checkout Screen **/
        super.cartTitle = "//android.widget.TextView[@text='Cart']";
        super.placeOrderButton = "submitOrderBtn";
        
        
	}
}
