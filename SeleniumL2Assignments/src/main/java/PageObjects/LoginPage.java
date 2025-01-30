package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver dr;

	public LoginPage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(linkText="Sign In")
	WebElement SignInBtn;
	 
	@FindBy(xpath="//*[@for='userName']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='userName']")
	WebElement user;
	
	@FindBy(xpath="//*[@for='password']")
	WebElement password;
	 
	@FindBy(xpath="//input[@id='password']")
	WebElement pass;
	
	@FindBy(linkText="Terms & Conditions")
	WebElement checkbox;
	
	@FindBy(xpath="//input[@name='submitBtn']")
	WebElement submitbtn;

	@FindBy(id="errorMsg")
	WebElement errormessage;
	
	public void clicksignin() {
		SignInBtn.click();
	}
	public void enterdetails(String un,String pwd) {
		username.click();
		user.sendKeys(un);
		password.click();
		pass.sendKeys(pwd);
	}
	public void EnableCheckbox() {
		Actions act =new Actions(dr);
		act.moveToElement(checkbox, -75, 0).click().perform();

	}
	public void Loginbtn() {
		submitbtn.click();
	}
	
	public String verifyerrormessage() {
		 return errormessage.getText();
	}
}