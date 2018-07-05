package support.steps;

import org.junit.Test;

import support.utils.OperationHelper;

public class StepsDefinations {
	private static  String[][] table;
	private static String dataRow = "";

	public static void main(String[] args) throws Throwable {

	}
	
	@Test
	public void TC001() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.launch("chrome");

		// Given I am on Home Page of "Expedia"
		table = new String[][] { { "Col_Index" }, { "1" } };
		dataRow = "0";
		support.openPage("Elements.xlsx", "Flights_Search", table, dataRow);

		// When I click on "Flights" tab
		support.waitForElementVisible(table, 20);
		support.click(table);

		// When I choose date of departing
		table = new String[][] { { "Col_Index" }, { "12" } };
		support.waitForElementClickable(table, 20);
		support.click(table);
		table = new String[][] { { "Col_Index" }, { "13" } };
		support.waitForElementVisible(table, 20);
		support.selectDatePicker(table, "10");

		
		
		
		
		
		
		
		
		
		
		
		

		
		// Close page
		support.closePage();
	}
}
