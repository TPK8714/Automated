package SeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class addUser {

	public static final String URL = "http://thedemosite.co.uk/addauser.php";

	@FindBy(name = "username")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(name = "FormsButton2")
	private WebElement button;

	WebDriver driver;

	public addUser(WebDriver driver) {
		this.driver = driver;
	}

	public void addUserPage(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		button.click();
	}

}
