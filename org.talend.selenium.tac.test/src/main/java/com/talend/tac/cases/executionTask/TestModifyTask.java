package com.talend.tac.cases.executionTask;

import java.awt.Event;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
public class TestModifyTask extends Login {
    
	@Test(groups={"ModifyTask"},dependsOnGroups={"DeleteTask"})
	@Parameters({"label","modifyTask"})
	public void testModifyTask(String label,String modifyLabel) {
		
		if(!selenium.isElementPresent("//span[text()='"+modifyLabel+"']")) {
			
			this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
			selenium.setSpeed(MID_SPEED);
		    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
			selenium.click("idSubModuleRefreshButton");
			selenium.mouseDown("//span[text()='"+label+"']");//select a exist task

			
			selenium.type("//input[@name='label']", modifyLabel);//task name /Label
			selenium.fireEvent("//input[@name='label']", "blur");
			selenium.click("idCommonProjectListBox");//select a project 
			selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
			selenium.click("idCommonBranchListBox()");  //select a Branch
			selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
			selenium.click("idCommonJobListBox()");   //select a job
			selenium.setSpeed(MID_SPEED);
			selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
			selenium.click("idCommonContextListBox()");  
			selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
	 
			selenium.click("idJobConductorExecutionServerListBox()"); //select a server
			selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
			selenium.click("idJobConductorExecutionServerListBox()");
			selenium.mouseDownAt("//div[@role='listitem'][1]",""+Event.ENTER); 
			selenium.click("idFormSaveButton");
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+modifyLabel+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}	
	}
}
