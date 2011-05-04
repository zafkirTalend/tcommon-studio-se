package com.talend.tac.cases.executionTask;

import java.awt.Event;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
public class TestModifyTask extends Login {
    
	@Test(groups={"ModifyTask"},dependsOnGroups={"DeleteTask"})
	@Parameters({"label","modifyTask","AddcommonProjectname","jobName"})
	public void testModifyTask(String label,String modifyLabel,String projectName,String jobName) {
		
		if(!selenium.isElementPresent("//span[text()='"+modifyLabel+"']")) {
			
			this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
			selenium.setSpeed(MID_SPEED);
		    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
			selenium.click("idSubModuleRefreshButton");
			selenium.mouseDown("//span[text()='"+label+"']");//select a exist task

			
			this.typeString("//input[@name='label']", modifyLabel);//task name /Label
			selenium.click("idCommonProjectListBox");//select a project 
			selenium.mouseDownAt("//div[text()='"+projectName+"']",""+Event.ENTER); 
			
			selenium.click("idCommonJobListBox()");
			this.waitForElementPresent("//div[text()='"+jobName+"']", WAIT_TIME);
			selenium.mouseDownAt("//div[text()='"+jobName+"']",""+Event.ENTER);
			
			selenium.click("idFormSaveButton");
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+modifyLabel+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}	
	}
}
