package arjit.ApiDemosAppTestPlan;

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

public class Scenario3 extends BaseTest{
	
	@Test
	public void TC_01_ScrollGesture() throws MalformedURLException, URISyntaxException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		scrollToElement("WebView");
		driver.findElement(AppiumBy.accessibilityId("WebView")).click();
	}
}
