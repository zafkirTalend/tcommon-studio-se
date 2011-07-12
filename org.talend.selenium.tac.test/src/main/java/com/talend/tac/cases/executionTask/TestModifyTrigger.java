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
    	this.waitForElementPresent("//span[text()='"+taskLabel+"']", WAIT_TIME);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task    	  	
    		
    	this.waitForElementPresent("//span[text()='"+labelBeforeModify+"']", WAIT_TIME);
	    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+labelBeforeModify+"']")); 
	    selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+labelBeforeModify+"']");//select a exist trigger 
    	
    	if(selenium.isElementPresent("idJobConductor"+triggerType+"TriggerLableInput")) {
    		
    		this.typeString("idJobConductor"+triggerType+"TriggerLableInput", labelAfterModified);//modify label
    		
    	} else {
    		
    		this.typeString("idJobConductor"+triggerType+"TriggerLabelInput", labelAfterModified);//modify label
    		                 
    	}
    	
        this.typeString("idJobConductor"+triggerType+"TriggerDescInput", description);//modify description
         
        if(selenium.isElementPresent("//input[@id='idJobConductorFileTriggerFileServerListBox']/parent::div//div[@class='x-form-trigger x-form-trigger-arrow ']")) {
    		
        	selenium.click("//input[@id='idJobConductorFileTriggerFileServerListBox']/parent::div//div[@class='x-form-trigger x-form-trigger-arrow ']");
    		this.waitForElementPresent("//div[text()='use_server_available']", WAIT_TIME);
    		selenium.mouseDown("//div[text()='use_server_available']");
        
        }
	   
        if(selenium.isElementPresent("id"+triggerType+"tTriggerSave")) {
        	
        	selenium.click("id"+triggerType+"tTriggerSave");//click save button
        	
        } else {
        	
        	selenium.click("id"+triggerType+"TriggerSave");//click save button
        	
        }
   		
        if(!selenium.isElementPresent("//span[text()='"+labelAfterModified+"']")) {
        	
    		selenium.click("idTriggerRefresh");//click refresh button
        	
        }

        selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(!selenium.isElementPresent("//span[text()='"+labelBeforeModify+"']"));
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+labelAfterModified+"']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	
	
	//test pause and resume a simple trigger
	@Test
	@Parameters({"labelTRunJobByTaskRun", "addSimpleTriggerLabelNotChooseDate"})
	public void testPauseResumeSimpleTrigger(String taskLabel, String triggerLabel) {
		

		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");//into JobConductor page
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']"));  
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task    	  	
    	
    	selenium.setSpeed(MID_SPEED);
       	selenium.mouseDown("//span[text()='"+triggerLabel+"']");//select a exist trigger
       	
       	selenium.click("idTriggerPause trigger");
       	this.waitForElementPresent("//span[text()='TestSimpleTriggerNotChooseDate']//" +
       			"ancestor::tbody[@role='presentation']//img[@alt='Paused']", WAIT_TIME);
       	Assert.assertTrue(selenium.isElementPresent("//span[text()='TestSimpleTriggerNotChooseDate']//" +
       			"ancestor::tbody[@role='presentation']//img[@alt='Paused']"));
       	selenium.click("idTriggerResume trigger");
    	this.waitForElementPresent("//span[text()='TestSimpleTriggerNotChooseDate']//" +
       			"ancestor::tbody[@role='presentation']//img[@alt='Normal']", WAIT_TIME);
		
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
    	selenium.click("//label[text()='Start time:']/parent::div//img");//start date
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
		
		selenium.click("//label[text()='End time:']/parent::div//img");//start date
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
		
	
    
        String timeIntervalXpath = "idJobConductorSimpleTriggerRptIntervalInput";
       	
       	Assert.assertEquals(selenium.getValue(timeIntervalXpath), "20");
    	this.typeString(timeIntervalXpath, "40");//Time interval (s)
    	
        selenium.setSpeed(MID_SPEED);
	    selenium.click("id"+triggerType+"TriggerSave");//click save button
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idTriggerRefresh");//click refresh button
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
