package reusableMethods;



import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageFactory.SearchResultPage;

public class ApplicationUtility extends Utility {
	//public static HashMap<String, String> addedProduct = new HashMap();
	
	public static HashMap<String, String> addProductToCart(String productName, ExtentTest test, SearchResultPage objSearchResultPage, HashMap<String, String> addedProduct)
	{
		
		try
		{
			dynamicWaitTillVisibilityOfElement(driver.findElement(By.xpath("//h1//*[contains(text(),"+productName+")]")), test);

			System.out.println(driver.getCurrentUrl());
			System.out.println(objSearchResultPage.ProductList.size());
			test.log(LogStatus.INFO,
					"Number of products appeared in search result is " + objSearchResultPage.ProductList.size());
			int flagAddedProduct = 0;
			for (int i = 0; i < objSearchResultPage.ProductList.size(); i++) {

				if (getDisplayedText(
						objSearchResultPage.ProductList.get(i).findElement(By.xpath("//*[@class='available-now']")),
						test).equalsIgnoreCase("In stock")) {
					test.log(LogStatus.INFO, "The search result is In Stock");
					System.out.println("within condition");
					
					hoverElement(objSearchResultPage.ProductList.get(i), test);
					
					System.out.println("hovering done");

					WebElement currentProductAddtoCart = objSearchResultPage.ProductList.get(i)
							.findElement(By.xpath("//*[text()='Add to cart']"));
					
					dynamicWaitTillVisibilityOfElement(currentProductAddtoCart, test);
					//Thread.sleep(5000);
					String selectedproductPrice=objSearchResultPage.ProductList.get(i).findElement(By.xpath("//*[@class='price product-price']")).getText().trim();
					String selectedproductName=objSearchResultPage.ProductList.get(i).findElement(By.xpath("//*[@class='product_img_link']")).getAttribute("title");
					System.out.println("selected product  "+ selectedproductName + " price is "+ selectedproductPrice);
					addedProduct.put(selectedproductName,selectedproductPrice);
					
					System.out.println("wait for add to cart done");
					clickButton(currentProductAddtoCart, test);
					System.out.println("click add to cart done");
					dynamicWaitTillVisibilityOfElement(objSearchResultPage.ContinueShopping, test);
					System.out.println("wait for continue shopping done");
                    clickButton(objSearchResultPage.ContinueShopping, test);
					System.out.println("click continue shopping done");
					flagAddedProduct++;

				}
				if (flagAddedProduct == 1) {
					break;
				}

			}
			
			System.out.println("Added product   "+ addedProduct);
			//test.log(LogStatus.INFO, "Added product", addedProduct);

		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Issue in adding product in cart ", e.getMessage());
		}
		return addedProduct;
	}

}
