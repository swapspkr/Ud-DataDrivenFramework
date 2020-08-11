package testcases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;
public class AddCustomerTest extends BaseTest {

	
	@Test(dataProvider="readExcel")
	public void addCustomer(String firstname, String lastName,String pincode, String alerttxt) {
		
		click("addCustBtn_CSS");
		type("firstName_CSS",firstname);
		type("lastName_CSS",lastName);
		type("post_CSS",pincode);
		click("addCustomer_CSS");
		Alert alert = driver.switchTo().alert();	
		Assert.assertTrue(alert.getText().contains(alerttxt),"Customer not added");
		alert.accept();
	}
	
	@DataProvider()
	public Object[][] readExcel() throws IOException {
		return TestUtil.readExcel("AddCustomerTest");
	}

	
}
