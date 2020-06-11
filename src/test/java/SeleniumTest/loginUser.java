package SeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginUser {

	public static final String URL = "http://thedemosite.co.uk/login.php";

	@FindBy(name = "username")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(name = "FormsButton2")
	private WebElement button;

	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center")
	private WebElement loginAttempt;

	WebDriver driver;

	public loginUser(WebDriver driver) {
		this.driver = driver;
	}

	public void loginUserPage(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		button.click();
	}

	public String loginAttemptText() {
		return loginAttempt.getText();
	}
}
