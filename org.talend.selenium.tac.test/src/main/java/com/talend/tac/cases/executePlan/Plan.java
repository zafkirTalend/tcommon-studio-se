package com.talend.tac.cases.executePlan;

import org.testng.Assert;

import com.talend.tac.base.Base;
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

	public void addTask(String label, String description, String projectName,
			String branchName, String jobName, String version, String context,
			String serverName, String statisticName) {
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"
				+ rb.getString("menu.jobConductor") + "']"));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idSubModuleAddButton");
		this.typeString("idJobConductorTaskLabelInput", label);// plan name
		this.typeString("idJobConductorTaskDescInput", description);// plan name
		if (!selenium.isChecked("idJobConductorTaskActiveListBox")) {
			selenium.click("idJobConductorTaskActiveListBox");// check active
			Assert.assertTrue(selenium
					.isChecked("idJobConductorTaskActiveListBox"));
		}
		this.selectDropDownList("idTaskProjectListBox", projectName);
		this.selectDropDownList("idTaskBranchListBox", branchName);
		this.selectDropDownList("idTaskJobListBox", jobName);
		this.selectDropDownList("idTaskVersionListBox", version);
		this.selectDropDownList("idTaskContextListBox", context);
		this.selectDropDownList("idJobConductorExecutionServerListBox",
				serverName);
		this.selectDropDownList("idJobConductorTaskStatisticsListBox",
				statisticName);
		this.selectDropDownList("idJobConductorOnUnavailableJobServerListBox",
				"Wait");
		if (!selenium.isElementPresent("//span[text()='" + label + "']")) {
			selenium.click("//span[@class='x-fieldset-header-text' and text()='Execution task']/ancestor::div[@class=' x-panel x-component']//button[@id='idFormSaveButton']");
			this.waitForElementPresent("//span[text()='" + label + "']",
					Base.WAIT_TIME);
		}
	}
	
	public void addPlan(String planLabel, String rootTask, String description) {

		this.openExecutionPlanMenu();
		this.clickWaitForElementPresent("//button[text()='Add plan']");
		this.typeString("idExecutionPlanPlanFormLabelInput", planLabel);
		this.typeString("idExecutionPlanPlanFormDescInput", description);
		this.selectDropDownList("String idExecutionPlanPlanFormTaskComboBox",
				rootTask);
		selenium.mouseDown("//span[@class='x-fieldset-header-text' and text()='Execution Plan']/ancestor::div[@class=' x-panel x-component']//button[@id='idFormSaveButton']");
		selenium.click("//span[@class='x-fieldset-header-text' and text()='Execution Plan']/ancestor::div[@class=' x-panel x-component']//button[@id='idFormSaveButton']");
		selenium.mouseUp("//span[@class='x-fieldset-header-text' and text()='Execution Plan']/ancestor::div[@class=' x-panel x-component']//button[@id='idFormSaveButton']");
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
		this.sleep(2000);
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
			this.sleep(2000);
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
