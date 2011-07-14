package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTriggerAddFileTrigger extends AddTrigger {
 
	//test add a cronTrigger use wrong form value
	@Test
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
	
	//test add a file trigger
	@Test(dependsOnMethods={"testAddTriggerAddFileTriggerWrongFormValue"})
	@Parameters({"TaskBaseBranch","addFileTriggerOfExist","addFileTriggerOfExistDescription","FolderPath",
		"FileMask","ServerForUseAvailable"})
    public void testAddTriggerAddFileTriggerAddExist(String taskLabel,String triggerLabel,String triggerDescription,
    		String folderPath,String fileMask,String serverName) {
    	
		String FilePath = this.parseRelativePath(folderPath);
		
		String newFilePath = FilePath.substring(6);
		System.out.println(newFilePath);
		addFileTrigger(taskLabel, triggerLabel, triggerDescription, "15", newFilePath, fileMask, serverName);
		
		selenium.click("idFileTriggerSave");
	
		if(!selenium.isElementPresent("//span[text()='"+triggerLabel+"']")) {
			selenium.click("idTriggerRefresh");
    	}
		this.waitForElementPresent("//span[text()='"+triggerLabel+"']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+triggerLabel+"']"));
		selenium.setSpeed(MIN_SPEED);
		this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
				"//span[text()='Running...']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
				"//span[text()='Running...']"));
		
		
	}
	
	//test add a	file trigger of label is exist
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
		
}
