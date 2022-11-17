package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.LoginPage;
import utility.ExcelOperations;

public class LoginTest {

	@Test(dataProvider = "LoginDataProvider")
	public void tc1(String url, String username, String password, boolean isLoginSuccessful) {
		System.out.println("STEP - Launch Chrome Browser & Hit url");
		PredefinedActions.start(url);

		System.out.println("STEP - Enter valid login credentials");
		LoginPage loginPage = new LoginPage();
		loginPage.login(username, password);

		if (isLoginSuccessful) {
			System.out.println("VERIFY - Login successfully");
			String expetedTitle = "Employee Management";
			String actualTitle = loginPage.getPageTitle();
			Assert.assertEquals(actualTitle, expetedTitle,
					"Expected title was " + expetedTitle + " but actual title is " + actualTitle);
		} else {
			System.out.println("VERIFY - Invalid credentials ,Retry Login page is loaded");
			String expetedTitle = "OrangeHRM";
			String actualTitle = loginPage.getPageTitle();
			Assert.assertEquals(actualTitle, expetedTitle,
					"Expected title was " + expetedTitle + " but actual title was " + actualTitle);

			String expectedUrlContent = "retryLogin";
			String actualCurrentURL = loginPage.getPageURL();
			Assert.assertTrue(actualCurrentURL.endsWith(expectedUrlContent));
		}

		PredefinedActions.closeBrowser();
	}

	@DataProvider(name = "LoginDataProvider")
	public Object[][] getLoginData() throws IOException {
		Object[][] data;
		// String fileName = ".//testdata/Logindata.xlsx";
		try {
			data = ExcelOperations.readExcelData(".//testdata/Logindata.xlsx", "Data");
		} catch (IOException e) {
			data = ExcelOperations.readExcelData(".//testdata1/Logindata.xlsx", "Data");
		}

		return data;
	}

	@DataProvider(name = "LoginDataProvider1")
	public Object[][] getLoginData1() {

		Object[][] data = new Object[2][4];
		data[0][0] = "https://ababel-trials77.orangehrmlive.com";
		data[0][1] = "Admin";
		data[0][2] = "jhLqPX@S07";
		data[0][3] = true;

		data[1][0] = "https://ababel-trials77.orangehrmlive.com";
		data[1][1] = "Admin";
		data[1][2] = "jhLqPX@S0789";
		data[1][3] = false;

		return data;
	}
}
