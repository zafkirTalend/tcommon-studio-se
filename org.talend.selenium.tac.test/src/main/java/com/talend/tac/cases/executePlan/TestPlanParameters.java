package com.talend.tac.cases.executePlan;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestPlanParameters extends Plan {
	@Test
	 @Parameters({ "parameters.plan.label",
	 "parameters.plan.roottask"})
	public void testCheckPlanExecutionLogs(String planParameters,String taskLabel) {
		// this.addPlan(planLabel, rootTask, "treeManagePlan");
		this.addPlan(planParameters, taskLabel, "testPlanparameters");
		this.runPlan(taskLabel);
		this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='"+taskLabel+" : [OK]']", MAX_WAIT_TIME);
        String logs = (this.getPlanLogsValue(planParameters,taskLabel, null));
        Assert.assertTrue((logs.contains("name: JackZhang"))&&(logs.contains("age: 23")), "test failed!");
	}
	@Test(dependsOnMethods="checkPlanExecutionLogs")
	 @Parameters({ "parameters.plan.label",
	 "parameters.plan.roottask"})
	public void testChangePlanParametersNotClickOverride(String planParameters,String taskLabel) {
		// this.addPlan(planLabel, rootTask, "treeManagePlan");
		this.waitForElementPresent("//span[text()='" + planParameters + "']",
				WAIT_TIME);
		this.sleep(2000);
		selenium.mouseDown("//span[text()='" + planParameters + "']");
		selenium.setSpeed(MID_SPEED);
		this.ChangePlanParamter("name", "talend");
		this.runPlan(taskLabel);
		this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='"+taskLabel+" : [OK]']", MAX_WAIT_TIME);
       String logs = (this.getPlanLogsValue(planParameters,taskLabel, null));
       Assert.assertTrue((logs.contains("name: JackZhang"))&&(logs.contains("age: 23")), "test failed!");
	   selenium.setSpeed(MIN_SPEED);
	}
	
	@Test(dependsOnMethods="checkPlanExecutionLogs")
	 @Parameters({ "parameters.plan.label",
	 "parameters.plan.roottask"})
	public void testChangePlanParametersAndClickOverride(String planParameters,String taskLabel) {
		// this.addPlan(planLabel, rootTask, "treeManagePlan");
		this.waitForElementPresent("//span[text()='" + planParameters + "']",
				WAIT_TIME);
		this.sleep(2000);
		selenium.mouseDown("//span[text()='" + planParameters + "']");
		String newname = "talend";
		selenium.setSpeed(MID_SPEED);
		this.ChangePlanParamter("name", newname);
		selenium.click("//button[text()='Override']");
		this.runPlan(taskLabel);
		this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='"+taskLabel+" : [OK]']", MAX_WAIT_TIME);
      String logs = (this.getPlanLogsValue(planParameters,taskLabel, null));
      Assert.assertTrue((logs.contains("name: "+newname+""))&&(logs.contains("age: 23")), "test failed!");
	selenium.setSpeed(MIN_SPEED);
	}
	
	public void ChangePlanParamter(String context,String value){
		
		this.clickWaitForElementPresent("//span[@class='x-tab-strip-text  ' and text()='Parameter']");
		this.clickWaitForElementPresent("idJobConductorCmdPrmAddButton");
		this.clickWaitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-parameter']");
		this.input(context);
		this.clickWaitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-description']");
	    this.input(value);
	}
	
	public void input(String value){
		selenium.type(
				"//input[@class=' x-form-field x-form-text x-form-focus']",
				"name");
		selenium.keyDown(
				"//input[@class=' x-form-field x-form-text x-form-focus']",
				"\\13");
	}
	
	public String getPlanLogsValue(String planLabel,String rootTask,String executeDate){
		String executionLogs = null;
//		selenium.refresh();
		this.waitForElementPresent("//span[text()='" + planLabel + "']", WAIT_TIME);
		this.sleep(2000);
		selenium.mouseDown("//span[text()='" + planLabel + "']");
		this.clickWaitForElementPresent("//span[@class='x-tab-strip-text  ' and text()='History']");
		this.clickWaitForElementPresent("//div[contains(@class,'x-small-editor x-toolbar x-component x-toolbar-layout-ct')]//table[contains(@class,'x-btn x-component x-btn-icon')]");
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-taskStartDate x-component']//a[@class='x-grid3-hd-btn']");
		selenium.click("//a[@class=' x-menu-item x-component' and text()='Sort Descending']");
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Plan: "+planLabel+" Task: "+rootTask+"']", WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Plan: "+planLabel+" Task: "+rootTask+"']");
		this.waitForElementPresent("//textarea[contains(@class,'x-form-field x-form-textarea') and @name='contextValues']", WAIT_TIME);
		executionLogs = selenium.getValue("//textarea[contains(@class,'x-form-field x-form-textarea') and @name='contextValues']");
		return executionLogs;
		
	}

	
}
