# README #

This README document is under work in progress.



### What is this repository for? ###

* This repository contains test for Elevate mobile apps (Android and iOS).
* iOS and Android builds are available at Nevercode

### How do I get set up? ###

* Summary of set up

* Configuration and Dependencies (Tools and Technologies used)
- Appium 1.7.1 – Mobile automation tool 
- Android SDK
- Java – Scripting language
- Selenium web driver – Web automation tool
- TestNG  6.11 – Unit Testing tool
- Maven  3.3.9 –  Project  management tool
- Eclipse – IDE tool  (Any other IDE such as intellij can also be used)
- Devices used for automation – iPhone8 (ios11), iPhone6 (ios10.3.3), Android Note4 (6.0.1)and Samsung Galaxy8 (7.0)

* Database configuration - N/A

### How to run Tests ###
* Run tests on Android device:
1. Set the value of ‘executeOnPlatform’ parameter to  "android" in ‘DesiredCapabilitiesPage’ [Stored at Elevate/src/main/java/com/elevate/desired_capabilities]
2. Set the capabilities i.e  appPath, deviceName, platformVersion, appPackage, platformName, appActivity, appWaitActivity in  "AndroidCapabilitiesPage"

* Run tests on iOS device:
1. Set the value of ‘executeOnPlatform’ parameter to  "ios" in ‘DesiredCapabilitiesPage’. [Stored at Elevate/src/main/java/com/elevate/desired_capabilities]
2. Set the capabilities i.e  appPath, deviceName, platformVersion, bundleId, platformName, appActivity, appWaitActivity, device UDID in  "iOSCapabilitiesPage"

* Set the 'username' and 'password' values in BaseDriver file in 'applyPropertiesMethod' [Stored at Elevate/src/main/java/com/elevate/driver/webdriver]

* Deployment instructions - N/A

### About framework: ###
1. Framework is based on  Page Object Model
2. All the Maven dependencies and plugins required for AWS device farm are stored in POM.xml file
3. Desired Capabilities required for the tests are stored at <Elevate/src/main/java/com/elevate/desired_capabilities>
4. Elevate app identifiers are stored at <Elevate/src/main/java/com/elevate/locators>
5. Android app is kept within framework at </Elevate/src/resources/apk>
6. iOS ipa file is kept at </Elevate/src/resources/iOS_ipaFile>
7. All the Pages are residing at <Elevate/src/main/java/com/elevate/pages>
8. Tests are written at </Elevate/src/test/java/com/elevate>


### To setup Amazon Device farm below steps mentioned in link works fine: ###
1. https://docs.aws.amazon.com/devicefarm/latest/developerguide/test-types-android-appium-java-testng.html
2. https://docs.aws.amazon.com/devicefarm/latest/developerguide/test-types-ios-appium-java-testng.html


### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
