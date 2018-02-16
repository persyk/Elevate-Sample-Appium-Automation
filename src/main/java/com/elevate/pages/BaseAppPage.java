package com.elevate.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.elevate.desired_capabilities.DesiredCapabilitiesPage;
import com.elevate.driver.pagedriver.BasePage;
import com.elevate.driver.utils.PropertyLoader;
import com.elevate.locators.AndroidLocatorsPage;
import com.elevate.locators.IOSLocatorsPage;
import com.elevate.locators.LocatorsPage;
import com.google.common.collect.Iterables;

/**
 * BaseThisLifeSignedInPage provides a convenient extension point for
 * implementers of pages for a logged in user having a standard footer and
 * header.
 */

public class BaseAppPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 60);
	private String alertTitle;
	private String alertMessage;
	private String headerTitle;
	String iOSLocatorFile = "src/resources/iOSLocatorFile.properties";
	String androidLocatorFile = "src/resources/androidLocatorFile.properties";
	public static String Webdriver = DesiredCapabilitiesPage.executeOnPlatform;

	public BaseAppPage(WebDriver driver) {
		super(driver);

		LocatorsPage locatorPage = getLocatorsPage();
		this.alertTitle = locatorPage.alertTitle;
		this.alertMessage = locatorPage.alertMessage;
		this.headerTitle = locatorPage.headerTitle;
	}

	@Override
	protected String getExpectedTitle() {
		return getDriver().getTitle();
	}

	public Boolean isAlertTitleDisplayed() {
		if (getDriverType().equalsIgnoreCase("android")) {
			waitForElement(By.id(alertTitle), "wait", 10);
			return isElementDisplayed(By.id(alertTitle), 1);
		} else {
			waitForElement(By.name(alertTitle), "wait", 10);
			return isElementDisplayed(By.name(alertTitle), 1);
		}
	}

	public String getAlertMessage() {
		return findElementById(alertMessage).getText();
	}

	public void logIn() {

	}

	public void logOut() {
		HomePage homePage = new HomePage(getDriver());
		MenuPage menupage = homePage.clickOnHamburgerIcon();
		SettingsPage settingsPage = menupage.clickOnSettingsOption();
		settingsPage.clickOnLogoutButton();
	}

	public String getTextOfHeaderTitle() {
		return findElementById(headerTitle).getText();
	}

	public ArrayList<String> getLocatorFileList() {
		ArrayList<String> locatorFileList = new ArrayList<String>();

		if (loadProperties().getProperty("webdriver").equalsIgnoreCase("android")) {
			File file = new File(androidLocatorFile);
			locatorFileList.add(file.getAbsolutePath());
			return locatorFileList;
		} else {
			File file = new File(iOSLocatorFile);
			locatorFileList.add(file.getAbsolutePath());
			return locatorFileList;
		}
	}

	public Properties loadLocatorFile() {
		ArrayList<String> locatorFile = getLocatorFileList();
		return PropertyLoader.loadDefaultAndCustomProps(locatorFile);
	}

	public String getDriverType() {
		return Webdriver;
	}

	public void enterLoginCredentials(String email, String password) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.clickOkOnApplePayPopUpIfVisible();
		loginPage.enterEmailAddress(email);
		loginPage.clickOnEmailScreenNextButton();
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButtonOnPasswordScreen();		
	}

	public <Any> Any getLocatorsPage() {
		if (Webdriver.equalsIgnoreCase("Android")) {
			return (Any) new AndroidLocatorsPage();
		} else {
			return (Any) new IOSLocatorsPage();
		}
	}

	public Point getCoordinatesOfLastElementOnScreen(By locator){
		List<WebElement> elementsVisibleOnScreen = driver.findElements(locator);
		WebElement lastElement = Iterables.getLast(elementsVisibleOnScreen);
		System.out.println("last service present on screen -"+ lastElement.getText());				
		return lastElement.getLocation();
	}

}
