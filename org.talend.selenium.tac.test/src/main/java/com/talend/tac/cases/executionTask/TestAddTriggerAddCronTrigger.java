package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTriggerAddCronTrigger extends Login{
    
	public void addTriggerAddCronTrigger(String labelCronTrigger, String descriptionSronTrigger,String years,
			String weeksStart, String weeksEnd, String monthsStart, String monthsEnd) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='testModifyTask']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add CronTrigger']");//add a  CronTrigger
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addCronTrigger")+"']"));
		selenium.setSpeed(MIN_SPEED);
		selenium.type("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset" +
				"//input[@name='label']", labelCronTrigger);//label
		selenium.fireEvent("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset" +
				"//input[@name='label']","blur");
//			                                                                      " x-form-field x-form-text x-form-invalid"
		selenium.type("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", descriptionSronTrigger);//description
		selenium.fireEvent("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']","blur");
		
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
	
	//add a cron triiger
	@Test
	@Parameters({"addCronTriggerLabel","addCronTriggerDescription"})
    public void testAddTriggerAddCronTrigger(String cronTriggerLabel,String description) {
		
		addTriggerAddCronTrigger(cronTriggerLabel, description, "2011", 
				"Sunday", "Saturday", "January", "December");
    			
		selenium.click("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
		"parent::div/parent::div//button[text()='Refresh']");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='TestCronTrigger']"));
		selenium.setSpeed(MIN_SPEED);
    }
	
	//add a exist cron triiger
	@Test(dependsOnMethods={"testAddTriggerAddCronTrigger"})
	@Parameters({"addCronTriggerLabel","addCronTriggerExistTriggerDescription"})
    public void testAddExistTriggerAddCronTrigger(String cronTriggerLabel, String description) {
		
		addTriggerAddCronTrigger(cronTriggerLabel, description, "2011", 
				"Sunday", "Saturday", "January", "December");
    			
		selenium.click("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
        selenium.setSpeed("1000");
		Assert.assertTrue(selenium.isTextPresent("Save failed: An execution trigger with this name already exists -- For more information see your log file"));
		selenium.setSpeed(MIN_SPEED);
		
    }
	
	//add a cron triiger of data is overdue
	@Test(dependsOnMethods={"testAddExistTriggerAddCronTrigger"})
	@Parameters({"addCronTriggerOverdue","addCronTriggerOverdueDescription"})
    public void testAddOverdueTriggerAddCronTrigger(String cronTriggerLabel, String description) {
		
		addTriggerAddCronTrigger(cronTriggerLabel, description, "2010", 
				"Sunday", "Saturday", "January", "December");
    			
		selenium.click("//span[text()='Add Cron trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed("1000");
     	Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.error.trigger_will_never_fire")));
		selenium.setSpeed(MIN_SPEED);	
     	
    }
	
}
