package com.elevate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.elevate.driver.webdriver.BaseDriver;
import com.elevate.pages.HomePage;
import com.elevate.pages.LoginPage;
import com.elevate.pages.OnBoardingPage;

public class TC01_VerifyUserIsAbleToLogin extends BaseDriver {
	

	/** @author QASource
	 * Description : Verify user is able to Login into Elevate App
	 * Script Name : TC01_VerifyUserIsAbleToLogin
	 * Method Name : verifyUserIsAbleToLogin()
	 *                                                     				Steps to reproduce:
	 * 																	1. Launch App and enter email address<br>
	 *																	2. Tap 'Next' button <br>
	 *  																3. Enter password<br>
	 *  																4. Tap 'Log in'
	 * @throws InterruptedException 
	 * 
	 **/
				
	@Test
	public void verifyUserIsAbleToLoginLogout() throws InterruptedException {
	
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.clickOkOnApplePayPopUpIfVisible();		
		// Verify email title displayed on Email Screen
		Assert.assertEquals(LoginPage.EMAIL_TITLE_TEXT, loginPage.getEmailTitleDisplayedonLoginScreen());
		loginPage.enterEmailAddress(getUserName());
	    
		Assert.assertTrue(loginPage.isEmailScreenNextButtonEnabled());
		loginPage.clickOnEmailScreenNextButton();		
		
		//Verify Password Screen
		Assert.assertEquals(LoginPage.PASSWORD_TITLE_TEXT, loginPage.getPasswordTitleDisplayedonPasswordScreen());
		loginPage.enterPassword(getUserPassword());
		OnBoardingPage onBoardingPage = loginPage.clickOnLoginButtonOnPasswordScreen();
		getLogger().info("Successfully tapped on login button.");
		
		//Skip Onboarding Screens
		HomePage homePage = onBoardingPage.skipAllOnboardingScreens();
		
		//Verify Welcome Screen
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		getLogger().info("Successfully logged in application.");		
		
		// Logout from application.
//		homePage.logOut();
//		Assert.assertEquals(LoginPage.EMAIL_TITLE_TEXT, loginPage.getEmailTitleDisplayedonLoginScreen());
	}
	
    //@Test
	public void verifyValidationForIncorrectPassword() {
		
    	LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterLoginCredentials(getUserName(), "Welcomestu1234");
		loginPage.clickOnLoginButtonOnPasswordScreen();
    	
        //Verify Alert Popup message
        Assert.assertTrue(loginPage.isAlertTitleDisplayed());
        Assert.assertTrue(loginPage.isCorrectAlertMessageDisplayed(loginPage.POPUP_MESSAGE_FOR_INCORRECT_CREDENTIALS));
        getLogger().info("Correct alert message displayed successfully.");
	}
    
   //@Test
	public void verifyValidationForInvalidUser() {
    	
    	LoginPage loginPage = new LoginPage(getDriver());
    	loginPage.enterLoginCredentials("hjhj@gg.com", getUserPassword());
    	loginPage.clickOnLoginButtonOnPasswordScreen();
    	
    	//Verify Alert Popup message
		Assert.assertTrue(loginPage.isAlertTitleDisplayed());
        Assert.assertTrue(loginPage.isCorrectAlertMessageDisplayed(loginPage.POPUP_MESSAGE_FOR_INCORRECT_CREDENTIALS));
        getLogger().info("Correct alert message displayed successfully.");
	}     
	
}
