package SeleniumL1Assignments;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
 
import java.util.List;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class Assignment2 {
   static  WebDriver dr;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		WebDriverManager.edgedriver().setup();
		 dr =new EdgeDriver();
		dr.manage().window().maximize();
		dr.get("https://naveenautomationlabs.com/opencart/");
	}
 
	@AfterClass
	public static void tearDownAfterClass() {
		dr.close();
	}
 
	@Before
	public void Login() {
		dr.findElement(By.xpath("//*[@title='My Account']")).click();
		dr.findElement(By.linkText("Login")).click();
		WebElement user=dr.findElement(By.id("input-email"));
		user.click();
		user.sendKeys("aadhrika1234@gmail.com");
		dr.findElement(By.name("password")).click();
		dr.findElement(By.name("password")).sendKeys("aadhrika");
		dr.findElement(By.cssSelector("[value=Login]")).click();
	}
 
	@Test
	public void AddToCart() throws Exception {
		dr.findElement(By.linkText("Components")).click();
		dr.findElement(By.linkText("Monitors (2)")).click();
		dr.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[2]/button[1]")).click();
		WebDriverWait wait = new WebDriverWait(dr,Duration.ofSeconds(15));
		WebElement chkBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='checkbox']")));
		chkBox.click();
		WebElement el=dr.findElement(By.name("option[208]"));
		el.click();
		el.clear();
		el.sendKeys("Apple");
		Select dropdown = new Select(dr.findElement(By.name("option[217]")));
		dropdown.selectByVisibleText("Blue (+$3.60)");
		dr.findElement(By.name("option[209]")).click();
		dr.findElement(By.name("option[209]")).sendKeys("Apple");
//	 Document upload
			dr.findElement(By.xpath("//*[text()=' Upload File']")).click();
			
			StringSelection stringSelection = new StringSelection("C:\\Users\\PD20313015\\Documents\\Task.txt");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
	        WebDriverWait waitAlert = new WebDriverWait(dr, Duration.ofSeconds(5));
	        waitAlert.until(ExpectedConditions.alertIsPresent());
			dr.switchTo().alert().accept();
	
//			Updating Time
			WebElement Timesetbtn=dr.findElement(By.xpath("(//*[@class='input-group-btn'])[3]//child::button"));
			Timesetbtn.click();
		    String Updatetime = "23:30";
		    String DisplayTime;
			WebElement HrDownBtn = dr.findElement(By.xpath("(//*[@class='glyphicon glyphicon-chevron-down'])[3]"));
			WebElement MinDownBtn = dr.findElement(By.xpath("(//*[@class='glyphicon glyphicon-chevron-down'])[4]"));	
			WebElement hourEle = dr.findElement(By.xpath("(//*[@data-action='showHours'])[2]"));	
			WebElement minEle = dr.findElement(By.xpath("(//*[@data-action='showMinutes'])[2]"));	
			String hour = Updatetime.split(":")[0];
			String min = Updatetime.split(":")[1];
			for(int i=0;i<60;i++) {
				DisplayTime = minEle.getText();
				if(DisplayTime.equals(min)) break;
				else MinDownBtn.click();
			}		
			for(int i=0;i<24;i++) {
				DisplayTime = hourEle.getText();
				if(DisplayTime.equals(hour)) break;
				else HrDownBtn.click();
			}
			Timesetbtn.click();
			
//			Updating Date
			WebElement datePicker = dr.findElement(By.xpath("(//*[@class='fa fa-calendar'])[1]"));
			datePicker.click();
			String DateMonYear = "1 Aug 2023";
			String date = DateMonYear.split(" ")[0];
			String month = DateMonYear.split(" ")[1];
			String year = DateMonYear.split(" ")[2];
			
			dr.findElement(By.xpath("(//*[@class='picker-switch'])[1]")).click();
			String yearPicker = dr.findElement(By.xpath("(//*[@class='picker-switch'])[2]")).getText();
			
			//year check; if given year is lesser than the displayed year
			if(Integer.parseInt(yearPicker)>Integer.parseInt(year)) {
				while(!yearPicker.equals(year))
				{
					dr.findElement(By.xpath("(//*[@class='picker-switch'])[2]//parent::tr/th")).click();
					yearPicker = dr.findElement(By.xpath("(//*[@class='picker-switch'])[2]")).getText();
				}
				List<WebElement> monthList = dr.findElements(By.xpath("(//td[@colspan='7'])[1]/span"));
				for (WebElement webEle : monthList) {
					if(webEle.getText().equals(month)) {
						webEle.click();
						break;
					}
				}
				dr.findElement(By.xpath("//*[text()='"+date+"']")).click();
			}
			
			//year check; if given year is greater than the displayed year
			else if(Integer.parseInt(yearPicker)<Integer.parseInt(year)) {
				while(!yearPicker.equals(year))
				{
					dr.findElement(By.xpath("(//*[@class='picker-switch'])[2]//parent::tr/th[3]")).click();
					yearPicker = dr.findElement(By.xpath("(//*[@class='picker-switch'])[2]")).getText();
				}
				List<WebElement> monthList = dr.findElements(By.xpath("(//td[@colspan='7'])[1]/span"));
				for (WebElement webEle : monthList) {
					if(webEle.getText().equals(month)) {
						webEle.click();
						break;
					}
				}
				dr.findElement(By.xpath("//*[text()='"+date+"']")).click();
			}
			
			//year check; if given year is equals to the displayed year
			else {
				List<WebElement> monthList = dr.findElements(By.xpath("(//td[@colspan='7'])[1]/span"));
				for (WebElement webEle : monthList) {
					if(webEle.getText().equals(month)) {
						webEle.click();
						break;
					}
				}
				dr.findElement(By.xpath("//*[text()='"+date+"']")).click();
			}
		dr.findElement(By.cssSelector("[id=button-cart]")).click();
		WebDriverWait waits = new WebDriverWait(dr,Duration.ofSeconds(15));
		WebElement ele = waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[1]/div[2]")));
		String error=ele.getText();
		System.out.println("the error displayed on clicking add to cart is: "+ error);
		String TotalItemsInCart=dr.findElement(By.id("cart-total")).getText();
		System.out.println(TotalItemsInCart);
 
	}
	@After
	public void LogOut() {
		dr.findElement(By.xpath("//*[@title='My Account']")).click();
		dr.findElement(By.linkText("Logout")).click();
	}
 
}
 
