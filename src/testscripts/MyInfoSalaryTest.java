package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.MyInfoPage;
import pages.MyInfo_SalaryPage;
import pages.DashboardPage.Menu;
import pages.MyInfoPage.MyInfoMenu;

public class MyInfoSalaryTest extends TestBase {

	@Test
	public void verifyCtc() {
		System.out.println("STEP - Click on MyInfo tab");
		DashboardPage dashboardPage = DashboardPage.getObject();
		dashboardPage.gotoMenu(Menu.MYINFO);

		System.out.println("STEP - Click on Salary tab");
		MyInfoPage myInfoPage = MyInfoPage.getObject();
		myInfoPage.gotoMenu(MyInfoMenu.SALARY);

		System.out.println("VERIFY - Cost to company is non zero");
		MyInfo_SalaryPage salaryPage = MyInfo_SalaryPage.getObject();

		String ctc = salaryPage.getCostToCompany();
		Assert.assertTrue(ctc.startsWith("$"), "Actual ctc displayed as : " + ctc);
		ctc = ctc.replace("$", "").replace(",", "");
		System.out.println("displayed ctc amount -> " + ctc);
		double d = Double.parseDouble(ctc);
		Assert.assertTrue(d > 0, "ctc value was : " + d);
	}
}
