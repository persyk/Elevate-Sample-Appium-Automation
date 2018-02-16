package com.elevate.pages;

//import io.appium.java_client.TouchAction;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.cafe.CafePage;
import com.elevate.pages.reserveARoom.ReserveRoomPage;
import com.elevate.pages.reserveARoom.UpcomingBookingsPage;
import com.gargoylesoftware.htmlunit.javascript.host.Location;

/**
 * @author QASource HomePage consists of all the methods related to home page
 */

public class HomePage extends BaseAppPage {

	// Element locators for Home Page.
	private String welcomeMessage;
	private String hamburgerIcon;
	private String servicesOnHomeScreen;
	private String serviceTitle_1;
	private String serviceTitle_2;
	private String serviceToBeSelected;
	
	//Data
	public final static String WELCOME_TEXT = "WELCOME";
	public final static String CAFE_SERVICE_TITLE = "TEA & SNACKS";
	public final static String RESERVE_A_ROOM_SERVICE_TITLE = "Rooms for booking";
	public final static String EVENTS_SERVICE_TITLE = "EVENTS";
	public final static String COMMUNICATION_SERVICE_TITLE = "COMMUNICATION";
	public final static String CONCIERGE_SERVICE_TITLE = "CONCIERGE";
	

	public HomePage(WebDriver driver) {
		super(driver);

		LocatorsPage locatorPage = getLocatorsPage();
		this.welcomeMessage = locatorPage.welcomeMessage;
		this.hamburgerIcon = locatorPage.hamburgerIcon;
		this.servicesOnHomeScreen = locatorPage.servicesOnHomeScreen;
		this.serviceTitle_1 = locatorPage.serviceTitle_1;
		this.serviceTitle_2 = locatorPage.serviceTitle_2; 
	}

	public Boolean isWelcomeMessageDisplayed() {
		if (getDriverType().equalsIgnoreCase("android")) {
			return findElementById(welcomeMessage).getText().contains(WELCOME_TEXT);
		} else {
			return isElementPresent(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+WELCOME_TEXT+"')]"));
		}
	}

	public Boolean isHamburgerIconDisplayed() {
		return isElementPresent(By.id(hamburgerIcon));
	}

	public MenuPage clickOnHamburgerIcon() {
		findElementById(hamburgerIcon).click();
		return new MenuPage(getDriver());
	}

	public <Any> Any clickOnServiceTitle(String serviceValue) {

		serviceToBeSelected = serviceTitle_1 + serviceValue + serviceTitle_2;
		System.out.println("service value - " + serviceToBeSelected);

		if (isElementPresent(By.xpath(serviceToBeSelected))) {
			findElementByXpath(serviceToBeSelected).click();
		}

		else {
			getLogger().info("Scrolling screen as '" + serviceValue + "' is not visible on the screen");
			int i = 1;
			while (i < 10 && !isElementPresent(By.xpath(serviceToBeSelected))) {
				Point coordinates = getCoordinatesOfLastElementOnScreen(By.xpath(servicesOnHomeScreen));
				int start_x = coordinates.getX();
				int start_y = coordinates.getY();
				swipe(start_x, start_y, 0, -400);
				i++;
			}

			int y = 1;
			while (y < 3 && isElementPresent(By.xpath(serviceToBeSelected))) {
				System.out.println(y);
				findElementByXpath(serviceToBeSelected).click();
				y++;
			}
		}

		switch (serviceValue) {
		case CAFE_SERVICE_TITLE:
			return (Any) new CafePage(getDriver());
		case RESERVE_A_ROOM_SERVICE_TITLE:
			return (Any) new ReserveRoomPage(getDriver());
		case EVENTS_SERVICE_TITLE:
			return (Any) new CafePage(getDriver());
		case COMMUNICATION_SERVICE_TITLE:
			return (Any) new CafePage(getDriver());
		case CONCIERGE_SERVICE_TITLE:
			return (Any) new CafePage(getDriver());
		default:
			throw new IllegalArgumentException();

		}
	}
	
	public UpcomingBookingsPage navigateToUpcomingBookings() {
		clickOnServiceTitle(RESERVE_A_ROOM_SERVICE_TITLE);
		ReserveRoomPage reserveRoomPage = new ReserveRoomPage(getDriver());
		reserveRoomPage.clickOnCalendarIcon();
		return new UpcomingBookingsPage(getDriver());
	}
}
