package testscripts;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PredefinedActions;
import pages.LoginPage;
import utility.PropertyFileOperations;

public class TestBase {

	@BeforeMethod
	public void setUp() throws IOException {
		PropertyFileOperations fileOperations = new PropertyFileOperations(".//config//settings.properties");
		String url = fileOperations.getValue("url");
		PredefinedActions.start(url);

		LoginPage loginPage = new LoginPage();
		loginPage.login(fileOperations.getValue("username"), fileOperations.getValue("password"));
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		int status = result.getStatus();
		if (ITestResult.FAILURE == status) {
			PredefinedActions.takeScreenshot(result.getMethod().getMethodName());
		} else if (ITestResult.SUCCESS == status) {
			PredefinedActions.takeScreenshot(result.getMethod().getMethodName());
		}
		PredefinedActions.closeBrowser();
	}
}
