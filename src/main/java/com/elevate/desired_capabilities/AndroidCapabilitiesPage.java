package com.elevate.desired_capabilities;

public class AndroidCapabilitiesPage extends DesiredCapabilitiesPage {
	
	public AndroidCapabilitiesPage() {
		
		super.appActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		super.appPackage = "com.convene.elevate.dev";
		super.appPath = "src/resources/apk/app-dev-release_160.apk";
		super.appWaitActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		//super.deviceName = "Galaxy S8";
		super.deviceName = "Note 4";
		super.platformName = "android";
		super.platformVersion = "6.0.1";
		super.resetKeyboard = true;
		super.unicodeKeyboard = true;
		super.trackClientPerf = true;		
	}

}
