package com.talend.tac.cases.executePlan;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class AddPlan extends Login {

	// add a method(add Plan)
	public void addPlan(String label, String description, String task) {

		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
		selenium.setSpeed(MID_SPEED);
		selenium.click("//button[text()='Add plan']");
		// type plan label
//		this.typeString(
//				"//input[@class=' x-form-field x-form-text x-form-invalid ' and @name='label']",
//				label);
		
		  selenium.type(
		  "//input[@class=' x-form-field x-form-text x-form-invalid' and @name='label']"
		  , label); 
		  selenium.fireEvent("//input[@name='label']", "blur");
		 
		// type plan description
//		this.typeString(
//				"//input[@class=' x-form-field x-form-text' and @name='description']",
//				description);
	
		  selenium.type(
		  "//input[@class=' x-form-field x-form-text' and @name='description']"
		  , description);
		  selenium.fireEvent("//input[@name='description']",
		  "blur");
		 
		// select a task for the plan
		selenium.click("String idExecutionPlanPlanFormTaskComboBox");
		selenium.mouseDownAt("//div[@role='listitem'][1]", ""
				+ KeyEvent.VK_ENTER);
		// click save button
		selenium.setSpeed(MID_SPEED);
		selenium.click("//button[@class='x-btn-text ' and @id='idFormSaveButton']");

		Assert.assertTrue(selenium.isElementPresent("//div[text()='" + label
				+ "']"));
		selenium.setSpeed(MIN_SPEED);
	}

	// add a plan
	@Test(groups = { "addplan" }, dependsOnGroups = { "cleanplan" })
	@Parameters({ "plan.label", "plan.description", "plan.task" })
	public void testAddPlan(String label, String description, String task) {

		addPlan(label, description, task);

	}

}
