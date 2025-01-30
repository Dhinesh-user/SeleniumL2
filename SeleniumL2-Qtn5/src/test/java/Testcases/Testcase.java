package Testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Base.launchBrowser;
import Pageobjects.LoginPage;
import Pageobjects.RegistrationPage;
import Util.Excel;

public class Testcase {
	WebDriver dr;
	launchBrowser LB;
	LoginPage lp;
	RegistrationPage rp;
	static ExtentTest test;
	static ExtentReports report;

	@DataProvider(name = "wrong creds")
	public Object[][] TestData() throws IOException {
		Excel ED = new Excel();
		Object[][] data = ED.getExcelData("C:\\Users\\PD20313015\\eclipse-workspace\\SeleniumL2-Qtn5\\Wrongcreds.xlsx",
				0);
		return data;
	}

	@DataProvider(name = "correct creds")
	public Object[][] TestData1() throws IOException {
		Excel ED = new Excel();
		Object[][] data = ED.getExcelData("C:\\Users\\PD20313015\\eclipse-workspace\\SeleniumL2-Qtn5\\crctcreds.xlsx",
				0);
		return data;
	}

	@BeforeSuite
	public void beforeSuite() {
		report = new ExtentReports(System.getProperty("user.dir") + "//ER.HTML");
	}

	@BeforeClass
	public void LaunchBrowser() throws InterruptedException, IOException {
		LB = new launchBrowser();
		dr = LB.browserinit();
		Thread.sleep(2000);
	}

	@Test(dataProvider = "wrong creds", priority = 1)
	public void Test1(String us, String pwd) throws Exception {
		test = report.startTest("Verify Error Message before registration");
		lp = new LoginPage(dr);
		lp.clksignon();
		Thread.sleep(5000);
		lp.Enterdetails(us, pwd);
		lp.clksubmit();
		String loginerror = lp.verifyerrormessage();
		if (loginerror.equals("Enter your userName and password correct")) {
			System.out.println("Error Message verified");
			test.log(LogStatus.PASS, test.addScreenCapture(LB.takeScreenshot(dr)), "Error Message verified");
			
		} else {
			System.out.println("Error Message not verified");
			test.log(LogStatus.FAIL, "Login is not successful");
		}

	}

	@Test(dataProvider = "correct creds", priority = 2)
	public void Test2(String us, String pwd) throws Exception {
		test = report.startTest("Verify success Message for registration");
		rp = new RegistrationPage(dr);
		rp.clkregister();
		Thread.sleep(2000);
		rp.enterdetails(us, pwd);
		rp.clksubmit();
		String success = rp.verifySuccessMsg();

		if (success.contains("Note: Your user name is " + us)) {
			System.out.println("Success Message verified");
			test.log(LogStatus.PASS, test.addScreenCapture(LB.takeScreenshot(dr)), "Success Message verified");
		} else {
			System.out.println("Success Message not verified");
			test.log(LogStatus.FAIL, "Login is not successful");
		}

	}

	@Test(dataProvider = "correct creds", priority = 3)
	public void Test3(String us, String pwd) throws Exception {
		test = report.startTest("Verify success Message after registration");
		lp = new LoginPage(dr);
		rp = new RegistrationPage(dr);
		rp.clickSignInLink();
		Thread.sleep(2000);
		lp.Enterdetails(us, pwd);
		lp.clksubmit();
		String loginsuccess = lp.verifyloginsuccess();
		if (loginsuccess.contains("Thank you for Loggin.")) {
			System.out.println("Login successful");
			test.log(LogStatus.PASS, test.addScreenCapture(LB.takeScreenshot(dr)), "Login successful");
		} else {
			System.out.println("Login is not successful");
			test.log(LogStatus.FAIL, "Login is not successful");
		}

	}

	@AfterMethod
	public void afterMethod() {
		report.endTest(test);
	}

	@AfterSuite
	public void afterSuite() {
		report.flush();
	}

	@AfterClass
	public void CloseBrowser() {
//		dr.close();
	}
}
