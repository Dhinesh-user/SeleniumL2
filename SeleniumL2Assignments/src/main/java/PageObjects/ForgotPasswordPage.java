package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
	WebDriver dr;
	
	public ForgotPasswordPage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
 
	@FindBy(linkText="Forgot Password")
	WebElement Forpwd;
	
	@FindBy(xpath="//input[@id='userName']")
	WebElement emailbox;
	
	@FindBy(name="submitBtn")
	WebElement submit;
	
	@FindBy(xpath="//*[@id='errorMsg']")
	WebElement email_error;
  
	public void clickforgotpwd() {
		Forpwd.click();
	}
	public void entermail(String email) {
		emailbox.sendKeys(email);
	}
	public void submitbtn() {
		submit.click();
	}
	public String verify_emailerror() {
		
		return email_error.getText();
	}
}
