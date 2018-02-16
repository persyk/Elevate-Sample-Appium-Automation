package com.elevate.pages.cafe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.elevate.locators.LocatorsPage;
import com.elevate.pages.BaseAppPage;

/**
 * @author QASource 
 * CafeMenuPage consists of all the methods related to cafe menu page
 */

public class CafeMenuPage extends BaseAppPage {

	// Element locators for Menu Page.
	private String menuPageTitle;
	private String menuSubCategoryTitle;
						
	public CafeMenuPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.menuPageTitle = locatorPage.cafesOnCafeScreen;
		this.menuSubCategoryTitle = locatorPage.menuSubCategory;
	}	
	
	public Boolean isMenuPageTitleDisplayed(String cafeTitle) {		
		return findElementById(menuPageTitle).getText().equalsIgnoreCase(cafeTitle);
	}		
		
	public void clickOnMenuSubCategory(String menuSubCategory) {
		String menu = "//android.widget.TextView[@text='"+menuSubCategory+"']";	
		int start_x = findElementById(menuSubCategoryTitle).getLocation().getX();
		int start_y = findElementById(menuSubCategoryTitle).getLocation().getY();
		for(int i=0;i<10;i++) {
		if(isElementPresent(By.xpath(menu))) {
		findElementByXpath(menu).click();
		break;
		}
		swipe(start_x, start_y, 0, -50);
		}
	}
	
	public CafeDetailsPage clickOnMenuSubItem(String menuItem) {
		String menu = "//android.widget.TextView[@text='"+menuItem+"']";	
		findElementByXpath(menu).click();
		return new CafeDetailsPage(getDriver());
	}
	
	public String getItemPrice(String menuItem) {
		int start_x = findElementById(menuSubCategoryTitle).getLocation().getX();
		int start_y = findElementById(menuSubCategoryTitle).getLocation().getY();
		for(int i=0;i<10;i++) {   
			if(isElementPresent(By.xpath("//android.widget.TextView[@text='"+menuItem+"']/following-sibling::android.widget.TextView[contains(@resource-id,'itemPrice')]"))) {
				break;
			}
			swipe(start_x, start_y, 0, -80);
		}
		return findElementByXpath("//android.widget.TextView[@text='"+menuItem+"']/following-sibling::android.widget.TextView[contains(@resource-id,'itemPrice')]").getText();
	}
	
	public String navigateToMenuItem(String menuSubCategory, String menuItem) {
		clickOnMenuSubCategory(menuSubCategory);
		return getItemPrice(menuItem);
	}
		
	
}
