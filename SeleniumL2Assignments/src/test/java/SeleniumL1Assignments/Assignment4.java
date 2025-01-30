package SeleniumL1Assignments;
import static org.junit.Assert.*;
 
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
import org.openqa.selenium.support.ui.Select;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class Assignment4 {
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
	public void viewTransactions() {
		dr.findElement(By.linkText("Components")).click();
		dr.findElement(By.linkText("Monitors (2)")).click();
		dr.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[2]/button[2]")).click();
		dr.findElement(By.id("wishlist-total")).click();
		String viewWishlist=dr.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[2]/a")).getText();
		System.out.println("Items in the wishlist: "+viewWishlist);
		dr.findElement(By.linkText("Continue")).click();
		dr.findElement(By.linkText("Your Transactions")).click();
		String Transaction=dr.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td")).getText();
		System.out.println(Transaction);
	}
	@After
	public void LogOut() {
		dr.findElement(By.xpath("//*[@title='My Account']")).click();
		dr.findElement(By.linkText("Logout")).click();
	}
 
}