package SeleniumTest;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class FTSE100 {

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
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void checkStock() {

		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
		WebElement cookie = driver.findElement(By.xpath("//*[@id=\"cookie-policy-overlay\"]/div/a[2]"));
		cookie.click();

		WebElement riser = driver.findElement(By.cssSelector("#view-constituents > ul > li:nth-child(2) > a > strong"));
		riser.click();
		WebElement companyDetails = driver.findElement(By.xpath("//*[@id=\"ls-row-LSE-L\"]/td[2]/a"));
		System.out.println(companyDetails.getText());

		WebElement faller = driver
				.findElement(By.cssSelector("#content_div_40583 > ul > li:nth-child(3) > a > strong"));
		faller.click();
		WebElement companyName = driver.findElement(By.xpath("//*[@id=\"ls-row-JET-L\"]/td[2]/a"));
		System.out.println(companyName.getText());

	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
