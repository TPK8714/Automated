package SeleniumTest;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class landingPage {

	@FindBy(id = "sb_form_q")
	private WebElement searchBar;

	public void search(String searchText) {
		searchBar.sendKeys(searchText);
		searchBar.sendKeys(Keys.ENTER);
	}
}
