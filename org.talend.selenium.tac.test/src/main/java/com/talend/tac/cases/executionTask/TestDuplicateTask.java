package com.talend.tac.cases.executionTask;

import java.awt.Event;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;
@Test(groups={"two"},dependsOnGroups={"one"})
public class TestDuplicateTask  extends Login {
    
	@Test
	public void testDuplicateTask() {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleRefreshButton");
		selenium.mouseDown("//div[text()='test_task']");//select a exist plan
        selenium.click("idSubModuleDuplicateButton");
        
		selenium.click("idJobConductorExecutionServerListBox()"); //select a server
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		
		selenium.click("idJobConductorExecutionServerListBox()"); //select a server
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER);
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='Copy_of_test_task']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
}
