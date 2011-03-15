package com.talend.tac.cases.executionTask;

import java.awt.Event;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTriggerAddFileTrigger extends Login {
  
	@Test
    public void testAddTriggerAddFileTrigger() {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MAX_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add FileTrigger']");//add a FileTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addFileTrigger")+"']"));
		selenium.type("//input[@class=' x-form-field x-form-text x-form-invalid']", "TestFileTrigger");//label
		selenium.fireEvent("//input[@class=' x-form-field x-form-text x-form-invalid']","blur");
		selenium.getValue("//input[@class=' x-form-field x-form-text x-form-invalid']");
		selenium.type("//input[@name='description']", "test add a CronTrigger");//description
		selenium.fireEvent("//input[@name='description']","blur");
		
		selenium.type("//input[@name='checkTimeInterval']", "5");//checkTimeInterval
		selenium.type("//input[@name='folderPath']", "//D:");//folderPath
		selenium.type("fileMask", "*.txt");//fileMask
		
		selenium.click("//input[@class=' x-form-checkbox' and @name='exist']");//*.txt is exist
		Assert.assertTrue(selenium.isChecked("//input[@class=' x-form-checkbox' and @name='exist']"));
		selenium.click("//input[@class=' x-form-checkbox' and @name='created']");//*.txt is created
		Assert.assertTrue(selenium.isChecked("//input[@class=' x-form-checkbox' and @name='created']"));
		selenium.click("//input[@class=' x-form-checkbox' and @name='modified']");//*.txt is modified
		Assert.assertTrue(selenium.isChecked("//input[@class=' x-form-checkbox' and @name='modified']"));
		selenium.click("//div[@class='x-form-trigger x-form-trigger-arrow ']");//choose server
		selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
		Assert.assertEquals(selenium.getValue("//input[@name='executionServerId']"), "aaaa");
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='TestFileTrigger']"));
		selenium.setSpeed(MIN_SPEED);
	 }
	 
	@Test(dependsOnMethods={"testAdd_trigger_AddFileTrigger()"})
    public void testAddTriggerAddFileTriggerAddExist() {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MAX_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add FileTrigger']");//add a FileTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addFileTrigger")+"']"));
		selenium.type("//input[@class=' x-form-field x-form-text x-form-invalid']", "TestFileTrigger");//label
		selenium.fireEvent("//input[@class=' x-form-field x-form-text x-form-invalid']","blur");
		selenium.getValue("//input[@class=' x-form-field x-form-text x-form-invalid']");
		selenium.type("//input[@name='description']", "test add a CronTrigger");//description
		selenium.fireEvent("//input[@name='description']","blur");
		
		selenium.type("//input[@name='checkTimeInterval']", "5");//checkTimeInterval
		selenium.type("//input[@name='folderPath']", "//D:");//folderPath
		selenium.type("fileMask", "*.txt");//fileMask
		
		selenium.click("//input[@class=' x-form-checkbox' and @name='exist']");//*.txt is exist
		Assert.assertTrue(selenium.isChecked("//input[@class=' x-form-checkbox' and @name='exist']"));
		selenium.click("//input[@class=' x-form-checkbox' and @name='created']");//*.txt is created
		Assert.assertTrue(selenium.isChecked("//input[@class=' x-form-checkbox' and @name='created']"));
		selenium.click("//input[@class=' x-form-checkbox' and @name='modified']");//*.txt is modified
		Assert.assertTrue(selenium.isChecked("//input[@class=' x-form-checkbox' and @name='modified']"));
		selenium.click("//div[@class='x-form-trigger x-form-trigger-arrow ']");//choose server
		selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
		Assert.assertEquals(selenium.getValue("//input[@name='executionServerId']"), "aaaa");
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.error.uniqueLabel")));
		selenium.setSpeed(MIN_SPEED);
	   
	 }
}
