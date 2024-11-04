package arjityadav.appium.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class CommonUtils {

	// windows command to find activity full name: adb shell dumpsys window |
	// findstr /R "mCurrentFocus"
	public void startActivityDirectly(String activityFullName, AndroidDriver driver) {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity",
				ImmutableMap.of("intent", activityFullName));
	}

	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath));

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int portNumber) {

		String nodePath = System.getenv("NODE_HOME");
		if (nodePath == null) {
			nodePath = "C:\\Program Files\\nodejs";
		}
		String path = nodePath + "\\node.exe";

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress).usingPort(portNumber)
				.withAppiumJS(new File(System.getProperty("user.home")
						+ "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.usingDriverExecutable(new File(path)).withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withArgument(GeneralServerFlag.LOG_LEVEL, "debug");

		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
		service.start();

		return service;
	}

	public void longPressAction(WebElement ele, AndroidDriver driver) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}

	public void scrollToElement(String elementText, AndroidDriver driver) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"));"));
	}

	public void swipeAction(WebElement ele, String swipeDirection, AndroidDriver driver) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) ele).getId(), "direction", swipeDirection, "percent", 0.25));
	}

	public void dragAndDropAction(WebElement ele, Integer xMovement, Integer yMovement, AndroidDriver driver) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", xMovement, "endY", yMovement));
	}

	public void switchToLandscapeMode(AndroidDriver driver) {
		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);
	}

	public void setClipBoardText(String text, AndroidDriver driver) {
		driver.setClipboardText(text);
	}

	public String getClipBoardText(AndroidDriver driver) {
		return driver.getClipboardText();
	}

	public String getToastErrorMessage(AndroidDriver driver) {
		return driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	}

	public void goBack(AndroidDriver driver) {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	public void checkAndStartEmulator(String emulatorName, String emulatorID) throws InterruptedException {
		try {
			while (!isEmulatorRunning(emulatorID)) {
				System.out.println("Emulator not running. Starting emulator...");
				startEmulator(emulatorName);
				// Wait for a few seconds to let the emulator start
				Thread.sleep(60000); // Adjust the sleep time as needed
			}
			System.out.println("Emulator is running.");
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isEmulatorRunning(String emulatorID) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "adb devices");
		Process process = builder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.contains(emulatorID)) {
				return true; // Emulator is already running
			}
		}
		return false; // Emulator not found in running devices
	}

	private void startEmulator(String emulatorName) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"cd /d C:\\Users\\" + System.getProperty("user.name")
						+ "\\AppData\\Local\\Android\\sdk\\emulator && emulator @" + emulatorName);
		builder.start();
	}

}
