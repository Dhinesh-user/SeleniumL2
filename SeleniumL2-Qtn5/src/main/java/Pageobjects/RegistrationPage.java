package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	WebDriver dr;

	public RegistrationPage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(linkText="registration form")
	WebElement regist;
	
	@FindBy(name="email")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="confirmPassword")
	WebElement cfmpwd;
	
	@FindBy(name="submit")
	WebElement submit;
	
	@FindBy(xpath="//*[contains(text(),' Note: Your user name is admin.')]")
	WebElement successmessage;
	
	@FindBy(linkText="sign-in")
	WebElement signIn;
	
	public void clkregister() {
		regist.click();
	}
	public void enterdetails(String us,String pwd) {
		username.click();
		username.sendKeys(us);
		password.click();
		password.sendKeys(pwd);
		cfmpwd.click();
		cfmpwd.sendKeys(pwd);
	}
	public void clksubmit() {
		submit.click();
	}
	
	public String verifySuccessMsg() {
		 return successmessage.getText();
	}
	public void clickSignInLink() {
		signIn.click();
	}

}
