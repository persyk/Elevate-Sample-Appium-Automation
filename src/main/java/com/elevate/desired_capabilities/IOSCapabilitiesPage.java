package com.elevate.desired_capabilities;

public class IOSCapabilitiesPage extends DesiredCapabilitiesPage {

	public IOSCapabilitiesPage() {

		super.appPath = "src/resources/iOS_ipaFile/Elevate_Testing_#16.ipa";
		super.bundleId = "com.convene.Elevate.dev";
		super.appActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		super.appWaitActivity = "com.convene.elevate.modules.splash.SplashScreenActivity";
		super.deviceName = "iPhone 8";
		super.platformName = "iOS";
		super.platformVersion = "11.0";
		super.deviceUdid = "e1847c5eb114258488776b9e2a4bc1e1d136a4a2";
		super.resetKeyboard = true;
		super.unicodeKeyboard = true;
		super.trackClientPerf = true;
	}

}
