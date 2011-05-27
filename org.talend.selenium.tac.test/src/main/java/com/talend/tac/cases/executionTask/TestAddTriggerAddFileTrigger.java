package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTriggerAddFileTrigger extends Login {
 
	//creat method(addFileTrigger) for test add file trigger
	public void addFileTrigger(String taskLabel, String triggerLabel, String triggerDescription,
			 String intervalTime, String folderPath, String fileMask, String serverName) {
		
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
		
		this.typeString("idJobConductorFileTriggerCheckTimeIntervalInput", intervalTime);//checkTimeInterval
		
		this.typeString("idJobConductorFileTriggerFolderPathInput", folderPath);//folderPath
		
		this.typeString("idJobConductorFileTriggerFileMaskInput", fileMask);//fileMask
		
		selenium.click("idJobConductorFileTriggerFtExitCheckBox");//*.txt is exist
		Assert.assertTrue(selenium.isChecked("idJobConductorFileTriggerFtExitCheckBox"));
	
		selenium.click("idJobConductorFileTriggerFileServerListBox");//select an server	
		this.waitForElementPresent("//div[text()='" + serverName + "']", WAIT_TIME);
		selenium.mouseDown("//div[text()='" + serverName + "']");
		
	}
	
	//test add a file trigger
	@Test(dependsOnGroups={"AddCronTrigger"})
	@Parameters({"TaskBaseBranch","addFileTriggerOfExist","addFileTriggerOfExistDescription","FolderPath",
		"FileMask","ServerForUseAvailable"})
    public void testAddTriggerAddFileTriggerAddExist(String taskLabel,String triggerLabel,String triggerDescription,
    		String folderPath,String fileMask,String serverName) {
    	
		addFileTrigger(taskLabel, triggerLabel, triggerDescription, "60", folderPath, fileMask, serverName);
		
		selenium.click("idFileTriggerSave");
	
		if(!selenium.isElementPresent("//span[text()='"+triggerLabel+"']")) {
			selenium.click("idTriggerRefresh");
    	}
		this.waitForElementPresent("//span[text()='"+triggerLabel+"']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+triggerLabel+"']"));
		selenium.setSpeed(MIN_SPEED);
		this.waitForElementPresent("//span[text()='Running...']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='Running...']"));
		
		
	}
	
	//test add a	 file trigger of label is exist
	@Test(dependsOnMethods={"testAddTriggerAddFileTriggerAddExist"})
	@Parameters({"TaskBaseBranch","addFileTriggerOfExist","addFileTriggerOfExistDescription","FolderPath",
		"FileMask","ServerForUseAvailable"})
    public void testAddTriggerAddFileTriggerAddExistOfLabelExist(String taskLabel,String triggerLabel,String triggerDescription,
    		String folderPath,String fileMask,String serverName) {
    	
		addFileTrigger(taskLabel, triggerLabel, triggerDescription, "60", folderPath, fileMask, serverName);
		
		selenium.click("idFileTriggerSave");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.error.uniqueLabel")));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	
	//test add a cronTrigger use wrong form value
	@Test(dependsOnMethods={"testAddTriggerAddFileTriggerAddExist"})
	@Parameters({"TaskBaseBranch","addFileTriggerOfExistWrongForm","addFileTriggerOfExistDescriptionWrongForm"
		,"ServerForUseAvailable"})
    public void testAddTriggerAddFileTriggerWrongFormValue(String taskLabel,String triggerLabel,String triggerDescription,
    		String serverName) {
    	
		addFileTrigger(taskLabel, triggerLabel, triggerDescription, "ss", "", "", serverName);
		
		Assert.assertTrue(selenium.isElementPresent("//input[@id='idJobConductorFileTriggerLabelInput']//ancestor::div" +
				"[@class='x-form-item ']//img[@class='gwt-Image x-component ']"));//add assert for "label"  
		Assert.assertTrue(selenium.isElementPresent("//input[@id='idJobConductorFileTriggerCheckTimeIntervalInput']" +
				"//ancestor::div[@class='x-form-item ']//img[@class='gwt-Image x-component ']"));//add assert for "Pollint (s):"  
		Assert.assertTrue(selenium.isElementPresent("//input[@id='idJobConductorFileTriggerFolderPathInput']" +
				"//ancestor::div[@class='x-form-item ']//img[@class='gwt-Image x-component ']"));//add assert for "Folder path:" 
		Assert.assertTrue(selenium.isElementPresent("//input[@id='idJobConductorFileTriggerFileMaskInput']//ancestor::div" +
				"[@class='x-form-item ']//img[@class='gwt-Image x-component ']"));//add assert for "File mask:" 
        
		selenium.click("idFileTriggerSave");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isTextPresent("Fix errors in form before save"));
		selenium.setSpeed(MIN_SPEED);
		
	 }
	
	
}
