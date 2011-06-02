package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddJVMParametersForTask  extends Login {
    
	String jvmParameterCorrect = "-Xms512M -Xmx512M";
	String jvmParameterDescription = "jvm set in task";
	
	String expectationValue = jvmParameterCorrect+" "+"("+jvmParameterDescription+")";
    
	//add method for add jvm parameter 
	public void addJVMParametersForTask(String label, String jvmParameter) {
    	
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	//select a exist task
    	selenium.setSpeed(MID_SPEED);
    	selenium.mouseDown("//span[text()='"+label+"']");
    	selenium.setSpeed(MIN_SPEED);
		this.clickWaitForElementPresent("//span[text()='JVM parameters']");//into JVMParameter page
		this.clickWaitForElementPresent("idJobConductorCmdPrmAddButton");//click add button, add jvm parameter
		//jvm parameters
		this.clickWaitForElementPresent("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//div[@class='x-grid3-cell-inner x-grid3-col-parameter']");
		this.waitForElementPresent("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//input[contains(@class,'x-form-field x-form-text x-form-focus')]", WAIT_TIME);//jvm
		selenium.click("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//input[contains(@class,'x-form-field x-form-text x-form-focus')]");
		selenium.type("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//input[contains(@class,'x-form-field x-form-text x-form-focus')]",jvmParameter);//input jvm parameter
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
				"//div[@class='x-grid3-cell-inner x-grid3-col-description']//ancestor::div[@	class='x-grid3-" +
				"viewport']//input","jvm set in task");//input jvm parameter description
		selenium.keyDown("//span[text()='JVM parameter']//ancestor::div[@class='x-grid3-viewport']" +
				"//div[@class='x-grid3-cell-inner x-grid3-col-description']//ancestor::div[@class='x-grid3-" +
				"viewport']//input", "\\13");
	    selenium.setSpeed(MIN_SPEED);
	    
	    selenium.click("//button[@id='idJobConductorTaskRunButton'  and @class='x-btn-text ' and text()='Run']");
	    this.waitForElementPresent("//label[text()='Ok']", WAIT_TIME);
	    selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
	    
	    
    }
	
	//check jvm value in grid and RecoverLastExecution
	public void checkJvmValueInGridRecoverLastExecution() {
	    
		if(selenium.isElementPresent("//table[contains(@class,'x-btn x-toolbar-more x-component x-btn-icon')]")) {
	    	
	    	selenium.click("//table[contains(@class,'x-btn x-toolbar-more x-component x-btn-icon')]");
	    	
	    }	
	    
	    selenium.click("idJobConductorTaskRecoverButton");//into 
	    
	    this.waitForElementPresent("//label[text()='JVM Values:']//ancestor::div[contains" +
	    		"(@class,'x-form-item')]//textarea[contains(@class,'x-form-field x-form-textarea')]", WAIT_TIME);
	    String lastRunEndTime = selenium.getValue("//label[text()='Task:']//ancestor::div" +
	    		"[@class=' x-form-label-left']/div[3]//input[@class=' x-form-field x-form-text']");
	    String jvmValue = selenium.getValue("//label[text()='JVM Values:']//ancestor::div[contains" +
	    		"(@class,'x-form-item')]//textarea[contains(@class,'x-form-field x-form-textarea')]");
	    System.out.println(jvmValue);
	    System.out.println(lastRunEndTime);
	    Assert.assertEquals(jvmValue, expectationValue);
		
	    this.clickWaitForElementPresent("!!!menu.grid_task_executions_history.element!!!");
	    
	    this.waitForElementPresent("//div[text()='Task execution monitoring']//ancestor::div[@class='x-" +
	    		"panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
	    		"//span[text()='Task start date']//ancestor::div[@class='x-grid3-viewport']" +
	    		"//div[@class='x-grid3-cell-inner x-grid3-col-taskEndDate']//" +
	    		"span[@title='"+lastRunEndTime+"']", WAIT_TIME);
	    selenium.mouseDown("//div[text()='Task execution monitoring']//ancestor::div[@class='x-" +
	    		"panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
	    		"//span[text()='Task start date']//ancestor::div[@class='x-grid3-viewport']" +
	    		"//div[@class='x-grid3-cell-inner x-grid3-col-taskEndDate']//" +
	    		"span[@title='"+lastRunEndTime+"']");
	    
	    String jvmValueInGrid = selenium.getValue("//div[text()='Task execution monitoring']" +
	    		"//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder" +
	    		" x-border-layout-ct']//textarea[@name='jvmValues']");
	    System.out.println("*-*-*-*-*-*"+jvmValueInGrid);
	    selenium.setSpeed(MAX_SPEED);
	    Assert.assertEquals(jvmValueInGrid, expectationValue);
	    selenium.setSpeed(MIN_SPEED);
		
	}
	
	//test add a correct jvm parameter and active to task, then run it and check its value display in grid and RecoverLastExecution
	@Test	
//	(dependsOnGroups={"AddTask"})
    @Parameters({"labelAddJVMParametersForTask"})
	public void testAddActiveCorrectJVMParametersForTask(String label) {
    	
    	addJVMParametersForTask(label, jvmParameterCorrect);
    	
    	checkJvmValueInGridRecoverLastExecution();
	    
    }
}
