package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;
@Test(groups={"DuplicateTask"},dependsOnGroups={"AddTask"})
public class TestDuplicateTask  extends Login {
    
	@Test
	@Parameters({"labelNotChooseActive","duplicateTask"})
	public void testDuplicateTask(String label, String duplicateLabel) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.click("idSubModuleRefreshButton");
		if(!selenium.isElementPresent("//span[text()='"+duplicateLabel+"']")) {
			selenium.mouseDown("//span[text()='"+label+"']");//select a exist task
	        selenium.click("idSubModuleDuplicateButton");
	        					
			selenium.click("idFormSaveButton");
			Assert.assertTrue(selenium.isElementPresent("//span[text()='"+duplicateLabel+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}		
	}
}
