package arjityadav.appium.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import arjityadav.appium.reports.ExtentReportManager;
import arjityadav.appium.utils.CommonUtils;
import io.appium.java_client.AppiumDriver;

public class ReportListeners extends CommonUtils implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    AppiumDriver driver;

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.setupExtentReport();
        System.out.println("Report initialized.");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushExtentReport();
        System.out.println("Extent Report flushed.");
        openReportInBrowser(ExtentReportManager.extentReportFile);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String className = result.getMethod().getRealClass().getSimpleName();
        ExtentTest test = ExtentReportManager.createTest(className + "\n>> " + methodName);
        extentTest.set(test);
        test.log(Status.INFO, "Test case '" + className + ">>" + methodName + "' execution started.");
        System.out.println("Execution of '" + className + ">>" + methodName + "' test has started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.PASS, "Test case '" + result.getMethod().getMethodName() + "' execution passed.");
        System.out.println("Test case '" + result.getMethod().getMethodName() + "' execution passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	extentTest.get().log(Status.FAIL, result.getThrowable());
    	try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.SKIP, "Test case '" + result.getMethod().getMethodName() + "' execution skipped.");
        System.out.println("Test case '" + result.getMethod().getMethodName() + "' execution skipped.");
    }

    /**
     * Starts a new test in the Extent Report.
     *
     * @param testName the name of the test to start
     */
    public static void startTest(String testName) {
        ExtentTest test = ExtentReportManager.createTest(testName);
        extentTest.set(test);
        test.log(Status.INFO, "Test case '" + testName + "' execution started.");
        System.out.println("Execution of '" + testName + "' test has started.");
    }

    /**
     * Ends the current test in the Extent Report.
     */
    public static void endTest() {
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.INFO, "Test case execution ended.");
            System.out.println("Execution of test case ended.");
        }
    }

    /**
     * Opens the generated report in the default web browser.
     *
     * @param reportFilePath the path to the report file
     */
    private void openReportInBrowser(String reportFilePath) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File reportFile = new File(reportFilePath);
            try {
                desktop.open(reportFile);
                System.out.println("Report opened in default web browser.");
            } catch (IOException e) {
                System.out.println("Error opening report file: " + e.getMessage());
            }
        } else {
            System.out.println("Desktop is not supported, unable to open report automatically.");
        }
    }
}