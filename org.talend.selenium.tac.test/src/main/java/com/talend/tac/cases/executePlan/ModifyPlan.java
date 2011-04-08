package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class ModifyPlan extends Login {
    
	//modify a plan
	@Test(groups={"modifyPlan"},dependsOnGroups={"addExist"})
	@Parameters({"plan.label","plan.modifyLabel","plan.modifyDescription"})
	public void testModifyPlan(String label,String newlabel,String newdescription) {
		
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");//into 'executionplan'
		Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
		selenium.click("idSubModuleRefreshButton");
		selenium.mouseDown("//div[text()='"+label+"']");//select a exist plan
		
        selenium.setSpeed(MID_SPEED);
        selenium.type("//input[@name='label']", newlabel);//label
		selenium.fireEvent("//input[@name='label']","blur");
		selenium.type("//input[@class=' x-form-field x-form-text' and @name='description']", newdescription);//description
		selenium.fireEvent("//input[@name='description']","blur");
		
	    selenium.click("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+newlabel+"']"));
	    selenium.setSpeed(MIN_SPEED);

	}
	
}
