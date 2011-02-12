package com.talend.tac.cases.license;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class AddLicense extends Login {
	@Test
	@Parameters({"license"})
	public void testAddLicense(String license) {

		selenium.click("idMenuLicenseElement");
		
		selenium.click("idEditButton");//improve later
		selenium
				.type(
						"//span[text()='Enter your license key']/following-sibling::div/input",
						license);
		selenium.click("//button[text()= 'Ok'");
	}

	
}
