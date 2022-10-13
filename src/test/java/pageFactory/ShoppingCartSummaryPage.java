package pageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartSummaryPage {
	WebDriver driver;

	@FindBy(xpath = "//h1[contains(text(),'Shopping-cart summary')]")
	public WebElement Header;

	@FindBy(xpath = "//tbody//*[@class='product-name']")
	public List<WebElement> ProductNameInSummaryTable;
	
	
	@FindBy(xpath = "//tbody//*[@class='cart_total']/span")
	public List<WebElement> ProductCostInSummaryTable;
	
	@FindBy(xpath = "//tbody//*[@class='icon-plus']")
	public List<WebElement> PlusSignInSummaryTable;
	
	@FindBy(xpath = "//tbody//*[@class='icon-minus']")
	public List<WebElement> MinusSignInSummaryTable;
	
	@FindBy(xpath = "//a[@title='Delete']")
	public List<WebElement> ProductDeletionInSummaryTable;
	
	
	@FindBy(xpath = "//td[@class='cart_quantity text-center']/input[position()=1]")
	public List<WebElement> ProductQuantityInSummaryTable;
	//p//a[@title='Proceed to checkout']
	
	@FindBy(xpath = "//p//a[@title='Proceed to checkout']")
	public WebElement ProceedToCheckoutButton;
	
	
	
	
	//a[@title='View my shopping cart']
	

	public ShoppingCartSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}


}
