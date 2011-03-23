package com.talend.tac.cases.dbConfig;

import static org.testng.Assert.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestDbConfig extends DbConfig {

	@Test(groups = { "initDB" })
	@Parameters( { "db.url", "db.userName", "db.userPassWd", "db.driver",
			"license" })
	public void testDbConfig(String url, String userName, String userPassWd,
			String driver, String license) {

		this.DbConfigProcess(url, userName, userPassWd, driver);
		waitForCheckConnectionStatus();
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
		waitForCheckConnectionStatus();
		selenium.click("idDbConfigLogoutButton");

		// license popup should be shown after clicking "go to login Page"
		// wait and judge the license popup
		// ->define the flag=false to avoid
		// "Couldn't access document.body.  Is this HTML page fully loaded?"
		boolean flag = false;
		while (flag == false) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = selenium.isTextPresent("No license set")
					|| selenium.isElementPresent("idLoginInput");
		}

		if (selenium.isTextPresent("No license set")) {
			selenium.type(
					"//label[text()='License:']/following-sibling::div//input",
					license);
			selenium.click("//button[text()='Ok']");
			if (selenium.isTextPresent("New license set"))
				selenium.click("//button[text()='Ok']");
		}
		waitForElementPresent("idLoginInput", WAIT_TIME);
	}

	@AfterTest
	public void killBrowser() {
		selenium.stop();
	}

}
