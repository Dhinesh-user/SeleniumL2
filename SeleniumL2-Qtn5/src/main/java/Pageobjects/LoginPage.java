package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver dr;

	public LoginPage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}

	@FindBy(linkText = "SIGN-ON")
	WebElement signon;

	@FindBy(name = "userName")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "submit")
	WebElement LoginBtn;

	@FindBy(xpath = "/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/span")
	WebElement LoginError;
	
	@FindBy(tagName="body")
	WebElement Loginsuccess;

	public void clksignon() {
     signon.click();
	}

	public void Enterdetails(String us, String pwd) {
     username.click();
     username.sendKeys(us);
     password.click();
     password.sendKeys(pwd);
	}

	public void clksubmit() {
      LoginBtn.click();
	}

	public String verifyerrormessage() {
      return LoginError.getText();
	}

	public String verifyloginsuccess() {
		
		return Loginsuccess.getText();
	}
	
}
