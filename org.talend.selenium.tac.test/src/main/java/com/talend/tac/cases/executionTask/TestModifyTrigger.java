package com.talend.tac.cases.executionTask;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
import com.talend.tac.cases.executePlan.TriggerDate;

public class TestModifyTrigger extends Login {
    
	TriggerDate date = new TriggerDate();
	
    //add method for test change trigger name	
	public void renameTrigger(String taskLabel, String labelBeforeModify, String labelAfterModified, 
			String description ,String triggerType) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");//into JobConductor page
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']"));  
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task    	  	
    	
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+labelBeforeModify+"']")); 
    	selenium.mouseDown("//span[text()='"+labelBeforeModify+"']");//select a exist trigger 
    	this.typeString("//span[text()='"+triggerType+"']/parent::legend/parent::fieldset" +
        		"//input[@name='label']", labelAfterModified);//modify label
		
        this.typeString("//span[text()='"+triggerType+"']/parent::legend/parent::fieldset" +
				"//input[@name='description']", description);//modify description
        selenium.setSpeed(MIN_SPEED);
        
        selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='"+triggerType+"']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");//click save button
		selenium.setSpeed(MIN_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+labelAfterModified+"']")) {
    		
			selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
			"parent::div/parent::div//button[text()='Refresh']");//click refresh button
			
    	}
		
		Assert.assertTrue(!selenium.isElementPresent("//span[text()='"+labelBeforeModify+"']"));
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+labelAfterModified+"']"));
		
	}
	
	
	
	//test pause and resume a simple trigger
	@Test(dependsOnGroups={"AddCronTrigger"})
	@Parameters({"labelTRunJobByTaskRun", "addSimpleTriggerLabelNotChooseDate", "fileTiggerLabelAfterModified",
		"fileTiggerLabelAfterModifiedDescription", "simpleTriggerType"})
	public void testPauseResumeSimpleTrigger(String taskLabel, String triggerLabel, String triggerType) {
		

		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");//into JobConductor page
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']"));  
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task    	  	
    	
    	selenium.setSpeed(MID_SPEED);
       	selenium.mouseDown("//span[text()='"+triggerLabel+"']");//select a exist trigger
       	
       	selenium.click("//button[text()='Pause trigger']");
       	this.waitForElementPresent("//span[text()='TestSimpleTriggerNotChooseDate']//" +
       			"ancestor::tbody[@role='presentation']//img[@alt='Paused']", WAIT_TIME);
       	Assert.assertTrue(selenium.isElementPresent("//span[text()='TestSimpleTriggerNotChooseDate']//" +
       			"ancestor::tbody[@role='presentation']//img[@alt='Paused']"));
       	selenium.click("//button[text()='Resume trigger']");
    	this.waitForElementPresent("//span[text()='TestSimpleTriggerNotChooseDate']//" +
       			"ancestor::tbody[@role='presentation']//img[@alt='Normal']", WAIT_TIME);
       	Assert.assertTrue(selenium.isElementPresent("//span[text()='TestSimpleTriggerNotChooseDate']//" +
       			"ancestor::tbody[@role='presentation']//img[@alt='Normal']"));
		
	}
	
	//test rename a simple trigger
	@Test(dependsOnMethods={"testPauseResumeSimpleTrigger"})
	@Parameters({"modifyTask", "addSimpleTriggerNumberOfTriggeringsRunnedAutoStopLabel", "simpleTiggerLabelAfterModified",
		"simpleTiggerLabelAfterModifiedDescription", "simpleTriggerType"})
	public void testRanameSimpleTrigger(String taskLabel, String labelBeforeModify,String labelAfterModified
			, String description, String triggerType) {
		
		renameTrigger(taskLabel, labelBeforeModify, labelAfterModified, description, triggerType);
		
	}
	
	//test rename a cron trigger
	@Test(dependsOnMethods={"testRanameSimpleTrigger"})
	@Parameters({"labelRefProJobByMainProTRunJobRun", "addCronTriggerLabel", "cronTiggerLabelAfterModified",
		"cronTiggerLabelAfterModifiedDescription", "cronTriggerType"})
	public void testRanameCronTrigger(String taskLabel, String labelBeforeModify,String labelAfterModified
			, String description, String triggerType) {
		
		renameTrigger(taskLabel, labelBeforeModify, labelAfterModified, description, triggerType);
		
	}
	
	
	//test change time interval of simple trigger
	@Test(dependsOnMethods={"testRanameCronTrigger"})
	@Parameters({"labelTRunJobByTaskRun", "addSimpleTriggerLabelNotChooseDate","simpleTriggerType"})
	public void testModifySimpleTriggerTimeInterval(String taskLabel, String triggerLabel, String triggerType) {
		

		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");//into JobConductor page
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']"));  
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task    	  	
    	
    	selenium.setSpeed(MID_SPEED);
       	selenium.mouseDown("//span[text()='"+triggerLabel+"']");//select a exist trigger
        this.waitForElementPresent("//input[@name='startTime']", WAIT_TIME);    
//        selenium.keyDown("//input[@name='startTime']", ""+Event.BACK_SPACE);
    	selenium.click("//label[text()='Start time:']/parent::div//div/div/div");//start date
		if(date.isClickFutureMonthButton(date.getFuture(24))) {//type in start time
			
        	selenium.setSpeed(MID_SPEED);
        	selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-right-icon x-component']");
        	selenium.click("//td[@class='x-date-active']/a/span[text()='"+date.getFuture(24).days+"']");
        	selenium.setSpeed(MIN_SPEED);
        	
        }

    	else{//click tianshu}  
        	selenium.setSpeed(MID_SPEED);
        	selenium.click("//td[@class='x-date-active']/a/span[text()='"+date.getFuture(24).days+"']");
        	selenium.setSpeed(MIN_SPEED);
        	
        }
		
		selenium.click("//label[text()='End time:']/parent::div//div/div/div");//start date
		if(date.isClickFutureMonthButton(date.getFuture(48))) {//type in start time
			
        	selenium.setSpeed(MID_SPEED);
        	selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-right-icon x-component']");
        	selenium.click("//td[@class='x-date-active']/a/span[text()='"+date.getFuture(48).days+"']");
        	selenium.setSpeed(MIN_SPEED);
        	
        }
        
    	else{//click tianshu}  
        	selenium.setSpeed(MID_SPEED);
        	selenium.click("//td[@class='x-date-active']/a/span[text()='"+date.getFuture(48).days+"']");
        	selenium.setSpeed(MIN_SPEED);
        	
        }  
		
	
    
        String timeIntervalXpath = "//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='repeatInterval']";
       	
       	Assert.assertEquals(selenium.getValue(timeIntervalXpath), "20");
    	this.typeString(timeIntervalXpath, "40");//Time interval (s)
    	
        selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='"+triggerType+"']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");//click save button
		selenium.setSpeed(MIN_SPEED);
		selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
		"parent::div/parent::div//button[text()='Refresh']");//click refresh button
        selenium.setSpeed(MID_SPEED);
	  	selenium.mouseDown("//span[text()='"+triggerLabel+"']");//select a exist trigger
       	Assert.assertEquals(selenium.getValue(timeIntervalXpath), "40");
       	selenium.setSpeed(MIN_SPEED);
		
	}
	
	//test rename a file trigger
	@Test(dependsOnMethods={"testModifySimpleTriggerTimeInterval"})
	@Parameters({"TaskBaseBranch", "addFileTriggerOfExist", "fileTiggerLabelAfterModified",
		"fileTiggerLabelAfterModifiedDescription", "fileTriggerType"})
	public void testRanameFileTrigger(String taskLabel, String labelBeforeModify,String labelAfterModified
			, String description, String triggerType) {
		
		renameTrigger(taskLabel, labelBeforeModify, labelAfterModified, description, triggerType);
		
	}
	
}
