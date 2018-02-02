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
		private String navigationBar;
						
	public CafeMenuPage(WebDriver driver) {
		super(driver);
		LocatorsPage locatorPage = getLocatorsPage();
		
		this.navigationBar = locatorPage.navigationBar;
		
	}	
	
	public Boolean isMenuCategoriesBarDisplayed() {
		return isElementPresent(By.id(navigationBar));
	}
	
	public void clickOnMenuCategory(String menuCategory, String menuCategory1) {		
		String menu = "//android.widget.TextView[@text='"+menuCategory+"' or @text = '"+menuCategory1+"']";	
		if (findElementByXpath("//android.widget.TextView[@text='"+menuCategory+"' or @text = '"+menuCategory1+"']").isSelected() == false) {
			findElementByXpath(menu).click();
		}			
	}
	
	public void clickOnMenuSubCategory(String menuSubCategory) {
		String menu = "//android.widget.TextView[@text='"+menuSubCategory+"']";		
		findElementByXpath(menu).click();		
	}
	
	public CafeDetailsPage clickOnMenuSubItem(String menuItem) {
		String menu = "//android.widget.TextView[@text='"+menuItem+"']";		
		findElementByXpath(menu).click();
		return new CafeDetailsPage(getDriver());
	}
	
	public String getItemPrice(String menuItem) {
	 return findElementByXpath("//android.widget.TextView[@text='"+menuItem+"']/following-sibling::android.widget.TextView[@resource-id='com.convene.elevate.beta:id/item_price']").getText();
	}
	
	public void selectAnItem(String menuSubCategory, String menuItem) {
		clickOnMenuSubCategory(menuSubCategory);
		clickOnMenuSubItem(menuItem);
	}		
	
	public String navigateToMenuItem(String menuCategory, String menuCategory1, String menuSubCategory, String menuItem) {
		clickOnMenuCategory(menuCategory, menuCategory1);
		clickOnMenuSubCategory(menuSubCategory);
		return getItemPrice(menuItem);
	}
		
	
}
