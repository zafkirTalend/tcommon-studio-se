package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
import com.talend.tac.cases.executePlan.TriggerDate;

public class TestAddTriggerAddCronTrigger extends Login{
    
    TriggerDate date = new TriggerDate().getFuture(24);
	
	public void addTriggerAddCronTrigger(String taskLabel,String labelCronTrigger, String descriptionSronTrigger,String years,
			String weeksStart, String weeksEnd, String monthsStart, String monthsEnd) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("idTriggerAdd trigger...");//add a trigger
		selenium.click("idTriggerAdd CRON trigger");//add a  CronTrigger
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addCronTrigger")+"']"));
		selenium.setSpeed(MIN_SPEED);
		this.typeString("idJobConductorCronTriggerLabelInput", labelCronTrigger);//label

		this.typeString("idJobConductorCronTriggerDescInput", descriptionSronTrigger);//description
			
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
	
	/***add a cron triiger, set date is by UI
	selected job is TRunJob(use tRunJob run child'job)**/
	@Test
//	(groups={"AddCronTrigger"},dependsOnGroups={"DeleteTrigger"})
	@Parameters({"labelRefProJobByMainProTRunJobRun","addCronTriggerLabel","addCronTriggerDescription"})
    public void testAddTriggerAddCronTrigger(String taskLabel,String cronTriggerLabel,String description) {
		
		addTriggerAddCronTrigger(taskLabel,cronTriggerLabel, description, "2011", 
				"Sunday", "Saturday", "January", "December");
    			
		selenium.click("idCrontTriggerSave");
				
		selenium.setSpeed(MID_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+cronTriggerLabel+"']")) {
			selenium.click("idTriggerRefresh");
    	}
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+cronTriggerLabel+"']"));
		selenium.setSpeed(MIN_SPEED);
    }
	
	//add a exist cron triiger, set date is by UI
	@Test(dependsOnMethods={"testAddTriggerAddCronTrigger"})
	@Parameters({"labelRefProJobByMainProTRunJobRun","addCronTriggerLabel","addCronTriggerExistTriggerDescription"})
    public void testAddExistTriggerAddCronTrigger(String taskLabel,String cronTriggerLabel, String description) {
		
		//open to execution task add trigger page
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
       	selenium.mouseDown("//span[text()='"+taskLabel+"']");
		selenium.click("idTriggerAdd trigger...");
		selenium.click("idTriggerAdd CRON trigger");

		this.typeString("idJobConductorCronTriggerLabelInput",cronTriggerLabel);
		//type  description
		this.typeString("idJobConductorCronTriggerDescInput", description);
    	//type minutes
		this.typeString("idJobConductorCronTriggerMintInput", date.minutes);
		//type hours
		this.typeString("idJobConductorCronTriggerHourInput", date.hours);
		//type days
		this.typeString("idJobConductorCronTriggerDayOfMonthInput", date.days);
		//type months
		this.typeString("idJobConductorCronTriggerMonthInput", date.months);
		//type years
		this.typeString("idJobConductorCronTriggerYearInput", date.years);	
		//click save button
		selenium.click("idCrontTriggerSave");
        selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isTextPresent("Save failed: An execution trigger with this name already exists -- For more information see your log file"));
		selenium.setSpeed(MIN_SPEED);
		
    }
	
	//add a cron triiger of date is overdue, set date is by UI
	@Test(dependsOnMethods={"testAddExistTriggerAddCronTrigger"})
	@Parameters({"labelRefProJobByMainProTRunJobRun","addCronTriggerOverdue","addCronTriggerOverdueDescription"})
    public void testAddOverdueTriggerAddCronTrigger(String taskLabel,String cronTriggerLabel, String description) {
		
		selenium.refresh();
		
		addTriggerAddCronTrigger(taskLabel,cronTriggerLabel, description, "2010", 
				"Sunday", "Saturday", "January", "December");
    	selenium.setSpeed(MID_SPEED);		
		selenium.click("//button[@id='idCrontTriggerSave']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed("5000");
	   	Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.error.trigger_will_never_fire")));
	    selenium.setSpeed(MIN_SPEED); 	                        
    }
	
	//add a CronTrigger,selected job is referencetjava(tjava(from referecepro))**/
	@Test(dependsOnMethods={"testAddTriggerAddCronTrigger"})
	@Parameters({ "labelReferenceproTjava","addCronTriggerByHandInputDateLabel", "addCronTriggerByHandInputDateDescription"})
	public void testAddCronByHandInputDateTrigger(String taskLabel,String addCronTrigger,String addCronTriggerDescription) throws InterruptedException{
	
		//open to execution task add trigger page
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
       	selenium.mouseDown("//span[text()='"+taskLabel+"']");
		selenium.click("idTriggerAdd trigger...");
		selenium.click("idTriggerAdd CRON trigger");
//		selenium.setSpeed(MID_SPEED);
		Thread.sleep(5000);
		selenium.setSpeed(MIN_SPEED);
		//type  label
		this.typeString("idJobConductorCronTriggerLabelInput",addCronTrigger);
		//type  description
		this.typeString("idJobConductorCronTriggerDescInput", addCronTriggerDescription);
    	//type minutes
		this.typeString("idJobConductorCronTriggerMintInput", date.minutes);
		//type hours
		this.typeString("idJobConductorCronTriggerHourInput", date.hours);
		//type days
		this.typeString("idJobConductorCronTriggerDayOfMonthInput", date.days);
		//type months
		this.typeString("idJobConductorCronTriggerMonthInput", date.months);
		//type years
		this.typeString("idJobConductorCronTriggerYearInput", date.years);	
		//click save button
		selenium.setSpeed(MID_SPEED);
		selenium.click("idCrontTriggerSave");
						
		selenium.setSpeed(MID_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+addCronTrigger+"']")) {
			selenium.click("idTriggerRefresh");
    	}
	    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+addCronTrigger+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	
}
