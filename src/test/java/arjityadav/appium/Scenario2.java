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

public class Scenario2 extends BaseTest{
	
	@Test
	public void TC_01_LongPressGesture() throws MalformedURLException, URISyntaxException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement peopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
		longPressAction(peopleNames);
		WebElement sampleMenuElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sample menu\"]"));
		String sampleMenu = sampleMenuElement.getText();
		Assert.assertEquals(sampleMenu, "Sample menu");
		Assert.assertTrue(sampleMenuElement.isDisplayed());
	}
}
