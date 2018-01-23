package com.elevate.driver.webdriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverLoader {

	public static final int DEFAULT_TIMEOUT_IN_SECONDS = 15;
	private static final Pattern PATTERN_DRIVER_TYPE_APPIUM_IOS = Pattern.compile("appium-(app|safari)-(ipad|iphone)");
	private static final Pattern PATTERN_DRIVER_TYPE_APPIUM_ANDROID = Pattern.compile("appium-(app|browser)-android");

	private static final Logger LOGGER = Logger.getLogger(WebDriverLoader.class);
	public static final String ENV_VAR_NAME_TUNNEL_IDENTIFIER = "TUNNEL_IDENTIFIER";
	private String testName;
	private URL seleniumGridHub;
	private String appPath;
	private String appName;
	private String appPackage;
	private String deviceName;
	private String platformVersion;
	private String appActivity;
	private String appWaitActivity;
	private boolean trackClientPerf;
	private String platformName;
	private String deviceUdid;
	private boolean unicodeKeyboard;
	private boolean resetKeyboard;
	private static final Random random = new Random(System.currentTimeMillis());
	protected static final int HTTP_CONNECT_TIMEOUT_IN_SECONDS_FAIL_FAST = 5;
	protected static final int HTTP_RESPONSE_TIMEOUT_IN_SECONDS_FAIL_FAST = 15;
	
	private String bundleid;
	

	public WebDriverLoader(Properties properties) {
//		setSeleniumGridHub(properties.getProperty("selenium.grid.hub"));
//		this.appPath = properties.getProperty("appPath");
//		this.deviceName = properties.getProperty("deviceName");
//		this.platformVersion = properties.getProperty("platformVersion");
//		this.appPackage = properties.getProperty("appPackage");
//		this.platformName = properties.getProperty("platformName");
//		this.appActivity = properties.getProperty("appActivity");
//		this.appWaitActivity = properties.getProperty("appWaitActivity");
//		this.deviceUdid = properties.getProperty("deviceUdid");
//		this.bundleid = properties.getProperty("appPackage");

		try {
			this.seleniumGridHub = new URL("http://127.0.0.1:4723/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		this.appPath = "src/resources/apk/app-dev-release_160.apk";
//		this.deviceName = "Note 4";
//		this.platformVersion = "6.0.1";
//		this.appPackage = "com.convene.elevate.dev";
//		this.platformName = "android";
		this.appActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		this.appWaitActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		this.deviceUdid = "e1847c5eb114258488776b9e2a4bc1e1d136a4a2";		
		this.unicodeKeyboard = true;
		this.resetKeyboard = true;
		
		//iOS
		this.appPath = "src/resources/iOS_ipaFile/Elevate_Dev_f.ipa";
		this.deviceName = "iPhone 8";
		this.platformVersion = "11.0";
		this.platformName = "iOS";
		this.bundleid = "com.convene.Elevate.dev";	

		this.trackClientPerf = Boolean.valueOf(properties.getProperty("trackClientPerf")).booleanValue();
	}

	public WebDriverLoader(String seleniumGridHub) {
		setSeleniumGridHub(seleniumGridHub);
	}

	public void setSeleniumGridHub(String seleniumGridHub) {
		try {
			if (seleniumGridHub.equals("saucelabs"))
				this.seleniumGridHub = new URL(getSauceLabsHubLocation());
			else
				this.seleniumGridHub = new URL(seleniumGridHub);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public WebDriver loadWebDriver(String driverType) {
		LOGGER.debug("Requested load of webdriver : " + driverType);
		System.out.println("Requested load of driver : " + driverType);
		WebDriver driver;
		if (isFirefox(driverType)) {
			driver = loadFirefoxDriver();
		} else {
			if (isInternetExplorer(driverType)) {
				driver = loadInternetExplorerDriver();
			} else {
				if (isChrome(driverType)) {
					driver = loadChromeDriver();
				} else {
					if (isSafari(driverType)) {
						driver = loadSafariDriver();
					} else {
						if (isDefaultRemote(driverType)) {
							driver = loadDefaultRemoteDriver();
						} else {
							if (isRemoteChrome(driverType)) {
								driver = loadRemoteChromeDriver();
							} else {
								if (isLocalIphone(driverType)) {
									driver = loadLocalIphoneDriver();
								} else {
									if (isLocalAndroid(driverType)) {
										driver = loadLocalAndroidDriver();
									} else {
										if (isAppium_android(driverType)) {
											driver = loadAppium_android(driverType, this.appPath, this.appName,
													this.appPackage, this.appActivity);
										} else if (isAppium_iOS(driverType)) {
											driver = loadAppium_iOS(driverType, this.appPath, this.appName);
										} else {
											throw new IllegalArgumentException(
													"Sorry, " + driverType + " is not supported yet");
										}
									}
								}
							}
						}
					}
				}
			}
		}

		setDefaultTimeout(driver, 15, TimeUnit.SECONDS);
		LOGGER.debug(this.testName + " loaded WebDriver: " + driver);
		return driver;
	}

	private WebDriver loadLocalIphoneDriver() {
		try {
			
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			caps.setCapability("unicodeKeyboard", true);
			caps.setCapability("resetKeyboard", true);
			
			// Package name
			File f = new File(appPath);	
			caps.setCapability(MobileCapabilityType.APP,f.getAbsolutePath());
			
			// App activity of the Application
			caps.setCapability("appActivity", appActivity);
			caps.setCapability("appWaitActivity", appWaitActivity);
			
			//new
			caps.setCapability(MobileCapabilityType.UDID,deviceUdid);
			caps.setCapability("bundleId", bundleid);// App activity of the Application
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			caps.setCapability("xcodeOrgId", "T8N3W6MR79");
			caps.setCapability("xcodeSigningId", "iPhone Developer");
//			caps.setCapability("waitForAppScript", "$.delay(1000);");
//			caps.setCapability("autoDismissAlerts", true);
			
							
			return new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			//return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			//return new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"), DesiredCapabilities.iphone());
			
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isLocalIphone(String driverType) {
		return "iphone".equals(driverType);
	}

	private WebDriver loadDefaultRemoteDriver() {
		return loadRemoteDriverFirefox();
	}

	private String getSauceLabsHubLocation() {
		return System.getProperty("selenium.grid.2.hub",
				"http://<<-------------------------------->>@ondemand.saucelabs.com:80/wd/hub");
	}

	private WebDriver loadRemoteDriverFirefox() {
		try {
			RemoteWebDriver remoteWebDriver = new RemoteWebDriver(this.seleniumGridHub, loadFirefoxCapabilities());
			return enhanceRemoteWebDriver(remoteWebDriver);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private WebDriver enhanceRemoteWebDriver(RemoteWebDriver remoteWebDriver) {
		remoteWebDriver.setFileDetector(new LocalFileDetector());
		return new Augmenter().augment(remoteWebDriver);
	}

	private boolean isDefaultRemote(String driverType) {
		return "remote".equals(driverType);
	}

	private boolean isFirefox(String driverType) {
		return "firefox".equals(driverType);
	}

	protected DesiredCapabilities loadFirefoxCapabilities() {

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		capabilities.setCapability("acceptSslCerts", true);

		try {
			capabilities.setCapability("firefox_profile", createFirefoxProfile(this.trackClientPerf).toJson());
			if (isWindows64Bit())
				System.setProperty("webdriver.gecko.driver", "src/resources/bin/x64/win/geckodriver.exe");
			else
				System.setProperty("webdriver.gecko.driver", "src/resources/bin/x32/win/geckodriver.exe");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return capabilities;
	}

	private WebDriver loadFirefoxDriver() {
		return new FirefoxDriver(loadFirefoxCapabilities());
	}

	static int generateProfilePort() {
		return (7054 + random.nextInt(10000));
	}

	static FirefoxProfile createFirefoxProfile() {
		return createFirefoxProfile(false);
	}

	static FirefoxProfile addUserAgentOverride(String userAgent) {
		FirefoxProfile profile = createFirefoxProfile();
		profile.setPreference("general.useragent.override", userAgent);
		return profile;
	}

	static FirefoxProfile createFirefoxProfile(boolean trackClientPerf) {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAssumeUntrustedCertificateIssuer(false);
		profile.setAcceptUntrustedCertificates(true);
		profile.setPreference("webdriver_firefox_port", generateProfilePort());

		if (trackClientPerf) {
			addYSlowExtension(profile);
		} else {
			profile.setPreference("network.http.connection-timeout", 5);
			profile.setPreference("network.http.response.timeout", 15);
		}

		return profile;
	}

	static void addYSlowExtension(FirefoxProfile profile) {
		try {
			File firebugExt = new File("src/resources/extensions/firefox/firebug-1.12.8.xpi");
			profile.addExtension(firebugExt);
			profile.setPreference("extensions.firebug.currentVersion", "1.12.7");
			profile.setPreference("extensions.firebug.allPagesActivation", "on");
			profile.setPreference("extensions.firebug.defaultPanelName", "net");
			profile.setPreference("extensions.firebug.net.enableSites", true);

			File yslowExt = new File("src/resources/extensions/firefox/yslow-3.1.8-fx.xpi");
			profile.addExtension(yslowExt);
			profile.setPreference("extensions.yslow.autorun", true);
			profile.setPreference("extensions.yslow.optinBeacon", true);
			profile.setPreference("extensions.yslow.beaconInfo", "basic,grade");

			LOGGER.info("Loaded Firebug and YSlow extensions in Firefox");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isInternetExplorer(String driverType) {
		return "internetexplorer".equals(driverType);
	}

	private WebDriver loadInternetExplorerDriver() {
		DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
		capab.setCapability("ignoreProtectedModeSettings", true);
		try {
			if (isWindows64Bit())
				System.setProperty("webdriver.ie.driver", "src/resources/bin/x64/win/IEDriverServer.exe");
			else
				System.setProperty("webdriver.ie.driver", "src/resources/bin/x32/win/IEDriverServer.exe");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new InternetExplorerDriver(capab);
	}

	private boolean isChrome(String driverType) {
		return "chrome".equals(driverType);
	}

	private WebDriver loadChromeDriver() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments(new String[] { "disable-extensions" });
		options.addArguments(new String[] { "ignore-certificate-errors" });
		options.addArguments(new String[] { "--start-maximized"});
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chromeOptions", options);
		System.setProperty("webdriver.gecko.verboseLogging", "false");
		try {
			if (isPlatformWindows())
				System.setProperty("webdriver.chrome.driver", "src/resources/bin/x32/win/chromedriver.exe");
			else if (isPlatformMAC())
				System.setProperty("webdriver.chrome.driver", "src/resources/bin/macosx/chromedriver");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ChromeDriver(capabilities);
	}

	private boolean isPlatformWindows() {
		return (System.getProperty("os.name").toLowerCase().startsWith("win".toLowerCase()));
	}

	private boolean isWindows64Bit() {
		return (System.getProperty("os.arch").contentEquals("amd64"));
	}

	private boolean isPlatformMAC() {
		return (System.getProperty("os.name").toLowerCase().startsWith("mac".toLowerCase()));
	}

	private boolean isRemoteChrome(String driverType) {
		return "remote-chrome".equals(driverType);
	}

	private WebDriver loadRemoteChromeDriver() {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments(new String[] { "test-type" });
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chromeOptions", options);
			RemoteWebDriver remoteWebDriver = new RemoteWebDriver(this.seleniumGridHub, capabilities);
			return enhanceRemoteWebDriver(remoteWebDriver);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isSafari(String driverType) {
		return "safari".equals(driverType);
	}

	private WebDriver loadSafariDriver() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.safari();
		capabilities.setCapability("acceptSslCerts", true);
		return new SafariDriver(capabilities);
	}

	private void setDefaultTimeout(WebDriver driver, int timeout, TimeUnit timeUnit) {
		driver.manage().timeouts().implicitlyWait(timeout, timeUnit);
	}

	public URL getSeleniumGrid2Hub() {
		return this.seleniumGridHub;
	}

	static boolean isAppium_iOS(String driverType) {
		return PATTERN_DRIVER_TYPE_APPIUM_IOS.matcher(driverType).matches();
	}

	static boolean isAppium_android(String driverType) {
		return PATTERN_DRIVER_TYPE_APPIUM_ANDROID.matcher(driverType).matches();
	}

	private WebDriver loadAppium_iOS(String driverType, String appPath, String appName) {
		LOGGER.debug("Appium version  with iOS version9.3");
		DesiredCapabilities desiredCapabilities = createAppiumCapabilities_iOS(driverType, appPath, appName);
		return new RemoteWebDriver(this.seleniumGridHub, desiredCapabilities);
	}

	static DesiredCapabilities createAppiumCapabilities_iOS(String driverType, String appPath, String appName) {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		String[] components = driverType.split("-");

		if (components.length > 1) {
			String appComponent = components[1];
			if ("safari".equalsIgnoreCase(appComponent))
				desiredCapabilities.setCapability("browserName", "safari");
			else {
				desiredCapabilities.setCapability("app", generateAppCapability(appPath, appName));
			}

		}

		String deviceName = "iPhone Simulator";
		if (driverType.toLowerCase().startsWith("saucelabs")) {
			deviceName = "iPhone Simulator";
		}
		if (components.length > 2) {
			String deviceComponent = components[2];
			if (!(deviceComponent.toLowerCase().equals("iphone"))) {
				if (deviceComponent.toLowerCase().equals("ipad"))
					deviceName = "iPad 2";
				else
					throw new IllegalArgumentException(
							"Unknown device " + deviceComponent + " in webdriver " + driverType);
			}
		}
		desiredCapabilities.setCapability("platformName", "iOS");
		desiredCapabilities.setCapability("deviceName", deviceName);
		desiredCapabilities.setCapability("platformVersion", "9.3");
		desiredCapabilities.setCapability("platform", Platform.MAC);

		desiredCapabilities.setCapability("version", "iOS-9.3");

		return desiredCapabilities;
	}

	private static String generateAppCapability(String appPath, String appName) {
		if ((appName == null) || (appName.equals("")))
			throw new IllegalArgumentException("appName not defined in defaultTest.properties or by mvn -D");
		if ((appPath == null) || (appPath.equals(""))) {
			throw new IllegalArgumentException("appPath not defined in defaultTest.properties or by mvn -D");
		}
		String fileSeparator = System.getProperty("file.separator");
		if (!(appPath.endsWith(fileSeparator))) {
			appPath = appPath + fileSeparator;
		}
		return appPath + appName;
	}

	private WebDriver loadAppium_android(String driverType, String appPath, String appName, String appPackage, String appActivity) {
		DesiredCapabilities desiredCapabilities = createAppiumCapabilities_Android(driverType, appPath, appName,appPackage, appActivity, unicodeKeyboard, resetKeyboard);
		return new RemoteWebDriver(this.seleniumGridHub, desiredCapabilities);
	}

	static DesiredCapabilities createAppiumCapabilities_Android(String driverType, String appPath, String appName, String appPackage, String appActivity, boolean unicodeKeyboard, boolean resetKeyboard) {
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		String[] components = driverType.split("-");

		if (components.length > 1) {
			String appComponent = components[1];
			if ("browser".equalsIgnoreCase(appComponent)) {
				desiredCapabilities.setCapability("browserName", "browser");
			} else {
				desiredCapabilities.setCapability("app", generateAppCapability(appPath, appName));
				desiredCapabilities.setCapability("appPackage", appPackage);
				desiredCapabilities.setCapability("appActivity", appActivity);
				
			}
		}

		if (components.length > 2) {
			String deviceComponent = components[2];
			if ("android".equalsIgnoreCase(deviceComponent)) {
				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("platformVersion", "5.0.1");
				desiredCapabilities.setCapability("deviceName", "4d00d41b85134057");
			} else {
				throw new IllegalArgumentException("Unknown device " + deviceComponent + " in webdriver " + driverType);
			}
		}
		return desiredCapabilities;
	}

	static String getVersion(String platformComponent, String platform, String defaultVersion) {
		String platformVersion = platformComponent.substring(platform.length());
		if (platformVersion.equals(""))
			platformVersion = defaultVersion;
		return platformVersion;
	}

	private boolean isLocalAndroid(String driverType) {
		return "android".equals(driverType);
	}

	private WebDriver loadLocalAndroidDriver() {
		try {
		/*	DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", "LocalAndroid");
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("browserName", "browser");
			capabilities.setCapability("platformVersion", "ANY");
			capabilities.setCapability("autoDismissAlerts", true);
			return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);*/
			
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			caps.setCapability("unicodeKeyboard", true);
			caps.setCapability("resetKeyboard", true);

			
			
			// Package name
			File f = new File(appPath);	
			caps.setCapability(MobileCapabilityType.APP,f.getAbsolutePath());
			caps.setCapability("appPackage", appPackage);

			// App activity of the Application
			caps.setCapability("appActivity", appActivity);
			caps.setCapability("appWaitActivity", appWaitActivity);
				
			return new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			
		} catch (Exception e) {
			e.getStackTrace();
			throw new IllegalArgumentException("The expected URl is http://127.0.0.1:4723/wd/hub");
		}
	}

	public static class WebDriverType {
		private final Map<String, String> desiredCapabilities;

		WebDriverType(Map<String, String> desiredCapabilities) {
			this.desiredCapabilities = desiredCapabilities;
		}

		public String getBrowserName() {
			return ((String) this.desiredCapabilities.get("browserName"));
		}

		public String getVersion() {
			return ((String) this.desiredCapabilities.get("version"));
		}

		public Platform getPlatform() {
			if (this.desiredCapabilities.containsKey("platform")) {
				return Platform.valueOf(((String) this.desiredCapabilities.get("platform")).toUpperCase());
			}
			return null;
		}

		public static WebDriverType toWebDriverType(String driverType) {
			String[] components = driverType.split("-");
			HashMap<String, String> desiredCapabilities = new HashMap<String, String>(3);

			if (components.length > 1) {
				String browserComponent = components[1];
				String browserName = ("internetexplorer".equals(browserComponent)) ? "internet explorer"
						: browserComponent;

				desiredCapabilities.put("browserName", browserName);

				if ("safari".equals(browserName)) {
					desiredCapabilities.put("version", "7.1");
				}
			}

			if (components.length > 2) {
				desiredCapabilities.put("platform", components[2]);
			}

			if (components.length > 3) {
				desiredCapabilities.put("version", components[3]);
			}

			return new WebDriverType(desiredCapabilities);
		}
	}
}