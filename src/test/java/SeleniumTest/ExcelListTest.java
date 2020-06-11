package SeleniumTest;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

@RunWith(Parameterized.class)
public class ExcelListTest {

	@Parameters
	public static Collection<Object[]> data() throws IOException {
		FileInputStream file = new FileInputStream("src/test/resources/DemoSiteDDT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows() - 1][4];

//		Reading
		for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
			ob[rowNum - 1][0] = sheet.getRow(rowNum).getCell(0).getStringCellValue();
			ob[rowNum - 1][1] = sheet.getRow(rowNum).getCell(1).getStringCellValue();
			ob[rowNum - 1][2] = sheet.getRow(rowNum).getCell(2).getStringCellValue();
			ob[rowNum - 1][3] = rowNum;
		}
		workbook.close();
		return Arrays.asList(ob);

	}

	private String username;
	private String password;
	private String expected;
	private int rowNum;

	private WebDriver driver;

	public ExcelListTest(String username, String password, String expected, int rowNum) {
		this.username = username;
		this.password = password;
		this.expected = expected;
		this.rowNum = rowNum;
	}

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "../chromedriver");
		driver = new ChromeDriver();
	}

	@Test
	public void login() throws IOException {
		// Selenium
		driver.get(addUser.URL);

		addUser addAUserPage = PageFactory.initElements(driver, addUser.class);
		addAUserPage.addUserPage(username, password);

		driver.get(loginUser.URL);
		loginUser loginPage = PageFactory.initElements(driver, loginUser.class);
		loginPage.loginUserPage(username, password);
		loginPage.loginAttemptText(); // return to me **Successful** or **Fai l**

		// Writing
		FileInputStream file = new FileInputStream("src/test/resources/DemoSiteDDT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell;
		cell = row.getCell(3);
		if (cell == null) {
			cell = row.createCell(3);
		}
		cell.setCellValue(loginPage.loginAttemptText());

		// Testing
		cell = row.getCell(4);
		if (cell == null) {
			cell = row.createCell(4);
		}

		try {
			assertEquals("Login not successful", expected, loginPage.loginAttemptText());
			cell.setCellValue("PASS");
		} catch (AssertionError e) {
			cell.setCellValue("FAIL");
			Assert.fail();
		} finally {
			FileOutputStream fileOut = new FileOutputStream("src/test/resources/DemoSiteDDT.xlsx");

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

			workbook.close();
			file.close();
		}

	}

	@After
	public void tearDown() {
		driver.quit();
	}
}

//public class theDemoSite {
//	private WebDriver driver;
//	private static ExtentReports report;
//
//	@BeforeClass
////	@SuppressWarnings("deprecation")
//	public void setup() {
//		report = new ExtentReports();
//		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport.html");
//		htmlReport.config().setAutoCreateRelativePathMedia(true);
//		report.attachReporter(htmlReport);
//	}
//
//	@Before
//	public void init() {
//		driver = new ChromeDriver();
//		ChromeOptions opts = new ChromeOptions();
//		opts.setHeadless(true);
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//	}
//
//	@Test
//	public void checkteUser() {
//
//		driver.get("https://thedemosite.co.uk/");
//		WebElement advance = driver.findElement(By.xpath("//*[@id=\"details-button\"]"));
//		advance.click();
//		WebElement proceed = driver.findElement(By.xpath("//*[@id=\"proceed-link\"]"));
//		proceed.click();
//		WebElement addUser = driver.findElement(
//				By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]"));
//		addUser.click();
//		WebElement userName = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
//		userName.sendKeys("Miss_K");
//		WebElement password = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
//		password.sendKeys("BigTime");
//		WebElement save = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
//		save.click();
//		WebElement CheckUser = driver.findElement(
//				By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
//		CheckUser.click();
//		WebElement userName1 = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
//		userName1.sendKeys("Miss_K");
//		WebElement password1 = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
//		password1.sendKeys("BigTime");
//		WebElement save1 = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
//		save1.click();
//		WebElement loginStatus = driver
//				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
//		assertTrue(loginStatus.getText().contains("Successful Login"));
//
//	}
