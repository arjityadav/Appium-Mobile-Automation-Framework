package arjityadav.appium.constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonConstants {

    // Timeout settings
    private static final int IMPLICIT_WAIT_TIME = 10;
    private static final int PAGE_LOAD_TIMEOUT = 10;

    // Timestamp for filenames
    private static final String DATE_TIME_STAMP = new SimpleDateFormat("ddMMyyyy_hhmmss").format(new Date());

    // Directory paths
    public static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    private static final String RESOURCES_FILE_PATH = PROJECT_DIRECTORY + "\\src\\test\\java\\resources";
    private static final String CONFIG_FILE_PATH = RESOURCES_FILE_PATH + "\\config.properties";
    private static final String DATA_FILE_PATH = RESOURCES_FILE_PATH + "\\testData.json";
    private static final String CURRENT_DATA_FILE_PATH = RESOURCES_FILE_PATH + "\\properties\\currentData.properties";
    private static final String SCREENSHOT_FILE_PATH = PROJECT_DIRECTORY + "\\reports\\screenshots\\";
    private static final String EXTENT_REPORT_FILE_PATH = PROJECT_DIRECTORY + "\\results\\reports\\";
    private static final String SCREENSHOT_FILE_NAME = "Screenshot_" + DATE_TIME_STAMP + ".png";

    /**
     * Returns the file path for storing screenshots.
     *
     * @return the screenshot file path
     */
    public static String getScreenshotFilePath() {
        return SCREENSHOT_FILE_PATH;
    }

    /**
     * Returns the file path for storing Extent reports.
     *
     * @return the Extent report file path
     */
    public static String getExtentReportFilePath() {
        return EXTENT_REPORT_FILE_PATH;
    }

    /**
     * Returns the file path for accessing the test data properties file.
     *
     * @return the test data file path
     */
    public static String getDataFilePath() {
        return DATA_FILE_PATH;
    }

    /**
     * Returns the file path for accessing the current data properties file.
     *
     * @return the current data file path
     */
    public static String getCurrentDataFilePath() {
        return CURRENT_DATA_FILE_PATH;
    }

    /**
     * Returns the file path for accessing the configuration properties file.
     *
     * @return the config file path
     */
    public static String getConfigFilePath() {
        return CONFIG_FILE_PATH;
    }

    /**
     * Returns a timestamp string used for file names.
     *
     * @return the current date and time stamp
     */
    public static String getDateTimeStamp() {
        return DATE_TIME_STAMP;
    }

    /**
     * Returns the file name for a screenshot, including a timestamp.
     *
     * @return the screenshot file name
     */
    public static String getScreenshotFileName() {
        return SCREENSHOT_FILE_NAME;
    }

    /**
     * Returns the implicit wait time for WebDriver.
     *
     * @return the implicit wait time in seconds
     */
    public static int getImplicitWaitTime() {
        return IMPLICIT_WAIT_TIME;
    }

    /**
     * Returns the page load timeout for WebDriver.
     *
     * @return the page load timeout in seconds
     */
    public static int getPageLoadTimeout() {
        return PAGE_LOAD_TIMEOUT;
    }
}