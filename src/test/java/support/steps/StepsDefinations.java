package support.steps;

import org.junit.Test;

import support.utils.OperationHelper;

public class StepsDefinations {
	private static String[][] table;
	private static String dataRow = "";

	public static void main(String[] args) throws Throwable {
		OperationHelper support = new OperationHelper();
		support.launch("chrome");
		support.max();
		support.openPage("https://www.google.com");
		support.takeScrShot_OfElement("logo-id", "hplogo", "element_scr_test.png");
		support.takeScrShot_OfPage("google_page.png");
		
		Thread.sleep(3000);
		support.closePage();
	}

	public void TC001() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.launch("chrome");

		table = new String[][] { { "Col_Index" }, { "1" } };
		dataRow = "0";
		support.openPage("Elements.xlsx", "Limo_Register", table, dataRow);

		support.click(table);

		table = new String[][] { { "Col_Index" }, { "2" } };
		support.uploadImage(table, "image.png");

		// When I input Email Address
		table = new String[][] { { "Col_Index" }, { "3" } };
		support.click(table);
		support.sendKey_WithValue(table, "acza@gmail.com");
		// When I input Password
		table = new String[][] { { "Col_Index" }, { "4" } };
		support.click(table);
		support.sendKey_WithValue(table, "123456789");
		// When I input Re-Type Password
		table = new String[][] { { "Col_Index" }, { "5" } };
		support.click(table);
		support.sendKey_WithValue(table, "123456789");
		// When I choose Personal from Account Profile
		table = new String[][] { { "Col_Index" }, { "6" } };
		support.selectDropDown(table, "text", "Personal");
		// When I click Mr. or Ms. check box
		table = new String[][] { { "Col_Index" }, { "8" } };
		support.click(table);
		// When I input First Name
		table = new String[][] { { "Col_Index" }, { "9" } };
		support.click(table);
		support.sendKey_WithValue(table, "test");
		// When I input Last Name
		table = new String[][] { { "Col_Index" }, { "10" } };
		support.sendKey_WithValue(table, "sutrix");
		// When I input 1st Number Phone
		table = new String[][] { { "Col_Index" }, { "12" } };
		support.waitForElementVisible(table, 20);
		support.click(table);
		support.sendKey_WithValue(table, "0123456789");
		// When I input 2nd Number Phone
		table = new String[][] { { "Col_Index" }, { "13" } };
		support.sendKey_WithValue(table, "0123456789");
		// When I input Cost Center Name
		table = new String[][] { { "Col_Index" }, { "14" } };
		support.sendKey_WithValue(table, "sutrix");
		// When I input Main Address
		table = new String[][] { { "Col_Index" }, { "15" } };
		support.sendKey_WithValue(table, "01 bach dang");
		// When I input Street No
		table = new String[][] { { "Col_Index" }, { "16" } };
		support.sendKey_WithValue(table, "01");
		// When I input Route
		table = new String[][] { { "Col_Index" }, { "17" } };
		support.sendKey_WithValue(table, "bach dang");
		// When I input Complement Address
		table = new String[][] { { "Col_Index" }, { "18" } };
		support.sendKey_WithValue(table, "01 bach dang");
		// When I input City
		table = new String[][] { { "Col_Index" }, { "19" } };
		support.sendKey_WithValue(table, "hcm");
		// When I input State
		table = new String[][] { { "Col_Index" }, { "20" } };
		support.sendKey_WithValue(table, "hcm");
		// When I input Zipcode
		table = new String[][] { { "Col_Index" }, { "21" } };
		support.sendKey_WithValue(table, "70000");
		// When I input Country
		table = new String[][] { { "Col_Index" }, { "22" } };
		support.sendKey_WithValue(table, "vn");
		// When I input Contact Name
		table = new String[][] { { "Col_Index" }, { "23" } };
		support.sendKey_WithValue(table, "test");
		// When I input Phone
		table = new String[][] { { "Col_Index" }, { "24" } };
		support.sendKey_WithValue(table, "0123456789");
		// When I click "I agree to Terms & Conditions"
		table = new String[][] { { "Col_Index" }, { "25" } };
		support.click(table);
		table = new String[][] { { "Col_Index" }, { "26" } };
		support.waitForElementClickable(table, 20);
		support.click(table);
		// And I click Register button
		// table = new String[][] { { "Col_Index" }, { "27" } };
		// support.click(table);

		// Close page
		Thread.sleep(3000);
		support.closePage();
	}

	@Test
	public void TC002() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.launch("chrome");

		table = new String[][] { { "Col_Index" }, { "1" } };
		dataRow = "0";
		support.openPage("Elements.xlsx", "Limo_Register", table, dataRow);

		support.click(table);

		// When I click Register button
		table = new String[][] { { "Col_Index" }, { "27" } };
		support.click(table);
		
		dataRow = "3";
		table = new String[][] { { "Col_Index" }, { "28" } };
		support.verifyElementWithText(table, dataRow);
		
		// Close page
		Thread.sleep(3000);
		support.closePage();
	}

}
