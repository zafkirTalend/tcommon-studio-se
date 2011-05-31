package com.talend.tac.cases.executePlan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

@SuppressWarnings("deprecation")
public class TestDuplicateSimpletrigger extends Login {
	TriggerDate date = new TriggerDate();
	// add a simpleTrigger use default start time and end time
	@Test
	@Parameters({ "plan.toaddsimpletrigger.label",
	"plan.simpletrigger.duplicate.label" })
	
	public void testDuplicateSimpleTrigger(String plan,String simpleTriggerToduplicate) throws InterruptedException {
		 this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
			this.waitForElementPresent("//div[@class='header-title' and text()='Execution Plan']", WAIT_TIME);
			Assert.assertTrue(selenium
					.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
		// select plan
		selenium.mouseDown("//span[text()='" + plan + "']");
															
		// select trigger to duplicate
		selenium.mouseDown("//span[text()='" + simpleTriggerToduplicate + "']");//
		// click duplicate trigger button
		selenium.click("idTriggerDuplicate");
		// configure trigger informations:start time,end time and so on
		// type simple trigger start time
		selenium.click("//input[@name='startTime']/parent::div/div[@class='x-form-trigger x-form-date-trigger']");
		this.clickWaitForElementPresent("//td[@class='x-date-active']/a/span[text()='" + date.getFuture(24).days + "']");
		/*selenium.setSpeed(MAX_SPEED);
		// selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-left-icon x-component ']");
		selenium.click("//td[@class='x-date-active']/a/span[text()='" + date.getFuture(24).days + "']");
		selenium.setSpeed(MIN_SPEED);*/
		// this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='startTime']",
		// date.getFuture("24"));//description
		// type simple trigger end time
		selenium.click("//input[@name='endTime']/parent::div/div[@class='x-form-trigger x-form-date-trigger']");
		this.clickWaitForElementPresent("//td[@class='x-date-active']/a/span[text()='" + date.getFuture(72).days + "']");
		/*selenium.setSpeed(MAX_SPEED);
		// selenium.click("//div[@class=' x-icon-btn x-nodrag x-date-left-icon x-component ']");
		selenium.click("//td[@class='x-date-active']/a/span[text()='" + date.getFuture(72).days + "']");
		selenium.setSpeed(MIN_SPEED);*/
		// this.typeString("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='endTime']",
		// date.getFuture("48"));//description
		// type simple trigger repeat count
		this.typeString(
				"//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='repeatCount']",
				"");// description
		// type simple trigger repeat interval
		this.typeString(
				"//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='repeatInterval']",
				"1500");// Number of triggerings
		
		// click save button to save trigger
		selenium.click("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-footer']/div[@class=' x-panel-btns']/div[@class=' x-small-editor x-panel-btns-center x-panel-fbar x-component x-toolbar-layout-ct']/table[@class='x-toolbar-ct']/tbody/tr/td[@class='x-toolbar-left']/table/tbody/tr[@class='x-toolbar-left-row']/td[@class='x-toolbar-cell']/table[@class=' x-btn x-component x-btn-text-icon ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[@class='x-btn-text ' and text()='Save']");
//		selenium.setSpeed(MAX_SPEED);
//		Thread.sleep(5000);
		this.waitForElementPresent("//span[text()='"+"Copy_of_"+simpleTriggerToduplicate+"']", WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//span[text()='"+"Copy_of_"+simpleTriggerToduplicate+"']"));
		selenium.setSpeed(MIN_SPEED);
	}

}
