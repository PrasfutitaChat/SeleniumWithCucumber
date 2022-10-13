package pageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	WebDriver driver;

	@FindBy(xpath = "//*[@class='product_list grid row']//div[@class='product-container']")
	public List<WebElement> ProductList;
	
	@FindBy(xpath = "//*[@title='Continue shopping']")
	public WebElement ContinueShopping;
	
	@FindBy(xpath = "//a[@title='View my shopping cart']")
	public WebElement ViewCart;
	
	
	@FindBy(xpath = "//*[@class='cart-info']//a[@class='cart_block_product_name']")
	public List<WebElement> CartInfoList;
	
	
	
	

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}
