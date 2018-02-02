package com.elevate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.elevate.driver.webdriver.BaseDriver;
import com.elevate.pages.cafe.CafeCheckoutPage;
import com.elevate.pages.cafe.CafeDetailsPage;
import com.elevate.pages.cafe.CafeMenuPage;
import com.elevate.pages.cafe.CafeOrderConfirmationPage;
import com.elevate.pages.cafe.CafePage;
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
		String cafeName = "QA_AUTOMATION";
		String cafeName1 = "QA_Automation";
		String menuCategory = "Veg";
		String menuCategory1 = "VEG";
		String menuSubcategory = "VEG SNACKS";
		String menuItem = "HONEY CHILLI POTATO";
	
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
		CafePage cafePage = homePage.clickOnServiceTitle(serviceTitle);
		Assert.assertTrue(cafePage.isCafeTitleDisplayed());
		
		// Select the Cafe and navigate to item.
		CafeMenuPage cafeMenuPage = cafePage.navigateToCafe(cafeName, cafeName1);
		Assert.assertTrue(cafeMenuPage.isMenuCategoriesBarDisplayed());	
		String price = cafeMenuPage.navigateToMenuItem(menuCategory,menuCategory1, menuSubcategory, menuItem);
		
		// Select the item and place and order.
		CafeDetailsPage cafeDetailsPage = cafeMenuPage.clickOnMenuSubItem(menuItem);
		Assert.assertTrue(cafeDetailsPage.isDetailsTitleDisplayed());
		Assert.assertTrue(cafeDetailsPage.isSelectedItemAndAmountDisplayed(menuItem, price));
		
		CafeCheckoutPage cafeCheckoutPage = cafeDetailsPage.clickOnAddToCart();
		Assert.assertTrue(cafeCheckoutPage.isCartTitleDisplayed());	
		CafeOrderConfirmationPage cafeOrderConfirmationPage = cafeCheckoutPage.clickOnPlaceOrderButton();		
		
		Assert.assertTrue(cafeOrderConfirmationPage.isOrderConfirmationMessageDisplayed());		
	}
	
	
}
