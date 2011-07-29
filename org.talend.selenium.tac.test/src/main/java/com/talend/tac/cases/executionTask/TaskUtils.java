package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
import com.talend.tac.cases.executePlan.TriggerDate;

public class TaskUtils extends Login {
   
    TriggerDate date = new TriggerDate();

	//into JobConductor page
	public void intoJobConductor(String taskLabel) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
        selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
	    selenium.setSpeed(MIN_SPEED);
	    
	    this.waitForElementPresent("//span[text()='"+taskLabel+"']", WAIT_TIME);
	    selenium.mouseDown("//span[text()='"+taskLabel+"']");//select an exist task
	    						
	}
	
  //creat a method of add task
	public void addTask(String label, String description, String projectName, String branchName,
			 String jobName, String version, String context, String serverName, String statisticName) {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
        selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
	    selenium.setSpeed(MIN_SPEED);
	    selenium.click("idSubModuleAddButton");
		this.typeString("idJobConductorTaskLabelInput", label);//plan name /Label
		this.typeString("idJobConductorTaskDescInput", description);//plan name /Label
    	
    	if(!selenium.isChecked("idJobConductorTaskActiveListBox")) {
    		
    		selenium.click("idJobConductorTaskActiveListBox");//check active
        	Assert.assertTrue(selenium.isChecked("idJobConductorTaskActiveListBox"));	
    		
    	}    
    	this.selectDropDownList("idTaskProjectListBox", projectName);
    	this.selectDropDownList("idTaskBranchListBox", branchName);
    	this.selectDropDownList("idTaskJobListBox", jobName);
    	this.selectDropDownList("idTaskVersionListBox", version);
    	this.selectDropDownList("idTaskContextListBox", context);
    	this.selectDropDownList("idJobConductorExecutionServerListBox", serverName);
    	this.selectDropDownList("idJobConductorTaskStatisticsListBox", statisticName);
    	this.selectDropDownList("idJobConductorOnUnavailableJobServerListBox", "Wait");
    	

	}
	
	//creation method for generate/deploy/run task
	public void generateDeployRunTask(String taskLabel, String buttonXpath) {
		
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");//into executiontask page
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	this.waitForElementPresent("//span[text()='"+taskLabel+"']", WAIT_TIME);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select an simple task
    	selenium.click(buttonXpath);//click function button
          	
	}
	
	//creation method for change commandline host address
	public void changeCommandLineConfig(String hostAddress) {
		
		selenium.refresh();
		this.clickWaitForElementPresent("idMenuConfigElement");//into Configuration page
		this.waitForElementPresent("//div[contains(text(),'Command line/primary')]", WAIT_TIME);
		selenium.mouseDown("//div[contains(text(),'Command line/primary')]");
		this.clickWaitForElementPresent("//div[contains(text(),'Command line/primary')]//ancestor::div[@class='x-grid-group ']" +
				"//div[text()='Host']//ancestor::tr[@role='presentation']//img[@title='Click to edit']");
		this.waitForElementPresent("//div[@class=' x-form-field-wrap  x-component']//input", WAIT_TIME); 
		System.out.println("*--------------*");
		this.typeString("//div[@class=' x-form-field-wrap  x-component']//input",hostAddress);
			
	}
	
	 //creat a add trigger method(addSimpleTrigger)
    public void addSimpleTrigger(String taskLabel, String triggerlabel, String triggerdescription, TriggerDate startDate
    		, TriggerDate endDate, String triggerCount, String repeatInterval, String type) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	this.waitForElementPresent("//span[text()='"+taskLabel+"']", WAIT_TIME);		
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("idTriggerAdd trigger...");//add a trigger
		selenium.click("idTriggerAdd simple trigger");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        
        this.typeString("idJobConductorSimpleTriggerLableInput", triggerlabel);//label
		
        this.typeString("idJobConductorSimpleTriggerDescInput", triggerdescription);//description
   
       
    	
        //######################################################################################3
        System.out.println("+++----"+endDate.days);
        System.out.println("+++----"+date.getCurrent().days); 	
    	if("future".equals(type)) {
    		selenium.click("//label[text()='Start time:']/parent::div//img");//start date
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
    		selenium.click("//label[text()='End time:']/parent::div//img");//start date
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
    	
    		selenium.click("//label[text()='Start time:']/parent::div//img");//start date
    	
    	    if(date.isClickPastMonthButton(startDate)) {//type in start time
    			
            	selenium.setSpeed(MID_SPEED);
            	selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-left-icon x-component']");
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
    		selenium.click("//label[text()='End time:']/parent::div//img");//start date
    		if(date.isClickPastMonthButton(endDate)) {//type in start time
    			
            	selenium.setSpeed(MID_SPEED);
            	selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-left-icon x-component']");
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
        this.typeString("idJobConductorSimpleTriggerRptCountInput", triggerCount);//Number of triggerings
	   
        this.typeString("idJobConductorSimpleTriggerRptIntervalInput", repeatInterval);//Time interval (s)
	    
    }    
    
    //creat a add trigger method(addCronTrigger)
	public void addTriggerAddCronTrigger(String taskLabel,String labelCronTrigger, String descriptionSronTrigger,String years,
			String weeksStart, String weeksEnd, String monthsStart, String monthsEnd) {
    	this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	this.waitForElementPresent("//span[text()='"+taskLabel+"']", WAIT_TIME);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
		selenium.click("idTriggerAdd trigger...");//add a trigger
		selenium.click("idTriggerAdd CRON trigger");//add a  CronTrigger
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addCronTrigger")+"']"));
		selenium.setSpeed(MIN_SPEED);
		this.typeString("idJobConductorCronTriggerLabelInput", labelCronTrigger);//label

		this.typeString("idJobConductorCronTriggerDescInput", descriptionSronTrigger);//description
			
		selenium.click("idSchedulingUiConfigButton");//choose data

		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='"+years+"']");//choose years
		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='"+weeksStart+"']");//choose day of weeks
		selenium.shiftKeyDown();
		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='"+weeksEnd+"']");
		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='"+monthsStart+"']");//choose months
		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='"+monthsEnd+"']");
		selenium.mouseDown("//div[@class='x-column-inner']/div[2]/div[2]/div/div/div[text()='00']");//choose hours
		selenium.mouseDown("//div[@class='x-column-inner']/div[2]/div[2]/div/div/div[text()='23']");
		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='00']");//choose minutes
		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='29']");
		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='30']");
		selenium.mouseDown("//div[contains(@class,'x-view-item') and text()='59']");
		selenium.shiftKeyUp();
		selenium.click("idSchedulingApplyButton");//save data
		
	}
	
	//creat method(addFileTrigger) for test add file trigger
	public void addFileTrigger(String taskLabel, String triggerLabel, String triggerDescription,
			 String intervalTime, String folderPath, String fileMask, String serverName) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.setSpeed(MIN_SPEED);
    	this.waitForElementPresent("//span[text()='"+taskLabel+"']", WAIT_TIME);
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task
    	
    	if(!selenium.isChecked("idJobConductorTaskActiveListBox")) {
    		
    		selenium.click("idJobConductorTaskActiveListBox");//check active
        	Assert.assertTrue(selenium.isChecked("idJobConductorTaskActiveListBox"));
        	selenium.click("idFormSaveButton");
    		
    	}
    
    	
    	selenium.mouseDown("//span[text()='"+taskLabel+"']");//select a exist task again
		selenium.click("idTriggerAdd trigger...");//add a trigger
		selenium.click("idTriggerAdd file trigger");//add a FileTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addFileTrigger")+"']"));
		
    	this.typeString("idJobConductorFileTriggerLabelInput", triggerLabel);//label
				                                                                     
		this.typeString("idJobConductorFileTriggerDescInput", triggerDescription);//description
		
		this.typeString("idJobConductorFileTriggerCheckTimeIntervalInput", intervalTime);//checkTimeInterval
		
		this.typeString("idJobConductorFileTriggerFolderPathInput", folderPath);//folderPath
		
		this.typeString("idJobConductorFileTriggerFileMaskInput", fileMask);//fileMask
		
		selenium.click("idJobConductorFileTriggerFtExitCheckBox");//*.txt is exist
		Assert.assertTrue(selenium.isChecked("idJobConductorFileTriggerFtExitCheckBox"));
	
		selenium.click("idJobConductorFileTriggerFileServerListBox");//select an server	
		this.waitForElementPresent("//div[text()='" + serverName + "']", WAIT_TIME);
		selenium.mouseDown("//div[text()='" + serverName + "']");
		
	}
	 //add a method of remove tasks
	public void clearTask(String taskLabel) {
		
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
		selenium.setSpeed(MIN_SPEED);
	    selenium.click("idSubModuleRefreshButton"); //click "Refresh"
		
		if(selenium.isElementPresent("//span[text()='"+taskLabel+"']")) {
			
			selenium.mouseDown("//span[text()='"+taskLabel+"']");//select  exist task
			selenium.chooseOkOnNextConfirmation();
			selenium.chooseOkOnNextConfirmation();
			selenium.click("idSubModuleDeleteButton");//clcik "Delete"
			Assert.assertTrue(selenium.getConfirmation().matches(other.getString("delete.plan.warning")));
			Assert.assertEquals(selenium.getConfirmation(), "Do you want to remove all of the related logs and archives");
			
		} else {
			
			System.out.println("the task is not exist");
			
		}
				
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
					selenium.click("idTriggerDelete");
					
					Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected trigger [\\s\\S]$"));
				    								
			 	} else {
			 		
			 		System.out.println("trigger element is no exist");
			 		break;
			 		
			 	}
			
			}
		
    	} else {
	 		
	 		System.out.println("task element is no exist");
	 		
	 	}

    }		
}
