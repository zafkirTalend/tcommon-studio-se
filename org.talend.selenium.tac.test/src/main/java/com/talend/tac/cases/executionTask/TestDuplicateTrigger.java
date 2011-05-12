package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDuplicateTrigger extends Login {
    
	@Test(dependsOnGroups={"AddSimpleTrigger"})
	@Parameters({"modifyTask","addSimpleTriggerLabel","duplicateTriggerLabel"})
	public void testDuplicateTrigger(String task, String trigger, String duplicateTrigger) {
	   
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+task+"']");//select a exist task
    	
    	selenium.mouseDown("//span[text()='"+trigger+"']");//select a exist task
    	
    	selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::" +
    			"div/parent::div/parent::div/parent::div//button[@id='idSubModuleDuplicateButton']");
    	
    
    	selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
    	selenium.setSpeed(MID_SPEED);
    	if(!selenium.isElementPresent("//span[text()='"+duplicateTrigger+"']")) {
			selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
			"parent::div/parent::div//button[text()='Refresh']");
    	}

    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+duplicateTrigger+"']"));
    	selenium.setSpeed(MIN_SPEED);    	
	}
}
