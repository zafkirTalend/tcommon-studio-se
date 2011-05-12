package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDuplicateTrigger extends Login {
    
	//add method for duplication trigger
	public void duplicateTrigger(String task, String trigger, String triggerType) {
	   
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+task+"']");//select a exist task
    	
    	selenium.mouseDown("//span[text()='"+trigger+"']");//select a exist task
    	
    	selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::" +
    			"div/parent::div/parent::div/parent::div//button[@id='idSubModuleDuplicateButton']");
    	
    
    	selenium.click("//span[text()='"+triggerType+"']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
    	selenium.setSpeed(MID_SPEED);
    	
    	if(!selenium.isElementPresent("//span[text()='Copy_of_"+trigger+"']")) {
			selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
			"parent::div/parent::div//button[text()='Refresh']");
    	}

    	Assert.assertTrue(selenium.isElementPresent("//span[text()='Copy_of_"+trigger+"']"));
    	selenium.setSpeed(MIN_SPEED);   
    	
	}
	
	//test duplication a simple trigger
	@Test(dependsOnGroups={"AddCronTrigger"})
	@Parameters({"labelTRunJobByTaskRun","addSimpleTriggerLabelNotChooseDate","simpleTriggerType"})
	public void testDuplicationSimpleTrigger(String task, String trigger, String triggerType) {
		
		duplicateTrigger(task, trigger, triggerType);
		
	}
	
	//test duplication a Cron trigger
	@Test(dependsOnMethods={"testDuplicationSimpleTrigger"})
	@Parameters({"labelRefProJobByMainProTRunJobRun","cronTiggerLabelAfterModified","cronTriggerType"})
	public void testDuplicationCronTrigger(String task, String trigger, String triggerType) {
		
		duplicateTrigger(task, trigger, triggerType);
		
	}
	
}
