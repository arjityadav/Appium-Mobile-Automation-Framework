# Appium Mobile Automation Framework

Framework for Mobile test automation (Native app and Browser) on Android devices :iphone:

![image](https://github.com/user-attachments/assets/2fffa5d5-2576-4315-a7be-7de3c09674c2)

## :rocket: Quick Start - Appium set up on Windows (Android):

1) Install [Java JDK11](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html)
   and [Eclipse IDE](https://www.eclipse.org/downloads)
2) Install [NodeJS](https://nodejs.org/en/download/)
3) Install [Android studio](https://developer.android.com/studio)
4) Install Appium Server using npm (CLI) command `npm install -g appium`. Appium server version 2.12.1

```
Command to check the installed appium version: `appium --version`
```

5) Add below Android SDK path in the environment variable

```
    - ANDROID_HOME = <path to Sdk folder>
    - %ANDROID_HOME%\tools
    - %ANDROID_HOME%\tools\bin
    - %ANDROID_HOME%\platform-tools
```
6) Install [Appium Inspector](https://github.com/appium/appium-inspector/releases)

## :pushpin: Creating Android Virtual Device (Emulator) from Android Studio:

1) Open Android Studio.
2) Click on Tools -> AVD Manager -> Create Virtual Device -> Select the device and OS version -> Finish.
3) Once Virtual device is created, click on Launch this AVD in the emulator.
4) Command to view the list of devices attached `adb devices`

## :pushpin: Android Real Device Set up:

1) Connect Android real device to the machine(Desktop/Laptop)
2) Turn on the developer options in android mobile ([How to do it?](https://developer.android.com/studio/debug/dev-options))
3) Enable USB debugging
4) Run command `adb devices` in cmd prompt to check whether the device is recognised

## :pushpin: Start Android Emulator from Command line

1) Open command prompt, go to `<sdk emulator path>`

```
Command to stard AVD: `emulator -avd <avd_name>`
Command to stop/kill AVD: `adb -e emu kill`
```

## :pushpin: Pushing the App (.apk file) to Android Emulator:

1) Copy the .apk file and paste it in the path - `<path to sdk platform-tools>`
2) Open the cmd terminal from the directory where APK file is placed and enter command `adb install <apk filename>`

## :pushpin: Android - Finding appPackage and appActivity:

If the app is already installed on your device then we can make use of appPackage and appActivity to launch the app

1) Open the app on the device, for which appPackage and appActivity is required.
2) Open powershell and enter command `adb shell dumpsys window | grep -E 'mCurrentFocus|mFocusedApp'`

## :pushpin: Inspecting Elements

### Appium Inspector

1) Start the Appium Server and connect with Real device/Emulator.
2) Open Appium Inspector app and provide the appium server details and Desired Capabilities.

![image](https://github.com/user-attachments/assets/4597e6da-822b-4b18-98c3-0cdf7f91e37b)

3) Click on Start session which will start the appium inspector with layout shown below.

![image](https://github.com/user-attachments/assets/fb9f6e17-6cad-4090-9dbb-a2ad665cb1da)


## :pushpin: Inspecting Element for mobile web browser

```
Type url `chrome://inspect/#devices` in the desktop chrome browser and start inspecting element
```

## :pushpin: Launching Android Emulator Automatically

Add below lines in the Desired capabilities

```
capability.setCapability(AndroidMobileCapabilityType.AVD, "ArjitPhone");
capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, "180000");
```

## :pushpin: Auto Discovery of compatible ChromeDriver

Start appium server using command `appium --allow-insecure chromedriver_autodownload`

## :pushpin: Auto download of compatible ChromeDriver programmatically

Add below line in the `AppiumServiceBuilder`

```
AppiumServiceBuilder builder = new AppiumServiceBuilder();
builder.withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload");
```

## :pushpin: Start Appium server programmatically

Use `AppiumServiceBuilder` and `AppiumDriverLocalService` to start the server programmatically Set environment
variable `APPIUM_HOME = <path to npm folder>\node_modules\appium\build\lib` where `main.js` file is present

## :pushpin: Key Features

:point_right: Supports Android and iOS Real Devices and Emulators.

:point_right: Ability to start and stop the appium server on run-time. Configurable through `config.properties`

:point_right: Supports capturing appium server logs on run-time.

:point_right: Page object model design.

:point_right: Supports capturing screenshots for failed steps

:point_right: Supports utilities to read test data from json file and provides data to each test based on the test
name.

## :pushpin: Running tests through Maven

:point_right: Run test using command `mvn test -Dgroups=<provide the group tag name to execute>`

## :pushpin: Running tests through testng xml

:point_right: Create or Select the required testng xml -> Right click and select Run

## :pushpin: Report (Extent reports)
![image](https://github.com/user-attachments/assets/941139b2-b5cd-4d79-b28c-736f1284a221)

![image](https://github.com/user-attachments/assets/dc625d0f-2075-4133-8c5e-c6ba101df57d)


