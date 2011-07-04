package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
public class TestModifyTask extends Login {
    
	//creation method for modify task
	public void modifiedTask() {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
	    
	}
	
	@Test(groups={"ModifyTask"},dependsOnGroups={"DeleteTask"})
	@Parameters({"label","modifyTask","AddreferenceProjectname","branchNameTrunk","jobNameReferencetjava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testModifyTask(String label,String modifyLabel,String projectName,String branchName,String jobName,
			String version,String context,String jobServer,String statistic) {
		
		modifiedTask();
		
		selenium.setSpeed(MID_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+modifyLabel+"']")) {
		    selenium.setSpeed(MIN_SPEED);
			this.waitForElementPresent("//span[text()='"+label+"']	", WAIT_TIME);
		    selenium.mouseDown("//span[text()='"+label+"']");//select a exist task

            this.typeString("idJobConductorTaskLabelInput", modifyLabel);//task name /Label            
            this.selectDropDownList("idTaskProjectListBox", projectName);
        	this.selectDropDownList("idTaskBranchListBox", branchName);
        	this.selectDropDownList("idTaskJobListBox", jobName);
        	this.selectDropDownList("idTaskVersionListBox", version);
        	this.selectDropDownList("idTaskContextListBox", context);
 
			selenium.click("idFormSaveButton");
			this.waitForElementPresent("//span[text()='"+modifyLabel+"']", WAIT_TIME);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+modifyLabel+"']"));
			
		}	
		selenium.setSpeed(MIN_SPEED);
	}
	
	//test modify label of generating task and check warn info
	@Test(dependsOnMethods={"testModifyTask"})
	@Parameters({"TaskBaseBranch"})
	public void testModifyLabelOfGeneratingTaskAndCheckWarnInfo(String label) {
		
		modifiedTask();		
		
		this.waitForElementPresent("//span[text()='"+label+"']", WAIT_TIME);
		selenium.mouseDown("//span[text()='"+label+"']");//select a exist task
        selenium.setSpeed(MID_SPEED);
		selenium.click("idJobConductorTaskGenerateButton");//generate task
		
		this.typeString("idJobConductorTaskLabelInput", "modifyGeneratingTask");//task name /Label   
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent("Another operation is locking the" +
				" task 'modifyGeneratingTask', please retry later "));
		selenium.setSpeed(MIN_SPEED);	
		
	}
	
	//test modify job of generating task and check warn info
	@Test(dependsOnMethods={"testModifyTask"})
	@Parameters({"TaskBaseBranch","modifyTask","AddreferenceProjectname","branchNameTrunk","jobNameReferencetjava","version0.1",
		"context"})
	public void testModifyJobOfGeneratingTaskAndCheckWarnInfo(String label,String modifyLabel,String projectName,String branchName,String jobName,
			String version,String context) throws InterruptedException {
		
		modifiedTask();		
		
		this.waitForElementPresent("//span[text()='"+label+"']", WAIT_TIME);
		selenium.mouseDown("//span[text()='"+label+"']");//select a exist task
        selenium.setSpeed(MID_SPEED);
		selenium.click("idJobConductorTaskGenerateButton");//generate task
		selenium.setSpeed(MIN_SPEED);
		Thread.sleep(3000);       
        this.selectDropDownList("idTaskProjectListBox", projectName);
    	this.selectDropDownList("idTaskBranchListBox", branchName);
    	this.selectDropDownList("idTaskJobListBox", jobName);
    	this.selectDropDownList("idTaskVersionListBox", version);
    	this.selectDropDownList("idTaskContextListBox", context);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("executionTask.locked")));
		selenium.setSpeed(MIN_SPEED);	
		
	}
	
}
