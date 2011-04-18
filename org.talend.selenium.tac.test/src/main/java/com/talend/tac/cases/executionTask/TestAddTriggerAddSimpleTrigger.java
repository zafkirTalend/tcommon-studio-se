package com.talend.tac.cases.executionTask;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@SuppressWarnings("deprecation")
public class TestAddTriggerAddSimpleTrigger extends Login{
    	
    Date date = new Date();
    Date date1 = new Date();
    DateFormat   df   =   new   SimpleDateFormat( "yyyy-MM-dd hh:MM:ss"); 
   
    //creat a add trigger method(addSimpleTrigger)
    public void addSimpleTrigger(String triggerlabel, String triggerdescription, String startDate
    		, String endDate, String triggerCount, String repeatInterval) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	
    	selenium.mouseDown("//span[text()='testModifyTask']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add SimpleTrigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        selenium.type("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='label']", triggerlabel);//label
		selenium.fireEvent("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
				"//input[@name='label']","blur");
		selenium.type("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", triggerdescription);//description
		selenium.fireEvent("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']","blur");
		    
		selenium.type("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='startTime']", startDate);
		selenium.fireEvent("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='startTime']", "blur");
		selenium.type("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='endTime']", endDate);
		selenium.fireEvent("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='endTime']", "blur");
	    selenium.type("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
	    		"//input[@name='repeatCount']", triggerCount);//Number of triggerings
	    selenium.fireEvent("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
	    		"//input[@name='repeatCount']", "blur");
        selenium.type("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='repeatInterval']", repeatInterval);//Time interval (s)
	    selenium.fireEvent("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
	    		"//input[@name='repeatInterval']", "blur");
	    
	    selenium.setSpeed(MIN_SPEED);
	}
    
    //add a method of remove all triggers
    @Test(groups={"AddSimpleTrigger"},dependsOnGroups={"ModifyTask"})
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
	@Parameters({"addSimpleTriggerLabel","addSimpleTriggerDescription"})
	public void testAddTriggerAddSimpleTrigger(String label, String description) {
	
	    date.setHours(date.getHours()+24);
	    date1.setHours(date.getHours()+48);
	    String s = df.format(date);//system date 
	    String s1 =  df.format(date1);
		
		addSimpleTrigger(label, description, s, s1, "5", "20");
		
    	selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
    	System.out.println(selenium.getValue("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='startTime']"));
    	System.out.println(selenium.getValue("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='endTime']"));
    	selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
    			"parent::div/parent::div//button[text()='Refresh']");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='TestSimpleTrigger']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	//add a exist simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTrigger"})
	@Parameters({"addSimpleTriggerLabel","addSimpleTriggerExistTriggerDescription"})
    public void testAddTriggerAddSimpleTriggerAddExist(String label, String description) {
    	
		addSimpleTrigger(label, description, "", "", "5", "20");
		
		selenium.click("//label[text()='Start time:']/parent::div//div/div/div");//start date
	    
		selenium.click("//a[(span/text()='12')]");
	    selenium.click("//label[text()='End time:']/parent::div//div/div/div");//end date
	    selenium.click("//a[(span/text()='13')]");
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
	@Parameters({"addSimpleTriggerWrongFormTimeIntervalLabel","addSimpleTriggerWrongFormTimeIntervalDescription"})
    public void testAddTriggerAddSimpleTriggerAddWrongFormTimeInterval(String label, String description) {
    	
		addSimpleTrigger(label, description, "", "", "5", "aa");
				 
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
	@Parameters({"addSimpleTriggerOverdueStartDataLabel","addSimpleTriggerOverdueStartDataDescription"})
    public void testAddTriggerAddSimpleTriggerAddOverdueStartData(String label, String description) {
    	
		addSimpleTrigger(label, description, "", "", "5", "20");
		
		selenium.click("//label[text()='Start time:']/parent::div//div/div/div");//start date
	    selenium.click("//a[(span/text()='12')]");
	    selenium.click("//label[text()='End time:']/parent::div//div/div/div");//end date
	    selenium.click("//a[(span/text()='13')]");
	   
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
	@Parameters({"addSimpleTriggerOverdueEndDatalabel","addSimpleTriggerOverdueEndDataDescription"})
    public void testAddTriggerAddSimpleTriggerAddOverdueEndData(String label, String description) {
   
	    addSimpleTrigger(label, description, "", "", "5", "20");
	    
		selenium.click("//label[text()='Start time:']/parent::div//div/div/div");//end date
	    selenium.click("//a[(span/text()='16')]");
		
		selenium.click("//label[text()='End time:']/parent::div//div/div/div");//end date
	    selenium.click("//a[(span/text()='12')]");
		
	    selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed("1000"); 
	    Assert.assertTrue(selenium.isTextPresent("End time cannot be before start time"));
	    selenium.setSpeed(MIN_SPEED);          
	   
	}
}
