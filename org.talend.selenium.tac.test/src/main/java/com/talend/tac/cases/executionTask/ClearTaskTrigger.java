package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class ClearTaskTrigger extends Login {
    
    //add a method of remove tasks
	public void clearTask(String taskLabel) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.setSpeed(MIN_SPEED);
	    selenium.click("idSubModuleRefreshButton"); //click "Refresh"
		
		if(selenium.isElementPresent("//span[text()='"+taskLabel+"']")) {
			
			selenium.mouseDown("//span[text()='"+taskLabel+"']");//select  exist task
			selenium.chooseOkOnNextConfirmation();
			selenium.click("idSubModuleDeleteButton");//clcik "Delete"
			Assert.assertTrue(selenium.getConfirmation().matches(other.getString("delete.plan.warning")));
		    
			
		} else {
			
			System.out.println("the task is not exist");
			
		}
				
	}
	
    //remove tasks
    @Test
    @Parameters({"labelAddJVMParametersForTask","labelRefProJobByMainProTRunJobRun","TaskDeleteLogs",
    	"duplicateTask","labelReferenceproTjava","TaskWithInactiveServer"})
    public void clearsTasks(String labelAddJVMParametersForTask, String labelRefProJobByMainProTRunJobRun, 
    		String taskDeleteLogs, String Copy_of_testTaskNotChooseActive,
    		String testAddTaskForTestReferenceproTjava,String TaskWithInactiveServer) {

         clearTask(labelAddJVMParametersForTask);
         clearTask(labelRefProJobByMainProTRunJobRun);
         clearTask("testTaskWithItems");
         clearTask(taskDeleteLogs);
         clearTask(Copy_of_testTaskNotChooseActive);
         clearTask(testAddTaskForTestReferenceproTjava);
         clearTask(TaskWithInactiveServer);

    }
    
    //add a method of remove triggers
    public void clearTriggers(String taskLabel) {
    
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
    	selenium.setSpeed(MID_SPEED);
    	if(selenium.isElementPresent("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/" +
    			"parent::ul/parent::div/parent::div/parent::div/parent::div//div[@class='x-grid3-cell-inner" +
    			" x-grid3-col-label']")) {
    	   
    		selenium.setSpeed(MIN_SPEED);	
			for(int i=0;;i++) {
				
				selenium.setSpeed(MID_SPEED);
			 	if(selenium.isElementPresent("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/" +
		    			"parent::ul/parent::div/parent::div/parent::div/parent::div//div[@class='x-grid3-cell-inner" +
		    			" x-grid3-col-label']")) {
				   
			 		selenium.setSpeed(MIN_SPEED);
					selenium.mouseDown("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/" +
					"parent::ul/parent::div/parent::div/parent::div/parent::div//div[@class='x-grid3-cell-inner" +
					" x-grid3-col-label']");
					selenium.chooseOkOnNextConfirmation();
					selenium.click("idTriggerDelete");
					
					Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected trigger [\\s\\S]$"));
				    								
			 	} else {
			 		
			 		System.out.println("trigger element is no exist");
			 		break;
			 		
			 	}
			
			}
		
    	} else {
	 		
	 		System.out.println("task element is no exist");
	 		
	 	}

    }		
  
    //add a method of remove all triggers
    @Test
    @Parameters({"modifyTask","labelTRunJobByTaskRun","TaskBaseBranch"})
    public void clearsAllTriggers(String modifyTask, String labelTRunJobByTaskRun, String TaskBaseBranch ) {
    	
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	
    	clearTriggers(labelTRunJobByTaskRun);
    	clearTriggers(modifyTask);
    	clearTriggers(TaskBaseBranch);
    	
    }   
    
}
