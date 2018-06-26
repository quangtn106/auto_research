package support.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class OperationHelper {
	private WebDriver driver = null;
	
	private String filePath = "";
	private String fileName = "";
	private String sheetName = "";
	private String sheetNameTest = "";
	private FileInputStream file;
	private FileOutputStream fileOut;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
		
	// ================QuangTN=================
	/**
	 * This function is launch browser
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD launch() function 
	 * UPDATED DATE: 6/18/2018 
	 * @param browser
	 */
	public void launch(String browser) {
		String rootPath = System.getProperty("user.dir");
		String driverPath = "\\src\\test\\references\\";
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", rootPath + driverPath + "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", rootPath + driverPath + "geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", rootPath + driverPath + "MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", rootPath + driverPath + "IEDriverServer_win_x64.exe");
			driver = new InternetExplorerDriver();
		}

		else {
			System.out.println("Browser is invalid");
		}
	}
	
	/**
	 * This function is close browser
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD close() function 
	 * UPDATED DATE: 6/23/2018 
	 */
	public void close() {
		driver.close();
	}

	/**
	 * This function is get elements
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD getElement() function 
	 * UPDATED DATE: 6/18/2018 
	 * @param eName
	 * @param eLocator
	 * @return element
	 */
	public WebElement getElement(String eName, String eLocator) {
		WebElement e;
		eName.toLowerCase();
		boolean isWhitespace = eName.matches(".*\\s+.*");
		if (isWhitespace == true) {
			System.out.println("White space is not allowed");
			return null;
		} else {
			if (eName.contains("-id")) {
				e = driver.findElement(By.id(eLocator));
			} else if (eName.contains("-name")) {
				e = driver.findElement(By.name(eLocator));
			} else if (eName.contains("-xp")) {
				e = driver.findElement(By.xpath(eLocator));
			} else if (eName.contains("-css")) {
				e = driver.findElement(By.cssSelector(eLocator));
			} else {
				System.out.println("objectName is invalid");
				return null;
			}
			return e;
		}
	}
	
	/**
	 * This function is open excel file
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD openFileExcel() function 
	 * UPDATED DATE: 6/19/2018
	 * @param filePath
	 * @param fileName
	 * @param sheetName
	 * @throws Throwable
	 */
	public void openFileExcel(String fileName, String sheetName) throws Throwable {
		filePath = "\\src\\test\\resources\\DataIn\\";
		String fileDir = System.getProperty("user.dir") + filePath + fileName;
		file = new FileInputStream(fileDir);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sheetName);
	}
	
	/**
	 * This function is change sheet excel file
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD changeToSheet() function 
	 * UPDATED DATE: 6/19/2018
	 * @param sheetName
	 */
	public void changeToSheet(String sheetName) {
		sheetNameTest = sheetName;
		sheet = workbook.getSheet(sheetNameTest);
		System.out.println("Changed to sheet" + sheetNameTest);
	}
	
	/**
	 * This function is get a URL from excel file
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD getURL_FromExcel() function 
	 * UPDATED DATE: 6/19/2018
	 * @param table
	 * @param row_index
	 * @return String URL
	 * @throws Throwable
	 */
	public String getURL_FromExcel(String[][] table, int row_index) throws Throwable {
		String url = "";
		fileName = "Elements.xlsx";
		sheetName = "Elements";
		openFileExcel(fileName, sheetName);
		int row_excel_index = Integer.parseInt(table[row_index][0]);
		int col_excel_index = Integer.parseInt(table[row_index][1]);
		row = sheet.getRow(row_excel_index);
		cell = row.getCell(col_excel_index);
		if (isCellEmpty(cell)) {
			url = null;
			System.out.println("Cell is blank");
		} else {
			url = cell.getStringCellValue().trim().toString();
		}
		return url;
	}
	
	/**
	 * This function is check if cell is blank or not
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD isCellEmpty() function 
	 * UPDATED DATE: 6/20/2018
	 * @param cell
	 * @return cellValue
	 */
	public boolean isCellEmpty(Cell cell) {
		boolean cellType;
		switch (cell.getCellTypeEnum()) {
		case STRING:
			cellType = false;
			break;
		case BLANK:
			cellType = true;
			break;
		default:
			cellType = false;
		}
		return cellType;
	}
	
	/**
	 * This function is open a URL from excel file
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD openPage() function 
	 * UPDATED DATE: 6/19/2018
	 * @param fileName
	 * @param sheetName
	 * @param table
	 * @param row_index
	 * @throws Throwable
	 */
	public void openPage(String fileName, String sheetName, String[][] table, String row_index) throws Throwable {
		int dataRowIndex = Integer.parseInt(row_index);
		String url = getURL_FromExcel(table, dataRowIndex);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
//	public void createExcelFile(String fileName, String sheetName) throws Throwable {
//        workbook = new XSSFWorkbook(fileName);
//        sheet = workbook.createSheet(sheetName);
//		
//	}
	
//	public void setValue_FromExcel(String fileName, String sheetName, String[][] table, String value) throws Throwable {
//		String result = "";
//		String before = cell.getStringCellValue().trim().toString();
//		filePath = "\\src\\test\\resources\\DataIn\\";
//		String fileDir = System.getProperty("user.dir") + filePath + fileName;
//		openFileExcel(fileName, sheetName);
//		changeToSheet(sheetName);
//		int row_index = 0;
//		int row_excel_index = Integer.parseInt(table[row_index][0]);
//		int col_excel_index = Integer.parseInt(table[row_index][1]);
//		row = sheet.getRow(row_excel_index);
//		cell = row.getCell(col_excel_index);
//		
//		if(isCellEmpty(cell)) {
////			fileOut = new FileOutputStream(fileDir);
////			
////	        fileOut.close();
////			result = cell.setCellValue(value);
//			cell = row.createCell(col_excel_index);
//	        cell.setCellType(CellType.STRING);
//	        cell.setCellValue(value);
//	        fileOut = new FileOutputStream(fileDir);
//	        workbook.write(fileOut);
//		} else {
//			result = before + value;
//		}
//	}
	
	/**
	 * This function is scroll to bottom page
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD scrollToBottomPage() function 
	 * UPDATED DATE: 6/23/2018 
	 */
	public void scrollToBottomPage() {
		JavascriptExecutor js  = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	/**
	 * This function is scroll to top page
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD scrollToTopPage() function 
	 * UPDATED DATE: 6/23/2018 
	 */
	public void scrollToTopPage() {
		JavascriptExecutor js  = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
	}
	
	/**
	 * This function is scroll to element in page
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD scrollToElement() function 
	 * UPDATED DATE: 6/23/2018 
	 */
	public void scrollToElement(String eName, String eLocator) {
		JavascriptExecutor js  = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", getElement(eName, eLocator));
	}
		
	/**
	 * This function is get value from excel
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD getValue_FromExcel() function 
	 * UPDATED DATE: 6/26/2018 
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 * @throws IOException
	 */
	public String getValue_FromExcel(int rowIndex, int cellIndex) throws Throwable {
		String result = "";
		fileName = "Elements.xlsx";
		sheetName = "Elements";
		openFileExcel(fileName, sheetName);
		row = sheet.getRow(rowIndex);
		cell = row.getCell(cellIndex);
		result = cell.getStringCellValue().trim().toString();
		return result;
	}
	
	/**
	 * This function is get element from table
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD getElement() function 
	 * UPDATED DATE: 6/26/2018 
	 * @param table
	 * @return
	 * @throws Throwable 
	 */
	public WebElement getElement(String[][] table) throws Throwable {
		int row_table_default = 1; // Get row(1) of datatable
		int row_excel_index_eName = Integer.parseInt(table[row_table_default][0]);
		int col_excel_index_eName = Integer.parseInt(table[row_table_default][1]);
		String eName = getValue_FromExcel(row_excel_index_eName, col_excel_index_eName);
		String eLocator = getValue_FromExcel(row_excel_index_eName + 1, col_excel_index_eName);
		WebElement e = getElement(eName, eLocator);
		return e;
	}
	
	/**
	 * This function is open a page
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD openPage() function 
	 * UPDATED DATE: 6/26/2018 
	 * @param table
	 * @throws Throwable
	 */
	public void openPage(String[][] table) throws Throwable {
		int row_table_default = 1; // Get row(1) of datatable
		int row_excel_index_eName = Integer.parseInt(table[row_table_default][0]);
		int col_excel_index_eName = Integer.parseInt(table[row_table_default][1]);
		String url = getValue_FromExcel(row_excel_index_eName, col_excel_index_eName);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	/**
	 * This function is get text of the element
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD getText() function 
	 * UPDATED DATE: 6/26/2018 
	 * @param table
	 * @return
	 * @throws Throwable
	 */
	public String getText(String[][] table) throws Throwable {
		String myText = getElement(table).getText();
		return myText;
	}
	
	/**
	 * This function is click on element
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD click() function 
	 * UPDATED DATE: 6/26/2018 
	 * @param table
	 * @throws Throwable
	 */
	public void click(String[][] table) throws Throwable {
		getElement(table).click();
	}
	
	/**
	 * This function is input data
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD input() function 
	 * UPDATED DATE: 6/26/2018 
	 * @param table
	 * @param value
	 * @throws Throwable
	 */
	public void input(String[][] table, String value) throws Throwable {
		getElement(table).sendKeys(value);
	}
	
	/**
	 * This function is select drop down list
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD selectDropDown() function 
	 * UPDATED DATE: 6/26/2018  
	 * @param table
	 * @param type
	 * @param value
	 * @throws Throwable
	 */
	public void selectDropDown(String[][] table, String type, String value) throws Throwable {
		WebElement e = getElement(table);
		Select ddl = new Select(e);
		if(type.equalsIgnoreCase("text")) {
			ddl.selectByVisibleText(value);
		}
		else if(type.equalsIgnoreCase("index")) {
			ddl.selectByIndex(Integer.parseInt(value));
		}
		else if(type.equalsIgnoreCase("value")) {
			ddl.selectByValue(value);
		}
	}
	
	/**
	 * This function is input data from row
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD inputFromRow() function 
	 * UPDATED DATE: 6/26/2018 
	 * @param table
	 * @param data_row_index
	 * @throws Throwable
	 */
	public void inputFromRow(String[][] table, String data_row_index) throws Throwable {
		int row_table_default = 1; // Get row(1) of datatable
		int row_excel_index_eName = Integer.parseInt(table[row_table_default][0]);
		int col_excel_index_eName = Integer.parseInt(table[row_table_default][1]);
		
		data_row_index = getValue_FromExcel(row_excel_index_eName + 2, col_excel_index_eName);
		getElement(table).sendKeys(data_row_index);
	}
	
	
	
	
	
	
	
	
	
	
	public String getURL_FromExcel1(String fileName, String sheetName, String data_row, String[][] table) throws Throwable {
		String url = "";
		openFileExcel(fileName, sheetName);
//		int row_table_default = 1;
		int rowIndex = Integer.parseInt(data_row);
//		int row_excel_index = Integer.parseInt(table[rowIndex][0]);
		int col_excel_index = Integer.parseInt(table[0][1]);
		row = sheet.getRow(rowIndex);
		cell = row.getCell(col_excel_index);
		if (isCellEmpty(cell)) {
			url = null;
			System.out.println("Cell is blank");
		} else {
			url = cell.getStringCellValue().trim().toString();
		}
		return url;
	}
	
	
	
	
	
	
	
	
	

}
