package com.talend.tac.cases.dbConfig;

import static org.testng.Assert.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestDbConfig extends DbConfig {

	@Test(groups = { "initDB" })
	@Parameters( { "db.url", "db.userName", "db.userPassWd", "db.driver",
			"license.file.path" })
	public void testDbConfig(String url, String userName, String userPassWd,
			String driver, String license) {

		this.DbConfigProcess(url, userName, userPassWd, driver);
		waitForCheckConnectionStatus(4);
		// simulate clicking ENTER to make button enabled.
		selenium.keyDown("idDbConfigDriverInput", "\\13");
		selenium.keyUp("idDbConfigDriverInput", "\\13");
		selenium.click("idDbConfigSaveButton");

		// if the parameter is saved successfully ,the save button will turn
		// gray.
		selenium
				.waitForCondition(
						"selenium.isElementPresent(\"//table[contains(@class,'disabled')]//button[@id='idDbConfigSaveButton']\")",
						"30000");
		waitForCheckConnectionStatus(4);
		//if no license, load the license from a file.
		System.out.println(selenium.isElementPresent("//div[text()='License Expired']") || selenium.isElementPresent("//div[text()='No license yet']"));
//		if(selenium.isElementPresent("//div[text()='License Expired']") || selenium.isElementPresent("//div[text()='No license yet']")){
			selenium.click("//button[text()='set new license']");
			selenium.type("//button[contains(text(),'Browse')]/ancestor::table[1]/preceding-sibling::input[1]", license);
			selenium.click("//button[text()='Upload']");
			if (selenium.isTextPresent("New license set"))
				clickWaitForElementPresent("//button[text()='Ok']");
			waitForCheckConnectionStatus(5);
//		}
		selenium.click("idDbConfigLogoutButton");
		waitForElementPresent("idLoginInput", WAIT_TIME);
	}

	@AfterTest
	public void killBrowser() {
		selenium.stop();
	}

}
