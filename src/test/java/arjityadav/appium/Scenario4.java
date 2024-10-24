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

public class Scenario4 extends BaseTest{
	
	@Test
	public void TC_01_SwipeGesture() throws MalformedURLException, URISyntaxException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		scrollToElement("Gallery");
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		WebElement img1 = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(img1.getAttribute("focusable"), "true");
		
		//swipe
		swipeAction(img1,"left");
		Assert.assertEquals(img1.getAttribute("focusable"), "false");
	}
}
