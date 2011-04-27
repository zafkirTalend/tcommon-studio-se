package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
import com.talend.tac.cases.executePlan.TriggerDate;

public class TestAddTriggerAddSimpleTrigger extends Login{
    	   
    TriggerDate date = new TriggerDate();
    //creat a add trigger method(addSimpleTrigger)
    public void addSimpleTrigger(String taskLabel, String triggerlabel, String triggerdescription, String startDate
    		, String endDate, String triggerCount, String repeatInterval) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add simple trigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='label']", triggerlabel);//label
		
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", triggerdescription);//description
        selenium.click("//label[text()='Start time:']/parent::div//div/div/div");//start date
	    selenium.click("//td[@class='x-date-active']/a/span[text()='"+startDate+"']");
	    selenium.click("//label[text()='End time:']/parent::div//div/div/div");//end date
	    selenium.click("//td[@class='x-date-active']/a/span[text()='"+endDate+"']");
      
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
	    		"//input[@name='repeatCount']", triggerCount);//Number of triggerings
	   
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='repeatInterval']", repeatInterval);//Time interval (s)
	    
    }    
    
    //add a method of remove all triggers
    @Test
//    (groups={"AddSimpleTrigger"},dependsOnGroups={"ModifyTask"})
    public void clearTriggers() {
    	
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='testModifyTask']");//select a exist task
    	selenium.setSpeed(MID_SPEED);
    	if(selenium.isElementPresent("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/" +
    			"parent::ul/parent::div/parent::div/parent::div/parent::div//div[@class='x-grid3-cell-inner" +
    			" x-grid3-col-label']")) {
    	   
    		selenium.setSpeed(MIN_SPEED);	
			for(int i=0;;i++) {
				
				selenium.setSpeed(MID_SPEED);
			 	if(selenium.isElementPresent("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/" +
		    			"parent::ul/parent::div/parent::div/parent::div/parent::div//div[@class='x-grid3-cell-inner" +
		    			" x-grid3-col-label']")) {
				   
			 		selenium.setSpeed(MIN_SPEED);
					selenium.mouseDown("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/" +
					"parent::ul/parent::div/parent::div/parent::div/parent::div//div[@class='x-grid3-cell-inner" +
					" x-grid3-col-label']");
					selenium.chooseOkOnNextConfirmation();
					selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent" +
							"::ul/parent::div/parent::div/parent::div/parent::div//button[@id='idSubModuleDeleteButton']");
					
					Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected trigger [\\s\\S]$"));
				    								
			 	} else {
			 		
			 		System.out.println("element is no exist");
			 		break;
			 		
			 	}
			
			}
		
    	} else {
	 		
	 		System.out.println("element is no exist");
	 		
	 	}

    }
    
	// add a simpleTrigger
	@Test(dependsOnMethods={"clearTriggers"})
	@Parameters({"modifyTask", "addSimpleTriggerLabel","addSimpleTriggerDescription"})
	public void testAddTriggerAddSimpleTrigger(String taskLable, String label, String description) {
	
	   	    	       
		addSimpleTrigger(taskLable, label, description, date.getFuture(24).days, date.getFuture(48).days, "5", "20");
				
    	selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
    	System.out.println(selenium.getValue("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='startTime']"));
    	System.out.println(selenium.getValue("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='endTime']"));
    	selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
    			"parent::div/parent::div//button[text()='Refresh']");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	
	// add a simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTrigger"})
	@Parameters({"modifyTask", "addSimpleTriggerLabelNotChooseDate","addSimpleTriggerNotChooseDateDescription"})
	public void testAddTriggerAddSimpleTriggerNotChooseDate(String taskLabel, String label, String description) {
		    
	    	       
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add simple trigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='label']", label);//label
		
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", description);//description

        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
	    		"//input[@name='repeatCount']", "20");//Number of triggerings
	   
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='repeatInterval']", "20");//Time interval (s)
	    
	    selenium.setSpeed(MIN_SPEED);
				
    	selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
    	
    	selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
    			"parent::div/parent::div//button[text()='Refresh']");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	
	//add a exist simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTriggerNotChooseDate"})
	@Parameters({"modifyTask", "addSimpleTriggerLabel","addSimpleTriggerExistTriggerDescription"})
    public void testAddTriggerAddSimpleTriggerAddExist(String taskLabel, String label, String description) {
    	
		addSimpleTrigger(taskLabel, label, description, "12", "13", "5", "20");
		
	    selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed("1000");
	    Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.error.uniqueLabel")));
		selenium.setSpeed(MIN_SPEED);   
	}
	
//	add a simpleTrigger of wrong form time interval 
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTriggerAddExist"})
	@Parameters({"modifyTask", "addSimpleTriggerWrongFormTimeIntervalLabel","addSimpleTriggerWrongFormTimeIntervalDescription"})
    public void testAddTriggerAddSimpleTriggerAddWrongFormTimeInterval(String taskLabel, String label, String description) {
    	
		addSimpleTrigger(taskLabel, label, description, date.getFuture(24).days, date.getFuture(48).days, "5", "aa");
				 
		selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed("1000"); 
	    Assert.assertTrue(selenium.isTextPresent("Fix errors in form before save"));
	    selenium.setSpeed(MIN_SPEED);     
	    
	 }
	
	//add a overdue(start date) simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTriggerAddWrongFormTimeInterval"})
	@Parameters({"modifyTask", "addSimpleTriggerOverdueStartDataLabel","addSimpleTriggerOverdueStartDataDescription"})
    public void testAddTriggerAddSimpleTriggerAddOverdueStartData(String taskLabel, String label, String description) {
    	
		addSimpleTrigger(taskLabel, label, description, "12", date.getFuture(24).days, "5", "20");
	
	    selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed("1000"); 
	    Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.simpleTrigger.error.startTimeLessThenServerTime")));
	    selenium.setSpeed(MIN_SPEED); 		
	    
	}
	
	//add a overdue(start date) simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTriggerAddOverdueStartData"})
	@Parameters({"modifyTask","addSimpleTriggerOverdueEndDatalabel","addSimpleTriggerOverdueEndDataDescription"})
    public void testAddTriggerAddSimpleTriggerAddOverdueEndData(String taskLabel, String label, String description) {
   
	    addSimpleTrigger(taskLabel, label, description, date.getFuture(24).days, date.getPast(24).days, "5", "20");
	   
	    selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed("1000"); 
	    Assert.assertTrue(selenium.isTextPresent("End time cannot be before start time"));
	    selenium.setSpeed(MIN_SPEED);          
	   
	}
}
