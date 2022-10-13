package reusableMethods;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utility {

	public static WebDriver driver = null;

	public static String readPropFile(String key) {
		String retVal = "";
		try {
			File file = new File("src\\test\\resources\\Properties\\EnvironmentVariables.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fileInput);
			retVal = prop.getProperty(key);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return retVal;

	}

	public static WebDriver launchBrowser() {

		try {

			System.setProperty("webdriver.chrome.driver", readPropFile("chromepath"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(readPropFile("url"));

			System.out.println("Bowser open properly");

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return driver;
	}

	public static void clickButton(WebElement element, ExtentTest test) {

		try {
			if (element.isDisplayed() || element.isEnabled()) {
				element.click();

			//	test.log(LogStatus.PASS, "The webelement " + element + "is clicked sucessfully ");
			} /*else {
				test.log(LogStatus.FAIL, "The webelement " + element + "is not clicked  ");
			}*/

		} catch (Exception e) {
			System.out.println(e.toString());
			test.log(LogStatus.FAIL, "The webelement " + element + "is not clicked  "+ e.toString());

		}

	}

	public static String getDisplayedText(WebElement element, ExtentTest test) {
		String attrValue = null;
		try {
			if (element.isDisplayed() || element.isEnabled()) {

				attrValue = element.getText().trim();
				//test.log(LogStatus.PASS, "Display text of webelement" + element + "is done sucessfully ");
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Display text of webelement " + element + " is not done ", e.getMessage());
		}
		return attrValue;
	}

	public boolean isElementDisplayed(WebElement element, ExtentTest test) {
		boolean condition = false;
		try {

			if (element.isDisplayed()) {
				condition = true;
				test.log(LogStatus.PASS, "The webelement" + element + "displays sucessfully ");
			} else {
				test.log(LogStatus.FAIL, "The webelement" + element + "doesn't display ");
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "The webelement" + element + "doesn't display "+ e.toString());
			System.out.println(e.toString());
		}
		return condition;
	}
	
	public static void setTextBoxValue(WebElement element, String sValue, ExtentTest test) {
		try {
			if (element.isDisplayed() || element.isEnabled()) {
				element.clear();
				element.sendKeys(sValue);
				//test.log(LogStatus.PASS, sValue + " value has been entered in textbox  " + element + " sucessfully ");
			} else {
				System.out.println("Element not present");
				test.log(LogStatus.FAIL, sValue + " value has not been entered in textbox  " + element);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			test.log(LogStatus.FAIL, sValue + " value has not been entered in textbox  " + element + e.toString());
		}
	}
	
	public static void scrollToElement(WebElement element,ExtentTest test)
	  {
	    try
	    {
	      ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", new Object[] { element });
	    }
	    catch (Exception e)
	    {
	    	test.log(LogStatus.FAIL,  " Control didn't move to element" + element+ e.toString());
	      
	    }
	  }
	
	
	public static void dynamicWaitTillVisibilityOfElement(WebElement element,ExtentTest test)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL,  " wait didn't happen" + element+ e.toString());
			System.out.println(e.toString());
		}
	}
	
	public static void hoverElement(WebElement element, ExtentTest test)
	{
		try
		{
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL,  " hover didn't happen" + element+ e.toString());
			System.out.println(e.toString());
		}
	}
	
	public static void quitDriver(WebDriver driver,ExtentTest test) {
		try {
			driver.quit();
			driver.close();
		} catch (Exception e) {
			test.log(LogStatus.FAIL,  " Browser close didn't happen" + e.toString());
			System.out.println(e.toString());
		}
	}

}
