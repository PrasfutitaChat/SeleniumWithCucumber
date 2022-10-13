package pageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
	WebDriver driver;

	@FindBy(id = "email")
	public WebElement Email;
	
	@FindBy(id = "passwd")
	public WebElement Password;
	
	@FindBy(id = "SubmitLogin")
	public WebElement SubmitButton;
	
	@FindBy(xpath = "//button[@name='processAddress']")
	public WebElement ProceedToCheckoutAddressButton;
	
	@FindBy(xpath = "//*[@type='checkbox']")
	public WebElement TermsAndServiceCheckbox;
	
	@FindBy(xpath = "//button[@name='processCarrier']")
	public WebElement ProceedToCheckoutCarrierButton;
	
	@FindBy(xpath = "//a[@class='logout']")
	public WebElement SignOutButton;
	
	

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}


}
