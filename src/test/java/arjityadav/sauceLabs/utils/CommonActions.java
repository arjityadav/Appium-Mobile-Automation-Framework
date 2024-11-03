package arjityadav.sauceLabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CommonActions {

	public static void longPressAction(WebElement ele, AndroidDriver driver) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "duration", 2000
			));
	}
	
	public static void scrollToElement(String elementText, AndroidDriver driver) {
	    driver.findElement(AppiumBy.androidUIAutomator(
	        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"));"
	    ));
	}
	
	public static void swipeAction(WebElement ele, String swipeDirection, AndroidDriver driver) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "direction", swipeDirection,
			    "percent", 0.25
			));
	}
	
	public static void dragAndDropAction(WebElement ele, Integer xMovement, Integer yMovement, AndroidDriver driver) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), 
			    "endX", xMovement,
			    "endY", yMovement
			));
	}
	
	public static void switchToLandscapeMode(AndroidDriver driver) {
		DeviceRotation landscape = new DeviceRotation(0,0,90);
		driver.rotate(landscape);
	}
	
	public static void setClipBoardText(String text, AndroidDriver driver) {
		driver.setClipboardText(text);
	}
	
	public static String getClipBoardText(AndroidDriver driver) {
		return driver.getClipboardText();
	}
	
	public static String getToastErrorMessage(AndroidDriver driver) {
		return driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	}
	
	public static void goBack(AndroidDriver driver) {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
}
