package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddJVMParametersForTask  extends Login {
    
	@Test
    @Parameters({"labelAddJVMParametersForTask"})
	public void testAddJVMParametersForTask(String label) {
    	
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	//select a exist task
    	selenium.setSpeed(MID_SPEED);
    	selenium.mouseDown("//span[text()='"+label+"']");
		this.clickWaitForElementPresent("//span[text()='JVM parameters']");
		this.clickWaitForElementPresent("idJobConductorCmdPrmAddButton()");
		//jvm parameters
		this.clickWaitForElementPresent("//td[@role='gridcell'][2]//img");
		this.waitForElementPresent("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']//input", WAIT_TIME);//jvm
		selenium.click("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']//input");
		selenium.type("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']//input","-Xms356M -Xmx1024M");
		//description
		this.clickWaitForElementPresent("//span[text()='Description']//ancestor::div[@class='x-grid3-viewport']//td[@class='x-grid3-col x-grid3" +
				"-cell x-grid3-td-description x-grid3-cell-last ']//img[@title='Click to edit']");
		this.waitForElementPresent("//span[text()='Description']//ancestor::div[@class='x-grid3-viewport']", WAIT_TIME);//jvm
		selenium.click("//span[text()='Description']//ancestor::div[@class='x-grid3-viewport']//input");
		selenium.type("//span[text()='Description']//ancestor::div[@class='x-grid3-viewport']//input","jvm set in task");
		selenium.keyDown("//span[text()='Description']//ancestor::div[@class='x-grid3-viewport']//input", "\\13");
	    selenium.setSpeed(MIN_SPEED);
	    
	    selenium.click("idJobConductorTaskRunButton()");
	    this.waitForElementPresent("//label[text()='Ok']", WAIT_TIME);
	    Assert.assertTrue(selenium.isElementPresent("//label[text()='Ok']"));
	    selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
	    
		
    }
}
