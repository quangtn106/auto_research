package support.utils;

import java.util.List;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.Point;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class OperationHelper {
	private WebDriver driver = null;
	
	private String filePath = "";
	private String sheetNameTest = "";
	private FileInputStream file;
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;
		
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
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", rootPath + driverPath + "geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", rootPath + driverPath + "MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", rootPath + driverPath + "IEDriverServer_win_x64.exe");
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("Browser is invalid");
		}
	}
	
	public void max() {
		driver.manage().window().maximize();
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
	
	public String getText(String eName, String eLocator) {
		String myText = getElement(eName, eLocator).getText();
		return myText;
	}
	
	public String getAttribute(String[][] table, String att) throws Throwable {
		String myAtt = getElement(table).getAttribute(att);
		return myAtt;
	}
	
	public String getAttribute(String eName, String eLocator, String att) {
		String myAtt = getElement(eName, eLocator).getAttribute(att);
		return myAtt;
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
	
	public void click(String eName, String eLocator) {
		getElement(eName, eLocator).click();
	}
	
	/**
	 * 
	 * @param table
	 * @throws Throwable
	 */
	public void sendKey(String[][] table, String row_index) throws Throwable {
		String value = getValue_FromDataTable(table, row_index);
		getElement(table).sendKeys(value);
	}
	
	public void sendKey_WithValue1(String[][] table, String value) throws Throwable {
		getElement(table).sendKeys(value);
	}
	
	public void sendKey_WithValue(String[][] table, String value) throws Throwable {
		Actions actions = new Actions(driver);
		WebElement element = getElement(table);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(value);
		actions.build().perform();
	}
		
	public void sendKey(String eName, String eLocator, String value) {
		WebElement e = getElement(eName, eLocator);
		e.sendKeys(value);
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
				e = driver.findElement(getIdentifer(eName, eLocator));
			} else if (eName.contains("-name")) {
				e = driver.findElement(getIdentifer(eName, eLocator));
			} else if (eName.contains("-xp")) {
				e = driver.findElement(getIdentifer(eName, eLocator));
			} else if (eName.contains("-css")) {
				e = driver.findElement(getIdentifer(eName, eLocator));
			} else {
				System.out.println("objectName is invalid");
				return null;
			}
			return e;
		}
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
		String eName = getValue_FromDataTable(table, "1");
		String eLocator = getValue_FromDataTable(table, "2");
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
		workbook = WorkbookFactory.create(file);
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
 	public String getValue_FromExcel(int col_excel, int row_excel) throws Throwable {
		String value = "";
		row = sheet.getRow(row_excel);
		cell = row.getCell(col_excel);
		if (isCellEmpty(cell)) {
			value = null;
			System.out.println("Cell is blank");
		} else {
			value = cell.getStringCellValue().trim().toString();
		}
		return value;
	}
 	
 	public String getValue_FromDataTable(String[][] table, String row_index) throws Throwable {
		int row_excel_index = Integer.parseInt(row_index);
		int col_excel_index = Integer.parseInt(table[1][0]);
 		String data = getValue_FromExcel(col_excel_index, row_excel_index);
 		return data;
 	}
	
	/**
	 * This function is open page with url get from excel
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD openPage(String fileName, String sheetName, String[][] table, String row_index) function 
	 * UPDATED DATE: 7/1/2018
	 * @param fileName
	 * @param sheetName
	 * @param table
	 * @param row_index
	 * @throws Throwable
	 */
	public void openPage(String fileName, String sheetName, String[][] table, String row_index) throws Throwable {
		openFileExcel(fileName, sheetName);
		String url = getValue_FromDataTable(table, row_index);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void openPage(String url) {
		driver.get(url);
	}
	
	/**
	 * This function is get identify of elements
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD getIndentifer(String[][] table) function 
	 * UPDATED DATE: 7/1/2018
	 * @param table
	 * @return
	 * @throws Throwable
	 */
	public By getIdentifer(String[][] table) throws Throwable{
		String eName = getValue_FromDataTable(table, "1");
		String eLocator = getValue_FromDataTable(table, "2");
		if (eName.contains("-id")) {
			return By.id(eLocator);
		} else if (eName.contains("-xp")) {
			return By.xpath(eLocator);
		} else if (eName.contains("-name")){
			return By.name(eLocator);
		} else {
			return null;
		}
	}
	
	public By getIdentifer(String eName, String eLocator) {
		if (eName.contains("-id")) {
			return By.id(eLocator);
		} else if (eName.contains("-xp")) {
			return By.xpath(eLocator);
		} else if (eName.contains("-name")){
			return By.name(eLocator);
		} else {
			return null;
		}
	}
	
	/**
	 * This function is wait for element visible
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD waitForElementVisible(String[][] table, int timeOut) function 
	 * UPDATED DATE: 7/1/2018
	 * @param table
	 * @param timeOut
	 * @throws Throwable
	 */
	public void waitForElementVisible(String[][] table, int timeOut) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getIdentifer(table)));
	}
	
	/**
	 * This function is verify element displayed
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD verifyElementDisplayed(String[][] table) function 
	 * UPDATED DATE: 7/1/2018
	 * @param table
	 * @throws Throwable
	 */
	public void verifyElementDisplayed(String[][] table) throws Throwable {
		WebElement e = getElement(table);
		Assert.assertTrue(e.isDisplayed());
	}

	/**
	 * This function is verify element with text
	 * AUTHOR: TRAN NGOC QUANG - SU 3 - GROUP 2 
	 * MODIFIED: 
	 * 		+ ADD verifyElementWithText(String[][] table, String row_index) function 
	 * UPDATED DATE: 7/1/2018
	 * @param table
	 * @param row_index
	 * @throws Throwable
	 */
	public void verifyElementWithText(String[][] table, String row_index) throws Throwable {
		String eData = getValue_FromDataTable(table, row_index);
		String eText = getElement(table).getText();
		verifyElementDisplayed(table);
		Assert.assertEquals(eData, eText);
	}
	
	public void verifyElementWithAttribute(String[][] table, String row_index, String att) throws Throwable {
		String eData = getValue_FromDataTable(table, row_index);
		String eText = getAttribute(table, att);
		verifyElementDisplayed(table);
		Assert.assertEquals(eData, eText);
	}
	
	public void selectListElement(String[][] table, String value, String tagName) throws Throwable {
		WebElement select = getElement(table);
		value.trim().toLowerCase();
		List<WebElement> options = select.findElements(By.tagName(tagName));
		for(WebElement option : options) {
			if(option.getText().trim().toLowerCase().contains(value)) {
				option.click();
				break;
			}
		}
	}
	
	public void waitForElementClickable(String[][] table, int timeOut) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(getIdentifer(table)));
	}

	public void selectDropDown(String[][] table, String type, String value) throws Throwable {
		WebElement e = getElement(table);
		Select ddl = new Select(e);
		if (type.equalsIgnoreCase("text")) {
			ddl.selectByVisibleText(value);
		} else if (type.equalsIgnoreCase("index")) {
			ddl.selectByIndex(Integer.parseInt(value));
		} else if (type.equalsIgnoreCase("value")) {
			ddl.selectByValue(value);
		}
	}

	public void uploadImage(String[][] table, String iName) throws Throwable {
		String rootPath = System.getProperty("user.dir");
		filePath = "\\src\\test\\resources\\DataIn\\";
		String fileDir = rootPath + filePath + iName;
		sendKey_WithValue1(table, fileDir);
	}
	
	public void takeScrShot_OfElement(String eName, String eLocator, String fileName) throws IOException {
		String rootPath = System.getProperty("user.dir");
		String filePath = rootPath + "\\src\\test\\resources\\DataIn\\img\\";
		
		WebElement e = getElement(eName, eLocator);
		
		// Get entire page screenshot
		TakesScreenshot scrShot = (TakesScreenshot)driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(srcFile);
		// Get the location of element on the page
		Point point = e.getLocation();
		// Get width and height of the element
		int eWidth = e.getSize().getWidth();
		int eHeigh = e.getSize().getHeight();
		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eScrShot = fullImg.getSubimage(point.getX(), point.getY(), eWidth, eHeigh);
		ImageIO.write(eScrShot, "png", srcFile);
		// Copy the element screenshot to disk
		File destFile = new File(filePath + fileName);
		FileUtils.copyFile(srcFile, destFile);
	}
	
	public void takeScrShot_OfPage(String fileName) throws IOException {
		String rootPath = System.getProperty("user.dir");
		String filePath = rootPath + "\\src\\test\\resources\\DataIn\\img\\";
		TakesScreenshot scrShot = (TakesScreenshot)driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath + fileName);
		FileUtils.copyFile(srcFile,destFile);
	}
	
	public void uploadSikuli(String fileName) throws Throwable {
		String rootPath = System.getProperty("user.dir");
		String filePath = rootPath + "\\src\\test\\resources\\DataIn\\img\\";
		String inputFilePath = rootPath + "\\src\\test\\resources\\DataIn\\img\\" + fileName;
		Screen s = new Screen();
		Pattern fileInputName = new Pattern(filePath + "fileInputName.PNG");
		Pattern openButton = new Pattern(filePath + "openButton.PNG");
		s.wait(fileInputName, 20);
		s.type(fileInputName, inputFilePath);
		s.click(openButton);
	}
	
	public Cookie getCookies() {
		driver.manage().getCookies();
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void getCookie() throws IOException {
		CookieManager cookieManager = new CookieManager();
		CookieHandler.setDefault(cookieManager);

		String urlParameters = "j_username=qateam&j_password=Lindy%20allegro&j_validate=true&_charset_=utf-8";

		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		String request = "http://author.dev.genesis.monkapps.com:4502/libs/granite/core/content/login.html/j_security_check";
		URL url = new URL(request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		conn.setUseCaches(false);
		try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
			wr.write(postData);
		}
		System.err.println(conn.getContent());

		List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
		for (HttpCookie cookie : cookies) {
			System.out.println(cookie.getDomain());
			System.out.println(cookie);
		}

		System.out.println(conn.getResponseMessage());
		System.out.println(conn.getResponseCode());
		UpdateURL("");
	}

	public void UpdateURL(String url) throws IOException {
		try {
			URL url111 = new URL(
					"http://author.dev.genesis.monkapps.com:4502/content/genesis/us/en/QA-testing/Testspr1.html?wcmmode=disabled");
			HttpURLConnection hConnection = (HttpURLConnection) url111.openConnection();
			HttpURLConnection.setFollowRedirects(true);

			hConnection.setDoOutput(true);
			hConnection.setRequestMethod("GET");

			// PrintStream ps = new PrintStream(hConnection.getOutputStream());
			// ps.print("username='123'&amp;password='555'");
			// ps.close();

			hConnection.connect();
			System.err.println("debug: " + hConnection.getResponseCode());

			if (HttpURLConnection.HTTP_OK == hConnection.getResponseCode()) {
				InputStream is = hConnection.getInputStream();
				OutputStream os = new FileOutputStream("output.html");
				int data;
				while ((data = is.read()) != -1) {
					os.write(data);
				}
				is.close();
				os.close();
				hConnection.disconnect();

				System.err.println("debug: " + hConnection.getResponseCode());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//System.err.println("debug: ");

	}
}
