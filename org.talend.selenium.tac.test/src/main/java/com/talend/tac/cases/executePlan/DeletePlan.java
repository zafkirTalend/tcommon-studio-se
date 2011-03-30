package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class DeletePlan extends Login {
    
	//delete a plan (choose 'Cancel')
	@Test(dependsOnGroups={"modifyPlan"})
	@Parameters({"plan.delete.label"})
	public void testCancelDeletePlan(String deletelabel) {
		
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");//into 'executionplan'
		Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
		selenium.click("idSubModuleRefreshButton");
		selenium.mouseDown("//div[text()='"+deletelabel+"']");//choose a exist plan
		selenium.chooseCancelOnNextConfirmation();//choose 'Cancel'
		selenium.click("idSubModuleDeleteButton");//click 'Delete' button
		Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected execution plan [\\s\\S]$"));
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+deletelabel+"']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//delete a plan (choose 'Ok')
	@Test(dependsOnMethods={"testCancelDeletePlan"})
	@Parameters({"plan.delete.label"})
	public void testDeletePlan(String deletelabel) {
		
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
		selenium.mouseDown("//div[text()='"+deletelabel+"']");//choose a exist plan
		selenium.chooseOkOnNextConfirmation();//choose 'Ok'
		selenium.click("idSubModuleDeleteButton");//click 'Delete' button
		Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected execution plan [\\s\\S]$"));
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[text()='"+deletelabel+"']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
}
