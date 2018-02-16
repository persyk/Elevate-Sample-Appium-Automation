package com.elevate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.elevate.driver.webdriver.BaseDriver;
import com.elevate.pages.cafe.CafeCheckoutPage;
import com.elevate.pages.cafe.CafeDetailsPage;
import com.elevate.pages.cafe.CafeMenuPage;
import com.elevate.pages.cafe.CafeOrderConfirmationPage;
import com.elevate.pages.cafe.CafePage;
import com.elevate.pages.reserveARoom.ReserveRoomPage;
import com.elevate.pages.reserveARoom.RoomBookingConfirmationPage;
import com.elevate.pages.reserveARoom.RoomDetailsPage;
import com.elevate.pages.reserveARoom.UpcomingBookingsPage;
import com.elevate.pages.reserveARoom.ViewRoomsPage;
import com.elevate.pages.HomePage;
import com.elevate.pages.LoginPage;
import com.elevate.pages.OnBoardingPage;

public class HomeScreenTests extends BaseDriver {


	/** @author QASource
	 * Description : Verify user is able to place an order
	 * Script Name : TC02_VerifyUserIsAbleToPlaceAnOrder
	 * Method Name : verifyUserIsAbleToOrderSnack()
	 *                                                     				Steps to reproduce:
	 * 																	1. Login to App<br>
	 *																	2. Tap on Cafe service title and select available vendors. <br>
	 *  																3. Click on menu item<br>
	 *  																4. Place an order.
	 * @throws InterruptedException 
	 * 
	 **/

	@Test
	public void verifyUserIsAbleToOrderSnacks() throws InterruptedException {

        String serviceTitle = "TEA & SNACKS";
        String cafeName = "QASource_Vendor";
        String cafeName1 = "QASource_Vendor";
        String menuSubcategory = "Veg Main Course";
        String menuItem = "Veg Manchurian";

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.clickOkOnApplePayPopUpIfVisible();

		// Verify email title displayed on Email Screen
		Assert.assertEquals(LoginPage.EMAIL_TITLE_TEXT, loginPage.getEmailTitleDisplayedonLoginScreen());
		loginPage.enterLoginCredentials(getUserName(), getUserPassword());
		OnBoardingPage onBoardingPage = loginPage.clickOnLoginButtonOnPasswordScreen(); 
		getLogger().info("Successfully tapped on login button.");

		// Skip Onboarding Screens
		HomePage homePage = onBoardingPage.skipAllOnboardingScreens();

		// Verify Welcome Message on Home Screen
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		getLogger().info("Successfully logged in application.");

		// Select the service.
		CafePage cafePage = homePage.clickOnServiceTitle(serviceTitle);
		Assert.assertTrue(cafePage.isCafePageTitleDisplayed(), "Navigation to cafe screen not working on tapping cafe services on Home screen");
		System.out.println("navigated to cafe screen");

		// Select the Cafe and navigate to item.
		CafeMenuPage cafeMenuPage = cafePage.navigateToCafe(cafeName, cafeName1);
		Assert.assertTrue(cafeMenuPage.isMenuPageTitleDisplayed(cafeName));	
		String price = cafeMenuPage.navigateToMenuItem(menuSubcategory, menuItem);

		// Select the item and place and order.
		CafeDetailsPage cafeDetailsPage = cafeMenuPage.clickOnMenuSubItem(menuItem);
		Assert.assertTrue(cafeDetailsPage.isDetailsTitleDisplayed());
		Assert.assertTrue(cafeDetailsPage.isSelectedItemAndAmountDisplayed(menuItem, price));

		CafeCheckoutPage cafeCheckoutPage = cafeDetailsPage.clickOnAddToCart();
		Assert.assertTrue(cafeCheckoutPage.isCartTitleDisplayed());	
		CafeOrderConfirmationPage cafeOrderConfirmationPage = cafeCheckoutPage.clickOnPlaceOrderButton();		

		// Verify order is placed successfully.
		Assert.assertTrue(cafeOrderConfirmationPage.isOrderConfirmationMessageDisplayed());		
	}

	@Test
	public void verifyUserIsAbleToBookARoom() throws InterruptedException {

		String serviceTitle = "Rooms for booking";
		String roomName = "QA_conferenceRoom";
		String timeSlotValue = "4PM-5PM";
		String timeSlotValue1 = "4pm-5pm";
		String timeSlotValueOther = "5PM-6PM";
		String timeSlotValueOther2 = "5pm-6pm";

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.clickOkOnApplePayPopUpIfVisible();

		// Verify email title displayed on Email Screen
		Assert.assertEquals(LoginPage.EMAIL_TITLE_TEXT, loginPage.getEmailTitleDisplayedonLoginScreen());
		loginPage.enterEmailAddress(getUserName());

		// Verify Email screen next button is enabled
		Assert.assertTrue(loginPage.isEmailScreenNextButtonEnabled());
		loginPage.clickOnEmailScreenNextButton();

		// Verify Password title displayed on Password Screen
		Assert.assertEquals(LoginPage.PASSWORD_TITLE_TEXT, loginPage.getPasswordTitleDisplayedonPasswordScreen());
		loginPage.enterPassword(getUserPassword());
		OnBoardingPage onBoardingPage = loginPage.clickOnLoginButtonOnPasswordScreen();
		getLogger().info("Successfully tapped on login button.");

		// Skip Onboarding Screens
		HomePage homePage = onBoardingPage.skipAllOnboardingScreens();

		// Verify Welcome Message on Home Screen
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		getLogger().info("Successfully logged in application.");

		// Select the service.
		ReserveRoomPage reserveRoomPage = homePage.clickOnServiceTitle(serviceTitle);
		Assert.assertTrue(reserveRoomPage.isReserveRoomTitleDisplayed());
		ViewRoomsPage viewRoomsPage = reserveRoomPage.clickOnViewRooms();
		RoomDetailsPage roomDetailsPage = viewRoomsPage.clickOnRoom(roomName);
		Assert.assertTrue(roomDetailsPage.isSelectedRoomTitleDisplayed(roomName));
		roomDetailsPage.selectTimePeriodForRoom(timeSlotValue, timeSlotValue1);
		roomDetailsPage.selectTimePeriodForRoom(timeSlotValueOther, timeSlotValueOther2);	   
		RoomBookingConfirmationPage roomBookingConfirmationPage = roomDetailsPage.clickOnReserveNowButton();
		Assert.assertTrue(roomBookingConfirmationPage.isRoomBookingConfirmationTitleDisplayed());
		roomBookingConfirmationPage.clickOnReserveDoneButton();
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		// UpcomingBookingsPage upcomingBookingsPage = homePage.navigateToUpcomingBookings();
		// Assert.assertTrue(upcomingBookingsPage.isUpcomingBookingsTitleDisplayed());
		// upcomingBookingsPage.cancelAllUpcomingBookings();
		// upcomingBookingsPage.clickOnBookingsCancelButton();    
	}


}
