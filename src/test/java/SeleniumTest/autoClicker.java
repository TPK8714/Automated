package SeleniumTest;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class autoClicker {

	private WebDriver driver;
	private static ExtentReports report;

	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport.html");
		htmlReport.config().setAutoCreateRelativePathMedia(true);
		report.attachReporter(htmlReport);
	}

	@Before
	public void init() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void clickCookies() {
		driver.get("https://orteil.dashnet.org/cookieclicker/");

		for (int i = 1; i < 50000; i++) {
			WebElement cookie = driver.findElement(By.cssSelector("#bigCookie"));
			cookie.click();
		}

		for (int i = 1; i < 6; i++) {
			WebElement cursorUpgrade = driver.findElement(By.cssSelector("#product0"));
			cursorUpgrade.click();

		}

		for (int i = 1; i < 6; i++) {
			WebElement nanUpgrade = driver.findElement(By.cssSelector("#product1"));
			nanUpgrade.click();

		}

		for (int i = 1; i < 3; i++) {
			WebElement farmUpgrade = driver.findElement(By.cssSelector("#product2"));
			farmUpgrade.click();

		}

		for (int i = 1; i < 2; i++) {
			WebElement mineUpgrade = driver.findElement(By.cssSelector("#product3"));
			mineUpgrade.click();
		}

		for (int i = 1; i < 2; i++) {
			WebElement factoryUpgrade = driver.findElement(By.cssSelector("#product4"));
			factoryUpgrade.click();
		}

		for (int i = 1; i < 2; i++) {
			WebElement bankUpgrade = driver.findElement(By.cssSelector("#product5"));
			bankUpgrade.click();
		}

//		for (int i = 1; i < 2; i++) {
//			WebElement templeUpgrade = driver.findElement(By.cssSelector("#product6"));
//			templeUpgrade.click();
//		}
//
//		for (int i = 1; i < 2; i++) {
//			WebElement Upgrade = driver.findElement(By.cssSelector("#product7"));
//			Upgrade.click();
//		}

	}

//	@After
//	public void tearDown() {
//		driver.quit();
//	}
}
