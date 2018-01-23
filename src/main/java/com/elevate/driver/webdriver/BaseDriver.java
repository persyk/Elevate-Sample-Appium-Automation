package com.elevate.driver.webdriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.elevate.driver.utils.Loader;

public class BaseDriver extends Loader {

	public WebDriver driver;
	private String siteURL;
	private String newWebProjectUrl;
	private String newMobileProjectUrl;
	private String webdriver;
	private String userName;
	private String userPassword;
	private String newPassword;
	private String userIncorrectPassword;
	private String userIncorrectEmail;

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@BeforeClass
	public void setBaseDriver() {
		setDriver(createDriver());
	}
	
	/**
	 * Method to load properties from properties file by initializing the getters and setters
	 */
	protected void applyProperties(Properties testProperties) {
		setSiteURL(testProperties.getProperty("app.server.hostname"));
		this.webdriver= "iphone";
		setUserName("elevatetest2k17@gmail.com");
		setUserPassword("7654321And");
		//this.webdriver = testProperties.getProperty("webdriver");
		//setUserName(testProperties.getProperty("UserName"));
		//setUserPassword(testProperties.getProperty("Password"));
		setNewPassword(testProperties.getProperty("NewPassword"));
		setUserIncorrectEmail(testProperties.getProperty("userIncorrectEmail"));
	}


	
	public File takeScreenshot(String methodName, WebDriver driver) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = System.getProperty("user.dir") + "/target/surefire-reports/";

			File destFile = new File((String) reportDirectory + "/screenshots/" + methodName + "_"
					+ formater.format(calendar.getTime()) + ".jpeg");
			FileUtils.copyFile(scrFile, destFile);
			return destFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Base Url setters
	 * @param siteURL
	 */
	protected void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

	protected void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	protected void setUserIncorrectEmail(String userIncorrectEmail){
		this.userIncorrectEmail = userIncorrectEmail;
		
	}
	
	public String getUserIncorrectEmail(){
		return userIncorrectEmail;
		
	}
	
	protected void setUserIncorrectPassword(String userIncorrectPassword){
		this.userIncorrectPassword = userIncorrectPassword;
		
	}
	
	public String getUserIncorrectPassword(){
		return userIncorrectPassword;
		
	}

	protected void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword() {
		return userPassword;
	}

	protected void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	protected WebDriver createDriver() {
		return createDriver("unnamed test");
	}

	protected WebDriver createDriver(String testName) {
		return new WebDriverLoader(getProperties()).loadWebDriver(this.webdriver);
	}

	protected WebDriver createDriver(String testName, String webdriver) {
		return new WebDriverLoader(getProperties()).loadWebDriver(webdriver);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	protected void logCookies(WebDriver driver) {
		Set<Cookie> cookies = driver.manage().getCookies();
		getLogger().info("driver has " + cookies.size() + " cookies");
		for (Cookie cookie : cookies) {
			getLogger().info("cookie: " + cookie);
		}
	}

	@AfterMethod
	public void stopDriver(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE)
			takeScreenshot(this.getClass().getName(), getDriver());
		getDriver().quit();
	}

	public String generateRandomString() {
		int RANDOM_STRING_LENGTH = 5;
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
}
