package com.talend.tac.cases.executionTask;

import java.awt.Event;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTask  extends Login {
    
	//creat a method of add task
	public void addTask(String label, String description, String projectName, String branchName,
			 String jobName, String version, String context, String serverName, String statisticName) {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
        selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
	    selenium.setSpeed(MIN_SPEED);
	    selenium.click("idSubModuleAddButton");
		this.typeString("//input[@name='label']", label);//plan name /Label
		this.typeString("//span[text()='Execution task']/parent::legend/" +
				"parent::fieldset/div/div[2]//input", description);//plan name /Label
		selenium.click("//label[text()='Project:']/parent::div/div/div/div");//select a project 
		this.waitForElementPresent("//div[text()='"+projectName+"' and @role='listitem']", WAIT_TIME);
		selenium.mouseDownAt("//div[text()='"+projectName+"' and @role='listitem']",""+Event.ENTER); 
		selenium.click("//label[text()='Branch:']/parent::div/div/div/div");//select a project 
		this.waitForElementPresent("//div[text()='"+branchName+"' and @role='listitem']", WAIT_TIME);
		selenium.mouseDownAt("//div[text()='"+branchName+"' and @role='listitem']",""+Event.ENTER); 
		selenium.click("//label[text()='Job:']/parent::div/div/div/div");//job
		this.waitForElementPresent("//div[text()='"+jobName+"' and @role='listitem']", WAIT_TIME);
		selenium.mouseDownAt("//div[text()='"+jobName+"' and @role='listitem']",""+Event.ENTER);
		selenium.click("//label[text()='Version:']/parent::div/div/div/div");//version
		this.waitForElementPresent("//div[text()='"+version+"' and @role='listitem']", WAIT_TIME);
		selenium.mouseDownAt("//div[text()='"+version+"' and @role='listitem']",""+Event.ENTER);
		selenium.click("//label[text()='Context:']/parent::div/div/div/div");//context
		this.waitForElementPresent("//div[text()='"+context+"' and @role='listitem']", WAIT_TIME);
		selenium.mouseDownAt("//div[text()='"+context+"' and @role='listitem']",""+Event.ENTER);
		selenium.click("//label[text()='Execution server:']/parent::div/div/div/div");//server
		this.waitForElementPresent("//div[text()='"+serverName+"' and @role='listitem']", WAIT_TIME);
		selenium.mouseDownAt("//div[text()='"+serverName+"' and @role='listitem']",""+Event.ENTER);
		selenium.click("//label[text()='Statistic:']/parent::div/div/div/div");//statistic
		this.waitForElementPresent("//div[text()='"+statisticName+"' and @role='listitem']", WAIT_TIME);
		selenium.mouseDownAt("//div[text()='"+statisticName+"' and @role='listitem']",""+Event.ENTER);
				
	}
	
	//add a task
	@Test(groups={"AddTask"})
	@Parameters({"label","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testAddTask(String label, String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic) {
	   	
		addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);
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
		
		addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);

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
		
		
	
		addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);
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
		
		addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);
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
		
		
		addTask(label, labelDescription, commonpro, branch, jobName, version, context, jobServer, statistic);
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
		
		
			
		addTask(label, labelDescription, referencepro, branch, jobName, version, context, jobServer, statistic);
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
		
        addTask(label, description, projectName, branchName, jobName, version, context, serverName, statisticName);
		
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {	
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}
		
	}
	
}