package support.steps;

import org.junit.Test;
import support.utils.OperationHelper;

public class StepsDefinations {
	private static String[][] table;
	private static String dataRow = "";

	public static void main(String[] args) throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Flights_Search");
		support.launch("chrome");
		
		// Given I am on Home Page of "Expedia"
		table = new String[][] {
			{ "Col_Index" },
			{ "1" }
		};
		dataRow = "0";
		support.openPage("Elements.xlsx", "Flights_Search", table, dataRow);
		
		// When I click on "Flights" tab
		support.waitForElementVisible(table, 20);
		support.click(table);
		
		// When I choose Roundtrip
		table = new String[][] {
			{ "Col_Index" },
			{ "2" }
		};
		support.waitForElementVisible(table, 20);
		support.click(table);
		
		// When I input "Ho Chi Minh"
		table = new String[][] {
			{ "Col_Index" },
			{ "5" }
		};
		support.click(table);
		support.sendKey(table);
		
		// And I choose the correct suggestion
		table = new String[][] {
			{ "Col_Index" },
			{ "6" }
		};
		support.waitForElementClickable(table, 20);
		support.selectListElement(table);
		
		// When I input "Ha Noi"
		table = new String[][] {
			{ "Col_Index" },
			{ "7" }
		};
		support.click(table);
		support.sendKey(table);
		
		// And I choose the correct suggestion
		table = new String[][] {
			{ "Col_Index" },
			{ "8" }
		};
		support.waitForElementVisible(table, 20);
		support.selectListElement(table);
		
		// When I choose date of departing
		// And I choose date of returning
		// When I choose travelers with "1" adult, "2" children, "1" age under 2
		// And I click on "Search" button
		// Then The webpage should display correct data
		// Close page
		Thread.sleep(2000); //just for test
		support.closePage();
	}
	
	//Verify menu Account
//	@Test
	public static void TC001() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Login_Page");
		support.launch("chrome");
		table = new String[][] { 
			{ "Col_Index" }, 
			{ "1" } 
		};
		String dataRowUrl = "0";
		support.openPage("Elements.xlsx", "Login_Page", table, dataRowUrl);
		support.waitForElementVisible(table, 20);
		String dataRowData = "3";
		support.verifyElementWithText(table, dataRowData);
		support.closePage();
	}
	
	//Verify menu Add Your Property
	@Test
	public void TC002() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Login_Page");
		support.launch("chrome");
		table = new String[][] {
			{ "Col_Index" },
			{ "1" }
		};
		String dataRowUrl = "0";
		support.openPage("Elements.xlsx", "Login_Page", table, dataRowUrl);
		support.waitForElementVisible(table, 20);
		table = new String[][] {
			{ "Col_Index" },
			{ "2" }
		};
		String dataRowData = "3";
		support.verifyElementWithText(table, dataRowData);
		support.closePage();
	}
	
	//Verify menu My Lists
	@Test
	public void TC003() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Login_Page");
		support.launch("chrome");
		table = new String[][] {
			{ "Col_Index" },
			{ "1" }
		};
		String dataRowURL = "0";
		support.openPage("Elements.xlsx", "Login_Page", table, dataRowURL);
		support.waitForElementVisible(table, 20);
		table = new String[][] {
			{ "Col_Index" },
			{ "3" }
		};
		String dataRowData = "3";
		support.verifyElementWithText(table, dataRowData);
		support.closePage();
	}
	
	//Verify menu My Trips
	@Test
	public void TC004() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Login_Page");
		support.launch("chrome");
		table = new String[][] {
			{ "Col_Index" },
			{ "1" }
		};
		String dataRowURL = "0";
		support.openPage("Elements.xlsx", "Login_Page", table, dataRowURL);
		support.waitForElementVisible(table, 20);
		table = new String[][] {
			{ "Col_Index" },
			{ "4" }
		};
		String dataRowData = "3";
		support.verifyElementWithText(table, dataRowData);
		support.closePage();
	}
	
	//Verify menu Support
	@Test
	public void TC005() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Login_Page");
		support.launch("chrome");
		table = new String[][] {
			{ "Col_Index" },
			{ "1" }
		};
		String dataRowURL = "0";
		support.openPage("Elements.xlsx", "Login_Page", table, dataRowURL);
		support.waitForElementVisible(table, 20);
		table = new String[][] {
			{ "Col_Index" },
			{ "5" }
		};
		String dataRowData = "3";
		support.verifyElementWithText(table, dataRowData);
		support.closePage();
	}
	
	//Verify menu LanguageES
	@Test
	public void TC006() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Login_Page");
		support.launch("chrome");
		table = new String[][] {
			{ "Col_Index" },
			{ "1" }
		};
		String dataRowURL = "0";
		support.openPage("Elements.xlsx", "Login_Page", table, dataRowURL);
		support.waitForElementVisible(table, 20);
		table = new String[][] {
			{ "Col_Index" },
			{ "6" }
		};
		String dataRowData = "3";
		support.verifyElementWithText(table, dataRowData);
		support.closePage();
	}
	
	//Verify menu LanguageCH
	@Test
	public void TC007() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Login_Page");
		support.launch("chrome");
		table = new String[][] {
			{ "Col_Index" },
			{ "1" }
		};
		String dataRowURL = "0";
		support.openPage("Elements.xlsx", "Login_Page", table, dataRowURL);
		support.waitForElementVisible(table, 20);
		table = new String[][] {
			{ "Col_Index" },
			{ "7" }
		};
		String dataRowData = "3";
		support.verifyElementWithText(table, dataRowData);
		support.closePage();
	}
	
	//Sprint URLs in sheet URL_List
	public static void TC008() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.openFileExcel("Elements.xlsx", "Login_Page");
		support.changeToSheet("URL_List");
		support.launch("chrome");
		table = new String[][] {
			{ "Col_Index" },
			{ "1" }
		};
		support.openPage("Elements.xlsx", "URL_List", table, "1");
		support.closePage();
		support.launch("chrome");
		support.openPage("Elements.xlsx", "URL_List", table, "4");
		support.closePage();
	}
}
