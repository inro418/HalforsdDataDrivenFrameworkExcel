package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String ExcelPath, String SheetName) throws IOException {

		projectPath = System.getProperty("user.dir");
		workbook = new XSSFWorkbook(ExcelPath);
		sheet = workbook.getSheet(SheetName);

	}

	public static void main(String[] args) throws IOException {
		// getRowCount();
		getCellDataString(0, 0);
		getCellDataNumber(1, 1);
		
	}

	public static int getRowCount() throws IOException {
		int rowCount = 0;

		rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("No of rows: " + rowCount);
		return rowCount;

	}

	public static int getColCount() throws IOException {
		int colCount = 0;

		colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("No of colunms: " + colCount);
		return colCount;

	}

	public static String getCellDataString(int rowNum, int colNum) throws IOException {
		String cellData = null;

		cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		// System.out.println(cellData);
		return cellData;
	}

	public static void getCellDataNumber(int rowNum, int colNum) throws IOException {

		double CellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
		System.out.println(CellData);
	}
}