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
    	
    	if(!selenium.isChecked("idJobConductorTaskActiveListBox")) {
    		
    		selenium.click("idJobConductorTaskActiveListBox");//check active
        	Assert.assertTrue(selenium.isChecked("idJobConductorTaskActiveListBox"));
        	selenium.click("idFormSaveButton");
    		
    	}
    
    	
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task again
		selenium.click("idTriggerAdd trigger...");//add a trigger
		selenium.click("idTriggerAdd file trigger");//add a FileTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addFileTrigger")+"']"));
		
    	this.typeString("idJobConductorFileTriggerLabelInput", triggerLabel);//label
				                                                                     
		this.typeString("idJobConductorFileTriggerDescInput", triggerDescription);//description
		
		this.typeString("idJobConductorFileTriggerCheckTimeIntervalInput", "20");//checkTimeInterval
		
		this.typeString("idJobConductorFileTriggerFolderPathInput", folderPath);//folderPath
		
		this.typeString("idJobConductorFileTriggerFileMaskInput", "*.txt");//fileMask
		
		selenium.click("idJobConductorFileTriggerFtExitCheckBox");//*.txt is exist
		Assert.assertTrue(selenium.isChecked("idJobConductorFileTriggerFtExitCheckBox"));
		selenium.setSpeed(MID_SPEED);
		
		selenium.click("idJobConductorFileTriggerFileServerListBox");
		selenium.mouseDown("//div[text()='" + serverName + "']");
		
		selenium.click("idFileTriggerSave");
		selenium.setSpeed(MIN_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+triggerLabel+"']")) {
			selenium.click("idTriggerRefresh");
    	}
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+triggerLabel+"']"));
		selenium.setSpeed(MIN_SPEED);
		this.waitForElementPresent("//span[text()='Running...']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='Running...']"));
		
		
	 }
	
}
