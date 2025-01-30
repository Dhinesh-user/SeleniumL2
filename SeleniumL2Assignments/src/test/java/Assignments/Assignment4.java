package Assignments;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

import Base.LaunchBrowser;
import PageObjects.ForgotPasswordPage;
import PageObjects.Homepage;
import PageObjects.LoginPage;
import Utilities.Excel;

public class Assignment4 {

	WebDriver dr;
    LaunchBrowser LB;
    Homepage hp;
    LoginPage lp;
    ForgotPasswordPage fp;
    static ExtentTest test;
	static ExtentReports report;
    
    @DataProvider(name = "creds")
	public Object[][] TestData() throws IOException {
		Excel ED = new Excel();
		Object[][] data = ED.getExcelData("C:\\Users\\PD20313015\\eclipse-workspace\\SeleniumL2Assignments\\ksrtc.xlsx", 0);
		return data;
	}
    @DataProvider(name = "email")
	public String[] TestData1() throws IOException {
		Excel ED = new Excel();
		String[] data = ED.getExcelData1("C:\\Users\\PD20313015\\eclipse-workspace\\SeleniumL2Assignments\\mailData.xlsx", 0);
		return data;
	}
    
    @BeforeSuite
	public void beforeSuite() {
		report = new ExtentReports(System.getProperty("user.dir") + "//ER.HTML");
	}

	@BeforeClass
	public void LaunchBrowser() throws InterruptedException, IOException {
		LB= new LaunchBrowser();
		dr=LB.browserinit();
	}
	
	@Test(priority=1)
	public void Test1() throws IOException {
		hp=new Homepage(dr);
		test = report.startTest("Verify the displayed contact Number");
		String contact="080-26252626";
		if(hp.verifycontactnumber().contains(contact)) {
			System.out.println("Given Contact No."+contact+"is displayed:");
			test.log(LogStatus.PASS, test.addScreenCapture(LB.takeScreenshot(dr)),"Contact No. is displayed");
			}
		else {
			System.out.println("Given Contact No."+contact+" is not displayed");
			test.log(LogStatus.FAIL, test.addScreenCapture(LB.takeScreenshot(dr)),"Contact No. is not displayed");
			}
	}
	
	@Test(dataProvider="creds", priority=2)
	public void Test2(String username, String password) throws IOException {
		lp = new LoginPage(dr);
		test = report.startTest("Verify the Login Error Message");
		lp.clicksignin();
		String currentURL1 = dr.getCurrentUrl();
		Assert.assertEquals(currentURL1, "https://ksrtc.in/oprs-web/login/show.do");
		lp.enterdetails(username, password);
		lp.EnableCheckbox();
		lp.Loginbtn();
		String currentURL2=dr.getCurrentUrl();
		Assert.assertEquals(currentURL2, "https://ksrtc.in/oprs-web/login/perform.do");
		String errMsg=lp.verifyerrormessage();
		if(errMsg.equals("Login incorrect. Please try again")) {
			System.out.println("error msg verified: "+errMsg);
			test.log(LogStatus.PASS, test.addScreenCapture(LB.takeScreenshot(dr)),"error msg verified");
		}
		else {
			System.out.println("error message is not verified");
			test.log(LogStatus.FAIL, "error message is not verified");
		}	
		
		
		
	}
	 @Test(dataProvider="email",priority=3)
	public void Test3(String email) throws IOException {
		 fp=new ForgotPasswordPage(dr);
		 test = report.startTest("Verify the Forgot Password error message");
		 fp.clickforgotpwd();
		 fp.entermail(email);
		 fp.submitbtn();
		 String email_err=fp.verify_emailerror();
		 if(email_err.equals("Login Name not found in the system.")) {
			 System.out.println("Email Error is verified and the message is:"+email_err);
			 test.log(LogStatus.PASS, test.addScreenCapture(LB.takeScreenshot(dr)),"Error message verified");
			 }
		 else {
			 System.out.println("Email Error is not verified");
			 test.log(LogStatus.FAIL, test.addScreenCapture(LB.takeScreenshot(dr)),"error message is not verified");
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
//	@AfterClass
//	public void CloseBrowser() {
////		dr.close();
//	}
	
}
