package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestAddTask  extends AddTask {
	

	//add a task
	@Test(groups={"AddTask"})
	@Parameters({"label","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testAddTask(String label, String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic) {
	   	
		this.addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {	
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}		
				
	}
    
	//add a exist task
	@Test(dependsOnMethods={"testAddTask"})
	@Parameters({"label","existLabelDescription","AddreferenceProjectname","branchNameTrunk","jobNameReferencetjava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testAddExistTask(String label, String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic) {
		
		selenium.refresh();
		
		this.addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);

		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("executionTask.error.uniqueLabel")));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//add a task of uncheck active 
	@Test(dependsOnMethods={"testAddExistTask"})
	@Parameters({"labelNotChooseActive","notChooseLabelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testAddNotChooseActiveTask(String label, String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic) {
		
		
	
		this.addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);
		selenium.setSpeed(MID_SPEED);
		selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");//uncheck "Active"
		Assert.assertFalse(selenium.isChecked("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']"));
		selenium.setSpeed(MIN_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
			
			selenium.click("idFormSaveButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
			selenium.setSpeed(MIN_SPEED);
		
		}
	}
	

	//add a task for test run tRunJob
	@Test(dependsOnMethods={"testAddTask"})
	@Parameters({"labelTRunJobByTaskRun","labelTRunJobByTaskRunDescription","AddcommonProjectname","branchNameTrunk","jobNameTRunJob","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testAddTaskForTestRunTRunJob(String label, String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic) {
		
		this.addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {	
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}		
				
	}
	
	//add a task for test run RefJobByMaintRunJobRun
	@Test(dependsOnMethods={"testAddTask"})
	@Parameters({"labelRefProJobByMainProTRunJobRun","labelRefJobByMaintRunJobRunDescription","AddcommonProjectname","branchNameTrunk","jobNameRefJobByMaintRunJobRun","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testAddTaskForTestRefJobByMaintRunJobRun(String label, String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic) {
		
		
		this.addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
					
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}		
				
	}
	
	//add a task for test run tjava(from referencepro)
	@Test(dependsOnMethods={"testAddTask"})
	@Parameters({"labelReferenceproTjava","labelReferenceproTjavaDescription","AddreferenceProjectname","branchNameTrunk","jobNameReferencetjava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testAddTaskForTestChooseReferecePro(String label, String labelDescription,String referencepro,String branch,String jobName,
			String version,String context,String jobServer,String statistic) {
		
		
			
		this.addTask(label, labelDescription, referencepro, branch, jobName, version, context, jobServer, statistic);
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {	
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}		
				
	}
	
	@Test(dependsOnMethods={"testAddTaskForTestChooseReferecePro"})
	@Parameters({"labelAddJVMParametersForTask","labelAddJVMParametersForTaskDescription","AddcommonProjectname","branchNameTrunk",
		"jobNameTJava","version0.1","context","ServerForUseAvailable","statisticEnabled"})
	public void testAddSimpleTask(String label,String description,String projectName,String branchName,
			String jobName,String version,String context,String serverName,String statisticName) {
		
		this.addTask(label, description, projectName, branchName, jobName, version, context, serverName, statisticName);
		
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {	
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}
		
	}
	
	//add a task with a unactive server
	@Test(dependsOnMethods={"testAddSimpleTask"})
	@Parameters({"TaskWithInactiveServer","labelDescription","AddcommonProjectname","branchNameTrunk",
		"jobNameTJava","version0.1","context","ServerForUseUnavailable","statisticEnabled"})
	public void testAddTaskWithInactiveServer(String label,String description,String projectName,String branchName,
			String jobName,String version,String context,String serverName,String statisticName) {
		
		this.addTask(label, description, projectName, branchName, jobName, version, context, serverName, statisticName);
		
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {	
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}
		
	}
}