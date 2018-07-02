package support.steps;

import org.junit.Test;
import support.utils.OperationHelper;

public class StepsDefinations {
	private static String[][] table;

	public static void main(String[] args) throws Throwable {
		TC008();
	}
	
	//Verify menu Account
	@Test
	public void TC001() throws Throwable {
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
