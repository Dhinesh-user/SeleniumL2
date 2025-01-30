package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
 
	WebDriver dr;
	String content;
	public Homepage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	@FindBy(tagName="body")
	 WebElement Pagecontent;
	
	public String verifycontactnumber() {
		content=Pagecontent.getText();
//		System.out.println(content);
		return content;
	}
}
