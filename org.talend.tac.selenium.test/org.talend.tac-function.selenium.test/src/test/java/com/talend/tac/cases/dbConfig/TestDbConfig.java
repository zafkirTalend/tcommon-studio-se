package com.talend.tac.cases.dbConfig;

import static org.testng.Assert.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.base.Base;

public class TestDbConfig extends Base {

	@Test(groups = { "initDB" })
	@Parameters( { "db.url", "db.userName", "db.userPassWd", "db.driver" })
	public void testDbConfig(String url, String userName, String userPassWd,
			String driver) {
		selenium.click("idLoginOpenDbConfigButton");
		assertTrue(selenium.isTextPresent("Database parameters"));
		selenium.type("idDbConfigLoginPasswordInput", "admin");
		selenium.click("idDbConfigSubmitButton");
		selenium.type("idDbConfigUrlInput", url);
		selenium.type("idDbConfigUserNmeInput", userName);
		selenium.type("idDbConfigPasswordInput", userPassWd);
		selenium.type("idDbConfigDriverInput", driver);
		selenium.click("idDbConfigCheckButton");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// System.out.println(selenium.getXpathCount("//div[text()='OK']"));
		if (selenium.getXpathCount("//div[text()='OK']").intValue() != 3) {
			fail("At least one parameter of DbConfig is wrong.");
		}
		// simulate clicking ENTER to make button enabled.
		selenium.keyDown("idDbConfigDriverInput", "\\13");
		selenium.keyUp("idDbConfigDriverInput", "\\13");
		selenium.click("idDbConfigSaveButton");
		selenium.click("idDbConfigLogoutButton");
		
		this.assertTrueIsElementPresent("idLoginOpenDbConfigButton");
//		assertTrue(selenium.isElementPresent("idLoginOpenDbConfigButton"));
	}

	@AfterTest
	public void killBrowser() {
		selenium.stop();
	}

}
