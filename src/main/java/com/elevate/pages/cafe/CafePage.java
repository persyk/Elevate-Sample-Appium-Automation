package com.elevate.pages.cafe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * CafePage consists of all the methods related to cafe page
 */

public class CafePage extends BaseAppPage {

	// Element locators for Menu Page.
	private String cafeTitle;
	private String navigationBar;


	public CafePage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();

		this.cafeTitle = locatorPage.cafeTitle;
		this.navigationBar = locatorPage.navigationBar;

	}

	public Boolean isCafeTitleDisplayed() {
		return isElementPresent(By.xpath(cafeTitle));
	}

	public CafeMenuPage navigateToCafe(String cafeTitle, String cafeTitle2) {
		/*Integer count = findElementsById("vendorTypeName").size();
		System.out.println(count);
		String cafe = "//android.widget.TextView[@text='"+cafeTitle+"' or @text = '"+cafeTitle2+"']";
		int start_x = findElementByXpath("//android.widget.TextView[@text = 'CAFE1' or @text = 'Cafe1']").getLocation().getX();
		int start_y = findElementByXpath("//android.widget.TextView[@text = 'CAFE1' or @text = 'Cafe1']").getLocation().getY();
		swipe(start_x, start_y, 0, -200);
		findElementByXpath(cafe).click();*/
		int start_x = findElementById("vendorTypeName").getLocation().getX();
		int start_y = findElementById("vendorTypeName").getLocation().getY();
		
		String cafe = "//android.widget.TextView[@text='"+cafeTitle+"' or @text = '"+cafeTitle2+"']";
		for(int i=0;i<10;i++) {
			if (isElementPresent(By.xpath(cafe))) {
				findElementByXpath(cafe).click();
				break;
			}
			swipe(start_x, start_y, 0, -80);
		}			
		return new CafeMenuPage(getDriver());
	}	
}
