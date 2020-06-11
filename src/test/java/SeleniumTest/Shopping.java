package SeleniumTest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Shopping {

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

		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(true);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test
	public void test() throws InterruptedException, IOException {
		driver.get("http://automationpractice.com/index.php");

		WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
		searchBar.sendKeys("Dress");
		searchBar.sendKeys(Keys.ENTER);
		WebElement searchBar2 = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
		assertEquals("Dress", searchBar2.getAttribute("value"));

		WebElement selectItem = driver.findElement(By.xpath("//*[@id=\"best-sellers_block_right\"]/div/ul/li[1]/a"));
		selectItem.click();
		WebElement addCart = driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button"));
		addCart.click();
		WebElement proceed = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
		proceed.click();

		WebElement checkOut = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));
		checkOut.click();
		WebElement userName = driver.findElement(By.xpath("//*[@id=\"email_create\"]"));
		userName.sendKeys("AndyLucy@outlook.com");
		WebElement submit = driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]"));
		submit.click();

	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
