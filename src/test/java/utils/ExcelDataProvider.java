package utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {

	WebDriver driver;
	
	@BeforeTest
	public void setUpTest() {
		String projectPath = System.getProperty("user.dir");
		
		// Set System property

	}

	@Test(dataProvider = "test1data")
	public void test1(String username, String password) {  
		System.out.println(username + "  |  " + password);         

		System.setProperty("webdriver.chrome.driver","C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://www.halfords.com/"); 
		driver.findElement(By.xpath("//a[@title='Account']")).click();  
		driver.findElement(By.name("loginEmail")).sendKeys(username);   
		driver.findElement(By.name("loginPassword")).sendKeys(password);   
	
		//System.out.println(driver.getTitle()); 
		//Assert.assertEquals("Get started with HubSpot CRM", driver.getTitle());  
		
		driver.quit();
		
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