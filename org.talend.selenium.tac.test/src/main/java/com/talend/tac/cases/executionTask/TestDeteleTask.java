package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;
@Test(dependsOnGroups={"two"})
public class TestDeteleTask  extends Login {
    
	
	@Test
	public void testCancleDeleteTask() {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleRefreshButton");//click "Refresh"
		selenium.mouseDown("//div[text()='test_task']");//select first plan
		selenium.chooseCancelOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");//clcik "Delete"
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.getConfirmation().matches(other.getString("delete.plan.warning")));
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='test_task']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	@Test(dependsOnMethods={"testCancleDeleteTask"})
	public void testDeleteTask() {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleRefreshButton"); //click "Refresh"
		selenium.mouseDown("//div[text()='test_task']");//select first plan
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");//clcik "Delete"
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.getConfirmation().matches(other.getString("delete.plan.warning")));
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[text()='test_task']"));//the plan cannot present
		selenium.setSpeed(MIN_SPEED);
		
	}
}
