package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
import com.talend.tac.cases.executePlan.TriggerDate;

public class TestAddTriggerAddCronTrigger extends Login{
    
    TriggerDate date = new TriggerDate().getFuture(24);
	
	public void addTriggerAddCronTrigger(String labelCronTrigger, String descriptionSronTrigger,String years,
			String weeksStart, String weeksEnd, String monthsStart, String monthsEnd) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='testModifyTask']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add CRON trigger']");//add a  CronTrigger
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addCronTrigger")+"']"));
		selenium.setSpeed(MIN_SPEED);
		this.typeString("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset" +
				"//input[@name='label']", labelCronTrigger);//label
		//			                                                                      " x-form-field x-form-text x-form-invalid"
		this.typeString("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", descriptionSronTrigger);//description
			
		selenium.click("idSchedulingUiConfigButton");//choose data
		
		selenium.mouseDown("//div[text()='"+years+"']");//choose years
		selenium.mouseDown("//div[text()='"+weeksStart+"']");//choose day of weeks
		selenium.shiftKeyDown();
		selenium.mouseDown("//div[text()='"+weeksEnd+"']");
		selenium.shiftKeyUp();
		selenium.mouseDown("//div[text()='"+monthsStart+"']");//choose months
		selenium.shiftKeyDown();
		selenium.mouseDown("//div[text()='"+monthsEnd+"']");
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
		
	}
	
	//add a cron triiger, set date is by UI
	@Test(groups={"AddCronTrigger"},dependsOnGroups={"DeleteTrigger"})
	@Parameters({"addCronTriggerLabel","addCronTriggerDescription"})
    public void testAddTriggerAddCronTrigger(String cronTriggerLabel,String description) {
		
		addTriggerAddCronTrigger(cronTriggerLabel, description, "2011", 
				"Sunday", "Saturday", "January", "December");
    			
		selenium.click("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
		"parent::div/parent::div//button[text()='Refresh']");
		
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+cronTriggerLabel+"']"));
		selenium.setSpeed(MIN_SPEED);
    }
	
	//add a exist cron triiger, set date is by UI
	@Test(dependsOnMethods={"testAddTriggerAddCronTrigger"})
	@Parameters({"addCronTriggerLabel","addCronTriggerExistTriggerDescription"})
    public void testAddExistTriggerAddCronTrigger(String cronTriggerLabel, String description) {
		
		//open to execution task add trigger page
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
       	selenium.mouseDown("//span[text()='testModifyTask']");
		selenium.click("//button[text()='Add trigger...']");
		selenium.click("//a[text()='Add CRON trigger']");

		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='label']",cronTriggerLabel);
		//type  description
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='description']", description);
    	//type minutes
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='minutes']", date.minutes);
		//type hours
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='hours']", date.hours);
		//type days
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='daysOfMonth']", date.days);
		//type months
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='months']", date.months);
		//type years
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='years']", date.years);	
		//click save button
		selenium.setSpeed(MID_SPEED);
		selenium.click("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
        selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isTextPresent("Save failed: An execution trigger with this name already exists -- For more information see your log file"));
		selenium.setSpeed(MIN_SPEED);
		
    }
	
	//add a cron triiger of date is overdue, set date is by UI
	@Test(dependsOnMethods={"testAddExistTriggerAddCronTrigger"})
	@Parameters({"addCronTriggerOverdue","addCronTriggerOverdueDescription"})
    public void testAddOverdueTriggerAddCronTrigger(String cronTriggerLabel, String description) {
		
		selenium.refresh();
		
		addTriggerAddCronTrigger(cronTriggerLabel, description, "2010", 
				"Sunday", "Saturday", "January", "December");
    	selenium.setSpeed(MID_SPEED);		
		selenium.click("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed(MID_SPEED);
	   	Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.error.trigger_will_never_fire")));
	    selenium.setSpeed(MIN_SPEED); 	
    }
	
	//add a CronTrigger
	@Test(dependsOnMethods={"testAddOverdueTriggerAddCronTrigger"})
	@Parameters({ "addCronTriggerByHandInputDateLabel", "addCronTriggerByHandInputDateDescription"})
	public void testAddCronByHandInputDateTrigger(String addCronTrigger,String addCronTriggerDescription) throws InterruptedException{
	
		//open to execution task add trigger page
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
       	selenium.mouseDown("//span[text()='testModifyTask']");
		selenium.click("//button[text()='Add trigger...']");
		selenium.click("//a[text()='Add CRON trigger']");
//		selenium.setSpeed(MID_SPEED);
		Thread.sleep(5000);
		selenium.setSpeed(MIN_SPEED);
		//type  label
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='label']",addCronTrigger);
		//type  description
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='description']", addCronTriggerDescription);
    	//type minutes
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='minutes']", date.minutes);
		//type hours
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='hours']", date.hours);
		//type days
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='daysOfMonth']", date.days);
		//type months
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='months']", date.months);
		//type years
		this.typeString("//div[text()='Job Conductor']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']" +
				"//span[text()='Add Cron trigger']//ancestor::fieldset//input[@name='years']", date.years);	
		//click save button
		selenium.setSpeed(MID_SPEED);
		selenium.click("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		
		selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
		"parent::div/parent::div//button[text()='Refresh']");
		
		selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+addCronTrigger+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	
}
