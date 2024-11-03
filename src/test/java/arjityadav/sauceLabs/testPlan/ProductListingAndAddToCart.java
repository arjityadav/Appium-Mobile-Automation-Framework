package arjityadav.sauceLabs.testPlan;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import arjityadav.sauceLabs.base.BaseClass;
import arjityadav.sauceLabs.utils.CommonActions;

public class ProductListingAndAddToCart extends BaseClass{
	
	@Test
	public void TC01_Verify_Product_Listing_Display() throws InterruptedException {
		loginPage.loginToSauceLabs();
		productsPage.verifyProductsOnPage();
	}
	
	@Test
	public void TC02_Verify_Add_To_Cart() throws InterruptedException {
		productsPage.clickOnAddToCard("Sauce Labs Backpack");
		productsPage.clickOnAddToCard("Sauce Labs Bike Light");
		productsPage.clickOnAddToCard("Sauce Labs Bolt T-Shirt");
		productsPage.verifyCartWithNumber("3");
		productsPage.clickOnRemoveFromCart("Sauce Labs Bolt T-Shirt");
		productsPage.verifyCartWithNumber("2");
	}
	
	@Test
	public void TC03_Verify_Cart_Items() throws InterruptedException {
		productsPage.clickOnCartIcon();
		productsPage.clickOnCheckoutBtn();
	}
	
	@Test
	public void TC04_Checkout_Information() throws InterruptedException {
		productsPage.clickOnCartIcon();
		productsPage.clickOnCheckoutBtn();
		productsPage.fillCheckoutInfo("Arjit", "Yadav", "229012");
		productsPage.clickOnContinueBtn();
		CommonActions.scrollToElement("FINISH", driver);
		Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text=\"Total: $43.18\"]")).isDisplayed());
		productsPage.clickOnFinishBtn();
	}
	
	@Test
	public void TC05_Order_Successfully_Placed() throws InterruptedException {
		Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]")).isDisplayed());
		productsPage.clickOnBackToHomeBtn();
		productsPage.onProductsPage();
	}
}
