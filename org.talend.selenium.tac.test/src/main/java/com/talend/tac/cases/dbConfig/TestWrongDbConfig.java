package com.talend.tac.cases.dbConfig;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestWrongDbConfig extends DbConfig {

	@Test
	@Parameters( { "db.url", "db.userName", "db.userPassWd", "db.driver" })
	public void testWrongURLDbConfig(String url, String userName,
			String userPassWd, String driver) {
		this.DbConfigProcess(url, userName, userPassWd, driver);
		selenium.waitForCondition("selenium.isTextPresent(\"Driver cannot understand url\")",1000*WAIT_TIME+"");
		selenium.click("idDbConfigLogoutButton");
		waitForElementPresent("idLoginInput", WAIT_TIME);
	}

	@Test
	@Parameters( { "db.url", "db.userName", "db.userPassWd", "db.driver" })
	public void testWrongDriverDbConfig(String url, String userName,
			String userPassWd, String driver) {
		this.DbConfigProcess(url, userName, userPassWd, driver);
		selenium.waitForCondition("selenium.isTextPresent(\"Cannot instantiate\")",1000*WAIT_TIME+"");
		selenium.click("idDbConfigLogoutButton");
		waitForElementPresent("idLoginInput", WAIT_TIME);
	}

	@Test
	@Parameters( { "db.url", "db.userName", "db.userPassWd", "db.driver" })
	public void testWrongAccountDbConfig(String url, String userName,
			String userPassWd, String driver) {
		this.DbConfigProcess(url, userName, userPassWd, driver);
		selenium.waitForCondition("selenium.isTextPresent(\"Wrong user name or password\")",1000*WAIT_TIME+"");
		selenium.click("idDbConfigLogoutButton");
		waitForElementPresent("idLoginInput", WAIT_TIME);
	}

	@AfterClass
	public void killBrowser() {
		selenium.stop();
	}

}
