package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class LoginPage extends PredefinedActions {

	private static LoginPage loginPage;

	@FindBy(id = "txtUsername")
	private WebElement userNameElement;

	@FindBy(id = "txtPassword")
	private WebElement passwordElement;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;

	@FindBy(css = "#txtUsername-error")
	private WebElement userNameErrorElement;

	@FindBy(css = "#txtPassword-error")
	private WebElement passwordErrorElement;

	@FindBy(css = "div.organization-logo.shadow>img")
	private WebElement logo;

	private LoginPage() {

	}

	public static LoginPage getObject() {
		if (loginPage == null)
			loginPage = new LoginPage();
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickOnLoginBtn();
	}

	public void enterUsername(String username) {
		// driver.findElement(By.id("txtUsername")).sendKeys(username);
		// WebElement element = getElement("id", "txtUsername", false);
		// setText(element,username);
		// setText("id", "txtUsername", false,username);
		// userNameElement.sendKeys(username);
		setText(userNameElement, username);
	}

	public void enterPassword(String password) {
		// driver.findElement(By.id("txtPassword")).sendKeys(password);
		setText(passwordElement, password);
	}

	public void clickOnLoginBtn() {
		// driver.findElement(By.xpath("//button[@type='submit']")).click();
		clickOnElement(submitButton, false);
	}

	public boolean isUsernameErrorMessageDisplayed() {
		// return
		// driver.findElement(By.cssSelector("#txtUsername-error")).isDisplayed();
		return isElementDisplayed(userNameErrorElement);
	}

	public boolean isPasswordErrorMessageDisplayed() {
		// return
		// driver.findElement(By.cssSelector("#txtPassword-error")).isDisplayed();
		return isElementDisplayed(passwordErrorElement);
	}

	public boolean isLogoDisplayed() {
		// return
		// driver.findElement(By.cssSelector("div.organization-logo.shadow>img")).isDisplayed();
		return isElementDisplayed(logo);
	}
}
