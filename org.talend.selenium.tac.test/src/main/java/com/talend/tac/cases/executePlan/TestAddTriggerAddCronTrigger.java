package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
public class TestAddTriggerAddCronTrigger extends Login{
    	
    
	@Test
	@Parameters({ "plan.toaddcrontrigger.label", "plan.crontrigger.label"})
	public void testAddCronTrigger(String planlabel,String crongtriggerlabel) throws InterruptedException{
	    TriggerDate date = new TriggerDate().getFuture(24);
		//open to execution plan add trigger page
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
    	selenium.setSpeed(MIN_SPEED);
    	selenium.click("idSubModuleRefreshButton");
    	selenium.mouseDown("//div[text()='"+planlabel+"']");
		selenium.click("//button[text()='Add trigger...']");
		selenium.click("//a[text()='Add CRON trigger']");
//		selenium.setSpeed(MID_SPEED);
		Thread.sleep(5000);
		selenium.setSpeed(MIN_SPEED);
		//type  label
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='label']",crongtriggerlabel);
		//type  description
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='description']", "testCrontrigger");
    	//type minutes
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='minutes']", date.minutes);
		//type hours
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='hours']", date.hours);
		//type days
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='daysOfMonth']", date.days);
		//type months
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='months']", date.months);
		//type years
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='years']", date.years);	
		//click save button
		selenium.setSpeed(MID_SPEED);
		selenium.click("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-footer']/div[@class=' x-panel-btns']/div[@class=' x-small-editor x-panel-btns-center x-panel-fbar x-component x-toolbar-layout-ct']/table[@class='x-toolbar-ct']/tbody/tr/td[@class='x-toolbar-left']/table/tbody/tr[@class='x-toolbar-left-row']/td[@class='x-toolbar-cell']/table[@class=' x-btn x-component x-btn-text-icon ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[@class='x-btn-text ' and text()='Save']");
		Thread.sleep(5000);
	    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+crongtriggerlabel+"']"), "Crontrigger added failed!");
	    selenium.setSpeed(MIN_SPEED);
	}

		
	

}
