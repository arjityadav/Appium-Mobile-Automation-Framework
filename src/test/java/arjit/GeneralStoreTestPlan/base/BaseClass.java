package arjit.GeneralStoreTestPlan.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseClass {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public String currentDirectory = System.getProperty("user.dir");
	
	@BeforeClass(alwaysRun = true)
	public void mobileTestConfiguration() throws MalformedURLException, URISyntaxException {
		
		// Appium server start automatically
		
		AppiumServiceBuilder builder = new AppiumServiceBuilder ();
        builder.withIPAddress ("127.0.0.1")
            .usingPort (4725)
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
		
		options.setApp(currentDirectory+"\\src\\test\\java\\resources\\General-Store.apk");

		// AndroidDriver Initialize
		driver = new AndroidDriver(new URI("http://127.0.0.1:4725").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// stop server
		driver.quit();
		service.stop();
	}
}
