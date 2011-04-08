package com.talend.tac.cases.executePlan;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@SuppressWarnings("deprecation")
public class TestAddAddSimpleTriggerUseDefaulttime extends Login{
 
	// add a simpleTrigger use default start time and end time
	@Test(groups={"plan.addtrigger"})
	public void testAddTriggerAddSimpleTrigger() {
    	this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.click("idSubModuleRefreshButton");
    	selenium.mouseDown("//div[text()='testAddPlan']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add SimpleTrigger']");//add a SimpleTrigger
		
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        selenium.setSpeed(MID_SPEED);
        selenium.type("//input[@name='label']", "TestSimpleTrigger");//label
		selenium.fireEvent("//input[@name='label']","blur");
		selenium.type("//input[@class=' x-form-field x-form-text' and @name='description']", "test add a SimpleTrigger");//description
		selenium.fireEvent("//input[@name='description']","blur");
	
        selenium.type("//input[@name='repeatInterval']", "20");//Time interval (s)
	    selenium.fireEvent("//input[@name='repeatInterval']", "blur");
	    selenium.click("//button[@id='idFormSaveButton' and  @class='x-btn-text ']");
	    selenium.setSpeed(MID_SPEED); 
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='TestSimpleTrigger']"));
	    selenium.setSpeed(MIN_SPEED);
	}

}
