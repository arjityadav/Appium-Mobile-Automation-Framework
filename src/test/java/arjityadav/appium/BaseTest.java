package arjityadav.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass(alwaysRun = true)
	public void mobileTestConfiguration() throws MalformedURLException, URISyntaxException {

		// Appium server start automatically
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\yadav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		// Android UI Automator Initialize
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("TestDevice");
		options.setApp(
				"C:\\Users\\yadav\\eclipse-workspace-latest\\appium\\src\\test\\java\\arjityadav\\appium\\resources\\ApiDemos-debug.apk");

		// AndroidDriver Initialize
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// stop server
		driver.quit();
		service.stop();
	}
}
