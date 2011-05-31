package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ModifyPlan extends Plan {
    
	//modify a plan
	@Test(groups={"modifyPlan"},dependsOnGroups={"addExist"})
	@Parameters({"plan.label","plan.modifyLabel","plan.modifyDescription"})
	public void testModifyPlan(String label,String newlabel,String newdescription) throws InterruptedException {
		
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent("//div[@class='header-title' and text()='Execution Plan']", WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
//		selenium.setSpeed(MID_SPEED);
		this.clickWaitForElementPresent("idSubModuleRefreshButton");
		this.waitForElementPresent("//span[text()='"+label+"']", WAIT_TIME);
		Thread.sleep(2000);
		selenium.mouseDown("//span[text()='"+label+"']");//select a exist plan
		
//        selenium.setSpeed(MID_SPEED);
        this.typeString("idExecutionPlanPlanFormLabelInput",newlabel);//label
		
        this.typeString("idExecutionPlanPlanFormDescInput",newdescription);//description
		
		
        selenium.click("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
	  this.waitForElementPresent("//span[text()='"+newlabel+"']", WAIT_TIME);
	    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+newlabel+"']"));
	    selenium.setSpeed(MIN_SPEED);

	}
	
}
