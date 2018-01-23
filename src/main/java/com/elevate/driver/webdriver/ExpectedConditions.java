package com.elevate.driver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.google.common.base.Predicate;

public class ExpectedConditions {

	public ExpectedConditions() {
	}

	public static ExpectedCondition<Boolean> presenceOfElementsLocated(final By locator) {

		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return Boolean.valueOf(!driver.findElements(locator).isEmpty());
			}

			public String toString() {
				return String.format("looking for presence of elements located by \"%s\"", new Object[] { locator });
			}
		};
	}

	public static Predicate<WebDriver> visibilityOfElementLocated(By xpath) {
		// TODO Auto-generated method stub
		return null;
	}
}