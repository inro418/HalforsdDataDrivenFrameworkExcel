

package utils;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {

	WebDriver driver;
	private static Logger logger = LogManager.getLogger();
	
	@BeforeTest
	public void setUpTest() {
		String projectPath = System.getProperty("user.dir");
	}
	
	@Test(dataProvider = "test1data")
	public void test1(String username, String password) throws InterruptedException {  
		System.out.println(username + "  |  " + password);         
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		logger.info("Open Browser");
		driver.manage().deleteAllCookies();
		logger.info("Delet Cookies");
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		logger.info("Setup Time");
		driver.manage().window().maximize();
		logger.info("Maximize the Window");
		
		driver.get("https://www.halfords.com/"); 
		logger.info("Launch URL");
		
		driver.findElement(By.xpath("//a[@title='Account']")).click(); 
		logger.info("Click Account Link");
		driver.findElement(By.name("loginEmail")).sendKeys(username);  
		logger.info("Enter EmailAddress");
		driver.findElement(By.name("loginPassword")).sendKeys(password); 
		logger.info("Enter Password");
		Thread.sleep(3000);
		driver.findElement(By.name("login")).click();
		logger.info("Click Login Btn");
		
		//System.out.println(driver.findElement(By.xpath("//p[@class='asset__text asset__text--bold']")).getAttribute("textContent"));
        System.out.println(driver.getTitle()); 
		Assert.assertEquals("Login or Register | Halfords UK", driver.getTitle());  
		logger.info("Verify the Page Title");
		driver.quit();
		logger.info("Close Browser");
	}
	
	@DataProvider(name = "test1data")
	public Object[][] getData() throws IOException {
		String excelPath = "C:\\Users\\user\\workspace\\HalfordsDataDrivenFramework\\halexl\\halfdata.xlsx";  
		Object data[][] = testData(excelPath, "Sheet1");
		return data;
	}

	public Object[][] testData(String excelPath, String SheetName) throws IOException {

		ExcelUtils excel = new ExcelUtils(excelPath, SheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data[][] = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {

				String cellData = excel.getCellDataString(i, j);
				System.out.print(cellData + " | ");
				data[i - 1][j] = cellData;
			}

			// System.out.println();
		}
		return data;

	}
}