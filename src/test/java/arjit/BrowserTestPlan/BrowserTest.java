package arjit.BrowserTestPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;


public class BrowserTest extends InstanceManager_Browser{
	
	@Test
	public void TC01_AmazonSearchAndOpenProductPage() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.amazon.in");
		System.out.println("Title: "+driver.getTitle());
		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-search-keywords")));
		searchBox.sendKeys("Sony PlayStation®5 Digital Edition (slim)");
		searchBox.sendKeys(Keys.ENTER);
		WebElement ps5 = driver.findElement(By.xpath("//span[text()='Sony PlayStation®5 Digital Edition (slim)']"));
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)", ps5);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@alt='Sony PlayStation®5 Digital Edition (slim)']")).click();
		Thread.sleep(2000);
	}
}
