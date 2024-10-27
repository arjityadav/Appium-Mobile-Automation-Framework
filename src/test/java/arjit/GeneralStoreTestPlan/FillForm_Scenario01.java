package arjit.GeneralStoreTestPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.time.Duration;

public class FillForm_Scenario01 extends InstanceManager_GeneralStore{
	
	@Test
	public void TC01_FillForm_Negative() {
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String errorToastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(errorToastMessage, "Please enter your name");
	}
	
	@Test
	public void TC02_FillForm_Positive() {
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		scrollToElement("Argentina");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		Assert.assertEquals(driver.findElement(By.id("android:id/text1")).getText(),"Argentina");
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Arjit");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
	
	@Test
	public void TC03_AddToCart() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\" and @text='Products']")));
		scrollToElement("Converse All Star");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Converse All Star']/parent::*//android.widget.TextView[@text='ADD TO CART']")).click();
		Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText(), "1");
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\" and @text='Cart']")));
		Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText(), "Converse All Star");
		WebElement onCartScreenPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));
		Assert.assertTrue(onCartScreenPrice.isDisplayed());
		Assert.assertEquals(onCartScreenPrice.getText(), "$ 55.0");
	}
	
	@Test
	public void TC04_TotalAmountInCart() throws InterruptedException {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\" and @text='Products']")));
		scrollToElement("Jordan 6 Rings");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan 6 Rings']/parent::*//android.widget.TextView[@text='ADD TO CART']")).click();
		Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText(), "2");
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\" and @text='Cart']")));
		WebElement onCartScreenPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));
		Assert.assertTrue(onCartScreenPrice.isDisplayed());
		Assert.assertEquals(onCartScreenPrice.getText(), "$ 220.0");
	}
}
