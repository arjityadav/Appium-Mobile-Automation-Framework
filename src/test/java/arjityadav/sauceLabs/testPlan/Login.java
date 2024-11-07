package arjityadav.sauceLabs.testPlan;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import arjityadav.sauceLabs.base.BaseClass;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Login extends BaseClass{

	@Test(groups = "smoke")
	public void TC01_Invalid_Username_and_Password() {
		loginPage.enterLoginDetails("wrong", "blahblah");
		Assert.assertTrue(loginPage.checkForErrorMessage());
		loginPage.clearFieldsOnLogin();
	}
	
	@Test(dataProvider = "getData")
	public void TC02_Valid_Username_and_Password(HashMap<String, String> input) throws InterruptedException {
		loginPage.enterLoginDetails(input.get("username"), input.get("password"));
		Assert.assertTrue(productsPage.onProductsPage());
	}
	
	//this test is meant to fail for screenshot check
	@Test(groups = "smoke")
	public void TC03_Valid_Username_and_Invalid_Password() {
		loginPage.enterLoginDetails("standard_user", "helloworld");
		Assert.assertTrue(productsPage.onProductsPage());
	}
	
	@DataProvider(name = "getData")
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\testData.json");
		return new Object[][] { {data.get(0)}};
	}
}
