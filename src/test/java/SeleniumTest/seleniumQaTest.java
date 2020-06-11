package SeleniumTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class seleniumQaTest {

	WebDriver driver;
	private static ExtentReports report;
	private ExtentTest test;

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

	}

	@Test
	public void test() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("https://www.bing.co.uk");
		WebElement searchBar = driver.findElement(By.id("sb_form_q"));
		searchBar.sendKeys("BLM");
		System.out.println(searchBar.getAttribute("innerText"));
		searchBar.sendKeys(Keys.ENTER);
		WebElement searchBar2 = driver.findElement(By.xpath("//*[@id=\"sb_form_q\"]"));
		assertEquals("BLM", searchBar2.getAttribute("value"));
	}

	@Test
	public void testPOM() {
		driver.get("https://www.bing.co.uk");
		this.test = report.createTest("testPOM");

		final String searchTerm = "BLM";

		landingPage landing = PageFactory.initElements(driver, landingPage.class);
		landing.search(searchTerm);

		BingSearchPage searchPage = PageFactory.initElements(driver, BingSearchPage.class);

		if (searchPage.getSearchTerm().equals(searchTerm)) {
			test.pass("Correct search term found.");
		} else {
			test.fail("Correct search term not found.");
			fail();
		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
