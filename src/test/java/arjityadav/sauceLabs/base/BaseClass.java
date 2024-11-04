package arjityadav.sauceLabs.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import arjityadav.appium.utils.CommonUtils;
import arjityadav.sauceLabs.pageObjects.LoginPage;
import arjityadav.sauceLabs.pageObjects.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass extends CommonUtils{

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public LoginPage loginPage;
	public ProductsPage productsPage;
	public String currentDirectory = System.getProperty("user.dir");
	
	@BeforeClass(alwaysRun = true)
	public void mobileTestConfiguration() throws URISyntaxException, InterruptedException, IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(currentDirectory+"\\src\\test\\java\\resources\\config.properties");
		prop.load(fis);
		
		String ipAddress = prop.getProperty("IPADDRESS");
		String port = prop.getProperty("PORT");
		String deviceName = prop.getProperty("DEVICE_NAME");
		String deviceId = prop.getProperty("DEVICE_ID");
		
		// AVD Device start automatically
		checkAndStartEmulator(deviceName, deviceId);
		
		// Appium server start automatically
		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		// Android UI Automator Initialize
		UiAutomator2Options options = new UiAutomator2Options();
		options.setChromedriverExecutable(currentDirectory+"\\driver\\chromedriver.exe");
		options.setUdid("emulator-5554");
		options.setApp(currentDirectory+"\\src\\test\\java\\resources\\SauceLabsApp.apk");
		options.setAppWaitActivity(prop.getProperty("APP_WAIT_ACTIVITY"));
		options.setAppWaitForLaunch(false);

		// AndroidDriver Initialize
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		loginPage = new LoginPage(driver);
		productsPage = new ProductsPage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// stop server
		driver.quit();
		service.stop();
	}
}
