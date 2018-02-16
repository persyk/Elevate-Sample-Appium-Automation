package com.elevate.pages.reserveARoom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * MenuPage consists of all the methods related to menu page
 */

public class ViewRoomsPage extends BaseAppPage {

	// Element locators for Menu Page.
		private String roomTitle;
				
	public ViewRoomsPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.roomTitle = locatorPage.roomNameTitle;
	}
	
	public RoomDetailsPage clickOnRoom(String roomNameValue) throws InterruptedException {
		Thread.sleep(2000);
		String roomName = "//android.widget.TextView[@text='"+roomNameValue+"']";
		int start_x = findElementById(roomTitle).getLocation().getX();
		int start_y = findElementById(roomTitle).getLocation().getY();
		for(int i=0;i<10;i++) {
			if(isElementPresent(By.xpath(roomName))) {
			findElementByXpath(roomName).click();
			break;
			}
			swipe(start_x, start_y, 0, -80);
			}
		return new RoomDetailsPage(getDriver());
	}
		
}
