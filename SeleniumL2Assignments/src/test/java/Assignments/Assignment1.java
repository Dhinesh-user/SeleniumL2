package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1 {
	WebDriver dr;

	@BeforeClass
	public void LaunchBrowser() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		dr.get("https://demowebshop.tricentis.com/");
	}

	@Test(priority = 1)
	public void Test1() {

		WebElement element = dr.findElement(By.xpath("//*[contains(text(),'Customer service')]"));
		JavascriptExecutor js = (JavascriptExecutor) dr;
		js.executeScript("arguments[0].scrollIntoView();", element);
		dr.findElement(By.linkText("Blog")).click();
		String URL = dr.getCurrentUrl();
		if (URL.equals("https://demowebshop.tricentis.com/blog")) {
			System.out.println("URL Matched and the displayed URL is: " + URL);
		} else {
			System.out.println("URL not Matched and the displayed URL is: " + URL);
		}
	}

	@Test(priority = 2)
	public void Test2() {
		String BlogArc = dr.findElement(By.xpath("//div[@class='master-wrapper-content']")).getText();
		if (BlogArc.contains("BLOG ARCHIVE")) {
			Assert.assertTrue(BlogArc.contains("BLOG ARCHIVE"));
			System.out.println("Able to find Blog Archive");
		} else {
			System.out.println("Not Able to find Blog Archive");
		}
	}

	@AfterClass
	public void CloseBrowser() {
		dr.close();
	}
}
