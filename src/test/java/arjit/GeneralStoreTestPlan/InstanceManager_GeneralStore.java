package arjit.GeneralStoreTestPlan;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class InstanceManager_GeneralStore {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass(alwaysRun = true)
	public void mobileTestConfiguration() throws MalformedURLException, URISyntaxException {

		// Appium server start automatically
		
		AppiumServiceBuilder builder = new AppiumServiceBuilder ();
        builder.withIPAddress ("127.0.0.1")
            .usingPort (4723)
            .withAppiumJS (
                new File ("C:\\Users\\yadav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
            .usingDriverExecutable (new File ("C:\\Program Files\\nodejs\\node.exe"))
            .withArgument (GeneralServerFlag.SESSION_OVERRIDE)
            .withArgument (GeneralServerFlag.LOG_LEVEL, "debug");

        service = AppiumDriverLocalService.buildService (builder);
        service.start ();

		// Android UI Automator Initialize
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("ArjitPhone");
		options.setApp("E:\\AppiumAutomation\\Appium-Mobile-Automation-Framework\\src\\test\\java\\resources\\General-Store.apk");

		// AndroidDriver Initialize
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// stop server
		driver.quit();
		service.stop();
	}
	
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "duration", 2000
			));
	}
	
	public void scrollToElement(String elementText) {
	    driver.findElement(AppiumBy.androidUIAutomator(
	        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"));"
	    ));
	}
	
	public void swipeAction(WebElement ele, String swipeDirection) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "direction", swipeDirection,
			    "percent", 0.25
			));
	}
	
	public void dragAndDropAction(WebElement ele, Integer xMovement, Integer yMovement) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "endX", xMovement,
			    "endY", yMovement
			));
	}
	
	public void switchToLandscapeMode() {
		DeviceRotation landscape = new DeviceRotation(0,0,90);
		driver.rotate(landscape);
	}
	
	public void setClipBoardText(String text) {
		driver.setClipboardText(text);
	}
	
	public String getClipBoardText() {
		return driver.getClipboardText();
	}
	
	public void startActivityDirectly(String activityFullName) {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent", activityFullName
			));
	}
}
