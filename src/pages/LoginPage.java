package pages;

import org.openqa.selenium.By;

import base.PredefinedActions;

public class LoginPage extends PredefinedActions {

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickOnLoginBtn();
	}

	public void enterUsername(String username) {
		driver.findElement(By.id("txtUsername")).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(By.id("txtPassword")).sendKeys(password);
	}

	public void clickOnLoginBtn() {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	public boolean isUsernameErrorMessageDisplayed() {
		return driver.findElement(By.cssSelector("#txtUsername-error")).isDisplayed();
	}

	public boolean isPasswordErrorMessageDisplayed() {
		return driver.findElement(By.cssSelector("#txtPassword-error")).isDisplayed();
	}

	public boolean isLogoDisplayed() {
		return driver.findElement(By.cssSelector("div.organization-logo.shadow>img")).isDisplayed();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
}
