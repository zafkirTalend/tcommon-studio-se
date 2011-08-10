package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;

public class TestGenerateDeployRunStopPauseTaskResumeTask extends TaskUtils {

	//test deploy a simple task
	@Test
	@Parameters({"modifyTask"})
	public void testGenerateTask(String taskLabel) {
		 
		
		generateDeployRunTask(taskLabel,"idJobConductorTaskGenerateButton");//click generate button
		this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
				"//span[text()='Generating...']", WAIT_TIME);
	   	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
	   			"//span[text()='Generating...']"));
    	this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
    			"//span[text()='Ready to deploy']", Base.MAX_WAIT_TIME);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
    			"//span[text()='Ready to deploy']"));
    	
	}
	
	//test deploy a simple task
	@Test(dependsOnMethods={"testGenerateTask"})
	@Parameters({"modifyTask"})
	public void testDeployTask(String taskLabel) {
		
		generateDeployRunTask(taskLabel,"//button[@id='idJobConductorTaskDeployButton' and text()='Deploy']");//click Deploy button
		this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
				"//span[text()='Ready to run']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
				"//span[text()='Ready to run']"));
//		selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
		
	}
	
	//test run a simple task
	@Test(dependsOnMethods={"testDeployTask"})
	@Parameters({"modifyTask"})
	public void testRunSimpleTask(String taskLabel) {
		 
		generateDeployRunTask(taskLabel,"//button[@id='idJobConductorTaskRunButton' and text()='Run']");//click Run button
		this.waitForElementPresent("//span[text()='Real time statistics']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='Real time statistics']"));
		this.waitForElementPresent("//label[text()='Ok']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//label[text()='Ok']"));
//		selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
				
	}

	//test stop a running task
	@Test(dependsOnMethods={"testGenerateTask"})	
	@Parameters({"modifyTask", "statisticRemoved(regeneration needed, fastest)", "statisticEnabled(regeneration needed)"})
	public void testStopARunningTask(String taskLabel, String statisticRemovedRegeneration, String statisticEnabledReGeneration) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");//into executiontask page
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select an simple task
		selenium.click("//button[@id='idJobConductorTaskRunButton' and text()='Run']");//click Run button
		
		selenium.setSpeed(MID_SPEED);
		selenium.mouseDown("//span[text()='"+taskLabel+"']");//select an simple task
		selenium.setSpeed(MIN_SPEED);
		selenium.chooseOkOnNextConfirmation();		
		selenium.click("idJobConductorTaskStopButton");//click stop button
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to stop the task " +
				"'"+taskLabel+"' before its normal end [\\s\\S]$"));
		selenium.setSpeed(MIN_SPEED);
		this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr[@role='presentation']" +
				"//span[text()='Killed by user']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr[@role='presentation']" +
				"//span[text()='Killed by user']"));
    	
	}
    
	
	//test generating a task using remote CommandLine
	/*needed start a remote commandLine*/
	@Test(dependsOnMethods={"testGenerateTask"})
	@Parameters({"remotehostAddress", "modifyTask", "localhostAddress"})
	public void testGenerateTaskUsingRemoteCommandLine(String remotehostAddress, String taskLabel,String localhostAddress) {
		
			
		
		generateDeployRunTask(taskLabel,"idJobConductorTaskGenerateButton");//click generate button
		selenium.setSpeed(MID_SPEED);
	   	Assert.assertTrue(selenium.isTextPresent("Generating..."));
    	selenium.setSpeed(MIN_SPEED);
    	this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
    			"//span[text()='Ready to deploy']", Base.MAX_WAIT_TIME);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
    			"//span[text()='Ready to deploy']"));
    	
    	changeCommandLineConfig(localhostAddress);
    	
	}
    

	//test generating a task using remote wrong CommandLine
	@Test(dependsOnMethods={"testGenerateTask"})
	@Parameters({"remotehostAddressWithWrong", "modifyTask", "localhostAddress"})
	public void testGenerateTaskUsingRemoteWrongCommandLine(String remotehostAddressWithWrong, String taskLabel,String localhostAddress) {
		
		changeCommandLineConfig(remotehostAddressWithWrong);
		
		generateDeployRunTask(taskLabel,"idJobConductorTaskGenerateButton");//click generate button	
    	this.waitForElementPresent("//span[text()='"+rb.getString("executionTask.errorStatus.connectionToCommandLineFailed")+"']", WAIT_TIME);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("executionTask.errorStatus.connectionToCommandLineFailed")+"']"));
    	
    	changeCommandLineConfig(localhostAddress);
    	
	}
		
    
	
	//Run a task with a Inactive server
	@Test(dependsOnMethods={"testGenerateTask"})
	@Parameters({"TaskWithInactiveServer","labelDescription","AddcommonProjectname","branchNameTrunk",
		"jobNameTJava","version0.1","context","ServerForUseUnavailable","statisticEnabled"})
	public void testRunTaskWithInactiveServer(String label,String description,String projectName,String branchName,
			String jobName,String version,String context,String serverName,String statisticName) {
				
		generateDeployRunTask(label, "//button[@id='idJobConductorTaskRunButton' and text()='Run']");//click Run button
		this.waitForElementPresent("//span[text()='JobServer is inactive']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='JobServer is inactive']"));
		this.waitForElementPresent("//label[text()='JobServer is inactive']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//label[text()='JobServer is inactive']"));
		selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
		
		
	}
	
	//generate a generating task and check warn info
	@Test(dependsOnMethods={"testRunSimpleTask"})
	@Parameters({"modifyTask"})
	public void testGenerateAGeneratingTask(String taskLabel) {
		
		generateDeployRunTask(taskLabel, "idJobConductorTaskGenerateButton");//click generate button
		this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
				"//span[text()='Generating...']", WAIT_TIME);
		
		selenium.setSpeed(MAX_SPEED);
		selenium.click("//button[@id='idJobConductorTaskGenerateButton']");
		selenium.click(MIN_SPEED);		
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isTextPresent("The requested action cannot be carried out because the execution task"), "The requested action cannot be carried out because the execution " +
				"task is without appear");
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//test pauseTask button and resumeTask button
	@Test(dependsOnMethods={"testRunSimpleTask"})
	@Parameters({"TaskBaseBranch","addCronTriggerLabel","addCronTriggerDescription"})
	public void testPauseTaskResumeTask(String taskLabel, String cronTriggerLabel,String description) {
		
		intoJobConductor(taskLabel);
		
		addTriggerAddCronTrigger(taskLabel,cronTriggerLabel, description, "2011", 
				"Sunday", "Saturday", "January", "December");
    			
		selenium.click("idCrontTriggerSave");
				
		selenium.setSpeed(MID_SPEED);
		
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+cronTriggerLabel+"']"));
		selenium.setSpeed(MIN_SPEED);
		
		selenium.setSpeed(MID_SPEED);
		selenium.click("idJobConductorTaskPauseButton");
		selenium.getConfirmation();
		selenium.setSpeed(MIN_SPEED);
		
		this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::table[@class='x-grid3-row-table']//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-triggersStatus')]" +
				"//img[@title='All triggers paused']", WAIT_TIME+35);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::table[@class='x-grid3-row-table']//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-triggersStatus')]" +
				"//img[@title='All triggers paused']"), "//span[text()='"+taskLabel+"']//ancestor::table[@class='x-grid3-row-table']//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-triggersStatus')]" +
						"//img[@title='All triggers paused'] is without appear");
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+cronTriggerLabel+"']//ancestor::table[@class='x-grid3-row-table']" +
				"//img[@alt='Paused']"), "//span[text()='"+cronTriggerLabel+"']//ancestor::table[@class='x-grid3-row-table']//img[@alt='Paused'] is without appear");
		
		selenium.setSpeed(MID_SPEED);
		selenium.click("idJobConductorTaskResumeTriggerButton");
		selenium.getConfirmation();
		selenium.setSpeed(MIN_SPEED);
		
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::table[@class='x-grid3-row-table']//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-triggersStatus')]" +
				"//img[@title='At least one Trigger is running' ]"), "//span[text()='"+taskLabel+"']//ancestor::table[@class='x-grid3-row-table']//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-triggersStatus')]" +
						"//img[@title='At least one Trigger is running' ] is without appear");
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+cronTriggerLabel+"']//ancestor::table[@class='x-grid3-row-table']" +
		"//img[@alt='Normal']"), "//span[text()='"+cronTriggerLabel+"']//ancestor::table[@class='x-grid3-row-table']//img[@alt='Normal'] is without appear");
		
	    clearTriggers(taskLabel);
	    
	}
	
}
  