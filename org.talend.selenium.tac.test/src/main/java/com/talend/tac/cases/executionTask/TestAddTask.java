package com.talend.tac.cases.executionTask;

import java.awt.Event;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTask  extends Login {
    
	//creat a method of add task
	public void addTask(String label, String description, int projectNum, 
			int statisticNum, int jobNum, int serverNum) {
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
		selenium.mouseDownAt("//div[@role='listitem']["+projectNum+"]",""+Event.ENTER); 
		
		selenium.click("idCommonJobListBox()");
		selenium.mouseDownAt("//div[@role='listitem']["+jobNum+"]",""+Event.ENTER);
		selenium.click("idJobConductorExecutionServerListBox()");
		selenium.mouseDownAt("//div[@role='listitem']["+serverNum+"]",""+Event.ENTER);
		selenium.click("//label[text()='Statistic:']/parent::div/div/div/div");
		selenium.mouseDownAt("//div[@role='listitem']["+statisticNum+"]",""+Event.ENTER);
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//add a task
	@Test(groups={"AddTask"})
	@Parameters({"label","labelDescription"})
	public void testAddTask(String label, String labelDescription) {
		
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
			
			addTask(label, labelDescription, 1, 1, 1, 1);
			
			selenium.click("idFormSaveButton");
	        selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='testTask']"));
			selenium.setSpeed(MIN_SPEED);
			
		}		
				
	}
    
	//add a exist task
	@Test(dependsOnMethods={"testAddTask"})
	@Parameters({"label","existLabelDescription"})
	public void testAddExistTask(String label, String labelDescription) {
		
		addTask(label, labelDescription, 1, 1, 1, 1);
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed("1000");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("executionTask.error.uniqueLabel")));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//add a task of uncheck active 
	@Test(dependsOnMethods={"testAddExistTask"})
	@Parameters({"labelNotChooseActive","notChooseLabelDescription"})
	public void testAddNotChooseActiveTask(String label, String labelDescription) {
		
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
			
		
			addTask(label, labelDescription, 1, 1, 1, 1);
			
			selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");//uncheck "Active"
			Assert.assertFalse(selenium.isChecked("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']"));
			
			selenium.click("idFormSaveButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='testTaskNotChooseActive']"));
			selenium.setSpeed(MIN_SPEED);
		
		}
	}
}
