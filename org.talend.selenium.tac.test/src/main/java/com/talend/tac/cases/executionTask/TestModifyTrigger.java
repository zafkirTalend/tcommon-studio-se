package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestModifyTrigger extends Login {
    
    //add method for test change trigger name	
	public void renameTrigger(String taskLabel, String labelBeforeModify, String labelAfterModified, 
			String description ,String triggerType) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");//into JobConductor page
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']"));  
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task    	  	
    	
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+labelBeforeModify+"']")); 
    	selenium.mouseDown("//span[text()='"+labelBeforeModify+"']");//select a exist trigger 
    	this.typeString("//span[text()='"+triggerType+"']/parent::legend/parent::fieldset" +
        		"//input[@name='label']", labelAfterModified);//modify label
		
        this.typeString("//span[text()='"+triggerType+"']/parent::legend/parent::fieldset" +
				"//input[@name='description']", description);//modify description
        selenium.setSpeed(MIN_SPEED);
        
        selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='"+triggerType+"']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");//click save button
		selenium.setSpeed(MIN_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+labelAfterModified+"']")) {
    		
			selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
			"parent::div/parent::div//button[text()='Refresh']");//click refresh button
			
    	}
		
		Assert.assertTrue(!selenium.isElementPresent("//span[text()='"+labelBeforeModify+"']"));
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+labelAfterModified+"']"));
		
	}
	
	//test rename a simple trigger
	@Test(dependsOnGroups={"AddCronTrigger"})
	@Parameters({"modifyTask", "addSimpleTriggerNumberOfTriggeringsRunnedAutoStopLabel", "simpleTiggerLabelAfterModified",
		"simpleTiggerLabelAfterModifiedDescription", "simpleTriggerType"})
	public void testRanameSimpleTrigger(String taskLabel, String labelBeforeModify,String labelAfterModified
			, String description, String triggerType) {
		
		renameTrigger(taskLabel, labelBeforeModify, labelAfterModified, description, triggerType);
		
	}
	
	//test rename a cron trigger
	@Test(dependsOnMethods={"testRanameSimpleTrigger"})
	@Parameters({"labelRefProJobByMainProTRunJobRun", "addCronTriggerLabel", "cronTiggerLabelAfterModified",
		"cronTiggerLabelAfterModifiedDescription", "cronTriggerType"})
	public void testRanameCronTrigger(String taskLabel, String labelBeforeModify,String labelAfterModified
			, String description, String triggerType) {
		
		renameTrigger(taskLabel, labelBeforeModify, labelAfterModified, description, triggerType);
		
	}
	
}
