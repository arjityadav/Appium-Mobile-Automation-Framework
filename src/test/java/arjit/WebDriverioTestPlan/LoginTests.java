package arjit.WebDriverioTestPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import io.appium.java_client.AppiumBy;

public class LoginTests extends InstanceManager{
	
	@Test(priority = 1)
	public void TestValidLogin() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Login")).click();
		driver.findElement(AppiumBy.accessibilityId("input-email")).sendKeys("testuser@example.com");
		driver.findElement(AppiumBy.accessibilityId("input-password")).sendKeys("ValidPass@123");
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"LOGIN\"]")).click();
		Thread.sleep(2000);
		WebElement successText = driver.findElement(By.xpath("You are logged in!"));
		Assert.assertTrue(successText.isDisplayed());
		
		driver.findElement(AppiumBy.accessibilityId("android:id/button1")).click();
	}
	
	@Test(priority = 2)
	public void TestInvalidEmailFormat() {
		driver.findElement(AppiumBy.accessibilityId("input-email")).clear();
		driver.findElement(AppiumBy.accessibilityId("input-password")).clear();
		driver.findElement(AppiumBy.accessibilityId("input-email")).sendKeys("invalidemail");
		driver.findElement(AppiumBy.accessibilityId("input-password")).sendKeys("ValidPass@123");
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"LOGIN\"]")).click();
		
		WebElement validationText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Please enter a valid email address\"]"));
		Assert.assertTrue(validationText.isDisplayed());
	}
	
	@Test(priority = 3)
	public void TestIncorrectPassword() {
		driver.findElement(AppiumBy.accessibilityId("input-email")).clear();
		driver.findElement(AppiumBy.accessibilityId("input-password")).clear();
		driver.findElement(AppiumBy.accessibilityId("input-email")).sendKeys("testuser@example.com");
		driver.findElement(AppiumBy.accessibilityId("input-password")).sendKeys("invalid");
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"LOGIN\"]")).click();
		
		WebElement validationText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]"));
		Assert.assertTrue(validationText.isDisplayed());
	}
	
	
	
}
