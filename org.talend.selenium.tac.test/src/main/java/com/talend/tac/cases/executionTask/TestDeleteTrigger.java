package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeleteTrigger extends Login {
    
	//delete a trigger
	@Test(groups={"DeleteTrigger"},dependsOnGroups={"AddSimpleTrigger"})
	@Parameters({"modifyTask","addSimpleTriggerLabel"})
	public void testDeleteTrigger(String task, String trigger) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//span[text()='"+task+"']");//select a exist task

    	selenium.mouseDown("//span[text()='"+trigger+"']");//select a exist task
    	
    	selenium.chooseOkOnNextConfirmation();
    	selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent" +
		"::ul/parent::div/parent::div/parent::div/parent::div//button[@id='idSubModuleDeleteButton']");
		Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected trigger [\\s\\S]$"));
		Assert.assertTrue(!selenium.isElementPresent("//span[text()='"+trigger+"']"));
    	
	}
}
