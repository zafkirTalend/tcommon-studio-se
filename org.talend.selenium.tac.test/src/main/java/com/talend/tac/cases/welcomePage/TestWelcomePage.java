package com.talend.tac.cases.welcomePage;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.talend.tac.cases.Login;

public class TestWelcomePage extends Login {
	@Test
	public void f() {
		AccessProcessInWelcomePage("Accounts");
		AccessProcessInWelcomePage("Projects");
		AccessProcessInWelcomePage("Projects authorizations");
		AccessProcessInWelcomePage("Projects references");
		AccessProcessInWelcomePage("License");
		AccessProcessInWelcomePage("Configuration");
		AccessProcessInWelcomePage("Accounts");
		AccessProcessInWelcomePage_Group("User settings: Change your password and svn account");

	}

	public void AccessProcessInWelcomePage(String locatorText) {
		this.waitForElementPresent("//div[text()='" + locatorText + "']",WAIT_TIME);
		selenium.click("//div[text()='" + locatorText + "']");
		assertTrue(selenium.getXpathCount("//div[text()='" + locatorText + "']").intValue() == 2);
		this.clickWaitForElementPresent("//span[text()='"+locatorText +"']//ancestor::a/preceding-sibling::a");
	}

	public void AccessProcessInWelcomePage_Group(String locatorText) {
		this.waitForElementPresent("//span[text()='" + locatorText + "']",WAIT_TIME);
		selenium.click("//span[text()='" + locatorText + "']");
		assertTrue(selenium.getXpathCount("//span[text()='" + locatorText + "']").intValue() == 2);
		this.clickWaitForElementPresent("//span[text()='"+locatorText +"']//ancestor::a/preceding-sibling::a");
	}
}
