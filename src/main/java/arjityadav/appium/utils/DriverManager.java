package arjityadav.appium.utils;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

    // ThreadLocal variables for WebDriver and SitesFixture to ensure thread safety
    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    /**
     * Retrieves the WebDriver instance associated with the current thread.
     *
     * @return the WebDriver instance, or null if none is set
     */
    public static AndroidDriver getDriver() {
        return driver.get();
    }

    /**
     * Sets the WebDriver instance for the current thread.
     *
     * @param driverParam the WebDriver instance to be associated with the current thread
     */
    public static void setDriver(AndroidDriver driverParam) {
        driver.set(driverParam);
    }

    /**
     * Removes the WebDriver instance associated with the current thread, if any.
     */
    public static void removeDriver() {
        driver.remove();
    }
}