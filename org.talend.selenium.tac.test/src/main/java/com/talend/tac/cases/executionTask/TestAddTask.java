package com.talend.tac.cases.executionTask;

import java.awt.Event;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTask  extends Login {
    
	//creat a method of add task
	public void addTask(String label, String description, String projectName, 
			int statisticNum, String jobName, String serverName) {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
        selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleAddButton");
		selenium.type("//input[@name='label']", label);//plan name /Label
		selenium.fireEvent("//input[@name='label']", "blur");
		selenium.type("//span[text()='Execution task']/parent::legend/" +
				"parent::fieldset/div/div[2]//input", description);//plan name /Label
		selenium.fireEvent("//span[text()='Execution task']/parent::legend/" +
				"parent::fieldset/div/div[2]//input", "blur");
		selenium.click("idCommonProjectListBox");//select a project 
		selenium.mouseDownAt("//div[text()='"+projectName+"']",""+Event.ENTER); 
		
		selenium.click("idCommonJobListBox()");
		this.waitForElementPresent("//div[text()='"+jobName+"']", WAIT_TIME);
		selenium.mouseDownAt("//div[text()='"+jobName+"']",""+Event.ENTER);
		selenium.click("idJobConductorExecutionServerListBox()");
		selenium.mouseDownAt("//div[text()='"+serverName+"']",""+Event.ENTER);
		selenium.click("//label[text()='Statistic:']/parent::div/div/div/div");
		selenium.mouseDownAt("//div[@role='listitem']["+statisticNum+"]",""+Event.ENTER);
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//add a task
	@Test(groups={"AddTask"})
	@Parameters({"label","labelDescription", "project", "job", "jobServer"})
	public void testAddTask(String label, String labelDescription, String project, String job, String server) {
		
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
//			addTask(label, labelDescription, "commonpro", 1, "commonproject", "serverTest");
			addTask(label, labelDescription, project, 1, job, server);
			System.out.println("parameters");
			
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='testTask']"));
			selenium.setSpeed(MIN_SPEED);
			
		}		
				
	}
    
	//add a exist task
	@Test(dependsOnMethods={"testAddTask"})
	@Parameters({"label","existLabelDescription"})
	public void testAddExistTask(String label, String labelDescription, String project, String job, String server) {
		
//		addTask(label, labelDescription, "commonpro", 1, "commonproject", "serverTest");
		addTask(label, labelDescription, project, 1, job, server);
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed("1000");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("executionTask.error.uniqueLabel")));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//add a task of uncheck active 
	@Test(dependsOnMethods={"testAddExistTask"})
	@Parameters({"labelNotChooseActive","notChooseLabelDescription"})
	public void testAddNotChooseActiveTask(String label, String labelDescription, String project, String job, String server) {
		
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
			
//			addTask(label, labelDescription, "commonpro", 1, "commonproject", "serverTest");
			addTask(label, labelDescription, project, 1, job, server);
			
			selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");//uncheck "Active"
			Assert.assertFalse(selenium.isChecked("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']"));
			
			selenium.click("idFormSaveButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='testTaskNotChooseActive']"));
			selenium.setSpeed(MIN_SPEED);
		
		}
	}
}
