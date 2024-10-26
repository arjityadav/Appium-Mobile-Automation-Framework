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

public class Scenario5 extends BaseTest{
	
	@Test
	public void TC_01_dragAndDropGesture() throws MalformedURLException, URISyntaxException, InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement redCircle1 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		longPressAction(redCircle1);
		dragAndDropAction(redCircle1,651,579);
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText(), "Dropped!");
	}
}
