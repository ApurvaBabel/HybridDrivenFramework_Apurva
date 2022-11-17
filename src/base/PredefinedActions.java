package base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import customExecptions.ElementNotEnabledException;

public class PredefinedActions {

	protected static WebDriver driver;
	static WebDriverWait wait;
	private static Actions actions;

	protected PredefinedActions() {

	}

	public static void start(String url) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_106.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
		actions = new Actions(driver);
	}

	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			} else {
				element = driver.findElement(By.id(locatorValue));
			}
			break;

		case "xpath":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			} else {
				element = driver.findElement(By.xpath(locatorValue));
			}
			break;

		case "cssSelector":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			} else {
				element = driver.findElement(By.cssSelector(locatorValue));
			}
			break;

		case "name":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			} else {
				element = driver.findElement(By.name(locatorValue));
			}
			break;

		case "className":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			} else {
				element = driver.findElement(By.className(locatorValue));
			}
			break;

		case "linkText":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			} else {
				element = driver.findElement(By.linkText(locatorValue));
			}
			break;

		case "partialLinkText":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			} else {
				element = driver.findElement(By.partialLinkText(locatorValue));
			}
			break;

		case "tagName":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			} else {
				element = driver.findElement(By.tagName(locatorValue));
			}
			break;
		}
		return element;
	}

	protected void scrollToElement(WebElement element) {
		if (!element.isDisplayed()) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true)", element);
		}
	}

	protected void setText(WebElement element, String text) {
		scrollToElement(element);
		if (element.isEnabled()) {
			element.sendKeys(text);
		} else {
			throw new ElementNotEnabledException(text + "can't be entered as element is not enabled");
		}
	}

	protected void setText(String locatorType, String locatorValue, boolean isWaitRequired, String text) {
		WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
		if (element.isEnabled()) {
			element.sendKeys(text);
		}
	}

	protected void clickOnElement(WebElement element, boolean isWaitRequiredBeforeClick) {
		scrollToElement(element);
		if (isWaitRequiredBeforeClick) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}
		element.click();
	}

	protected boolean isElementDisplayed(WebElement element) {
		scrollToElement(element);
		return element.isDisplayed();
	}

	protected void mouseHoverOnElement(WebElement element) {
		actions.moveToElement(element).build().perform();
	}

	protected List<String> getListOfWebElementText(List<WebElement> list) {
		List<String> listOfElementText = new ArrayList<String>();
		for (WebElement element : list) {
			listOfElementText.add(element.getText());
		}
		return listOfElementText;
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageURL() {
		return driver.getCurrentUrl();
	}

	public static void closeBrowser() {
		driver.close();
	}
}
