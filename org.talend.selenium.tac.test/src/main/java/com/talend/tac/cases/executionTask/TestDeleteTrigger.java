package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeleteTrigger extends Login {
    
	@Test(dependsOnMethods={"DuplicateTrigger"})
	public void testCancleDeleteTrigger() {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
//    	selenium.mouseDown("//div[text()='a']");//select a exist task
    	selenium.mouseDown("//div[text()='TestSimpleTrigger']");//select a exist task
    	
    	selenium.chooseCancelOnNextConfirmation();
    	selenium.click("idSubModuleDeleteButton");
    	Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected trigger [\\s\\S]$"));
    	
	}
	
	@Test(dependsOnMethods={"testCancleDeleteTrigger()"})
	public void testDeleteTrigger() {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
//    	selenium.mouseDown("//div[text()='a']");//select a exist task
    	selenium.mouseDown("//div[text()='TestSimpleTrigger']");//select a exist task
    	
    	selenium.chooseOkOnNextConfirmation();
    	selenium.click("idSubModuleDeleteButton");
    	Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected trigger [\\s\\S]$"));
    	
	}
}
