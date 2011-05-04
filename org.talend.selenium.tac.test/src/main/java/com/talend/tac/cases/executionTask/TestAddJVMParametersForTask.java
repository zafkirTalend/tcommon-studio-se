package com.talend.tac.cases.executionTask;

import java.awt.Event;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddJVMParametersForTask  extends Login {
    @Test
    @Parameters({"modifyTask"})
	public void testAddJVMParametersForTask(String label) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	//select a exist task
    	selenium.mouseDown("//span[text()='"+label+"']");
		this.clickWaitForElementPresent("//span[text()='JVM parameters']");
		this.clickWaitForElementPresent("idJobConductorCmdPrmAddButton()");
		//jvm parameters
		this.clickWaitForElementPresent("//td[@role='gridcell'][2]//img");
		this.waitForElementPresent("//input[@class=' x-form-field x-form-text x-form-focus']", WAIT_TIME);//jvm
		selenium.click("//input[@class=' x-form-field x-form-text x-form-focus']");
		selenium.type("//input[@class=' x-form-field x-form-text x-form-focus']","-Xms356M -Xmx1024M");
		//description
		this.clickWaitForElementPresent("//td[@role='gridcell'][3]//img");
		this.waitForElementPresent("//input[@class=' x-form-field x-form-text x-form-focus']", WAIT_TIME);//jvm
		selenium.click("//input[@class=' x-form-field x-form-text x-form-focus']");
		selenium.type("//input[@class=' x-form-field x-form-text x-form-focus']","jvm set in task");
		selenium.keyDown("//input[@class=' x-form-field x-form-text x-form-focus']", "\\13");
	}
}
