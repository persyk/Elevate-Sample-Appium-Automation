package com.elevate.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.elevate.locators.LocatorsPage;
import com.gargoylesoftware.htmlunit.WebConsole.Logger;

/**
 * @author QASource LoginPage consists of all the methods related to login page
 */

public class LoginPage extends BaseAppPage {

	private String emailloginScreenTitle;
	private String loginEmail;
	private String loginEmailNextButton;
	private String passwordloginScreenTitle;
	private String loginPassword;
	private String loginButton;
	private String forgotPassword;
	private String recoveryScreenTitle;
	private String resetEmailInput;
	private String sendButtonRecoveryEmail;
	private String cancelButtonRecoveryScreen;
	private String incorrectFormatPasswordMessage;
	private String applePayPopUpOkButton;

	// Data.
	public final static String EMAIL_TITLE_TEXT = "Email";
	public final static String PASSWORD_TITLE_TEXT = "Password";
	public final static String HINT_MESSAGE_FOR_INCORRECT_PWD_FORMAT = "Hint: your password must be at least 8 characters with 1 uppercase letter and 1 number.";
	public final static String POPUP_MESSAGE_FOR_INVALID_USER = "User does not exist.";
	public final static String POPUP_MESSAGE_FOR_INCORRECT_CREDENTIALS_ANDROID = "The credentials provided were invalid.";
	public final static String POPUP_MESSAGE_FOR_INCORRECT_CREDENTIALS_IOS = "Incorrect username or password";
	public final static String test = "";

	public LoginPage(WebDriver driver) {
		super(driver);

		LocatorsPage locatorPage = getLocatorsPage();

		this.applePayPopUpOkButton = locatorPage.applePayPopUpOkButton;
		this.emailloginScreenTitle = locatorPage.emailloginScreenTitle;
		this.loginEmail = locatorPage.loginEmail;
		this.loginEmailNextButton = locatorPage.loginEmailNextButton;
		this.passwordloginScreenTitle = locatorPage.passwordloginScreenTitle;
		this.loginPassword = locatorPage.loginPassword;
		this.loginButton = locatorPage.loginButton;
	    this.incorrectFormatPasswordMessage = locatorPage.incorrectFormatPasswordMessage;

	}

	/**
	 * Method to validate Email Title on Email Screen
	 *
	 * @return success message
	 */

	public String getEmailTitleDisplayedonLoginScreen() {
		getLogger().info("Email Screen Title - " + findElementById(emailloginScreenTitle).getText());
		return findElementById(emailloginScreenTitle).getText();
	}

	public boolean isEmailFieldPresent() {
		return findElementById(loginEmail).isDisplayed();
	}

	public void enterEmailAddress(String email) {
		findElementById(loginEmail).click();
        findElementById(loginEmail).clear();
		findElementById(loginEmail).sendKeys(email);
	}

	public Boolean isEmailScreenNextButtonEnabled() {
		return findElementById(loginEmailNextButton).isEnabled();
	}

	public void clickOnEmailScreenNextButton() {
		findElementById(loginEmailNextButton).click();
		waitForElement(By.id(passwordloginScreenTitle), "waiting for password screen", 10);
	}

	public void enterPassword(String password) {
		findElementById(loginPassword).click();
		findElementById(loginPassword).clear();		
		findElementById(loginPassword).sendKeys(password);
	}

	/**
	 * Method to validate Password Title on Password Screen * @return
	 */

	public String getPasswordTitleDisplayedonPasswordScreen() {
		getLogger().info("Password Screen Title-" + findElementById(passwordloginScreenTitle).getText());
		return findElementById(passwordloginScreenTitle).getText();
	}

	public boolean isPasswordFieldPresent() {
		return findElementById(loginPassword).isDisplayed();
	}

	public Boolean isLoginButtonEnabled() {
		return findElementById(loginButton).isEnabled();
	}

	public OnBoardingPage clickOnLoginButtonOnPasswordScreen() {
		findElementById(loginButton).click();
		return new OnBoardingPage(getDriver());
	}

	public String getPasswordHintMessage() {
		waitForElement(By.id(incorrectFormatPasswordMessage), "wait", 10);
		System.out.println(findElementById(incorrectFormatPasswordMessage).getText());
		return findElementById(incorrectFormatPasswordMessage).getText();
	}

	/**
	 * Method to verify that user is able to login into the application
	 *
	 * @return login user name
	 */

	public WebElement isEmailOnRecoveryScreenDisplayed() {
		return findElementById(resetEmailInput);
	}

	public Boolean isRecoveryScreenSendButtonEnabled() {
		return findElementById(sendButtonRecoveryEmail).isEnabled();
	}

	public String isRecoveryTitleDisplayedonRecoveryScreen() {
		System.out.println(findElementById(recoveryScreenTitle).getText());
		return findElementById(recoveryScreenTitle).getText();
	}
	
	public String expectedValidationMessageForIncorrectPassword(){		
		if(getDriverType().equalsIgnoreCase("android"))
			return POPUP_MESSAGE_FOR_INCORRECT_CREDENTIALS_ANDROID;
		else
			return POPUP_MESSAGE_FOR_INCORRECT_CREDENTIALS_IOS;
		
	}

	public Boolean isCorrectAlertMessageDisplayed(String alertMessage) {
		getLogger().info("Validation message shown - "+ getAlertMessage());
		return getAlertMessage().equalsIgnoreCase(alertMessage);
	}

	// iOS Specific
	public void clickOkOnApplePayPopUpIfVisible() {
		if (getDriverType().equalsIgnoreCase("ios")) {
			 //waitForElement(By.id(applePayPopUpOkButton), "wait", 10);
			if (isElementPresent(By.id(applePayPopUpOkButton))) {
				driver.findElement(By.id(applePayPopUpOkButton)).click();
				System.out.println("OK button tapped");
			}
		}
	}
}
