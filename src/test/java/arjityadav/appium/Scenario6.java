package arjityadav.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Scenario6 extends BaseTest{
	
	@Test
	public void TC_01_rotateLandscapeGesture() throws MalformedURLException, URISyntaxException, InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		switchToLandscapeMode();
		driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"WiFi settings\"]")).click();
		String wifiSetting = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(wifiSetting, "WiFi settings");
		setClipBoardText("Arjit's Wifi");
		driver.findElement(By.id("android:id/edit")).sendKeys(getClipBoardText());
		driver.findElement(By.id("android:id/button1")).click();
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
	}
}
