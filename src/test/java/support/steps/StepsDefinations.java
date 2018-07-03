package support.steps;

import org.junit.Assert;
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
		support.openFileExcel("Elements.xlsx", "Flights_Search");
		support.launch("chrome");

		// Given I am on Home Page of "Expedia"
		table = new String[][] { { "Col_Index" }, { "1" } };
		dataRow = "0";
		support.openPage("Elements.xlsx", "Flights_Search", table, dataRow);

		// When I click on "Flights" tab
		support.waitForElementVisible(table, 20);
		support.click(table);

		// When I choose Roundtrip
		table = new String[][] { { "Col_Index" }, { "2" } };
		support.waitForElementVisible(table, 20);
		support.click(table);

		// When I input "Ho Chi Minh"
		table = new String[][] { { "Col_Index" }, { "5" } };
		support.click(table);
		support.sendKey(table);

		// And I choose the correct suggestion
		table = new String[][] { { "Col_Index" }, { "6" } };
		support.waitForElementClickable(table, 20);
		support.selectListElement(table);

		// When I input "Ha Noi"
		table = new String[][] { { "Col_Index" }, { "7" } };
		support.click(table);
		support.sendKey(table);

		// And I choose the correct suggestion
		table = new String[][] { { "Col_Index" }, { "8" } };
		support.waitForElementVisible(table, 20);
		support.selectListElement(table);

		// When I choose date of departing
		table = new String[][] { { "Col_Index" }, { "12" } };
		support.waitForElementClickable(table, 20);
		support.click(table);
		table = new String[][] { { "Col_Index" }, { "13" } };
		support.waitForElementVisible(table, 20);
		support.selectDatePicker(table, "10");

		// And I choose date of returning
		table = new String[][] { { "Col_Index" }, { "14" } };
		support.waitForElementClickable(table, 20);
		support.click(table);
		table = new String[][] { { "Col_Index" }, { "15" } };
		support.waitForElementVisible(table, 20);
		support.selectDatePicker(table, "15");
		
		// When I choose travelers with "1" adult, "2" children, "1" age under 2
		table = new String[][] { { "Col_Index" }, { "16" } };
		support.click(table);
		table = new String[][] { { "Col_Index" }, { "17" } };
		support.waitForElementClickable(table, 20);
		support.click(table);
		support.click(table);
		table = new String[][] { { "Col_Index" }, { "19" } };
		support.waitForElementClickable(table, 20);
		support.selectDropDown(table, "text", "10");
		table = new String[][] { { "Col_Index" }, { "20" } };
		support.selectDropDown(table, "text", "6");
		table = new String[][] { { "Col_Index" }, { "18" } };
		support.click(table);
		table = new String[][] { { "Col_Index" }, { "21" } };
		support.waitForElementClickable(table, 20);
		support.selectDropDown(table, "text", "Under 1");
		table = new String[][] { { "Col_Index" }, { "22" } };
		support.click(table);
			
		// And I click on "Search" button
		table = new String[][] { { "Col_Index" }, { "11" } };
		support.click(table);
		
		// Then The webpage should display correct data
		table = new String[][] { { "Col_Index" }, { "23" } };
		support.waitForElementVisible(table, 20);
		String actualDepartFlight = support.getAttribute(table, "value");
		String expectedDepartFlight = support.getData_FromTable(table, "3");
		Assert.assertEquals(expectedDepartFlight, actualDepartFlight);
		table = new String[][] { { "Col_Index" }, { "24" } };
		String actualReturnFlight = support.getAttribute(table, "value");
		String expectedReturnFlight = support.getData_FromTable(table, "3");
		Assert.assertEquals(expectedReturnFlight, actualReturnFlight);
		table = new String[][] { { "Col_Index" }, { "25" } };
		String actualDepartDay = support.getAttribute(table, "value");
		String expectedDepartDay = support.getData_FromTable(table, "3");
		Assert.assertEquals(expectedDepartDay, actualDepartDay);
		table = new String[][] { { "Col_Index" }, { "26" } };
		String actualReturnDay = support.getAttribute(table, "value");
		String expectedReturnDay = support.getData_FromTable(table, "3");
		Assert.assertEquals(expectedReturnDay, actualReturnDay);
		
		// Close page
		support.closePage();
	}
}
