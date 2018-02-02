package com.elevate.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elevate.locators.LocatorsPage;

/**
 * @author QASource 
 * MenuPage consists of all the methods related to menu page
 */

public class CafePage extends BaseAppPage {

	// Element locators for Menu Page.
		private String cafeTitle;
				
	public CafePage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.cafeTitle = locatorPage.cafeTitle;
	}
	
	public Boolean isCafeTitleDisplayed() {
		return isElementPresent(By.xpath(cafeTitle));
	}
	
	public void clickOnCafeTitle(String cafeTitle) throws InterruptedException {
		String cafe = "//android.widget.TextView[@text='"+cafeTitle+"']";
		//while(!findElementByXpath(cafe).isDisplayed()){
			System.out.println(findElementByXpath("//android.widget.TextView[@text='THE GREAT BEER']").getLocation());
			System.out.println(findElementByXpath("//android.widget.TextView[@text='MCD_CAFE']").getLocation());
			System.out.println(findElementByXpath("//android.widget.TextView[@text='Cafe1']").getLocation());
			int start_x = findElementByXpath("//android.widget.TextView[@text='THE GREAT BEER']").getLocation().getX();
			int start_y = findElementByXpath("//android.widget.TextView[@text='THE GREAT BEER']").getLocation().getY();
			swipe(start_x, start_y, 0, -200);
			Thread.sleep(2000);
		//}
		
		/*if (isElementPresent(By.xpath(cafeTitle))) {
			findElementByXpath(service).click();
			return new CafePage(getDriver());				
		}
		else
		{
			int start_x = findElementByXpath("//android.widget.TextView[@text='EVENTS']").getLocation().getX();
			int start_y = findElementByXpath("//android.widget.TextView[@text='EVENTS']").getLocation().getY();
			swipe(start_x, start_y, 0, -1480);
			findElementByXpath(service).click();
		    return new CafePage(getDriver());
		}*/
	}
	
}
