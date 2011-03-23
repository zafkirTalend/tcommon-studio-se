package com.talend.tac.cases.executionTask;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@SuppressWarnings("deprecation")
public class TestAddTriggerAddSimpleTrigger extends Login{
    	
    Date date = new Date();
    Date date1 = new Date();
    DateFormat   df   =   new   SimpleDateFormat( "yyyy-MM-dd"); 
   
	// add a simpleTrigger
	
	@Test
	public void testAddTriggerAddSimpleTrigger() {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add SimpleTrigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        selenium.type("//input[@class=' x-form-field x-form-text x-form-invalid']", "TestSimpleTrigger");//label
		selenium.fireEvent("//input[@class=' x-form-field x-form-text x-form-invalid']","blur");
		selenium.type("//input[@name='description']", "test add a SimpleTrigger");//description
		selenium.fireEvent("//input[@name='description']","blur");
	
	    date.setHours(date.getHours()+24);
	    date1.setHours(date.getHours()+48);
	    String s = df.format(date);//system date 
	    String s1 =  df.format(date1);
	    
		selenium.type("//input[@name='startTime']", s);
		selenium.fireEvent("//input[@name='startTime']", "blur");
		selenium.type("//input[@name='endTime']", s1);
		selenium.fireEvent("//input[@name='endTime']", "blur");
	    selenium.type("//input[@name='triggeredCount']", "3");//Number of triggerings
	    selenium.fireEvent("//input[@name='triggeredCount']", "blur");
        selenium.type("//input[@name='repeatInterval']", "3");//Time interval (s)
	    selenium.fireEvent("//input[@name='repeatInterval']", "blur");
	    selenium.click("//div[@class=' x-panel x-component ']/div[2]/div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button[@id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED); 
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='TestSimpleTrigger']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	//add a exist simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTrigger()"})
    public void testAddTriggerAddSimpleTriggerAddExist() {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add SimpleTrigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
       
        selenium.type("//input[@class=' x-form-field x-form-text x-form-invalid']", "TestSimpleTrigger");//label
		selenium.fireEvent("//input[@class=' x-form-field x-form-text x-form-invalid']","blur");
		selenium.type("//input[@name='description']", "test add a exist SimpleTrigger");//description
		selenium.fireEvent("//input[@name='description']","blur");
		selenium.click("//div[@class='x-form-trigger x-form-date-trigger']");//start date
	    
		selenium.click("//a[(span/text()='12')]");
	    selenium.click("//div[@class=' x-form-label-left']/div[4]/div/div/div[@class='x-form-trigger x-form-date-trigger']");//end date
	    selenium.click("//a[(span/text()='13')]");
	    selenium.type("//input[@name='triggeredCount']", "3");//Number of triggerings
	    selenium.fireEvent("//input[@name='triggeredCount']", "blur");
        selenium.type("//input[@name='repeatInterval']", "3");//Time interval (s)
	    selenium.fireEvent("//input[@name='repeatInterval']", "blur");
	    selenium.click("//div[@class=' x-panel x-component ']/div[2]/div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button[@id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED); 
		Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.error.uniqueLabel")));
		selenium.setSpeed(MIN_SPEED);
	   
	}
	
//	add a simpleTrigger of wrong form time interval 
	@Test
    public void testAddTriggerAddSimpleTriggerAddWrongFormTimeInterval() {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add SimpleTrigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        selenium.type("//input[@class=' x-form-field x-form-text x-form-invalid']", "TestSimpleTriggerAddWrongFormTimeInterval");//label
		selenium.fireEvent("//input[@class=' x-form-field x-form-text x-form-invalid']","blur");
		selenium.type("//input[@name='description']", "test add a simpleTrigger of wrong form time interval");//description
		selenium.fireEvent("//input[@name='description']","blur");
		
		selenium.click("//div[@class='x-form-trigger x-form-date-trigger']");//start date
	    selenium.click("//a[(span/text()='12')]");
	    selenium.click("//div[@class=' x-form-label-left']/div[4]/div/div/div[@class='x-form-trigger x-form-date-trigger']");//end date
	    selenium.click("//a[(span/text()='13')]");
	    selenium.type("//input[@name='triggeredCount']", "3");//Number of triggerings
	    selenium.fireEvent("//input[@name='triggeredCount']", "blur");
        selenium.type("//input[@name='repeatInterval']", "aa");//Time interval (s)
	    selenium.fireEvent("//input[@name='repeatInterval']", "blur");
	    selenium.click("//div[@class=' x-panel x-component ']/div[2]/div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button[@id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED); 
		Assert.assertTrue(selenium.isTextPresent("Save failed: The field 'Time interval' has to be set with an integer greater than 0"));
		selenium.click("//button[text()='"+rb.getString("executionPlan.errorStatus.ok")+"']");
		selenium.setSpeed(MIN_SPEED);
	   
	 }
	
	//add a overdue(start date) simpleTrigger
	@Test
    public void testAddTriggerAddSimpleTriggerAddOverdueStartData() {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add SimpleTrigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
       
        selenium.type("//input[@class=' x-form-field x-form-text x-form-invalid']", "TestSimpleTriggerOverdueStartData");//label
		selenium.fireEvent("//input[@class=' x-form-field x-form-text x-form-invalid']","blur");
		selenium.type("//input[@name='description']", "test add a overdue(startDate) simpleTrigger");//description
		selenium.fireEvent("//input[@name='description']","blur");
		
		selenium.click("//div[@class='x-form-trigger x-form-date-trigger']");//start date
	    selenium.click("//a[(span/text()='12')]");
	    selenium.click("//div[@class=' x-form-label-left']/div[4]/div/div/div[@class='x-form-trigger x-form-date-trigger']");//end date
	    selenium.click("//a[(span/text()='13')]");
	    selenium.type("//input[@name='triggeredCount']", "3");//Number of triggerings
	    selenium.fireEvent("//input[@name='triggeredCount']", "blur");
        selenium.type("//input[@name='repeatInterval']", "aa");//Time interval (s)
	    selenium.fireEvent("//input[@name='repeatInterval']", "blur");
	    selenium.click("//div[@class=' x-panel x-component ']/div[2]/div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button[@id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED); 
		Assert.assertTrue(selenium.isTextPresent("Save failed: Start time has to be set with a date greater than server time (2011-03-14 16h45)"));
		selenium.click("//button[text()='"+rb.getString("executionPlan.errorStatus.ok")+"']");
		selenium.setSpeed(MIN_SPEED);
	   
	}
	//add a overdue(start date) simpleTrigger
	@Test
    public void testAddTriggerAddSimpleTriggerAddOverdueEndData() {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_test_task']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add SimpleTrigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
       
        selenium.type("//input[@class=' x-form-field x-form-text x-form-invalid']", "TestSimpleTriggerOverdueEndData");//label
		selenium.fireEvent("//input[@class=' x-form-field x-form-text x-form-invalid']","blur");
		selenium.type("//input[@name='description']", "test add a overdue(endDate) simpleTrigger");//description
		selenium.fireEvent("//input[@name='description']","blur");

	    date.setHours(date.getHours()+24);
	    String s = df.format(date);//system date 
	    System.out.println(s);
	    date1.setHours(date.getHours()-24);
	    String s1 = df.format(date1);//system date 
	    System.out.println(s1);
	    
		selenium.type("//input[@name='startTime']", s);
		selenium.fireEvent("//input[@name='startTime']", "blur");
		selenium.type("//input[@name='endTime']", s1);
		selenium.fireEvent("//input[@name='endTime']", "blur");
	    selenium.type("//input[@name='triggeredCount']", "3");//Number of triggerings
	    selenium.fireEvent("//input[@name='triggeredCount']", "blur");
        selenium.type("//input[@name='repeatInterval']", "5");//Time interval (s)
	    selenium.fireEvent("//input[@name='repeatInterval']", "blur");
	    selenium.click("//div[@class=' x-panel x-component ']/div[2]/div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button[@id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED); 
		Assert.assertTrue(selenium.isTextPresent("Save failed: End time cannot be before start time"));
		selenium.click("//button[text()='"+rb.getString("executionPlan.errorStatus.ok")+"']");
		selenium.setSpeed(MIN_SPEED);
	   
	}
}
