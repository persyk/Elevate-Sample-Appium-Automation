package com.elevate.pages.reserveARoom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * MenuPage consists of all the methods related to menu page
 */

public class ReserveRoomPage extends BaseAppPage {

	// Element locators for Menu Page.
		private String reserveRoomTitle;
		private String viewRoomsButton;
		private String dateButton;
		private String attendeesCount;
		private String calendorIcon;
				
	public ReserveRoomPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.reserveRoomTitle = locatorPage.reserveRoomPageTitle;
		this.viewRoomsButton = locatorPage.viewRoomsButton;
		this.dateButton = locatorPage.dateButton;
		this.attendeesCount = locatorPage.attendeesCount;
		this.calendorIcon = locatorPage.calendarIcon;
	}
	
	public Boolean isReserveRoomTitleDisplayed() {
		return isElementPresent(By.xpath(reserveRoomTitle));
	}
	
	public ViewRoomsPage clickOnViewRooms() {
		findElementById(viewRoomsButton).click();
		return new ViewRoomsPage(getDriver());
	}
	
	public String getSelectedDate() {
		return findElementById(dateButton).getText();   
	}
	
	public String getSelectedAttendeesCount() {
		return findElementById(attendeesCount).getText();
	}
	
	public void selectDateValue() {
		findElementById(dateButton).click();
		
	}
		
	public void clickOnCalendarIcon() {
		findElementById(calendorIcon).click();
	}
	
}
