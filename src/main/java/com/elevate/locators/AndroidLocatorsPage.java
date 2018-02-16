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
		super.servicesOnHomeScreen = "//android.widget.TextView[contains(@resource-id,'serviceTitle')]";
		super.serviceTitle_1 = servicesOnHomeScreen+"[contains(@text,'";
		super.serviceTitle_2 = "')]";
		
		/** Menu Screen **/
		super.settingsOption = "settingsBtn";
		
		/** Settings Screen **/
		super.settingsTitle = "//android.widget.TextView[contains(@text,'settings')]";
		super.logoutButton = "optionLogout";
		
		/** Cafe Screen **/
        super.cafePageTitle = "//android.widget.TextView[contains(@text,'Food & Drink')]";
        super.cafesOnCafeScreen = "vendorName";
        super.orderConfirmedMessage = "titleView";
        
        /** Cafe Menu Screen **/
        super.menuSubCategory = "subcategoryName";
        
        /** Cafe Details Screen **/
        super.detailsTitle =  "//android.widget.TextView[@text='Details']";
        super.addToCartButton = "updateCartButton";
        
        /** Cafe Checkout Screen **/
        super.cartTitle = "//android.widget.TextView[@text='Cart']";
        super.placeOrderButton = "submitOrderBtn";
        super.cardText = "cardTextView";
        
        /** Reserve Room Screen **/
    	super.reserveRoomPageTitle = "//android.widget.TextView[@text='Book a Room']";
    	super.viewRoomsButton = "viewRoomBtn";
    	super.dateButton = "dateBtn";
    	super.attendeesCount = "attendeesCount";
    	super.calendarIcon = "calendarIconBtn";
    	
    	/** View Rooms Screen **/
    	super.roomNameTitle = "roomName";
    	
    	/** RoomDetails Screen **/
    	super.roomDetailsPageTitle = "spaceName";
    	super.timeSlot = "timePeriod";
    	super.reserveNowButton = "reserveButton";
    	
    	/** RoomBookingConfirmation Screen **/
    	super.roomBookingConfirmationTitle = "//android.widget.TextView[@text='Your Booking Is Confirmed']";
    	super.reserveConfirmationDoneButton = "reserveDoneButton";
    	
    	/** Upcoming Bookings Screen **/
    	super.upcomingBookingsScreenTitle = "//android.widget.TextView[@text='Upcoming Bookings']";
    	super.bookingsDateTitle = "bookingDate";
    	super.bookingsCancelButton = "swipeCancel";

	}
}
