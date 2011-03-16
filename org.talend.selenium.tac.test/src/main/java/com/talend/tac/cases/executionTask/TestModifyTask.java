package com.talend.tac.cases.executionTask;

import java.awt.Event;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
public class TestModifyTask extends Login {
    
	@Test(groups={"ModifyTask"},dependsOnGroups={"DeleteTask"})
	public void testModifyTask() {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleRefreshButton");
		selenium.mouseDown("//div[text()='testTaskNotChooseActive']");//select a exist plan

		
		selenium.type("//input[@name='label']", "testModifyTask");//plan name /Label
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
		Assert.assertTrue(selenium.isElementPresent("//div[text()='testModifyTask']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
}
