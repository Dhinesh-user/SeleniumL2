package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2 {
	WebDriver dr;
	Alert pop;

	@BeforeClass
	public void LaunchBrowser() throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		dr = new EdgeDriver();
		dr.manage().window().maximize();
		dr.get("https://the-internet.herokuapp.com/javascript_alerts");
	}
	@Test(priority=1)
	public void Alert() {
		dr.findElement(By.xpath("//button[@onClick='jsAlert()']")).click();
		pop=dr.switchTo().alert();
		String alert = pop.getText();
		Assert.assertEquals(alert, "I am a JS Alert");
		System.out.println("Alert Message Verified: "+alert);
		pop.accept();
		String result =dr.findElement(By.id("result")).getText();
		Assert.assertEquals(result, "You successfully clicked an alert");
		System.out.println("Result Contains: "+result);
	}
	@Test(priority=2)
	public void Confirm() {
		dr.findElement(By.xpath("//button[@onClick='jsConfirm()']")).click();
		String jsconfirm= pop.getText();
		Assert.assertEquals(jsconfirm, "I am a JS Confirm");
		System.out.println("jsConfirm Message verified: "+jsconfirm);
		pop.accept();
		String confirm =dr.findElement(By.id("result")).getText();
		Assert.assertEquals(confirm, "You clicked: Ok");
		System.out.println("Result Contains: "+confirm);
		dr.findElement(By.xpath("//button[@onClick='jsConfirm()']")).click();
		pop.dismiss();
		String cancel =dr.findElement(By.id("result")).getText();
		Assert.assertEquals(cancel, "You clicked: Cancel");
		System.out.println("Result Contains: "+cancel);
	}
	
	@Test(priority=3)
	public void prompt() {
		dr.findElement(By.xpath("//button[@onClick='jsPrompt()']")).click();
		String prompt = pop.getText();
		String str = "All is Well";
		Assert.assertEquals(prompt,"I am a JS prompt");
		System.out.println("jsPrompt Message verified: "+prompt);
		pop.sendKeys(str);
		pop.accept();
		String prompresult =dr.findElement(By.id("result")).getText();
		Assert.assertEquals(prompresult, "You entered: "+str);
		System.out.println("Result Contains: "+prompresult);
		dr.findElement(By.xpath("//button[@onClick='jsPrompt()']")).click();
		pop.dismiss();
		String promptcancel_result = dr.findElement(By.id("result")).getText();
		Assert.assertEquals(promptcancel_result, "You entered: null");
		System.out.println("Result Contains: "+promptcancel_result);
		
	}
	@AfterClass
	public void CloseBrowser() {
//		dr.close();
	}
}
