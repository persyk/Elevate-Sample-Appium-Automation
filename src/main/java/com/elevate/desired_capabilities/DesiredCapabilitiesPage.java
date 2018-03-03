package com.elevate.desired_capabilities;

public class DesiredCapabilitiesPage {

	private static String mavenProperty;
	public String appPath;
	public String deviceName;
	public String platformVersion;
	public String appName;
	public String appPackage;
	public String platformName;
	public String appActivity;
	public String appWaitActivity;
	public String deviceUdid;
	public String bundleId;
	public boolean unicodeKeyboard;
	public boolean resetKeyboard;
	public boolean trackClientPerf;
	public String seleniumGridHub = "http://127.0.0.1:4723/wd/hub";

	/**
	 * Set value to 'ios' to run tests on iOS app
	 * Set value to 'android' to run tests on Android app
	 * Execute mvn test -Denv=android to run tests on Android or mvn test -Denv=ios to run tests on iOS
	 **/
	public static String executeOnPlatform;


	public static String platformSelector(){
		mavenProperty = System.getProperty("env");
		if (mavenProperty == null)
			executeOnPlatform = "android";
		else if (mavenProperty.equals("ios"))
			executeOnPlatform = "ios";
		else if (mavenProperty.equals("android"))
			executeOnPlatform = "android";
		return executeOnPlatform;
	}

}
