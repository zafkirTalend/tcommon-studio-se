package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddJVMParametersForTask  extends Login {
    
//	@Test
//	(dependsOnGroups={"AddTask"})
    @Parameters({"labelAddJVMParametersForTask"})
	public void testAddJVMParametersForTask(String label) {
    	
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	//select a exist task
    	selenium.setSpeed(MID_SPEED);
    	selenium.mouseDown("//span[text()='"+label+"']");
    	selenium.setSpeed(MIN_SPEED);
		this.clickWaitForElementPresent("//span[text()='JVM parameters']");
		this.clickWaitForElementPresent("idJobConductorCmdPrmAddButton");
		//jvm parameters
		this.clickWaitForElementPresent("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//div[@class='x-grid3-cell-inner x-grid3-col-parameter']");
		this.waitForElementPresent("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//input[contains(@class,'x-form-field x-form-text x-form-focus')]", WAIT_TIME);//jvm
		selenium.click("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//input[contains(@class,'x-form-field x-form-text x-form-focus')]");
		selenium.type("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//input[contains(@class,'x-form-field x-form-text x-form-focus')]","-version");
		//description
		this.clickWaitForElementPresent("//span[text()='Description']//ancestor::div[@class='x-grid3-viewport']" +
				"//div[@class='x-grid3-cell-inner x-grid3-col-description']");
		this.waitForElementPresent("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//div[@class='x-grid3-cell-inner x-grid3-col-description']//ancestor::div[@class='x-grid3-" +
				"viewport']//input", WAIT_TIME);//jvm
		selenium.click("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//div[@class='x-grid3-cell-inner x-grid3-col-description']//ancestor::div[@class='x-grid3-" +
				"viewport']//input");
		selenium.type("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//div[@class='x-grid3-cell-inner x-grid3-col-description']//ancestor::div[@class='x-grid3-" +
				"viewport']//input","jvm set in task");
		selenium.keyDown("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//div[@class='x-grid3-cell-inner x-grid3-col-description']//ancestor::div[@class='x-grid3-" +
				"viewport']//input", "\\13");
	    selenium.setSpeed(MIN_SPEED);
	    
	    selenium.click("//button[@id='idJobConductorTaskRunButton'  and @class='x-btn-text ' and text()='Run']");
	    this.waitForElementPresent("//label[text()='Ok']", WAIT_TIME);
	    selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
	    
	    if(selenium.isElementPresent("//table[contains(@class,'x-btn x-toolbar-more x-component x-btn-icon')]")) {
	    	
	    	selenium.click("//table[contains(@class,'x-btn x-toolbar-more x-component x-btn-icon')]");
	    	
	    }	
	    
	    selenium.click("idJobConductorTaskRecoverButton");
	    
	    String jvmValue = selenium.getText("//label[text()='JVM Values:']//ancestor::div[contains" +
	    		"(@class,'x-form-item')]//textarea[contains(@class,'x-form-field x-form-textarea')]");
	    System.out.println(jvmValue);
	    
	    Assert.assertTrue(selenium.isTextPresent(jvmValue));
		
    }
}
