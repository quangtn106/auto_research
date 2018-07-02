package support.utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperationHelper {
	private WebDriver driver = null;
	
	private String filePath = "";
	private String fileName = "";
	private String sheetName = "";
	private String sheetNameTest = "";
	private FileInputStream file;
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
	public void closePage() {
		driver.quit();
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
		System.out.println("Changed to sheet " + sheetNameTest);
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
	 * This function is get element with table as parameter
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD getElement(String[][] table) function 
	 * UPDATED DATE: 6/30/2018 
	 * @param table
	 * @return web elements
	 * @throws Throwable
	 */
	public WebElement getElement(String[][] table) throws Throwable {
		String eName = getValue_FromExcel(table, "1");
		String eLocator = getValue_FromExcel(table, "2");
		WebElement e = getElement(eName, eLocator);
		return e;
	}
	
	/**
	 * This function is open file excel with filename and sheetname as parameter
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD openFileExcel(String fileName, String sheetName) function 
	 * UPDATED DATE: 6/30/2018 
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
	 * This function is get value from excel
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD getValue_FromExcel(String[][] table, String row_index) function 
	 * UPDATED DATE: 6/30/2018
	 * @param table
	 * @param row_index
	 * @return value of cells
	 * @throws Throwable
	 */
	public String getValue_FromExcel(String[][] table, String row_index) throws Throwable {
		String value = "";
		int row_excel_index = Integer.parseInt(row_index);
		int col_excel_index = Integer.parseInt(table[1][0]);
		row = sheet.getRow(row_excel_index);
		cell = row.getCell(col_excel_index);
		if (isCellEmpty(cell)) {
			value = null;
			System.out.println("Cell is blank");
		} else {
			value = cell.getStringCellValue().trim().toString();
		}
		return value;
	}
	
	public void openPage(String fileName, String sheetName, String[][] table, String row_index) throws Throwable {
		String url = getValue_FromExcel(table, row_index);
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println(url); //print string url
	}
	
	public By getIndentifer(String[][] table) throws Throwable{
		String eName = getValue_FromExcel(table, "1");
		String eLocator = getValue_FromExcel(table, "2");
		if (eName.contains("-id")) {
			return By.id(eLocator);
		}
		if (eName.contains("-xp")) {
			return By.xpath(eLocator);
		}
		if (eName.contains("-name")){
			return By.name(eLocator);
		}
		return null;
	}
	
	public void waitForElementVisible(String[][] table, int timeOut) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getIndentifer(table)));
	}
	
	public void verifyElementDisplayed(String[][] table) throws Throwable {
		WebElement e = getElement(table);
		Assert.assertTrue(e.isDisplayed());
	}

	public void verifyElementWithText(String[][] table, String row_index) throws Throwable {
		String eData = getValue_FromExcel(table, row_index);
		String eText = getElement(table).getText();
		verifyElementDisplayed(table);
		Assert.assertEquals(eData, eText);
	}
	
	

}
