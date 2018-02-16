package com.elevate.pages.cafe;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * CafePage consists of all the methods related to cafe page
 */

public class CafePage extends BaseAppPage {

	// Element locators for Menu Page.
	private String cafePageTitle;
	private String cafesOnCafeScreen;
		
	public CafePage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();

		this.cafePageTitle = locatorPage.cafePageTitle;
		this.cafesOnCafeScreen = locatorPage.cafesOnCafeScreen;
	}

	public Boolean isCafePageTitleDisplayed() {
		return isElementPresent(By.xpath(cafePageTitle));
	}

	public CafeMenuPage navigateToCafe(String cafeTitle, String cafeTitle2) {
		String cafe = "//android.widget.TextView[@text='"+cafeTitle+"' or @text = '"+cafeTitle2+"']";
		if (isElementPresent(By.xpath(cafe))) {
			findElementByXpath(cafe).click();
			}
		else {
			getLogger().info("Scrolling screen as '" + cafe + "' is not visible on the screen");
			int i = 1;
			while (i < 10 && !isElementPresent(By.xpath(cafe))) {
				Point coordinates = getCoordinatesOfLastElementOnScreen(By.id(cafesOnCafeScreen));
				int start_x = coordinates.getX();
				int start_y = coordinates.getY();
				swipe(start_x, start_y, 0, -100);
				i++;
			}

			int y = 1;
			while (y < 3 && isElementPresent(By.xpath(cafe))) {
				findElementByXpath(cafe).click();
				y++;
			}
		}
		return new CafeMenuPage(getDriver());
		
	}
		
	
	
	
}
