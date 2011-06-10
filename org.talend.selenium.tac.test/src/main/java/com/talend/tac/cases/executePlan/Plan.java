package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import com.talend.tac.cases.Login;

public class Plan extends Login {
	public static int TriggerCheckTime = 40;
	
	public void openExecutionPlanMenu(){
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent(
				"//div[@class='header-title' and text()='Execution Plan']",
				WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
	}

	public void addPlan(String planLabel, String rootTask, String description) {

		this.openExecutionPlanMenu();
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
	
	public void deletePlan(String planLabel){
		this.waitForElementPresent("//span[text()='" + planLabel + "']",
				WAIT_TIME);
		this.sleep(2000);
		selenium.mouseDown("//span[text()='" + planLabel + "']");
		selenium.click("idSubModuleDeleteButton");
		this.sleep(3000);
		Assert.assertFalse(selenium.isElementPresent("//span[text()='" + planLabel + "']"), "plan "+planLabel +" delete failed! ");
	}
	

	public void runPlan(String planLabel) {
		this.waitForElementPresent("//span[text()='" + planLabel + "']",
				WAIT_TIME);
		this.sleep(2000);
		selenium.mouseDown("//span[text()='" + planLabel + "']");
		selenium.click("//div[text()='Execution Plan']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idJobConductorTaskRunButton']");

	}
	
	public void runPlanAndCheck(String planLabel,String taskName,int executeTimes){
		selenium.refresh();
		this.waitForElementPresent("//span[text()='" + planLabel + "']",
				WAIT_TIME);
		this.sleep(2000);
		selenium.mouseDown("//span[text()='" + planLabel + "']");
		for (int i = 0; i < executeTimes; i++) {
			selenium.click("//div[text()='Execution Plan']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idJobConductorTaskRunButton']");
			this.waitForElementPresent("//span[text()='Running...']", WAIT_TIME);
			this.waitForElementPresent("//span[text()='Ready to run']",
					WAIT_TIME);
			this.waitForElementPresent(
					"//span[@class='x-tree3-node-text' and text()='" + taskName
							+ " : [OK]']", MAX_WAIT_TIME);
		}
		
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

	public void sleep(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
