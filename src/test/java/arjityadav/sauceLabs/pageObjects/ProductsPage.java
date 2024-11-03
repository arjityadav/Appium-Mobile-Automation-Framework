package arjityadav.sauceLabs.pageObjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import arjityadav.sauceLabs.utils.CommonActions;
import arjityadav.sauceLabs.utils.CommonUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage extends CommonActions{
	
	AndroidDriver driver;
	
	public ProductsPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
	private WebElement productsPageTitle;
	
	public Boolean onProductsPage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(productsPageTitle));
		return productsPageTitle.isDisplayed();
	}
	
	public void verifyProductsOnPage() {
		List<WebElement> productTitles = driver.findElements(By.xpath("//android.widget.TextView[@content-desc=\"test-Item title\"]"));
		Assert.assertEquals(productTitles.get(0).getText(),"Sauce Labs Backpack");
		Assert.assertEquals(productTitles.get(1).getText(),"Sauce Labs Bike Light");
	}
	
	public void clickOnAddToCard(String productName) {
		CommonActions.scrollToElement(productName, driver);
		WebElement addToCardBtn = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text='"+productName+"']//..//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]"));
		addToCardBtn.click();
	}
	
	public void clickOnRemoveFromCart(String productName) {
		CommonActions.scrollToElement(productName, driver);
		WebElement removeFromCartBtn = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text='"+productName+"']//..//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]"));
		removeFromCartBtn.click();
	}
	
	public void verifyCartWithNumber(String number) {
		WebElement numberOfItemsInCart = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]//android.widget.TextView"));
		Assert.assertEquals(numberOfItemsInCart.getText(),number);
	}
	
	public void clickOnCartIcon() {
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]")).click();
	}
	
	public void clickOnCheckoutBtn() {
		CommonActions.scrollToElement("CHECKOUT", driver);
		driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT")).click();
	}
	
	public void fillCheckoutInfo(String firstName, String lastName, String zipCode) {
		driver.findElement(AppiumBy.accessibilityId("test-First Name")).sendKeys(firstName);
		driver.findElement(AppiumBy.accessibilityId("test-Last Name")).sendKeys(lastName);
		driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code")).sendKeys(zipCode);
	}
	
	public void clickOnContinueBtn() {
		driver.findElement(AppiumBy.accessibilityId("test-CONTINUE")).click();
	}
	
	public void clickOnFinishBtn() {
		driver.findElement(AppiumBy.accessibilityId("test-FINISH")).click();
	}
	
	public void clickOnBackToHomeBtn() {
		driver.findElement(AppiumBy.accessibilityId("test-BACK HOME")).click();
	}
}
