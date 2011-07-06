package com.talend.tac.cases.grid;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Parameters;

public class TestGridRecoveryManagement extends Grid {

	// @Test
	@Parameters({ "grid.task.label", "labelDescription",
			"AddcommonProjectname", "branchNameTrunk", "jobNameTJava",
			"version0.1", "context", "ServerForUseAvailable",
			"statisticEnabled" })
	public void testGridWaitingExecutionHasNoRecovery(String label,
			String description, String projectName, String branchName,
			String jobName, String version, String context, String serverName,
			String statisticName) {
		this.cleanTask();
		this.addTask(label, description, projectName, branchName, jobName,
				version, context, serverName, statisticName);
		this.openGridMenu();
		this.sleep(5000);
		Assert.assertTrue(
				selenium.getXpathCount(
						"//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "
								+ label + "']").intValue() == 0,
				"Grid past execution display failed!");
		this.openTaskMenu();
		this.addSimpleTriggerForTask(label, "testSimpleTrigger", "3600", "3");
		this.waitForTextPresent("Generating...", WAIT_TIME);
		this.waitForElementPresent("//span[text()='Ready to run']", WAIT_TIME);
		this.openGridMenu();
		this.sleep(5000);
		Assert.assertTrue(
				selenium.getXpathCount(
						"//span[@title='Waiting for triggering...' and text()='Waiting for triggering...']")
						.intValue() == 3,
				"Grid Future execution display failed!");
	}

	@Test
	@Parameters({ "grid.task.label", "labelDescription",
			"AddcommonProjectname", "branchNameTrunk", "jobNameTJava",
			"version0.1", "context", "ServerForUseAvailable",
			"statisticEnabled" })
	public void testGridShowStatisticView(String label, String description,
			String projectName, String branchName, String jobName,
			String version, String context, String serverName,
			String statisticName) {
		this.cleanTask();
		this.addTask(label, description, projectName, branchName, jobName,
				version, context, serverName, statisticName);
		this.openGridMenu();
		this.sleep(5000);
		Assert.assertTrue(
				selenium.getXpathCount(
						"//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "
								+ label + "']").intValue() == 0,
				"Grid past execution display failed!");
		this.openTaskMenu();
		this.runTask(label, 1);
		this.openGridMenu();
		this.sleep(5000);
		Assert.assertTrue(
				selenium.getXpathCount(
						"//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "
								+ label + "']").intValue() == 1,
				"Grid Future execution display failed!");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "
						+ label
						+ "']//ancestor::table[@class='x-grid3-row-table']//table[@title='Show statistic view']//button[@class='x-btn-text']",
				WAIT_TIME);
		selenium.click("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "
				+ label
				+ "']//ancestor::table[@class='x-grid3-row-table']//table[@title='Show statistic view']//button[@class='x-btn-text']");
		
		//verify informations about contexts
		this.waitForElementPresent(
				"//span[@class='x-fieldset-header-text' and text()='Context Parameters']",
				WAIT_TIME);
		this.waitForElementPresent(
				"//span[@class='x-fieldset-header-text' and text()='Context Parameters']//ancestor::fieldset[@class=' x-fieldset x-component']//textarea",
				WAIT_TIME);
		this.sleep(5000);
		String statisticLogsContext = selenium
				.getValue("//span[@class='x-fieldset-header-text' and text()='Context Parameters']//ancestor::fieldset[@class=' x-fieldset x-component']//textarea");
		System.out.println(statisticLogsContext);
		Assert.assertTrue(statisticLogsContext.contains("name: JackZhang"));
		Assert.assertTrue(statisticLogsContext.contains("age: 23"));
        //verify informations about TASK
		Assert.assertTrue(selenium.getValue("//label[text()='Task:']//ancestor::div[@class='x-form-item ']//input").equals("Task: "+label), "Task task information shows wrong!");
		Assert.assertTrue(selenium.getValue("//span[@class='x-fieldset-header-text' and text()='Task']//ancestor::fieldset[@class=' x-fieldset x-component']//label[text()='Project:']//ancestor::div[@class='x-form-item ']//input").contains(projectName), "Task project information shows wrong!");
	    //verify job execution
		Assert.assertTrue(selenium.getValue("//span[@class='x-fieldset-header-text' and text()='Job Execution']//ancestor::fieldset[@class=' x-fieldset x-component']//label[text()='Job Server:']//ancestor::div[@class='x-form-item ']//input").contains(serverName), "Task server information shows wrong!");
		Assert.assertTrue(selenium.getValue("//span[@class='x-fieldset-header-text' and text()='Job Execution']//ancestor::fieldset[@class=' x-fieldset x-component']//label[text()='Job:']//ancestor::div[@class='x-form-item ']//input").contains(jobName), "Task job information shows wrong!");
	}

}
