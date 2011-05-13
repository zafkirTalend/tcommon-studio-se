package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTriggerAddFileTrigger extends Login {
 
	 
	@Test(dependsOnGroups={"AddCronTrigger"})
	@Parameters({"TaskBaseBranch","addFileTriggerOfExist","addFileTriggerOfExistDescription","FolderPath",
		"FileMask","triggerExistCondition","ServerForUseAvailable"})
    public void testAddTriggerAddFileTriggerAddExist(String taskLabel,String triggerLabel,String triggerDescription,
    		String folderPath,String fileMask,String triggerExistCondition,String serverName) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add file trigger']");//add a FileTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addFileTrigger")+"']"));
		
    	this.typeString("//span[text()='Add file trigger']/parent::legend/parent::fieldset" +
				"//input[@name='label']", triggerLabel);//label
				                                                                     
		this.typeString("//span[text()='Add file trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", triggerDescription);//description
		
		this.typeString("//input[@name='checkTimeInterval']", "20");//checkTimeInterval
		
		this.typeString("//input[@name='folderPath']", folderPath);//folderPath
		
		this.typeString("//input[@name='fileMask']", "*.txt");//fileMask
		
		selenium.click("//input[@class=' x-form-checkbox' and @name='exist']");//*.txt is exist
		Assert.assertTrue(selenium.isChecked("//input[@class=' x-form-checkbox' and @name='exist']"));
		selenium.setSpeed(MID_SPEED);
	
		selenium.click("//label[text()='Check file server:']/parent::div//input[@name='executionServerId']");
		selenium.mouseDown("//div[text()='" + serverName + "']");
		
		selenium.click("//span[text()='Add file trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+triggerLabel+"']")) {
			selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
			"parent::div/parent::div//button[text()='Refresh']");
    	}
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+triggerLabel+"']"));
		Assert.assertTrue(selenium.isElementPresent("//span[text()='Generating...']"));
		selenium.setSpeed(MIN_SPEED);
		
	 }
	
}
