package arjit.GeneralStoreTestPlan.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CommonActions {
	
	static AndroidDriver driver;
	
	public CommonActions(AndroidDriver driver) {
		CommonActions.driver=driver;
	}

	public static void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "duration", 2000
			));
	}
	
	public static void scrollToElement(String elementText) {
	    driver.findElement(AppiumBy.androidUIAutomator(
	        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"));"
	    ));
	}
	
	public static void swipeAction(WebElement ele, String swipeDirection) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "direction", swipeDirection,
			    "percent", 0.25
			));
	}
	
	public static void dragAndDropAction(WebElement ele, Integer xMovement, Integer yMovement) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "endX", xMovement,
			    "endY", yMovement
			));
	}
	
	public static void switchToLandscapeMode() {
		DeviceRotation landscape = new DeviceRotation(0,0,90);
		driver.rotate(landscape);
	}
	
	public static void setClipBoardText(String text) {
		driver.setClipboardText(text);
	}
	
	public static String getClipBoardText() {
		return driver.getClipboardText();
	}
	
	public static void startActivityDirectly(String activityFullName) {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent", activityFullName
			));
	}
	
	public static String getToastErrorMessage() {
		return driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	}
}
