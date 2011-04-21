package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDuplicateCrontrigger extends Login {

	@Test
	// (groups={"plan.trigger.duplicate"})
	@Parameters({ "plan.toaddcrontrigger.label",
			"plan.crontrigger.duplicate.label" })
	public void testAddTriggerAddSimpleTrigger(String plan,
			String triggertoduplicate) {
		TriggerDate date = new TriggerDate().getFuture(24);
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
		selenium.click("idSubModuleRefreshButton");
		// select plan
		selenium.mouseDown("//div[text()='" + plan + "']");// select a exist
		// select trigger to duplicate
		selenium.mouseDown("//span[text()='" + triggertoduplicate + "']");//
		// click duplicate trigger button
		selenium.click("//table[@class=' x-btn x-component x-btn-text-icon x-btn-focus ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[text()='Duplicate']");
		// configure trigger information about time
		// type minutes
		this.typeString(
				"//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='minutes']",
				date.minutes);
		// type hours
		this.typeString(
				"//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='hours']",
				date.hours);
		// type days
		this.typeString(
				"//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='daysOfMonth']",
				date.days);
		// type months
		this.typeString(
				"//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='months']",
				date.months);
		// type years
		this.typeString(
				"//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader']/div[@class=' x-panel-noborder x-panel x-component']/div[@class='x-panel-bwrap']/div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder']/form[@class=' x-form-label-left']/fieldset[@class=' x-fieldset x-component']/div[@class=' x-form-label-left']/div[@class='x-form-item ']/div/div[@class=' x-form-field-wrap  x-component ']/input[@name='years']",
				date.years);

		// click save button to save trigger
		selenium.click("//div[@class=' x-panel x-component ']/div[@class='x-panel-bwrap']/div[@class='x-panel-footer']/div[@class=' x-panel-btns']/div[@class=' x-small-editor x-panel-btns-center x-panel-fbar x-component x-toolbar-layout-ct']/table[@class='x-toolbar-ct']/tbody/tr/td[@class='x-toolbar-left']/table/tbody/tr[@class='x-toolbar-left-row']/td[@class='x-toolbar-cell']/table[@class=' x-btn x-component x-btn-text-icon ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[@class='x-btn-text ' and text()='Save']");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"
				+ "Copy_of_" + triggertoduplicate + "']"));
		selenium.setSpeed(MIN_SPEED);
	}

}
