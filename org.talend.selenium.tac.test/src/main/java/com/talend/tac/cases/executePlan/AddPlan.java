package com.talend.tac.cases.executePlan;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class AddPlan extends Plan {

	// add a method(add Plan)
	public void addPlan(String label, String description, String task) {

		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent("//div[@class='header-title' and text()='Execution Plan']", WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
//		selenium.setSpeed(MID_SPEED);
		this.clickWaitForElementPresent("//button[text()='Add plan']");
		// type plan label
//		this.typeString(
//				"//input[@class=' x-form-field x-form-text x-form-invalid ' and @name='label']",
//				label);
		this.typeString("idExecutionPlanPlanFormLabelInput", label);
//		  selenium.type(
//		  "//input[@class=' x-form-field x-form-text x-form-invalid' and @name='label']"
//		  , label); 
//		  selenium.fireEvent("//input[@name='label']", "blur");
		 
		// type plan description
//		this.typeString(
//				"//input[@class=' x-form-field x-form-text' and @name='description']",
//				description);
	this.typeString("idExecutionPlanPlanFormDescInput", description);
//		  selenium.type(
//		  "//input[@class=' x-form-field x-form-text' and @name='description']"
//		  , description);
//		  selenium.fireEvent("//input[@name='description']",
//		  "blur");
//		 
		// select a task for the plan
//		selenium.click("String idExecutionPlanPlanFormTaskComboBox");
//		this.waitForElementPresent("//div[text()='"+task+"']", WAIT_TIME);
//		selenium.mouseDownAt("//div[@role='listitem'][1]", ""
//				+ KeyEvent.VK_ENTER);
		this.selectDropDownList("String idExecutionPlanPlanFormTaskComboBox", task);
		// click save button
//		selenium.setSpeed(MID_SPEED);
		selenium.mouseDown("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
		selenium.click("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
		selenium.mouseUp("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
		this.waitForElementPresent("//span[text()='" + label
				+ "']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='" + label
				+ "']"));
		selenium.setSpeed(MIN_SPEED);
	}

	// add a plan
	@Test(groups = { "addplan" })//, dependsOnGroups = { "cleanplan" }
	@Parameters({ "plan.label", "plan.description", "plan.task" })
	public void testAddPlan(String label, String description, String task) {

		addPlan(label, description, task);

	}

}
