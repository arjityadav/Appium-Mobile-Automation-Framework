package arjityadav.sauceLabs.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;

public class CommonUtils {
	
	// windows command to find activity full name: adb shell dumpsys window | findstr /R "mCurrentFocus"
	public static void startActivityDirectly(String activityFullName, AndroidDriver driver) {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent", activityFullName
			));
	}
	
	@SuppressWarnings("deprecation")
	public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException{
		
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath));
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		return data;
	}
	
}
