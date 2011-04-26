package com.talend.tac.cases.welcomePage;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.talend.tac.cases.Login;

public class TestWelcomePage extends Login {
	@Test
	public void testAccessFromWelcomePage() {
		AccessProcessInWelcomePage("Accounts");
		AccessProcessInWelcomePage("Projects");
		AccessProcessInWelcomePage("Projects authorizations");
		AccessProcessInWelcomePage("Projects references");
		AccessProcessInWelcomePage("License");
		AccessProcessInWelcomePage("Configuration");
		AccessProcessInWelcomePage("Accounts");
		AccessProcessInWelcomePage_Group("User settings");

	}

	public void AccessProcessInWelcomePage(String locatorText) {
		this.waitForElementPresent("//div[text()='" + locatorText + "']",WAIT_TIME);
		selenium.click("//div[text()='" + locatorText + "']");
		assertTrue(selenium.getXpathCount("//div[text()='" + locatorText + "']").intValue() == 2);
		this.clickWaitForElementPresent("//span[text()='"+locatorText +"']//ancestor::a/preceding-sibling::a");
	}

	public void AccessProcessInWelcomePage_Group(String locatorText) {
		this.waitForElementPresent("//span[contains(text(),'"+ locatorText + "')]",WAIT_TIME);
		selenium.click("//span[contains(text(),'"+ locatorText + "')]");
		assertTrue(selenium.getXpathCount("//span[contains(text(),'"+ locatorText + "')]").intValue() == 3);
		this.clickWaitForElementPresent("//span[contains(text(),'" +locatorText +"')]//ancestor::a/preceding-sibling::a");
	}
}
