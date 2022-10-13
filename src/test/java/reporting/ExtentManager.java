package reporting;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            extent = new ExtentReports("src\\test\\resources\\Reports\\TestReport"+dateFormat.format(date)+".html", true);

            // optional
            extent.config()
                    .documentTitle("Automation Report")
                    .reportName("Regression")
                    .reportHeadline("");

            // optional
            extent
                    .addSystemInfo("Selenium Version", "3.141.59")
                    .addSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static String CaptureScreen(WebDriver driver, String ImagesPath)
    {
        TakesScreenshot oScn = (TakesScreenshot) driver;
        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
        File oDest = new File(ImagesPath+".jpg");
        try
        {
            FileUtils.copyFile(oScnShot, oDest);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".jpg";
    }
}