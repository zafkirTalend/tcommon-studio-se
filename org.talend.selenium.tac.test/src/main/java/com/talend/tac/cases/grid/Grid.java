package com.talend.tac.cases.grid;

import org.testng.Assert;

import com.talend.tac.base.Base;
import com.talend.tac.cases.executePlan.Plan;

public class Grid extends Plan {

	public void openGridMenu() {
		this.clickWaitForElementPresent("!!!menu.grid_task_executions_history.element!!!");
		this.waitForElementPresent(
				"//div[@class='header-title' and text()='Task execution monitoring']",
				WAIT_TIME);
	}

	public void openTaskMenu() {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"
				+ rb.getString("menu.jobConductor") + "']"));
		selenium.setSpeed(MIN_SPEED);
	}
	
	public void cleanTask(){
		this.openTaskMenu();
		selenium.setSpeed(MID_SPEED);
		int n = selenium.getXpathCount("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-label ']//div[@class='x-grid3-cell-inner x-grid3-col-label']").intValue();
		selenium.setSpeed(MIN_SPEED);
		for(int i = 0; i < n; i++){
			this.deleteTask(selenium.getText("//td[@class='x-grid3-col x-grid3-cell x-grid3-td-label ']//div[@class='x-grid3-cell-inner x-grid3-col-label']"));
		}
	}
	
	public void runTask(String tasklabel,int times) {
		for (int i = 0; i < times; i++) {
			selenium.refresh();
			this.waitForElementPresent("//span[text()='" + tasklabel + "']",
					WAIT_TIME);
			this.sleep(2000);
			selenium.mouseDown("//span[text()='" + tasklabel + "']");
			this.sleep(2000);
			selenium.click("//button[@id='idJobConductorTaskRunButton'  and @class='x-btn-text ' and text()='Run']");
			// Date start = new Date();
			this.waitForElementPresent("//span[text()='Real time statistics']", Base.WAIT_TIME);
			Assert.assertTrue(
					this.waitElement("//label[text()='Ok']", Base.WAIT_TIME),
					"task run failed!");
			// close the pop window
			selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
		   }
		}
	
	public void addSimpleTriggerForTask(String taskLabel, String TriggerLabel,
			String triggerInterval, String executionTimes) {
		this.openTaskMenu();
		this.waitForElementPresent("//span[text()='" + taskLabel + "']",
				Base.WAIT_TIME);
		this.sleep(2000);
		selenium.mouseDown("//span[text()='" + taskLabel + "']");
		selenium.click("idTriggerAdd trigger...");// add a trigger
		selenium.click("idTriggerAdd simple trigger");// add a SimpleTrigger
		Assert.assertTrue(selenium.isElementPresent("//span[text()='"
				+ rb.getString("trigger.action.addSimpleTrigger") + "']"));
		this.typeString("idJobConductorSimpleTriggerLableInput", TriggerLabel);// label
		this.typeString("idJobConductorSimpleTriggerRptCountInput",
				executionTimes);
		this.typeString("idJobConductorSimpleTriggerRptIntervalInput",
				triggerInterval);
		selenium.click("idSimpleTriggerSave");
		selenium.setSpeed(MIN_SPEED);
		if (!selenium.isElementPresent("//span[text()='" + TriggerLabel + "']")) {
			selenium.click("idTriggerRefresh");
		}
		this.waitForElementPresent("//span[text()='" + TriggerLabel + "']",
				WAIT_TIME);

	}

	public void deleteTask(String taskLabel) {
		this.openTaskMenu();
		this.waitForElementPresent("//span[text()='" + taskLabel + "']",
				Base.WAIT_TIME);
		this.sleep(2000);
		selenium.mouseDown("//span[text()='" + taskLabel + "']");
		selenium.chooseOkOnNextConfirmation();
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");// clcik "Delete"
		Assert.assertTrue(selenium.getConfirmation().matches(
				other.getString("delete.plan.warning")));
		Assert.assertEquals(selenium.getConfirmation(),
				"Do you want to remove all of the related logs and archives");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium
				.isElementPresent("//span[text()='testTaskNotChooseActive']"));// the
		selenium.setSpeed(MIN_SPEED);
	}
}
