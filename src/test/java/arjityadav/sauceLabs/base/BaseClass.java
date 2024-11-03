package arjityadav.sauceLabs.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import arjityadav.sauceLabs.pageObjects.LoginPage;
import arjityadav.sauceLabs.pageObjects.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseClass {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public LoginPage loginPage;
	public ProductsPage productsPage;
	public String currentDirectory = System.getProperty("user.dir");
	
	@BeforeClass(alwaysRun = true)
	public void mobileTestConfiguration() throws MalformedURLException, URISyntaxException {
		
		// Appium server start automatically
		
		AppiumServiceBuilder builder = new AppiumServiceBuilder ();
        builder.withIPAddress ("127.0.0.1")
            .usingPort (4723)
            .withAppiumJS (
                new File ("C:\\Users\\yadav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
            .usingDriverExecutable (new File ("C:\\Program Files\\nodejs\\node.exe"))
            .withArgument (GeneralServerFlag.SESSION_OVERRIDE)
            .withArgument (GeneralServerFlag.LOG_LEVEL, "debug");

        service = AppiumDriverLocalService.buildService (builder);
        service.start ();

		// Android UI Automator Initialize
		UiAutomator2Options options = new UiAutomator2Options();
		options.setChromedriverExecutable(currentDirectory+"\\driver\\chromedriver.exe");
		options.setUdid("emulator-5554");
		options.setApp(currentDirectory+"\\src\\test\\java\\resources\\SauceLabsApp.apk");
		options.setAppWaitActivity("com.swaglabsmobileapp.MainActivity");
		options.setFullReset(true);

		// AndroidDriver Initialize
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
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
