package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.LoginPage;

public class LoginTest {

	@Test
	public void tc1() {
		System.out.println("STEP - Launch Chrome Browser & Hit url");
		PredefinedActions.start("https://ababel-trials77.orangehrmlive.com");
		
		System.out.println("STEP - Enter valid login credentials");
		LoginPage loginPage = new LoginPage();
		loginPage.login("Admin", "jhLqPX@S07");
		
		System.out.println("VERIFY - Login successfully");
		String expetedTitle = "Employee Management";
		String actualTitle = loginPage.getPageTitle();
		
		Assert.assertEquals(actualTitle, expetedTitle, "Expected title was " + expetedTitle + " but actual title is " + actualTitle);
		
		PredefinedActions.closeBrowser();
	}
}
