package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTriggerAddCronTrigger extends Login{
   
	@Test
    public void testAddTriggerAddCronTrigger() {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add CronTrigger']");//add a  CronTrigger
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addCronTrigger")+"']"));
		selenium.setSpeed(MID_SPEED);
		selenium.type("//div[@class='x-form-item ']/div/div/input[@class=' x-form-field x-form-text  x-form-invalid' and @name='label']", "TestCronTrigger");//label
		selenium.fireEvent("//div[@class='x-form-item ']/div/div/input[@class=' x-form-field x-form-text  x-form-invalid' and @name='label']","blur");
//			                                                                      " x-form-field x-form-text x-form-invalid"
		selenium.type("//input[@name='description']", "test add a CronTrigger");//description
		selenium.fireEvent("//input[@name='description']","blur");
		
		selenium.click("idSchedulingUiConfigButton");//choose data
		
		selenium.mouseDown("//div[text()='2011']");//choose years
		selenium.mouseDown("//div[text()='Sunday']");//choose day of weeks
		selenium.shiftKeyDown();
		selenium.mouseDown("//div[text()='Saturday']");
		selenium.shiftKeyUp();
		selenium.mouseDown("//div[text()='March']");//choose months
		selenium.mouseDown("//div[@class='x-column-inner']/div[2]/div[2]/div/div/div[text()='00']");//choose hours
		selenium.shiftKeyDown();
		selenium.mouseDown("//div[@class='x-column-inner']/div[2]/div[2]/div/div/div[text()='23']");
		selenium.shiftKeyUp();
		selenium.mouseDown("//div[text()='00']");//choose minutes
		selenium.shiftKeyDown();
		selenium.mouseDown("//div[text()='29']");
		selenium.shiftKeyUp();
		selenium.mouseDown("//div[text()='30']");
		selenium.shiftKeyDown();
		selenium.mouseDown("//div[text()='59']");
		selenium.shiftKeyUp();
		selenium.click("idSchedulingApplyButton");//save data
		
		selenium.click("idFormSaveButton");
        Assert.assertTrue(selenium.isElementPresent("//div[text()='TestCronTrigger']"));
		selenium.setSpeed(MIN_SPEED);
    }
}
