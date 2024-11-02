package arjit.GeneralStoreTestPlan.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
		System.out.println("Activity Name: "+driver.currentActivity());
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
	
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"Arjit Yadav", "Male", "Australia"}};
	}
}
