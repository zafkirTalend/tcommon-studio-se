package com.talend.tac.cases.audit;

import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAudit extends Login {
	@Test
	public void testAudit(){
		this.clickWaitForElementPresent("!!!menu.audit.element!!!");
		//project
		this.clickWaitForElementPresent("//label[text()='Project:']/following-sibling::div//table//div//div");
		this.waitForElementPresent("//div[@role='listitem'][1]",WAIT_TIME);
		selenium.mouseDown("//div[@role='listitem'][1]");
		//branches
		this.clickWaitForElementPresent("//label[text()='Branch:']/following-sibling::div//div/div");
		this.waitForElementPresent("//div[@role='listitem' and text()='trunk']", WAIT_TIME);
		selenium.mouseDown("//div[@role='listitem' and text()='trunk']");
		//start
		this.waitForElementPresent("//button[text()='Start audit']",WAIT_TIME);
		selenium.click("//button[text()='Start audit']");
		//audit is started,but it need a very long time to be finished. we can think and check the result.
	}
}
