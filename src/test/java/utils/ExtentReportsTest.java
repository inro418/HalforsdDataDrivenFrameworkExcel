package utils;

import java.io.IOException;
 
import org.testng.annotations.Test;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
 
public class ExtentReportsTest
{
 
@Test
public void LoginTest() throws IOException
{    

	// URL for everything about Extent Reports   https://learn-automation.com/extent-report-with-selenium-webdriver/
	
            // Create Object of ExtentHtmlReporter and provide the path where you want to generate the report
            // I used (.) in path where represent the current working directory
    ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/Halfords.html");
            // Create object of ExtentReports class- This is main class which will create report
    ExtentReports extent = new ExtentReports();
    
            // attach the reporter which we created in Step 1
    extent.attachReporter(reporter);
    
            // call createTest method and pass the name of TestCase- Based on your requirement
    ExtentTest logger=extent.createTest("LoginTest");
    
            // log method will add logs in report and provide the log steps which will come in report
    logger.log(Status.INFO, "Login To Halfords");
  
    logger.log(Status.PASS, "Title verified");
  
   
    extent.flush();
    
  }
}