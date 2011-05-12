package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
import com.talend.tac.cases.executePlan.TriggerDate;

public class TestAddTriggerAddSimpleTrigger extends Login{
    	   
    TriggerDate date = new TriggerDate();
 
    //creat a add trigger method(addSimpleTrigger)
    public void addSimpleTrigger(String taskLabel, String triggerlabel, String triggerdescription, TriggerDate startDate
    		, TriggerDate endDate, String triggerCount, String repeatInterval, String type) {
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
   
       
    	
        //######################################################################################3
        System.out.println("+++----"+endDate.days);
        System.out.println("+++----"+date.getCurrent().days); 	
    	if("future".equals(type)) {
    		selenium.click("//label[text()='Start time:']/parent::div//div/div/div");//start date
    		if(date.isClickFutureMonthButton(startDate)) {//type in start time
    			
            	selenium.setSpeed(MID_SPEED);
            	selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-right-icon x-component']");
            	selenium.click("//td[@class='x-date-active']/a/span[text()='"+startDate.days+"']");
            	selenium.setSpeed(MIN_SPEED);
            	
            }
            
        	else{//click tianshu}  
            	selenium.setSpeed(MID_SPEED);
            	selenium.click("//td[@class='x-date-active']/a/span[text()='"+startDate.days+"']");
            	selenium.setSpeed(MIN_SPEED);
            	
            }
    		selenium.click("//label[text()='End time:']/parent::div//div/div/div");//start date
    		if(date.isClickFutureMonthButton(endDate)) {//type in start time
    			
            	selenium.setSpeed(MID_SPEED);
            	selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-right-icon x-component']");
            	selenium.click("//td[@class='x-date-active']/a/span[text()='"+endDate.days+"']");
            	selenium.setSpeed(MIN_SPEED);
            	
            }
            
        	else{//click tianshu}  
            	selenium.setSpeed(MID_SPEED);
            	selenium.click("//td[@class='x-date-active']/a/span[text()='"+endDate.days+"']");
            	selenium.setSpeed(MIN_SPEED);
            	
            }  
    		
    	} 
    	
    	
    	else {
    	
    		selenium.click("//label[text()='Start time:']/parent::div//div/div/div");//start date
    	
    	    if(date.isClickPastMonthButton(startDate)) {//type in start time
    			
            	selenium.setSpeed(MID_SPEED);
            	selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-right-icon x-component']");
            	selenium.click("//td[@class='x-date-active']/a/span[text()='"+startDate.days+"']");
            	selenium.setSpeed(MIN_SPEED);
            	
            } else{//click tianshu}  
            	selenium.setSpeed(MID_SPEED);
            	
            	if((startDate.days).equals(date.getCurrent().days)) {
            		
            		selenium.click("//td[contains(@class,'x-date-today')]//span[text()='"+startDate.days+"']");
            		
            	} else {
            		
            		selenium.click("//td[@class='x-date-active']/a/span[text()='"+startDate.days+"']");
            	
            	}
            	
            	selenium.setSpeed(MIN_SPEED);
            	
            } 
    		selenium.click("//label[text()='End time:']/parent::div//div/div/div");//start date
    		if(date.isClickPastMonthButton(endDate)) {//type in start time
    			
            	selenium.setSpeed(MID_SPEED);
            	selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-right-icon x-component']");
            	selenium.click("//td[@class='x-date-active']/a/span[text()='"+endDate.days+"']");
            	selenium.setSpeed(MIN_SPEED);
            	
            }  else{//click tianshu}  
            	selenium.setSpeed(MID_SPEED);
                        	
            	if((endDate.days).equals(date.getCurrent().days)) {
            		
            		selenium.click("//td[contains(@class,'x-date-today')]//span[text()='"+endDate.days+"']");
            		            	
            	} else {
            		
            		selenium.click("//td[@class='x-date-active']/a/span[text()='"+endDate.days+"']");
            	
            	}
            	
            	selenium.setSpeed(MIN_SPEED);
    		
    	   }
    	
    	}
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
	    		"//input[@name='repeatCount']", triggerCount);//Number of triggerings
	   
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='repeatInterval']", repeatInterval);//Time interval (s)
	    
    }    
    
	
    //add a method of remove triggers
    public void clearTriggers(String taskLabel) {
    
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
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
  
    //add a method of remove all triggers
    @Test(groups={"AddSimpleTrigger"},dependsOnGroups={"TestGenerateDeployRun"})
    @Parameters({"labelTRunJobByTaskRun","labelRefProJobByMainProTRunJobRun","labelReferenceproTjava",
    	"modifyTask","duplicateTask"})
    public void clearsAllTriggers(String labelTRunJobByTaskRun,String labelRefProJobByMainProTRunJobRun,
    		String labelReferenceproTjava,String modifyTask,String duplicateTask ) {
    	
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	
    	clearTriggers(labelTRunJobByTaskRun);
    	clearTriggers(labelRefProJobByMainProTRunJobRun);
    	clearTriggers(labelReferenceproTjava);
    	clearTriggers(modifyTask);
    	clearTriggers(duplicateTask);
    	
    }
    
  /**add a simpleTrigger, set its 'NumberOfTriggerings'(1)
    when after trigger task second run finish---trigger will auto stop**/
    @Test(dependsOnMethods={"clearsAllTriggers"})
	@Parameters({"modifyTask","addSimpleTriggerNumberOfTriggeringsRunnedAutoStopLabel",
		"addSimpleTriggerNumberOfTriggeringsRunnedAutoStopDescription"})
    public void testAddSimpleTriggerNumberOfTriggeringsRunnedAutoStop(String taskLabel, String label, String description) {
   
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add simple trigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='label']", label);//label
		
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", description);//description

        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
	    		"//input[@name='repeatCount']", "1");//Number of triggerings
	   
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='repeatInterval']", "5");//Time interval (s)
	       		
	   
	    selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
    		
			selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
			"parent::div/parent::div//button[text()='Refresh']");
			
    	}
    	
		waitForElementPresent("//span[text()='2 / 2']",WAIT_TIME);
	  			
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[@title='All triggers completed']/img"));
		selenium.setSpeed(MIN_SPEED);
			
		
	} 
    
    
	// add a simpleTrigger
	@Test(dependsOnMethods={"clearsAllTriggers"})
	@Parameters({"modifyTask", "addSimpleTriggerLabel","addSimpleTriggerDescription"})
	public void testAddTriggerAddSimpleTrigger(String taskLable, String label, String description) {
	  
		addSimpleTrigger(taskLable, label, description, date.getFuture(24), date.getFuture(48), "5", "20","future"  );
		
		selenium.setSpeed(MID_SPEED);
    	selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
    	selenium.setSpeed(MIN_SPEED);
    	System.out.println(selenium.getValue("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='startTime']"));
    	System.out.println(selenium.getValue("//span[text()='Add simple trigger']/parent::legend/parent::fieldset//input[@name='endTime']"));
		selenium.setSpeed(MID_SPEED);
    	if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
			selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
			"parent::div/parent::div//button[text()='Refresh']");
    	}
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	
	// add a simpleTrigger use default date, selected job is TRunJob(use tRunJob run child'job)
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTrigger"})
	@Parameters({"labelTRunJobByTaskRun", "addSimpleTriggerLabelNotChooseDate","addSimpleTriggerNotChooseDateDescription"})
	public void testAddTriggerAddSimpleTriggerNotChooseDate(String taskLabel, String label, String description) {
		    
	    	       
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add simple trigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='label']", label);//label
		
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", description);//description
	   
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='repeatInterval']", "20");//Time interval (s)
	       
				
    	selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
    			"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
  
    	selenium.setSpeed(MID_SPEED);
    	if(!selenium.isElementPresent("//span[text()='"+label+"']")) {
			selenium.click("//span[text()='Triggers']/parent::span/parent::em/parent::a/parent::li/parent::ul/parent::div/" +
			"parent::div/parent::div//button[text()='Refresh']");
    	}
    	Assert.assertTrue(selenium.isElementPresent("//span[text()='"+label+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	
	//add a exist simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTriggerNotChooseDate"})
	@Parameters({"modifyTask", "addSimpleTriggerLabel","addSimpleTriggerExistTriggerDescription"})
    public void testAddTriggerAddSimpleTriggerAddExist(String taskLabel, String label, String description) {
    	
		addSimpleTrigger(taskLabel, label, description, date.getFuture(24), date.getFuture(24), "5", "20", "future");
		
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
	@Parameters({"modifyTask", "addSimpleTriggerWrongFormLabel","addSimpleTriggerWrongFormDescription","addSimpleTriggerWrongFormStartTime",
		"addSimpleTriggerWrongFormEndTime","addSimpleTriggerWrongFormNumberOfTriggers","addSimpleTriggerWrongFormTimeInterval"})
    public void testAddTriggerAddSimpleTriggerAddWrongFormFileds(String taskLabel, String label, String description
    		, String startTime, String endTime, String numberOfTriggering, String timeInterval) {
    	
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add simple trigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='label']", label);//label
    	selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='Add simple trigger']//ancestor::fieldset//label[text()='Label:']/parent::div//img"));
		selenium.setSpeed(MIN_SPEED);
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
				"//input[@name='description']", description);//description
      
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
	    		"//input[@name='repeatCount']", numberOfTriggering);//Number of triggerings
    	selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='Add simple trigger']//ancestor::fieldset//label[text()='Number of triggerings:']/parent::div//img"));
		selenium.setSpeed(MIN_SPEED);
        this.typeString("//span[text()='Add simple trigger']/parent::legend/parent::fieldset" +
        		"//input[@name='repeatInterval']", timeInterval);//Time interval (s)
       	selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='Add simple trigger']//ancestor::fieldset//label[text()='Time interval (s):']/parent::div//img"));
		selenium.setSpeed(MIN_SPEED);
	    
	 }
	
	//add a overdue(start date) simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTriggerAddWrongFormFileds"})
	@Parameters({"modifyTask", "addSimpleTriggerOverdueStartDataLabel","addSimpleTriggerOverdueStartDataDescription"})
    public void testAddTriggerAddSimpleTriggerAddOverdueStartData(String taskLabel, String label, String description) {
    	
		addSimpleTrigger(taskLabel, label, description, date.getPast(24), date.getCurrent(), "5", "20", "past");
	
	    selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed(MID_SPEED); 
	    Assert.assertTrue(selenium.isTextPresent(rb.getString("trigger.simpleTrigger.error.startTimeLessThenServerTime")));
	    selenium.setSpeed(MIN_SPEED); 		
	    
	}
	
	//add a overdue(end date) simpleTrigger
	@Test(dependsOnMethods={"testAddTriggerAddSimpleTriggerAddOverdueStartData"})
	@Parameters({"modifyTask","addSimpleTriggerOverdueEndDatalabel","addSimpleTriggerOverdueEndDataDescription"})
    public void testAddTriggerAddSimpleTriggerAddOverdueEndData(String taskLabel, String label, String description) {
		System.out.println(date.getPast(24).months+"/-/-/-/-/");    
	    addSimpleTrigger(taskLabel, label, description, date.getCurrent(), date.getPast(24), "5", "20", "past");
	    System.out.println(date.getPast(24).months+"/-/-/-/-");
	    selenium.setSpeed(MID_SPEED);
	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/" +
		"parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
		selenium.setSpeed(MIN_SPEED);
		selenium.setSpeed("1000"); 
	    Assert.assertTrue(selenium.isTextPresent("End time cannot be before start time"));
	    selenium.setSpeed(MIN_SPEED);          
	   
	}
	
 	
}
