package Assignments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment3 {
	WebDriver dr;
	
	@BeforeClass
	public void LaunchBrowser() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		dr.get("https://demo.guru99.com/test/web-table-element.php");
	}
	@Test
	public void Testcase() {
		int row_Size=dr.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr/td[4]")).size();
		int col_Size=dr.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[1]/td")).size();
		
		System.out.println("Total no.of rows: "+row_Size+" & "+"Total no.of columns: "+col_Size);
		List<WebElement> lst= dr.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr/td[4]"));
 
		List<String> currentPrice=new ArrayList<String>();
		for (WebElement e:lst) {
			currentPrice.add(e.getText());
		}
		String Max=Collections.max(currentPrice);
		int index=currentPrice.indexOf(Max)+1;
		String name= dr.findElement(By.xpath("//*[@id='leftcontainer']/table/tbody/tr["+index+"]/td[1]")).getText();
	    System.out.println("Highest Current price is: "+Max+" Company name having highest current price is: "+name);
		}
		
	
	
	@AfterClass
	public void CloseBrowser() {
		dr.close();
	}

}
