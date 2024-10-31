package arjit.GeneralStoreTestPlan.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import arjit.GeneralStoreTestPlan.base.BaseClass;
import arjit.GeneralStoreTestPlan.pageObjects.CartPage;
import arjit.GeneralStoreTestPlan.pageObjects.InitialFormPage;
import arjit.GeneralStoreTestPlan.pageObjects.ProductsListingPage;
import arjit.GeneralStoreTestPlan.utils.CommonActions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.time.Duration;
import java.util.Set;


public class FillForm_Scenario01 extends BaseClass{
	
	@Test
	public void TC01_FillForm_Negative() {
		InitialFormPage formPage = new InitialFormPage(driver);
		formPage.clickLetsShopButton();
		Assert.assertEquals(CommonActions.getToastErrorMessage(), "Please enter your name");
	}
	
	@Test
	public void TC02_FillForm_Positive() {
		InitialFormPage formPage = new InitialFormPage(driver);
		formPage.updateCountry("Argentina");
		formPage.setName("Arjit");
		driver.hideKeyboard();
		formPage.selectGender("female");
		formPage.selectGender("male");
		formPage.clickLetsShopButton();
	}
	
	@Test
	public void TC03_AddToCart() throws InterruptedException {
		String productToAddToCart = "Converse All Star";
		ProductsListingPage productPage = new ProductsListingPage(driver);
		productPage.verifyProductsPage();
		CommonActions.scrollToElement(productToAddToCart);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Converse All Star']/parent::*//android.widget.TextView[@text='ADD TO CART']")).click();
		Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText(), "1");
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		CartPage cartPage = new CartPage(driver);
		cartPage.verifyCartPage();
		Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText(), productToAddToCart);
		WebElement onCartScreenPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));
		Assert.assertTrue(onCartScreenPrice.isDisplayed());
		Assert.assertEquals(onCartScreenPrice.getText(), "$ 55.0");
	}
	
	@Test
	public void TC04_TotalAmountInCart() throws InterruptedException {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\" and @text='Products']")));
		CommonActions.scrollToElement("Jordan 6 Rings");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan 6 Rings']/parent::*//android.widget.TextView[@text='ADD TO CART']")).click();
		Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText(), "2");
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\" and @text='Cart']")));
		WebElement onCartScreenPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));
		Assert.assertTrue(onCartScreenPrice.isDisplayed());
		Assert.assertEquals(onCartScreenPrice.getText(), "$ 220.0");
	}
	
	@Test
	public void TC05_OpenTermsAndConditionsLongPress() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement termsText = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		CommonActions.longPressAction(termsText);
		WebElement termsPopupHeading = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.androidsample.generalstore:id/alertTitle")));
		Assert.assertEquals(termsPopupHeading.getText(),"Terms Of Conditions");
		driver.findElement(By.id("android:id/button1")).click();
	}
	
	@Test
	public void TC06_BrowserLevelTesting() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(2000);
		Set<String> contexts = driver.getContextHandles();
		for(String contextName:contexts) {
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("Arjit Yadav");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
	}
}
