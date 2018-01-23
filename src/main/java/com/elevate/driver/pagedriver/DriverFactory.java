package com.elevate.driver.pagedriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.elevate.driver.utils.Loader;

public class DriverFactory extends Loader {

	private WebDriver driver;

	public DriverFactory(WebDriver driver) {
		this.driver = driver;
	}

	protected static boolean isCurrentlyVisible(WebDriver driver, By locator) {
		List<WebElement> elements = driver.findElements(locator);
		if (elements.isEmpty()) {
			return false;
		}
		for (WebElement element : elements) {
			if (!element.isDisplayed()) {
				return false;
			}
		}
		return true;
	}

//	protected Logger getLogger() {
//		return Logger.getLogger(getClass().toString());
//	}

	public void sleep(long waitTimeInMS, String reason) {
		if (reason == null) {
			throw new IllegalArgumentException("a reason for sleeping must be specified");
		}
		getLogger().info("Sleeping for " + waitTimeInMS + "ms because: " + reason);
		try {
			Thread.sleep(waitTimeInMS);
		} catch (InterruptedException e) {
			getLogger().info("Caught InterruptedException while sleeping: " + e.getMessage());
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	protected WebElement findElementById(String id) {
		return getDriver().findElement(By.id(id));
	}

	protected WebElement findElementById(String id, int nTry) {
		isElementDisplayed(By.id(id), nTry);
		return findElementById(id);
	}

	protected List<WebElement> findElementsById(String id) {
		return getDriver().findElements(By.id(id));
	}

	protected List<WebElement> findElementsById(String id, int nTry) {
		isElementDisplayed(By.id(id), nTry);
		return findElementsById(id);
	}

	protected WebElement findElementByClassName(String className) {
		return getDriver().findElement(By.className(className));
	}

	protected WebElement findElementByClassName(String className, int nTry) {
		isElementDisplayed(By.className(className), nTry);
		return findElementByClassName(className);
	}

	protected List<WebElement> findElementsByClassName(String className) {
		return getDriver().findElements(By.className(className));
	}

	protected List<WebElement> findElementsByClassName(String className, int nTry) {
		isElementDisplayed(By.className(className), nTry);
		return findElementsByClassName(className);
	}

	protected WebElement findElementByCssSelector(String selector) {
		return getDriver().findElement(By.cssSelector(selector));
	}

	protected WebElement findElementByCssSelector(String selector, int nTry) {
		isElementDisplayed(By.cssSelector(selector), nTry);
		return findElementByCssSelector(selector);
	}

	protected List<WebElement> findElementsByCssSelector(String selector) {
		return getDriver().findElements(By.cssSelector(selector));
	}

	protected List<WebElement> findElementsByCssSelector(String selector, int nTry) {
		isElementDisplayed(By.cssSelector(selector), nTry);
		return findElementsByCssSelector(selector);
	}

	protected WebElement findElementByXpath(String xpathExpression) {
		return getDriver().findElement(By.xpath(xpathExpression));
	}

	protected WebElement findElementByXpath(String xpathExpression, int nTry) {
		isElementDisplayed(By.xpath(xpathExpression), nTry);
		return findElementByXpath(xpathExpression);
	}

	protected List<WebElement> findElementsByXpath(String xpathExpression) {
		return getDriver().findElements(By.xpath(xpathExpression));
	}

	protected List<WebElement> findElementsByXpath(String xpathExpression, int nTry) {
		isElementDisplayed(By.xpath(xpathExpression), nTry);
		return findElementsByXpath(xpathExpression);
	}

	protected WebElement findElementByLinkText(String linkText) {
		return getDriver().findElement(By.linkText(linkText));
	}

	protected WebElement findElementByLinkText(String linkText, int nTry) {
		isElementDisplayed(By.linkText(linkText), nTry);
		return findElementByLinkText(linkText);
	}

	protected List<WebElement> findElementsByLinkText(String linkText) {
		return getDriver().findElements(By.linkText(linkText));
	}

	protected List<WebElement> findElementsByLinkText(String linkText, int nTry) {
		isElementDisplayed(By.linkText(linkText), nTry);
		return findElementsByLinkText(linkText);
	}

	protected WebElement findElementByName(String name) {
		return getDriver().findElement(By.name(name));
	}

	protected WebElement findElementByName(String name, int nTry) {
		isElementDisplayed(By.name(name), nTry);
		return findElementByName(name);
	}

	protected List<WebElement> findElementsByName(String name) {
		return getDriver().findElements(By.name(name));
	}

	protected List<WebElement> findElementsByName(String name, int nTry) {
		isElementDisplayed(By.name(name), nTry);
		return findElementsByName(name);
	}

	protected WebElement findElementByPartialLinkText(String partialLinkText) {
		return getDriver().findElement(By.partialLinkText(partialLinkText));
	}

	protected WebElement findElementByPartialLinkText(String partialLinkText, int nTry) {
		isElementDisplayed(By.partialLinkText(partialLinkText), nTry);
		return findElementByPartialLinkText(partialLinkText);
	}

	protected List<WebElement> findElementsByPartialLinkText(String partialLinkText) {
		return getDriver().findElements(By.partialLinkText(partialLinkText));
	}

	protected List<WebElement> findElementsByPartialLinkText(String partialLinkText, int nTry) {
		isElementDisplayed(By.partialLinkText(partialLinkText), nTry);
		return findElementsByPartialLinkText(partialLinkText);
	}

	protected WebElement findElementByTagName(String tagName) {
		return getDriver().findElement(By.tagName(tagName));
	}

	protected WebElement findElementByTagName(String tagName, int nTry) {
		isElementDisplayed(By.tagName(tagName), nTry);
		return findElementByTagName(tagName);
	}

	protected List<WebElement> findElementsByTagName(String tagName) {
		return getDriver().findElements(By.tagName(tagName));
	}

	protected List<WebElement> findElementsByTagName(String tagName, int nTry) {
		isElementDisplayed(By.tagName(tagName), nTry);
		return findElementsByTagName(tagName);
	}

	protected boolean isElementPresent(By locatorKey) {
		try {
			getDriver().findElement(locatorKey);
			return true;
		} catch (NoSuchElementException e) {
		}
		return false;
	}

	public boolean isElementPresent(By locatorKey, int nTry) {
		try {
			for (int i = 0; i < nTry; i++) {
				if (isElementPresent(locatorKey))
					return true;
				Thread.sleep(1000L);
			}
			return false;
		} catch (Exception e) {
			getLogger().info("Exception while waiting for element to appear: " + e.getMessage());
		}
		return false;
	}

	public boolean isElementNotPresent(final By locator, int nTry) {
		return false;
	}

	public boolean isElementDisplayed(By locatorKey, int nTry) {
		try {
			for (int i = 0; i < nTry; i++) {
				if (getDriver().findElement(locatorKey).isDisplayed())
					return true;
				Thread.sleep(1000L);
			}
			return false;
		} catch (Exception e) {
		}
		return false;
	}

	public boolean isTextPresent(String text) {
		WebElement bodySource = getDriver().findElement(By.tagName("body"));
		String bodyText = bodySource.getText();
		return bodyText.contains(text);
	}

	public void selectFromDropDownByVisibleText(WebElement element, String visibleText) {
		Select dropDown = new Select(element);
		dropDown.selectByVisibleText(visibleText);
	}

	public String getFirstSelectedOption(WebElement element) {
		return new Select(element).getFirstSelectedOption().getText();
	}

	public void forceRefresh(String reason) {
		if (reason == null) {
			throw new IllegalArgumentException("a reason for force refresh must be specified");
		}
		getLogger().info("Refreshing page because: " + reason);
		getDriver().navigate().refresh();
	}

	protected final WebDriver goToPopupHavingUrl(String url) {
		WebDriver popup = null;
		Iterator<String> ite = getDriver().getWindowHandles().iterator();
		while (ite.hasNext()) {
			popup = getDriver().switchTo().window(((String) ite.next()).toString());
			if (popup.getCurrentUrl().contains(url)) {
				getLogger().info("The url of the current pop up is : " + popup.getCurrentUrl().toString());
				getLogger().info("PopUp is now focussed");
			}
		}
		return popup;
	}

	protected final WebDriver goToPopupHavingTitle(String title) {
		WebDriver popup = null;
		boolean found = false;
		Iterator<String> ite = getDriver().getWindowHandles().iterator();
		while (ite.hasNext()) {
			popup = getDriver().switchTo().window(((String) ite.next()).toString());
			if (popup.getTitle().contains(title)) {
				getLogger().info("The title of the current pop up is : " + popup.getTitle());
				getLogger().info("PopUp is now focussed");
				found = true;
			}
		}
		if (!found) {
			throw new RuntimeException("failed to find popup window with title <" + title + ">");
		}
		return popup;
	}

	public final WebDriver switchToChildWindow() {
		WebDriver childWindow = null;
		Iterator<String> ite = getDriver().getWindowHandles().iterator();
		while (ite.hasNext()) {
			childWindow = getDriver().switchTo().window(((String) ite.next()).toString());
			getLogger().info("The title of the current pop up is : " + childWindow.getTitle());
			getLogger().info("PopUp is now focussed");
		}
		return childWindow;
	}

	public void closeChildWindow() {
		getDriver().close();
	}

	public void mouseOver(WebElement element) {
		Actions builder = new Actions(getDriver());
		builder.moveToElement(element).build().perform();
		sleep(2000L, "waiting for mouse over event to happen");
		getLogger().info("moused-over: " + element);
	}

	public void mouseOverAndClick(WebElement element) {
		Actions builder = new Actions(getDriver());
		builder.moveToElement(element).click().build().perform();
		sleep(2000L, "waiting for mouse over and click event to happen");
		getLogger().info("moused-over and clicked: " + element);
	}

	public void javascriptMouseOver(WebElement element) {
		String mouseOverScript = "if(document.createEvent){   var evObj = document.createEvent('MouseEvents');   evObj.initEvent(       'mouseover',       false,        false   );    arguments[0].dispatchEvent(evObj);}else if(document.createEventObject){   arguments[0].fireEvent('onmouseover');}";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript(mouseOverScript, new Object[] { element });
	}

	public void mouseOut(WebElement element) {
		Actions builder = new Actions(getDriver());
		builder.moveToElement(element).release();
		sleep(2000L, "waiting for mouse out event to happen");
		getLogger().info("MouseOut the element");
	}

	public void doubleClick(WebElement onElement) {
		Actions action = new Actions(getDriver());
		action.doubleClick(onElement);
		action.perform();
	}

	public void goToPopupPageAndClose(String mwh) {
		WebDriver popup = null;
		Iterator<String> ite = getDriver().getWindowHandles().iterator();
		while (ite.hasNext()) {
			popup = getDriver().switchTo().window(((String) ite.next()).toString());
			String hwnd = popup.getWindowHandle();
			if (!hwnd.equalsIgnoreCase(mwh)) {
				getLogger().info("The title of the current pop up is : " + popup.getTitle());
				getLogger().info("PopUp is now focussed");
				break;
			}
		}
		getLogger().info("About to close focussed/PopUp window");
		popup.close();
		getLogger().info("Focussed/PopUp window is closed; returning control to main window");
		getDriver().switchTo().window(mwh);
		getLogger().info("Control returned to main window.");
	}

	protected void executeScript(String javascript, WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript(javascript, new Object[] { element });
	}

	protected Object executeScript(String javascript) {
		return ((JavascriptExecutor) getDriver()).executeScript(javascript, new Object[0]);
	}

	protected void executeScriptFromFile(String relativeFilePath) {
	}

	protected void injectJQueryDragAndDropHelper() {
		executeScriptFromFile("javascript/drag_and_drop_helper.js");
	}

	public void openTab(String url) {
		String script = "var d=document,a=d.createElement('a');a.target='_blank';a.href='%s';a.innerHTML='.';d.body.appendChild(a);return a";
		Object resultOfExecuteScript = executeScript(String.format(script, new Object[] { url }));
		if ((resultOfExecuteScript instanceof WebElement)) {
			WebElement anchor = (WebElement) resultOfExecuteScript;
			anchor.click();
			executeScript("var a=arguments[0];a.parentNode.removeChild(a);", anchor);
		} else {
			throw new CannotOpenTabException(resultOfExecuteScript);
		}
	}

	public String openWindow() {
		Object resultOfExecuteScript = executeScript("window.open()");
		Set<String> windowHandles = getDriver().getWindowHandles();
		if (windowHandles.isEmpty()) {
			throw new CannotOpenWindowException(resultOfExecuteScript);
		}
		Iterator<String> handlesIterator = windowHandles.iterator();
		String handle;
		do {
			handle = (String) handlesIterator.next();
		} while (handlesIterator.hasNext());
		return handle;
	}

	public void stopBrowserPageLoad(int times) {
		sleep(300L, "waiting for page to finish loading, before stopping page from loading");
		for (int i = 0; i < times; i++) {
			getDriver().findElement(By.tagName("body")).sendKeys(new CharSequence[] { "Keys.ESCAPE" });
			getLogger().info("attempt to stop browser [" + (i + 1) + "] time(s)");
		}
	}

	protected void type(WebElement target, String value) {
		target.clear();
		target.sendKeys(new CharSequence[] { value });
	}

	protected long getCurrentTime() {
		return System.currentTimeMillis();
	}

	protected List<String> getValuesFromSelect(WebElement target) {
		List<String> list = new ArrayList<String>();
		Select select = new Select(target);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			list.add(option.getText());
		}
		return list;
	}

	protected void select(WebElement target, String item) {
		boolean found = false;
		Select select = new Select(target);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			String selectOption = option.getText();
			if (selectOption.equals(item)) {
				option.click();
				found = true;
				break;
			}
			if ((!found) && (selectOption.contains(item))) {
				option.click();
				found = true;
				break;
			}
		}
		if (!found) {
			throw new java.util.InputMismatchException("item <" + item + "> not found in the list");
		}
	}

	protected void select(WebElement target) {
		Select select = new Select(target);
		List<WebElement> options = select.getOptions();
		int count = options.size();
		if (count <= 1) {
			throw new RuntimeException("No items found in the list to select, list was empty.");
		}
		Random rand = new Random();
		int value = rand.nextInt(count);
		select.selectByIndex(value);
	}

	public void maximizeWindow() {
		getDriver().manage().window().maximize();
	}

	public boolean isNotBlank(String str) {
		return StringUtils.isNotBlank(str);
	}

	public boolean isMonkeyImageDislayed(WebElement element) {
		String attrib = element.getAttribute("src");
		if (attrib == null) {
			throw new RuntimeException("Null pointer exception thrown, getting object attribute");
		}
		return attrib.contains("crazymonkey");
	}

	protected void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public void dragAndDrop(WebElement dragElement, WebElement dropTarget) {
		getLogger().info("dragging " + dragElement + " to " + dropTarget);
		Actions builder = new Actions(getDriver());
		builder.clickAndHold(dragElement);
		builder.perform();
		mouseOver(dragElement);
		builder.moveToElement(dropTarget).release(dropTarget);
		builder.perform();
		sleep(1000L, "waiting for release to happen else throws an exception on page");
	}

	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
		}
		return false;
	}

	public Alert switchToAlert() {
		return getDriver().switchTo().alert();
	}

	public void acceptAlert(Alert alert) {
		alert.accept();
	}

	public void dismissAlert(Alert alert) {
		alert.dismiss();
	}

	public String getAlertText(Alert alert) {
		return alert.getText();
	}

	public String getWindowHandle() {
		return getDriver().getWindowHandle();
	}

	public void switchToWindow(String handle) {
		getDriver().switchTo().window(handle);
		sleep(1000L, "wait for window switch");
	}

	public String getUrl() {
		return getDriver().getCurrentUrl();
	}

	public String getSiteURL() {
		String url = getUrl();
		try {
			URL aURL = new URL(url);
			return aURL.getProtocol() + "://" + aURL.getHost();
		} catch (MalformedURLException e) {
			throw new RuntimeException("Failed to build site url", e);
		}
	}

	public void switchToFrame(String frame) {
		getDriver().switchTo().frame(frame);
	}

	public void switchToDefault() {
		getDriver().switchTo().defaultContent();
	}

	public boolean inSelectOptions(List<WebElement> actOptions, String[] expOptions) {
		boolean found = true;
		if (expOptions.length > actOptions.size()) {
			throw new RuntimeException("Expected length is greater than the actual length");
		}
		if (expOptions.length < actOptions.size()) {
			throw new RuntimeException("Expected length is smaller than the actual length");
		}
		for (int i = 1; i < expOptions.length; i++) {
			if (!expOptions[i].contentEquals(((WebElement) actOptions.get(i)).getText().trim())) {
				found = false;
			}
		}
		return found;
	}

	protected void dragAndDropViaJQueryHelper(String dragSourceJQuerySelector, String dropTargetJQuerySelector) {
		String javascript = "var dropTarget = jQuery(" + dropTargetJQuerySelector + ");" + "\n" + "jQuery("
				+ dragSourceJQuerySelector + ").simulate('drag', { dropTarget: dropTarget });";
		getLogger().info("executing javascript:\n" + javascript);
		executeScript(javascript);
		getLogger().info("executed drag-n-drop action via javascript");
	}
	
	
	public void swipe(int start_x, int start_y, int end_x, int end_y)
	{
		 TouchAction ta = new TouchAction((AppiumDriver<WebElement>) getDriver());
		 ta.press(start_x, start_y).moveTo(end_x, end_y).release().perform();
	}

	static class CannotOpenTabException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		CannotOpenTabException(Object resultOfExecuteScript) {
			super();
		}
	}

	static class CannotOpenWindowException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		CannotOpenWindowException(Object resultOfExecuteScript) {
			super();
		}
	}

	public String getAbsolutePath(String relativeUrl) {
		URL fileUrl = DriverFactory.class.getClassLoader().getResource(relativeUrl);
		if (System.getProperty("os.name").contains("Win")) {
			return fileUrl.getFile().replaceFirst("/", "").replaceAll("/", "\\\\");
		}
		return fileUrl.getFile();
	}

	public void navigateBack() {
		getDriver().navigate().back();
	}

	public WebElement waitForElement(By by, String reason, int timeOut) {
		if (reason == null) {
			throw new IllegalArgumentException("a reason for wait must be specified");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
		long tStart = System.currentTimeMillis();
		WebElement element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		long tElapsed = System.currentTimeMillis() - tStart;
		getLogger().info("the reason for wait: " + reason + " waited " + tElapsed + "ms");
		return element;
	}

	public WebElement waitForElement(WebElement element, String reason, int timeOut) {
		if (reason == null) {
			throw new IllegalArgumentException("a reason for wait must be specified");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
		long tStart = System.currentTimeMillis();
		WebElement el = (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
		long tElapsed = System.currentTimeMillis() - tStart;
		getLogger().info("the reason for wait: " + reason + " waited " + tElapsed + "ms");
		return el;
	}

	public void waitForTextToLoad(By locator, String reason, int timeOut) {
		if (reason == null) {
			throw new IllegalArgumentException("a reason for wait must be specified");
		}
		String text = "";
		while (text.length() == 0) {
			WebElement element = getDriver().findElement(locator);
			text = element.getText();
			sleep(500L, "waiting for text to load");
			timeOut = timeOut--;
			if (timeOut == 0) {
				break;
			}
		}
	}

	@Override
	protected void applyProperties(Properties paramProperties) {
		// TODO Auto-generated method stub
	}
}