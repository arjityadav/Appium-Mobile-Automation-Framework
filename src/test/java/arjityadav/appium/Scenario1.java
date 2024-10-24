package arjityadav.appium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import io.appium.java_client.AppiumBy;

public class Scenario1 extends BaseTest{
	
	@Test
	public void TC01_SetWifiName() throws MalformedURLException, URISyntaxException {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"WiFi settings\"]")).click();
		String wifiSetting = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(wifiSetting, "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("Arjit's Wifi");
		driver.findElement(By.id("android:id/button1")).click();
	}
}
