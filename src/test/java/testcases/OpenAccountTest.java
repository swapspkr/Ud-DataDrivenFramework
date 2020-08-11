package testcases;


import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class OpenAccountTest extends BaseTest {


	
	@Test(dataProvider="readExcel")
	public void openAccount(String customer, String currency) {
		
		click("openAccount_XPATH");
		select("customer_XPATH",customer);
		select("currency_CSS",currency);
		click("process_CSS");
		
	}
	
	@DataProvider()
	public Object[][] readExcel() throws IOException {
		
		return TestUtil.readExcel("OpenAccountTest");
	}
	

}
