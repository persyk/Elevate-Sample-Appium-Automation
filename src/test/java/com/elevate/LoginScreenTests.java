package com.elevate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.elevate.driver.webdriver.BaseDriver;
import com.elevate.pages.HomePage;
import com.elevate.pages.LoginPage;
import com.elevate.pages.OnBoardingPage;

public class LoginScreenTests extends BaseDriver {

	/**
	 * @author QASource 
	 * Description : Verify user is able to Login into Elevate
	 *         App Script Name : TC01_VerifyUserIsAbleToLogin Method Name :
	 *         verifyUserIsAbleToLogin() Steps to reproduce: 
	 *         1. Launch App and enter email address<br>
	 *         2. Tap 'Next' button <br>
	 *         3. Enter password<br>
	 *         4. Tap 'Log in'
	 *         5. Verify Homesceen and tap on Hamburger 
	 *         6 Tap on Settings and Logout.
	 *  * 
	 **/

	@Test
	public void verifyUserIsAbleToLoginLogout() throws InterruptedException {

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

		// Logout from application.
		homePage.logOut();
		Assert.assertEquals(LoginPage.EMAIL_TITLE_TEXT,loginPage.getEmailTitleDisplayedonLoginScreen());
		getLogger().info("Successfully logged out from application.");
	}

	@Test
	public void verifyValidationForIncorrectPassword() {

		LoginPage loginPage = new LoginPage(getDriver());

		// Provide login credentials.
		loginPage.enterLoginCredentials(getUserName(), "Welcomestu1234");
		loginPage.clickOnLoginButtonOnPasswordScreen();

		//Verify Alert Popup message
		Assert.assertTrue(loginPage.isAlertTitleDisplayed());

		// Verify Alert Popup message for incorrect password.
		Assert.assertTrue(loginPage.isCorrectAlertMessageDisplayed(loginPage.expectedValidationMessageForIncorrectPassword()));
		getLogger().info("Correct alert message displayed successfully.");
	} 


	@Test
	public void verifyValidationForInvalidUser() {

		LoginPage loginPage = new LoginPage(getDriver());

		// Enter login credentials
		loginPage.enterLoginCredentials("hjhj@gg.com", getUserPassword());
		loginPage.clickOnLoginButtonOnPasswordScreen();

		// Verify Alert Popup is displayed.
		Assert.assertTrue(loginPage.isAlertTitleDisplayed());

		// Verify Alert Popup message for invalid user
		Assert.assertTrue(loginPage.isCorrectAlertMessageDisplayed(loginPage.expectedValidationMessageForIncorrectPassword()));       
		getLogger().info("Correct alert message displayed successfully.");
	}    

	@Test
	public void verifyValidationForIncorrectFormatPassword() {

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterLoginCredentials(getUserName(), "test");
		loginPage.clickOnLoginButtonOnPasswordScreen();
		
		// Verify Hint shown for incorrect password format
		Assert.assertEquals(LoginPage.HINT_MESSAGE_FOR_INCORRECT_PWD_FORMAT, loginPage.getPasswordHintMessage());
		getLogger().info("Hint message displayed successfully for incorrect format password");
	}

}