package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;

public class TestPlanExecution extends Plan {

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
			selenium.click("idFormSaveButton");
			this.waitForElementPresent("//span[text()='" + label + "']",
					Base.WAIT_TIME);
		}
	}

	public void addPlan(String label, String description, String task) {

		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent(
				"//div[@class='header-title' and text()='Execution Plan']",
				WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
		this.clickWaitForElementPresent("//button[text()='Add plan']");
		this.typeString("idExecutionPlanPlanFormLabelInput", label);
		this.typeString("idExecutionPlanPlanFormDescInput", description);
		this.selectDropDownList("String idExecutionPlanPlanFormTaskComboBox",
				task);
		// click save button
		selenium.mouseDown("//span[@class='x-fieldset-header-text' and text()='Execution Plan']//ancestor::div[@class='x-tab-panel-body x-tab-panel-body-top']//button[@id='idFormSaveButton']");
		selenium.click("//span[@class='x-fieldset-header-text' and text()='Execution Plan']//ancestor::div[@class='x-tab-panel-body x-tab-panel-body-top']//button[@id='idFormSaveButton']");
		selenium.mouseUp("//span[@class='x-fieldset-header-text' and text()='Execution Plan']//ancestor::div[@class='x-tab-panel-body x-tab-panel-body-top']//button[@id='idFormSaveButton']");
		this.waitForElementPresent("//span[text()='" + label + "']", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//span[text()='" + label
				+ "']"));
		selenium.setSpeed(MIN_SPEED);
	}

	@Test
	@Parameters({"plan.unexecuted.label","plan.unexecuted.tasklabel","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testGeneratePlan(String planlabel,String tasklabel, String labelDescription,String commonpro,String branch,String jobName,
			String version,String context,String jobServer,String statistic) throws InterruptedException {
		
		addTask(tasklabel, labelDescription,commonpro,
				branch, jobName,version, context,
				jobServer,statistic);
		addPlan(planlabel, "planunexcuted", tasklabel) ;
		selenium.refresh();
		this.waitForElementPresent("//span[text()='" + planlabel + "']", WAIT_TIME);
		Thread.sleep(2000);
		selenium.mouseDown("//span[text()='" + planlabel + "']");
		selenium.click("//div[text()='Execution Plan']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idJobConductorTaskGenerateButton']");
		this.waitForElementPresent("//span[text()='Generating...']", WAIT_TIME);
		this.waitForElementPresent("//span[text()='Ready to deploy']", MAX_WAIT_TIME);
	}
	
	@Test(dependsOnMethods={"testGeneratePlan"})
	@Parameters({"plan.unexecuted.label"})
	public void testDeployPlan(String planLabel){
		selenium.refresh();
		this.waitForElementPresent("//span[text()='" + planLabel + "']", WAIT_TIME);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.mouseDown("//span[text()='" + planLabel + "']");
		this.sleep(2000);
		selenium.click("//div[text()='Execution Plan']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idJobConductorTaskDeployButton']");
		this.waitForElementPresent("//span[text()='Ready to run']", MAX_WAIT_TIME);
		
	}
	
	@Test(dependsOnMethods={"testDeployPlan"})
	@Parameters({"plan.unexecuted.label"})
	public void testRunPlan(String planLabel){
		selenium.refresh();
		this.waitForElementPresent("//span[text()='" + planLabel + "']", WAIT_TIME);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.mouseDown("//span[text()='" + planLabel + "']");
		this.sleep(2000);
		selenium.click("//div[text()='Execution Plan']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idJobConductorTaskRunButton']");
		this.waitForElementPresent("//span[text()='Running...']", WAIT_TIME);
		this.waitForElementPresent("//span[text()='Ready to run']", MAX_WAIT_TIME);
	}

}
