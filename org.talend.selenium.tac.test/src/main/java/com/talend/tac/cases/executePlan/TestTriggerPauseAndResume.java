package com.talend.tac.cases.executePlan;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestTriggerPauseAndResume extends Login {

	@Test
	@Parameters({ "plan.toaddsimpletrigger.label", "plan.trigger.pauseandresume.label"})
	public void testPauseAndResume(String plan,String triggerPauseAndResume) throws InterruptedException {
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
    	selenium.setSpeed(MIN_SPEED);
    	AddSimpleTrigger(plan,triggerPauseAndResume);
//    	selenium.mouseOver("//img[]")
    	String timeleft1 = selenium.getText("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-remaingTimeForNextFire ']");
    	System.out.println("time1: "+timeleft1);
    	Thread.sleep(2000);
    	String timeleft2 = selenium.getText("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-remaingTimeForNextFire ']");
    	System.out.println("time2:"+timeleft2);
    	Assert.assertFalse(timeleft1.equals(timeleft2), "trigger added failed!");
    	Thread.sleep(10000);
    	
    	//click pause trigger button
    	selenium.mouseDown("//span[text()='"+triggerPauseAndResume+"']");
    	selenium.click("//table[@class=' x-btn x-component x-btn-text-icon ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[@class='x-btn-text ' and text()='Pause trigger']");
    	Thread.sleep(5000);
    	String timeleft3 = selenium.getText("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-remaingTimeForNextFire ']");
    	System.out.println("time3:"+timeleft3);
    	Thread.sleep(2000);
    	String timeleft4 = selenium.getText("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-remaingTimeForNextFire ']");
    	System.out.println("time4:"+timeleft4);
    	Assert.assertTrue(timeleft3.equals(timeleft4), "trigger paused failed!");
	    //click resume trigger button
    	selenium.click("//table[@class=' x-btn x-component x-btn-text-icon ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[@class='x-btn-text ' and text()='Resume trigger']");
    	Thread.sleep(3000);
    	String timeleft5 = selenium.getText("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-remaingTimeForNextFire ']");
    	System.out.println("time5:"+timeleft5);
    	Thread.sleep(2000);
    	String timeleft6 = selenium.getText("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-remaingTimeForNextFire ']");
    	System.out.println("time6:"+timeleft6);
    	Assert.assertFalse(timeleft5.equals(timeleft6), "trigger resumed failed!");
	}
	public void AddSimpleTrigger(String addsimpletriggerplan,String defaulttimeSimpletrigger) {
		
    	selenium.click("idSubModuleRefreshButton");
    	selenium.mouseDown("//div[text()='"+addsimpletriggerplan+"']");//select a exist task
		selenium.click("//button[text()='Add trigger...']");//add a trigger
		selenium.click("//a[text()='Add simple trigger']");//add a SimpleTrigger
        Assert.assertTrue(selenium.isElementPresent("//span[text()='"+rb.getString("trigger.action.addSimpleTrigger")+"']"));
        selenium.setSpeed(MIN_SPEED);
        //type simple trigger label
        this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='label']",defaulttimeSimpletrigger );//label
		//type simple trigger description
        this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='description']", "SimpleTrigger");//description
       
        //type simple trigger repeat count
        this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='repeatCount']", "40");//description
	    //type simple trigger repeat interval
        this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='repeatInterval']", "50");//Number of triggerings
//	    selenium.click("//span[text()='Add simple trigger']/parent::legend/parent::fieldset/parent::form/parent::div/parent::div/parent::div/parent::div/parent::div//button[@id='idFormSaveButton']");
	    selenium.click("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-footer']/div[@class=' x-panel-btns']/div[@class=' x-small-editor x-panel-btns-center x-panel-fbar x-component x-toolbar-layout-ct']/table[@class='x-toolbar-ct']/tbody/tr/td[@class='x-toolbar-left']/table/tbody/tr[@class='x-toolbar-left-row']/td[@class='x-toolbar-cell']/table[@class=' x-btn x-component x-btn-text-icon ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[@class='x-btn-text ' and text()='Save']");
	    selenium.setSpeed(MID_SPEED); 
//	    Thread.sleep(5000);
	    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+defaulttimeSimpletrigger+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	

}
