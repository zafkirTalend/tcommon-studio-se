package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class ModifyPlan extends Login {
    
	//modify a plan
	@Test(groups={"modifyPlan"},dependsOnGroups={"addPlan"})
	public void testModifyPlan() {
		
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");//into 'executionplan'
		Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
		selenium.click("idSubModuleRefreshButton");
		selenium.mouseDown("//div[text()='testAddPlan']");//select a exist plan
		
        selenium.setSpeed(MID_SPEED);
        selenium.type("//input[@name='label']", "testModifyPlan");//label
		selenium.fireEvent("//input[@name='label']","blur");
		selenium.type("//input[@class=' x-form-field x-form-text' and @name='description']", "test modify a plan");//description
		selenium.fireEvent("//input[@name='description']","blur");
		
	    selenium.click("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='testModifyPlan']"));
	    selenium.setSpeed(MIN_SPEED);

	}
	
}
