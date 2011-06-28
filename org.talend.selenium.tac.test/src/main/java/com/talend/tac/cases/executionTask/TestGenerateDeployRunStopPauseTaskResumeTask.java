package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestGenerateDeployRunStopPauseTaskResumeTask extends Login {
	
	public void generateDeployRunTask(String taskLabel, String buttonXpath) {
		
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");//into executiontask page
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select an simple task
    	selenium.click(buttonXpath);//click function button
          	
	}
	
	public void changeCommandLineConfig(String hostAddress) {
		
		selenium.refresh();
		this.clickWaitForElementPresent("idMenuConfigElement");//into Configuration page
		this.waitForElementPresent("//div[contains(text(),'Command line/primary')]", WAIT_TIME);
		selenium.mouseDown("//div[contains(text(),'Command line/primary')]");
		this.clickWaitForElementPresent("//div[contains(text(),'Command line/primary')]//ancestor::div[@class='x-grid-group ']" +
				"//div[text()='Host']//ancestor::tr[@role='presentation']//img[@title='Click to edit']");
		this.waitForElementPresent("//div[@class=' x-form-field-wrap  x-component']//input", WAIT_TIME); 
		System.out.println("*--------------*");
		this.typeString("//div[@class=' x-form-field-wrap  x-component']//input",hostAddress);
			
	}
	
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
    			"//span[text()='Ready to deploy']", WAIT_TIME);
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
	
	//Run a task with a job containing a subjob
	@Test(dependsOnMethods={"testRunSimpleTask"})
	@Parameters({"labelTRunJobByTaskRun"})
	public void testRunTaskWithJobContainingSubjob(String taskLabel) {
		
		generateDeployRunTask(taskLabel, "//button[@id='idJobConductorTaskRunButton' and text()='Run']");//click Run button
		this.waitForElementPresent("//span[text()='Real time statistics']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='Real time statistics']"));
		this.waitForElementPresent("//label[text()='Ok']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//label[text()='Ok']"));
		selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
		
	}

	//test stop a running task
	@Test(dependsOnMethods={"testRunTaskWithJobContainingSubjob"})
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
	@Test(dependsOnMethods={"testStopARunningTask"})
	@Parameters({"remotehostAddress", "modifyTask", "localhostAddress"})
	public void testGenerateTaskUsingRemoteCommandLine(String remotehostAddress, String taskLabel,String localhostAddress) {
		
		changeCommandLineConfig(remotehostAddress);
		
		generateDeployRunTask(taskLabel,"idJobConductorTaskGenerateButton");//click generate button
		selenium.setSpeed(MID_SPEED);
	   	Assert.assertTrue(selenium.isTextPresent("Generating..."));
    	selenium.setSpeed(MIN_SPEED);
    	this.waitForElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
    			"//span[text()='Ready to deploy']", WAIT_TIME);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+taskLabel+"']//ancestor::tr" +
    			"//span[text()='Ready to deploy']"));
    	
    	changeCommandLineConfig(localhostAddress);
    	
	}
    

	//test generating a task using remote wrong CommandLine
	@Test(dependsOnMethods={"testGenerateTaskUsingRemoteCommandLine"})
	@Parameters({"remotehostAddressWithWrong", "modifyTask", "localhostAddress"})
	public void testGenerateTaskUsingRemoteWrongCommandLine(String remotehostAddressWithWrong, String taskLabel,String localhostAddress) {
		
		changeCommandLineConfig(remotehostAddressWithWrong);
		
		generateDeployRunTask(taskLabel,"idJobConductorTaskGenerateButton");//click generate button	
    	this.waitForElementPresent("//span[text()='"+rb.getString("executionTask.errorStatus.connectionToCommandLineFailed")+"']", WAIT_TIME);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("executionTask.errorStatus.connectionToCommandLineFailed")+"']"));
    	
    	changeCommandLineConfig(localhostAddress);
    	
	}
	
    
	
	//Run a task with a Inactive server
	@Test(dependsOnMethods={"testGenerateTaskUsingRemoteWrongCommandLine"})
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
	
}
