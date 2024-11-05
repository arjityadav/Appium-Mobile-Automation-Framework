package arjityadav.appium.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import arjityadav.appium.constants.CommonConstants;
import arjityadav.appium.utils.ConfigReader;

public class ExtentReportManager {

    // Static variables for ExtentReports instance, report file path, and suite start time
    private static ExtentReports extentReport;
    public static String extentReportFile;
    private static long suiteStartTime;

    /**
     * Sets up the Extent Report instance and configuration.
     *
     * @return the configured ExtentReports instance
     */
    public static ExtentReports setupExtentReport() {
        try {
            System.out.println("Setting up Extent Report...");

            // Generate the file path for the report
            String filePath = CommonConstants.getExtentReportFilePath();
            String fileName = "ExecutionReport_" + new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + ".html";
            extentReportFile = filePath + fileName;
            System.out.println("Report File Path: " + extentReportFile);
            
            File reportFile = new File(extentReportFile);
            if (!reportFile.exists()) {
                if (reportFile.createNewFile()) {
                    System.out.println("Report file created: " + extentReportFile);
                } else {
                    System.err.println("Failed to create report file: " + extentReportFile);
                }
            }

            // Initialize ExtentReports and configure the ExtentSparkReporter
            extentReport = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setReportName(ConfigReader.getValue("TEST_REPORT_NAME"));
            sparkReporter.config().setDocumentTitle(ConfigReader.getValue("TEST_REPORT_NAME"));
            sparkReporter.config().setTimeStampFormat("dd-MMM-yyyy hh:mm:ss a");

            // Attach the reporter and set system info
            extentReport.attachReporter(sparkReporter);
            extentReport.setSystemInfo("Application", ConfigReader.getValue("APPLICATION"));
            extentReport.setSystemInfo("Application URL", ConfigReader.getValue("OS"));
            extentReport.setSystemInfo("Tested By", ConfigReader.getValue("TESTED_BY"));
            System.out.println("Extent Report setup completed.");

            // Record the start time of the suite
            suiteStartTime = System.currentTimeMillis();

            return extentReport;
        } catch (Exception e) {
            System.err.println("Error occurred while setting up Extent Report: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the ExtentReports instance. If not already set up, it sets up a new instance.
     *
     * @return the ExtentReports instance
     */
    public static ExtentReports getExtentReport() {
        if (extentReport == null) {
            setupExtentReport();
        }
        return extentReport;
    }

    /**
     * Flushes the Extent Report, ensuring all information is written to the report.
     * Also calculates and logs the total execution time of the test suite.
     */
    public static void flushExtentReport() {
        if (extentReport != null) {
            long suiteEndTime = System.currentTimeMillis();
            long suiteExecutionTime = suiteEndTime - suiteStartTime;
            String totalTime = String.format("%02d:%02d:%02d",
                    (suiteExecutionTime / (1000 * 60 * 60)) % 24,
                    (suiteExecutionTime / (1000 * 60)) % 60,
                    (suiteExecutionTime / 1000) % 60);

            // Add the total execution time to the Extent report
            extentReport.setSystemInfo("Total Execution Time", totalTime);
            extentReport.flush();
        }
    }

    /**
     * Creates a new test in the Extent Report with the given name.
     *
     * @param testName the name of the test to create
     * @return the ExtentTest instance representing the created test
     */
    public static ExtentTest createTest(String testName) {
        if (extentReport == null) {
            setupExtentReport();
        }
        return extentReport.createTest(testName);
    }
}