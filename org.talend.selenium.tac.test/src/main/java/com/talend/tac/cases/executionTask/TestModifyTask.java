package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
public class TestModifyTask extends Login {
    
	@Test(groups={"ModifyTask"},dependsOnGroups={"DeleteTask"})
	@Parameters({"label","modifyTask","AddreferenceProjectname","branchNameTrunk","jobNameReferencetjava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testModifyTask(String label,String modifyLabel,String projectName,String branchName,String jobName,
			String version,String context,String jobServer,String statistic) {
		
		if(!selenium.isElementPresent("//span[text()='"+modifyLabel+"']")) {
			
			this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
			selenium.setSpeed(MID_SPEED);
		    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
			
		    selenium.click("idSubModuleRefreshButton");
			selenium.mouseDown("//span[text()='"+label+"']");//select a exist task
            selenium.setSpeed(MIN_SPEED);
            this.typeString("idJobConductorTaskLabelInput", modifyLabel);//task name /Label
            
            this.selectDropDownList("idTaskProjectListBox", projectName);
        	this.selectDropDownList("idTaskBranchListBox", branchName);
        	this.selectDropDownList("idTaskJobListBox", jobName);
        	this.selectDropDownList("idTaskVersionListBox", version);
        	this.selectDropDownList("idTaskContextListBox", context);
 
			selenium.click("idFormSaveButton");
			this.waitForElementPresent("//span[text()='"+modifyLabel+"']", WAIT_TIME);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+modifyLabel+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}	
	}
}
