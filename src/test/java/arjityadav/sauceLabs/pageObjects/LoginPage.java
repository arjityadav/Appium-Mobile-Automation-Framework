package arjityadav.sauceLabs.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import arjityadav.appium.utils.CommonUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends CommonUtils{
	
	AndroidDriver driver;
	
	public LoginPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]")
	private WebElement usernameField;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
	private WebElement passwordField;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")
	private WebElement loginBtn;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]")
	private WebElement errorMessage;
	
	
	// Can't use activity switch as all activities are marked as MainActivity
//	public void setActivity() {
//		CommonUtils.startActivityDirectly("com.swaglabsmobileapp/com.swaglabsmobileapp.MainActivity", driver);
//		
//	}
	
	public void enterLoginDetails(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginBtn.click();
	}
	
	public void loginToSauceLabs() {
		usernameField.sendKeys("standard_user");
		passwordField.sendKeys("secret_sauce");
		loginBtn.click();
	}
	
	public void clearFieldsOnLogin() {
		usernameField.clear();
		passwordField.clear();
	}
	
	public Boolean checkForErrorMessage() {
		return errorMessage.isDisplayed();
	}
	
}
