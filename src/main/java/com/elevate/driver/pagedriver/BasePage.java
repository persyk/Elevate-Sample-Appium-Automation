package com.elevate.driver.pagedriver;

import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends DriverFactory implements Page {

	protected static final long DELAY_BETWEEN_ELEMENT_CHECKS_IN_MS = 250L;
	private final Properties resources;
	public ExpectedCondition<Boolean> isPageLoadedCondition;
	private static int currentPageLoadTimeOutInSecondsDefault = -1;
	public WebDriver driver;

	public BasePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.resources = new Properties();
		validatePageLoaded(driver);
	}

	protected void waitFor(WebDriver driver, ExpectedCondition<Boolean> expectedCondition, int timeOutInSeconds) {
		getLogger().info("waiting up to " + timeOutInSeconds * 1000 + "ms for expectedCondition: " + expectedCondition);

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, 250L);
		long tStart = System.currentTimeMillis();
		wait.until(expectedCondition);

		long tElapsed = System.currentTimeMillis() - tStart;
		getLogger().info("waited for page load " + tElapsed + "ms");
	}

	protected void validatePageLoaded(WebDriver driver) {
	}

	protected int getPageLoadTimeOut() {
		return getDefaultPageLoadTimeOutInSeconds();
	}

	public static int getDefaultPageLoadTimeOutInSeconds() {
		if (currentPageLoadTimeOutInSecondsDefault <= 0)
			currentPageLoadTimeOutInSecondsDefault = loadDefaultPageLoadTimeOutInSeconds();
		return currentPageLoadTimeOutInSecondsDefault;
	}

	private static int loadDefaultPageLoadTimeOutInSeconds() {
		Map<String, String> env = System.getenv();
		int loadedDefaultPageLoadTimeout;
		if (env.containsKey("TEST_DEFAULT_PAGE_TIME_OUT_IN_SEC")) {
			loadedDefaultPageLoadTimeout = Integer
					.parseInt((String) env.get("TEST_DEFAULT_PAGE_TIME_OUT_IN_SEC"));
		} else {
			loadedDefaultPageLoadTimeout = 5;
		}
		Logger.getLogger(BasePage.class.toString()).info(
				"Loaded default page load timeout: " + Integer.toString(loadedDefaultPageLoadTimeout) + " seconds.");
		return loadedDefaultPageLoadTimeout;
	}

	protected abstract String getExpectedTitle();

	public Properties getResources() {
		return this.resources;
	}

	public String getTitle() {
		return getDriver().getTitle();
	}
}
