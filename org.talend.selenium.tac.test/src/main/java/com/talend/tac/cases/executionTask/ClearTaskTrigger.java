package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ClearTaskTrigger extends TaskUtils {
    
   
    @Test
    @Parameters({"labelRefProJobByMainProTRunJobRun","TaskDeleteLogs",
    	"labelReferenceproTjava","TaskWithInactiveServer","duplicateTask",
    	"taskWithTjavaWithMulripleCheckpoint","taskWithTrunjobWithCheckpoint","TaskWithJobOfGenerateBigLogs"
    	,"TaskOfAutomatedSelectingComboValue","taskForTestFileTrigger","TaskLabelOfLatestJob"})
    public void clearsTasks(String labelRefProJobByMainProTRunJobRun, 
    		String taskDeleteLogs,String testAddTaskForTestReferenceproTjava,
    		String TaskWithInactiveServer,String Copy_of_testTaskNotChooseActive,String 
    		taskWithTjavaWithMulripleCheckpoint,String taskWithTrunjobWithCheckpoint
    		,String taskWithJobOfGenerateBigLogs,String taskOfAutomatedSelectingComboValue
    		,String taskForTestFileTrigger,String taskLabelOfLatestJob) {

         clearTask(labelRefProJobByMainProTRunJobRun);
         clearTask("testTaskWithItems");
         clearTask(taskDeleteLogs);
         clearTask(testAddTaskForTestReferenceproTjava);
         clearTask(TaskWithInactiveServer);
         clearTask(Copy_of_testTaskNotChooseActive);
         clearTask(taskWithTjavaWithMulripleCheckpoint);
         clearTask(taskWithTrunjobWithCheckpoint);
         clearTask(taskWithJobOfGenerateBigLogs);
         clearTask(taskOfAutomatedSelectingComboValue);
         clearTask(taskForTestFileTrigger);
         clearTask(taskLabelOfLatestJob);
         
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
