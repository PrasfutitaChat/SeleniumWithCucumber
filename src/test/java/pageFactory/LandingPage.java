package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;

	@FindBy(xpath = "//*[@title='View my shopping cart']//*[@class='ajax_cart_no_product']")
	public WebElement EmptyCart;

	@FindBy(xpath = "//*[contains(text(),'Sign in')]")
	public WebElement SignInLink;
	
	@FindBy(xpath = "//*[@placeholder='Search']")
	public WebElement SearchBox;
	
	@FindBy(xpath = "//*[@name='submit_search']")
	public WebElement SearchButton;
	
	
	

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}
