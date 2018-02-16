package com.elevate.desired_capabilities;

public class AndroidCapabilitiesPage extends DesiredCapabilitiesPage {
	
	public AndroidCapabilitiesPage() {
		
		super.appActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		//super.appPackage = "com.convene.elevate.beta";
		//super.appPath = "src/resources/apk/app-staging-release_55.apk";
		super.appPackage = "com.convene.elevate.dev";		
		super.appPath = "src/resources/apk/app-dev-release_273.apk";
		super.appWaitActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
//		super.platformVersion = "6.0.1";
//      super.deviceName = "Note 4";
		super.platformName = "android";
		super.deviceName = "Galaxy S8";
		super.platformVersion = "7.0";		
		super.resetKeyboard = true;
		super.unicodeKeyboard = true;
		super.trackClientPerf = true;		
	}

}
