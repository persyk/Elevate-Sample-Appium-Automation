package com.elevate.pages.reserveARoom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;
import com.elevate.pages.HomePage;

/**
 * @author QASource 
 * MenuPage consists of all the methods related to menu page
 */

public class RoomBookingConfirmationPage extends BaseAppPage {

	// Element locators for Menu Page.
		private String roomBookingConfirmationTitle;
		private String doneButton;
		
				
	public RoomBookingConfirmationPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.roomBookingConfirmationTitle = locatorPage.roomBookingConfirmationTitle;
		this.doneButton = locatorPage.reserveConfirmationDoneButton;
		
	}
	
	public Boolean isRoomBookingConfirmationTitleDisplayed() {
		return isElementPresent(By.xpath(roomBookingConfirmationTitle));
	}
	
	public HomePage clickOnReserveDoneButton() {
		findElementById(doneButton).click();
		return new HomePage(getDriver());
	}
	
	
}
