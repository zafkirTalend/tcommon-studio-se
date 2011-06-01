package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import com.talend.tac.cases.Login;

public class Plan extends Login {

	public void addPlan(String planLabel, String rootTask, String description) {

		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent(
				"//div[@class='header-title' and text()='Execution Plan']",
				WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
		this.clickWaitForElementPresent("//button[text()='Add plan']");
		this.typeString("idExecutionPlanPlanFormLabelInput", planLabel);
		this.typeString("idExecutionPlanPlanFormDescInput", description);
		this.selectDropDownList("String idExecutionPlanPlanFormTaskComboBox",
				rootTask);
		selenium.mouseDown("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
		selenium.click("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
		selenium.mouseUp("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
		this.waitForElementPresent("//span[text()='" + planLabel + "']",
				WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"
				+ planLabel + "']"));
		selenium.setSpeed(MIN_SPEED);
	}

	public void deleteTrigger(String fileTriggerLabel) {
		this.waitForElementPresent("//span[text()='" + fileTriggerLabel + "']",
				WAIT_TIME);
		selenium.mouseDown("//span[text()='" + fileTriggerLabel + "']");
		selenium.chooseOkOnNextConfirmation();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("idTriggerDelete");
		selenium.getConfirmation();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(
				selenium.isElementPresent("//span[text()='" + fileTriggerLabel
						+ "']"), "trigger delete failed!");
	}
	
	public void sleep(int seconds){
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
