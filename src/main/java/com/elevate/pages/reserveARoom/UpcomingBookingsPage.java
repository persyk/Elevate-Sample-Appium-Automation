package com.elevate.pages.reserveARoom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * MenuPage consists of all the methods related to menu page
 */

public class UpcomingBookingsPage extends BaseAppPage {

	// Element locators for Menu Page.
		private String upcomingBookingsTitle;
		private String bookingsDateTitle;
		private String bookingsCancelButton;
						
	public UpcomingBookingsPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.upcomingBookingsTitle = locatorPage.upcomingBookingsScreenTitle;
		this.bookingsDateTitle = locatorPage.bookingsDateTitle;
		this.bookingsCancelButton = locatorPage.bookingsCancelButton;		
	}
	
	public Boolean isUpcomingBookingsTitleDisplayed() {
		return isElementPresent(By.xpath(upcomingBookingsTitle));
	}
	
	public void clickOnBookingsCancelButton() { 
	findElementById(bookingsCancelButton).click();
	}
	
	public void cancelAllUpcomingBookings() {
		String dateTitle = "//android.widget.LinearLayout/android.widget.FrameLayout[contains(@resource-id,'swipeLayout')]";
		
		int start_x = findElementById(bookingsDateTitle).getLocation().getX();
		int start_y = findElementById(bookingsDateTitle).getLocation().getY();
		System.out.println(start_x);
		System.out.println(start_y);
		
		swipe(start_x, start_y, 500, 0);
	}
		
}
