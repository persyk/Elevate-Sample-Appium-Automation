package com.elevate.desired_capabilities;

public class AndroidCapabilitiesPage extends DesiredCapabilitiesPage {
	
	public AndroidCapabilitiesPage() {
		
		super.appActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		super.appPackage = "com.convene.elevate.beta";
		super.appPath = "src/resources/apk/app-staging-release_43.apk";
		super.appWaitActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		super.deviceName = "Galaxy S8";
		//super.deviceName = "Note 4";
		super.platformName = "android";
		super.platformVersion = "7.0";
		//super.platformVersion = "6.0.1";
		super.resetKeyboard = true;
		super.unicodeKeyboard = true;
		super.trackClientPerf = true;		
	}

}
