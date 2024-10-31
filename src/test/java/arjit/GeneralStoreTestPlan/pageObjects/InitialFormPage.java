package arjit.GeneralStoreTestPlan.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import arjit.GeneralStoreTestPlan.utils.CommonActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class InitialFormPage extends CommonActions{
	
	AndroidDriver driver;
	
	public InitialFormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letShopBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelected;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleRadioBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadioBtn;
	
	public void clickLetsShopButton() {
		letShopBtn.click();
	}
	
	public void updateCountry(String country) {
		countryDropdown.click();
		CommonActions.scrollToElement("Argentina");
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
		Assert.assertEquals(countrySelected.getText(),country);
	}
	
	public void setName(String name) {
		nameField.click();
		nameField.sendKeys(name);
	}
	
	public void selectGender(String gender) {
		if(gender.equalsIgnoreCase("male")) {
			maleRadioBtn.click();
		}else {
			femaleRadioBtn.click();
		}
		
		
	}
}
