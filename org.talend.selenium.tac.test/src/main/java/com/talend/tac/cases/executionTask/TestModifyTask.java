package com.talend.tac.cases.executionTask;

import java.awt.Event;

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
			selenium.click("//label[text()='Project:']/parent::div/div/div/div");//modify project
			this.waitForElementPresent("//div[text()='"+projectName+"' and @role='listitem']", WAIT_TIME);
			selenium.mouseDownAt("//div[text()='"+projectName+"' and @role='listitem']",""+Event.ENTER); 
			selenium.click("//label[text()='Branch:']/parent::div/div/div/div");//modify branch
			this.waitForElementPresent("//div[text()='"+branchName+"' and @role='listitem']", WAIT_TIME);
			selenium.mouseDownAt("//div[text()='"+branchName+"' and @role='listitem']",""+Event.ENTER); 
			selenium.click("//label[text()='Job:']/parent::div/div/div/div");//modify job
			this.waitForElementPresent("//div[text()='"+jobName+"' and @role='listitem']", WAIT_TIME);
			selenium.mouseDownAt("//div[text()='"+jobName+"' and @role='listitem']",""+Event.ENTER);
			selenium.click("//label[text()='Version:']/parent::div/div/div/div");//version
			this.waitForElementPresent("//div[text()='"+version+"' and @role='listitem']", WAIT_TIME);
			selenium.mouseDownAt("//div[text()='"+version+"' and @role='listitem']",""+Event.ENTER);
			selenium.click("//label[text()='Context:']/parent::div/div/div/div");//context
			this.waitForElementPresent("//div[text()='"+context+"' and @role='listitem']", WAIT_TIME);
			selenium.mouseDownAt("//div[text()='"+context+"' and @role='listitem']",""+Event.ENTER);
			
			selenium.click("idFormSaveButton");
			this.waitForElementPresent("//span[text()='"+modifyLabel+"']", WAIT_TIME);
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+modifyLabel+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}	
	}
}
