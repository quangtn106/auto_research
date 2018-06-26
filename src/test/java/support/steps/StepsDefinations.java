package support.steps;

import support.utils.OperationHelper;

public class StepsDefinations {
	private static String[][] table;

	public static void main(String[] args) throws Throwable {
		TC002();
	}

	private static void TC001() throws Throwable {
		OperationHelper support = new OperationHelper();
		support.launch("chrome");
		table = new String[][] { 
			{ "Row_Index", "Column_Index" }, 
			{ "0", "1" } 
		};
		support.openPage(table);
		Thread.sleep(2000); // just for test
		table = new String[][] { 
			{ "Row_Index", "Column_Index" }, 
			{ "1", "1" } 
		};
		support.inputFromRow(table, "4");
		table = new String[][] { 
			{ "Row_Index", "Column_Index" }, 
			{ "1", "2" } 
		};
		support.click(table);
		Thread.sleep(3000);
		support.close();
	}
	
	private static void TC002() throws Throwable {
		OperationHelper support = new OperationHelper();
		table = new String[][] {
				{"Col_Index"}
				,{"1"}
		};
//		String data_row_url = "1";
		System.out.println(support.getURL_FromExcel(table, 1));
	}
}
