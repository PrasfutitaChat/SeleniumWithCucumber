package step;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.LandingPage;
import pageFactory.SearchResultPage;
import pageFactory.ShoppingCartSummaryPage;
import pageFactory.SignInPage;
import reporting.ExtentManager;
import reusableMethods.ApplicationUtility;
import reusableMethods.Utility;

public class OrderBooking extends ApplicationUtility {
	LandingPage objLandingPage;
	SearchResultPage objSearchResultPage;
	SignInPage objSignInPage;
	public static HashMap<String, String> addedProduct = new HashMap();
	ShoppingCartSummaryPage objShoppingCartSummaryPage;
	WebDriver driver;
	private ExtentReports extent = ExtentManager.getInstance();
	private ExtentTest test;

	@Given("^Open the url$")
	public void open_the_url() {
		try {
			test = extent.startTest("Place order and perform post validation",
					"Place an order on the eCommerce website. Perform post\r\n"
							+ "order validations and assertions of test data.");
			test.log(LogStatus.INFO, "<b>Given : Open the url</b>");
			driver = launchBrowser();
			/*if(isElementDisplayed(objLandingPage.SignInLink, test))
			{
				test.log(LogStatus.PASS, "The landing page is opened properly ");
			}
			else
			{
				test.log(LogStatus.FAIL, "The landing page is not opened properly ");
			}*/
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@When("^Validate that cart should be empty and user is not logged in$")
	public void validate_that_cart_should_be_empty_and_user_is_not_logged_in() {
		try {

			test.log(LogStatus.INFO, "<b>When : Validate that cart should be empty and user is not logged in</b>");
			objLandingPage = new LandingPage(driver);
			if (getDisplayedText(objLandingPage.EmptyCart, test).equalsIgnoreCase("(empty)")) {
				test.log(LogStatus.PASS, "The cart is empty ");
			} else {
				test.log(LogStatus.FAIL, "The cart is not empty ");
			}

			if(isElementDisplayed(objLandingPage.SignInLink, test))
			{
				test.log(LogStatus.PASS, "User is not logged in ");
			}
			else
			{
				test.log(LogStatus.FAIL, "User is logged in ");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/*@When("^Verify Search box is performing perfectly$")
	public void verify_Search_box_is_performing_perfectly() {
		try {

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}*/

	@When("^Perform multiple searches and add product to cart from each search\\.$")
	public void perform_multiple_searches_and_add_product_to_cart_from_each_search() {
		try {
			test.log(LogStatus.INFO,
					"<b>When : Perform multiple searches and add product to cart from each search</b>");
			objSearchResultPage = new SearchResultPage(driver);
			setTextBoxValue(objLandingPage.SearchBox, "Dress", test);
			clickButton(objLandingPage.SearchButton, test);
			
			addedProduct=addProductToCart("Dress",test,objSearchResultPage,addedProduct);
			
			setTextBoxValue(objLandingPage.SearchBox, "Blouse", test);
			clickButton(objLandingPage.SearchButton, test);
			
			addedProduct=addProductToCart("Blouse",test,objSearchResultPage,addedProduct);
			
			//clickButton(objSearchResultPage.ViewCart, test);
			//System.out.println("in main test "+ addedProduct);
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Then("^All products will be added to cart properly$")
	public void all_products_will_be_added_to_cart_properly()  {
		try {
			test.log(LogStatus.INFO,
					"<b>Then : All products will be added to cart properly</b>");
		//	objSearchResultPage = new SearchResultPage(driver);
			hoverElement(objSearchResultPage.ViewCart, test);
			for(int i=0;i<objSearchResultPage.CartInfoList.size();i++)
			{
				System.out.println("In cart info   "+objSearchResultPage.CartInfoList.get(i).getAttribute("title"));
				String productName=objSearchResultPage.CartInfoList.get(i).getAttribute("title");
				if(addedProduct.containsKey(productName))
				{
					test.log(LogStatus.PASS, "Added product ---" +productName+" --- showing in cart sucessfully  " );
				}
				else
				{
					test.log(LogStatus.FAIL, "Added product is showing in cart sucessfully  " +productName );
				}
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}

	@When("^Proceed to checkout$")
	public void proceed_to_checkout()  {
		try {
			test.log(LogStatus.INFO,
					"<b>When : Proceed to checkout</b>");
		clickButton(objSearchResultPage.ViewCart, test);
		objShoppingCartSummaryPage=new ShoppingCartSummaryPage(driver);
		
		dynamicWaitTillVisibilityOfElement(objShoppingCartSummaryPage.Header, test);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			
		}
		

	}

	@Then("^The added products are correctly displayed and amounts are correct$")
	public void the_added_products_are_correctly_displayed_and_amounts_are_correct()  {
		try {
			test.log(LogStatus.INFO,
					"<b>Then : The added products are correctly displayed and amounts are correct</b>");
			//objShoppingCartSummaryPage=new ShoppingCartSummaryPage(driver);
			for(int i=0;i<objShoppingCartSummaryPage.ProductNameInSummaryTable.size();i++)
			{
				String productName=objShoppingCartSummaryPage.ProductNameInSummaryTable.get(i).getText();
				
				if(addedProduct.containsKey(productName))
				{
					test.log(LogStatus.PASS, "Added product ---" +productName+" --- showing in product summary table  sucessfully  " );
				}
				else
				{
					test.log(LogStatus.FAIL, "Added product is not showing in product summary table sucessfully  " +productName );
				}
			}
			
			for(int i=0;i<objShoppingCartSummaryPage.ProductCostInSummaryTable.size();i++)
			{
				String productCost=objShoppingCartSummaryPage.ProductCostInSummaryTable.get(i).getText();
				System.out.println("Product cost in summary table "+ productCost);
				
				if(addedProduct.containsValue(productCost))
				{
					test.log(LogStatus.PASS, "Added product price---" +productCost+" --- showing in product summary table  sucessfully  " );
				}
				else
				{
					test.log(LogStatus.FAIL, "Added product is not showing in product summary table sucessfully  " +productCost );
				}
			}
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}

	@When("^Perform increase and decrease of product quantity should work perfectly$")
	public void perform_increase_and_decrease_of_product_quantity_should_work_perfectly()  {
		try
		{
			test.log(LogStatus.INFO,
					"<b>When : Perform increase and decrease of product quantity should work perfectly</b>");
			objShoppingCartSummaryPage.PlusSignInSummaryTable.get(0).click();
			Thread.sleep(10000);
			//dynamicWaitTillVisibilityOfElement(driver.findElement(By.xpath("//*[text()='3 Products']")), test);
			String plusvVal=objShoppingCartSummaryPage.ProductQuantityInSummaryTable.get(0).getAttribute("value");
			System.out.println( "quantity "+ plusvVal);
			if(objShoppingCartSummaryPage.ProductQuantityInSummaryTable.get(0).getAttribute("value").equalsIgnoreCase("2"))
			{
				test.log(LogStatus.PASS, "Product quantity has increased properly");
			}
			else
			{
				test.log(LogStatus.FAIL, "Product quantity has not increased properly");
			}
			
			objShoppingCartSummaryPage.MinusSignInSummaryTable.get(0).click();
			Thread.sleep(10000);
			//dynamicWaitTillVisibilityOfElement(driver.findElement(By.xpath("//*[text()='2 Products']")), test);
			String minusVal=objShoppingCartSummaryPage.ProductQuantityInSummaryTable.get(0).getAttribute("value");
			System.out.println("quantity "+ minusVal);
			if(objShoppingCartSummaryPage.ProductQuantityInSummaryTable.get(0).getAttribute("value").equalsIgnoreCase("1"))
			{
				test.log(LogStatus.PASS, "Product quantity has decreased properly");
			}
			else
			{
				test.log(LogStatus.FAIL, "Product quantity has not decreased properly");
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}

	@When("^Performing add or remove products functionality should work perfectly$")
	public void performing_add_or_remove_products_functionality_should_work_perfectly() {
		try
		{
			test.log(LogStatus.INFO,
					"<b>When : Performing add or remove products functionality should work perfectly</b>");
			objShoppingCartSummaryPage.ProductDeletionInSummaryTable.get(0).click();
			Thread.sleep(10000);
			if(driver.findElements(By.xpath("//tbody/tr")).size()==1)
			{
				test.log(LogStatus.PASS, "Product has been deleted properly");
			}
			else
			{
				test.log(LogStatus.FAIL, "Product has not been deleted properly");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}

	@When("^Now login and complete the checkout and save order id/details$")
	public void now_login_and_complete_the_checkout_and_save_order_id_details()  {
		try
		{
			test.log(LogStatus.INFO,
					"<b>When : Now login and complete the checkout and save order id/details</b>");
			objSignInPage=new SignInPage(driver);
			clickButton(objShoppingCartSummaryPage.ProceedToCheckoutButton, test);
			setTextBoxValue(objSignInPage.Email, "rupsa@gmail.com", test);
			setTextBoxValue(objSignInPage.Password, "Password@123", test);
			clickButton(objSignInPage.SubmitButton, test);
			clickButton(objSignInPage.ProceedToCheckoutAddressButton, test);
			clickButton(objSignInPage.TermsAndServiceCheckbox, test);
			clickButton(objSignInPage.ProceedToCheckoutCarrierButton, test);
			
			if(driver.findElement(By.xpath("//p[@class='product-name']/a")).getText().equalsIgnoreCase("Blouse"))
			{
				test.log(LogStatus.PASS, "Summary is showing correct product");
			}
			else
			{
				test.log(LogStatus.FAIL, "Summary is not showing correct product");
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}

/*	@Then("^The saved order details should display properly in Order history and details section$")
	public void the_saved_order_details_should_display_properly_in_Order_history_and_details_section()
			throws Throwable {

	}*/

	@Then("^Log out and validate that the user is logged out$")
	public void log_out_and_validate_that_the_user_is_logged_out() throws Throwable {
		// quitDriver(driver);
		
		test.log(LogStatus.INFO,
				"<b>Then : Log out and validate that the user is logged out</b>");
		clickButton(objSignInPage.SignOutButton, test);
		if(isElementDisplayed(objLandingPage.SignInLink, test))
		{
			test.log(LogStatus.PASS, "User is logged out properly ");
		}
		else
		{
			test.log(LogStatus.FAIL, "User is not logged out properly ");
		}
		
		
		extent.endTest(test);
		extent.flush();
		quitDriver(driver,test);

	}

}
