package com.talend.tac.cases.executionTask;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.AntAction;
import com.talend.tac.base.Base;

public class TestAddTriggerAddFileTrigger extends TaskUtils {  
	
	Hashtable properties = new Hashtable();
	//test add a cronTrigger use wrong form value
	@Test
	@Parameters({"TaskBaseBranch","addFileTriggerOfExistWrongForm","addFileTriggerOfExistDescriptionWrongForm"
		,"ServerForUseAvailable"})
    public void testAddTriggerAddFileTriggerWrongFormValue(String taskLabel,String triggerLabel,String triggerDescription,
    		String serverName) {
    	
		addFileTrigger(taskLabel, triggerLabel, triggerDescription, "ss", "", "", serverName,
				"idJobConductorFileTriggerFtExitCheckBox");
		
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
	@Test
	@Parameters({"TaskBaseBranch","addFileTriggerOfExist","addFileTriggerOfExistDescription","FolderPath",
		"FileMaskOfTxt","ServerForUseAvailable"})
    public void testAddTriggerAddFileTriggerAddExist(String taskLabel,String triggerLabel,String triggerDescription,
    		String folderPath,String fileMask,String serverName) {
    	
		String FilePath = this.getAbsolutePath(folderPath);		
		System.out.println(FilePath);
		
		AntAction antAction = new AntAction();
		properties.put("file.path", FilePath+"testFiletrigger.txt");
		antAction.runTarget("File.xml", "create", properties);
		
		addFileTrigger(taskLabel, triggerLabel, triggerDescription, "30", FilePath, fileMask, serverName,
				"idJobConductorFileTriggerFtExitCheckBox");
		
		selenium.click("idFileTriggerSave");
	
		if(!selenium.isElementPresent("//span[text()='"+triggerLabel+"']")) {
			selenium.click("idTriggerRefresh");
    	}
		this.waitForElementPresent("//span[text()='"+triggerLabel+"']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+triggerLabel+"']"));
		selenium.setSpeed(MIN_SPEED);
		this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
				"//span[text()='Running...']", Base.MAX_WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
				"//span[text()='Running...']"));
		
		
	}
	
	//test add a	file trigger of label is exist
	@Test
	@Parameters({"TaskBaseBranch","addFileTriggerOfExist","addFileTriggerOfExistDescription","FolderPath",
		"FileMaskOfTxt","ServerForUseAvailable"})
    public void testAddTriggerAddFileTriggerAddExistOfLabelExist(String taskLabel,String triggerLabel,String triggerDescription,
    		String folderPath,String fileMask,String serverName) {    	
		
		addFileTrigger(taskLabel, triggerLabel, triggerDescription, "60", folderPath, fileMask, serverName,
				"idJobConductorFileTriggerFtExitCheckBox");
		
		
		selenium.click("idFileTriggerSave");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.error.uniqueLabel")));
		selenium.setSpeed(MIN_SPEED);
		
	}	
	
	//FileTriiger with Exist option is no longer executed after deletion of the cheked file
	@Test
	@Parameters({"taskForTestFileTrigger","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled", "addFileTriggerOfCheckExistOption",
		"addFileTriggerOfExistDescription","FolderPath",
		"FileMaskOfXml"})
	public void testFileTriigerWithExistOptionIsNoLongerExecutedAfterDeletionOfTheChekedFile(String label,
			String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic, String fileTriggerLabel,
			String fileTriggerDesc, String folderPath, String fileMask) {
		
		
		this.addTask(label, labelDescription, commonpro, branch, jobName,
				version, context, jobServer, statistic);
		
		selenium.click("idFormSaveButton");
        selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
		selenium.setSpeed(MIN_SPEED);
		
		String FilePath = this.getAbsolutePath(folderPath);		
		System.out.println(FilePath);
		
		AntAction antAction = new AntAction();
		properties.put("file.path", FilePath+"testFileTrigger.xml");
		antAction.runTarget("File.xml", "create", properties);	
		
		this.addFileTrigger(label, fileTriggerLabel, labelDescription, "30",
				FilePath, fileMask, jobServer,
				"idJobConductorFileTriggerFtExitCheckBox");		
		

		selenium.click("idFileTriggerSave");
	
		if(!selenium.isElementPresent("//span[text()='"+fileTriggerLabel+"']")) {
			selenium.click("idTriggerRefresh");
    	}
		this.waitForElementPresent("//span[text()='"+fileTriggerLabel+"']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+fileTriggerLabel+"']"));
		selenium.setSpeed(MIN_SPEED);
		this.waitForElementPresent("//span[text()='"+label+"']//ancestor::tr" +
				"//span[text()='Running...']", Base.MAX_WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']//ancestor::tr" +
				"//span[text()='Running...']"));		
		
		AntAction antAction1 = new AntAction();
		antAction1.runTarget("File.xml", "delete", properties);			

		this.waitForElementPresent("//span[text()='"+label+"']//ancestor::tr" +
				"//span[text()='Ready to run']", Base.MAX_WAIT_TIME);
		
		int logsConut = this.getXpathCount("//div[contains(@class,'x-grid3-" +
				"cell-inner x-grid3-col-startDate')]");
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.clickWaitForElementPresent("//span[text()='Logs']//ancestor::div[contains(@class,'x-tab-panel" +
				" x-component')]//td[11]/table[contains(@class,'x-component x-unselectable')]//button");
		int logsConutAfterDeleteFile = this.getXpathCount("//div[contains(@class,'x-grid3-" +
		"cell-inner x-grid3-col-startDate')]");
		
		Assert.assertEquals(logsConut, logsConutAfterDeleteFile);
		
		this.clearTask(label);
		
	}
	
//	@Test
	@Parameters({"taskForTestFileTrigger","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled", "addFileTriggerOfCheckExistOption",
		"addFileTriggerOfExistDescription","FolderPath",
		"FileMaskOfXml"})
	public void testCreateFileTriggerCheckCreatedOption(String label,
			String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic, String fileTriggerLabel,
			String fileTriggerDesc, String folderPath, String fileMask) {
		
		this.addTask(label, labelDescription, commonpro, branch, jobName,
				version, context, jobServer, statistic);
		
		selenium.click("idFormSaveButton");
        selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
		selenium.setSpeed(MIN_SPEED);
		
		String FilePath = this.getAbsolutePath(folderPath);		
		System.out.println(FilePath);
		
		this.addFileTrigger(label, fileTriggerLabel, labelDescription, "30",
				FilePath, fileMask, jobServer,
				"idJobConductorFileTriggerFtCreateCheckBox");		
		

		selenium.click("idFileTriggerSave");
	
		if(!selenium.isElementPresent("//span[text()='"+fileTriggerLabel+"']")) {
			selenium.click("idTriggerRefresh");
    	}
		this.waitForElementPresent("//span[text()='"+fileTriggerLabel+"']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+fileTriggerLabel+"']"));
		
		
		AntAction antAction = new AntAction();
		properties.put("file.path", FilePath+"testFileTrigger.xml");
		antAction.runTarget("File.xml", "create", properties);
		
		this.waitForElementPresent("//span[text()='"+label+"']//ancestor::tr" +
				"//span[text()='Running...']", Base.MAX_WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']//ancestor::tr" +
				"//span[text()='Running...']"));		

		this.waitForElementPresent("//span[text()='"+label+"']//ancestor::tr" +
				"//span[text()='Ready to run']", Base.MAX_WAIT_TIME);		
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int logsConut = this.getXpathCount("//div[contains(@class,'x-grid3-" +
				"cell-inner x-grid3-col-startDate')]");		
		
		Assert.assertEquals(logsConut, 1);	
		
		this.clearTask(label);
		AntAction antAction1 = new AntAction();
		antAction1.runTarget("File.xml", "delete", properties);	
		
	}
	
}
