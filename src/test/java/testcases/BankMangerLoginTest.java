package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BankMangerLoginTest extends BaseTest {

	@Test
	public void loginAsBankManager() throws IOException{
	
		click("bmlbtn_CSS");
		Assert.assertTrue(isElementPresent("addCustBtn_CSS"),"Add Customer Button not Exist");
		
	}
}
