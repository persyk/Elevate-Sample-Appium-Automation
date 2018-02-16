package com.elevate.pages.reserveARoom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * MenuPage consists of all the methods related to menu page
 */

public class RoomDetailsPage extends BaseAppPage {

	// Element locators for Menu Page.
		private String roomDetailsPageTitle;
		private String timeSlot;
		private String reserveNowButton;
						
	public RoomDetailsPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.roomDetailsPageTitle = locatorPage.roomDetailsPageTitle;
		this.timeSlot = locatorPage.timeSlot;
		this.reserveNowButton = locatorPage.reserveNowButton;
		
	}
	
	public Boolean isSelectedRoomTitleDisplayed(String roomTitle) {
		return findElementById(roomDetailsPageTitle).getText().equalsIgnoreCase(roomTitle);
	}
	
	public void selectTimePeriodForRoom(String timeValue, String timeValue2) {
		String timePeriod = "//android.widget.TextView[@text='"+timeValue+"' or @text = '"+timeValue2+"']";
		if(isElementPresent(By.xpath(timePeriod))) {
			findElementByXpath(timePeriod).click();		
		}
	}
	
	public RoomBookingConfirmationPage clickOnReserveNowButton() {
		if(findElementById(reserveNowButton).isEnabled()) {
		findElementById(reserveNowButton).click();		
		}
		return new RoomBookingConfirmationPage(getDriver());
	}
	

}
