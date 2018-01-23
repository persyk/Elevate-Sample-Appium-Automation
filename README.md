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
* How to run tests - #To be added [Currently working on creating separate java classes that will pick platform's desired capabilities at run time]
1. Set 'Webdriver' value to "android/iphone" in BaseAppPage. java (Line number 29) [Package: java/com/elevate/pages]
2. Set value to "android/iphone" in this.webdriver= "android/iphone"; at line number 51 under applyproperties() method in BaseDriver. java [Package: java/com/elevate/driver/webdriver]
3. Appium capabilities has to be set in webDriverLoader.java class constructor WebDriverLoader(Properties properties)

* Deployment instructions - N/A

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
