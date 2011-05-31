package com.talend.tac.cases.executePlan;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestAddTriggerAddCronTriggerByUI extends Plan{
    	
    
	@Test
	@Parameters({ "plan.toaddcrontrigger.label", "plan.crontrigger.byui.label"})
	public void testAddCronTrigger(String planlabel,String crongtriggerlabel) throws InterruptedException{
	    TriggerDate date = new TriggerDate().getFuture(24);
	    HashMap<String, String> map = date.getMonthmap();
		//open to execution plan add trigger page
	    this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent("//div[@class='header-title' and text()='Execution Plan']", WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
    	selenium.mouseDown("//span[text()='"+planlabel+"']");
		selenium.click("//button[text()='Add trigger...']");
		selenium.click("//a[text()='Add CRON trigger']");
//		selenium.setSpeed(MID_SPEED);
		//type  label
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='label']",crongtriggerlabel);
		//type  description
		this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='description']", "CrontriggerUI");
		//configure trigger information use UI calendar
		selenium.setSpeed(MAX_SPEED);
		selenium.click("idSchedulingUiConfigButton");
//		Thread.sleep(5000);
		selenium.setSpeed(MIN_SPEED);
		//select years
		selenium.mouseDown("//div[@class='x-view-item' and text()='"+date.years+"']");
		//select months
		selenium.mouseDown("//div[@class='x-view-item' and text()='"+map.get(date.months.toString())+"']");
		//select days
		selenium.mouseDown("//span[text()='Days of month **']//ancestor::div[@class=' x-panel list x-component x-column']//div[text()='"+date.days+"']");
		//select hours
		selenium.mouseDown("//span[text()='Hours *']//ancestor::div[@class=' x-panel list x-component x-column']//div[text()='"+date.hours+"']");
		//select minutes
		selenium.mouseDown("//span[text()='Minutes *']//ancestor::div[@class=' x-panel list x-component x-column-layout-ct x-column']//div[text()='"+date.hours+"']");
		//click apply button
		selenium.click("idSchedulingApplyButton");
		//click save button
//		selenium.setSpeed(MID_SPEED);
		selenium.click("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-footer']/div[@class=' x-panel-btns']/div[@class=' x-small-editor x-panel-btns-center x-panel-fbar x-component x-toolbar-layout-ct']/table[@class='x-toolbar-ct']/tbody/tr/td[@class='x-toolbar-left']/table/tbody/tr[@class='x-toolbar-left-row']/td[@class='x-toolbar-cell']/table[@class=' x-btn x-component x-btn-text-icon ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[@class='x-btn-text ' and text()='Save']");
//		Thread.sleep(5000);
		this.waitForElementPresent("//span[text()='"+crongtriggerlabel+"']", WAIT_TIME);
	    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+crongtriggerlabel+"']"), "Crontrigger added failed!");
	    selenium.setSpeed(MIN_SPEED);
	}

		
	

}
