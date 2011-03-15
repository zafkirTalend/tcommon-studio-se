package com.talend.tac.cases.executionTask;

import java.awt.Event;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@Test(groups={"one"})
public class TestAddTask  extends Login {
    
	@Test
	public void testAddTask() {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
        selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleAddButton");
		selenium.type("//input[@name='label']", "test_task");//plan name /Label
		selenium.fireEvent("//input[@name='label']", "blur");
		selenium.click("idCommonProjectListBox");//select a project 
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonBranchListBox()");  //select a Branch
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonJobListBox()");   //select a job
		selenium.setSpeed(MAX_SPEED);
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonContextListBox()");  
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
 
		selenium.click("idJobConductorExecutionServerListBox()"); //select a server
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idJobConductorExecutionServerListBox()");
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idFormSaveButton");
		waitForElementPresent("//div[text()='test_task']", 10);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='test_task']"));
		selenium.setSpeed(MIN_SPEED);
		
	}


	@Test(dependsOnMethods={"testAddTask"})
	public void testAddExistTask() {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleAddButton");
		selenium.type("//input[@name='label']", "test_task");//plan name /Label
		selenium.fireEvent("//input[@name='label']", "blur");
		selenium.click("idCommonProjectListBox");//select a project 
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonBranchListBox()");  //select a Branch
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonJobListBox()");   //select a job
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonContextListBox()");
		selenium.setSpeed(MAX_SPEED);
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idJobConductorExecutionServerListBox()"); //select a server
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idJobConductorExecutionServerListBox()");
		selenium.setSpeed(MAX_SPEED);
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("executionTask.error.uniqueLabel")));
		selenium.click("//button[text()='"+rb.getString("executionPlan.errorStatus.ok")+"']");
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	@Test(dependsOnMethods={"testAddExistTask"})
	public void testAddNotChooseActiveTask() {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleAddButton");
		selenium.type("//input[@name='label']", "test_task_notChooseActive");//plan name /Label
		selenium.fireEvent("//input[@name='label']", "blur");
		selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");//uncheck "Active"
		Assert.assertFalse(selenium.isChecked("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']"));
		selenium.click("idCommonProjectListBox");//select a project 
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonBranchListBox()");  //select a Branch
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonJobListBox()");   //select a job
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idCommonContextListBox()");  
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idJobConductorExecutionServerListBox()"); //select a server
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idJobConductorExecutionServerListBox()");
		selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='test_task_notChooseActive']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
}
