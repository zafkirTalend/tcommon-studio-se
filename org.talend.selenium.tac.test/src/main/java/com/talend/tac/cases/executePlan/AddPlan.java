package com.talend.tac.cases.executePlan;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddPlan extends Plan {

	// add a plan
	@Test(groups = { "addplan" },dependsOnGroups={"Menu"})
	// , dependsOnGroups = { "cleanplan" }
	@Parameters({ "plan.label", "plan.description", "plan.task" })
	public void testAddPlan(String label, String description, String task) {
		this.addPlan(label, task, description);	
		// addPlan(label, description, task);

	}
	
	@Test(groups = { "addplan" },dependsOnGroups={"Menu"})
	// , dependsOnGroups = { "cleanplan" }
	@Parameters({ "plan.label", "plan.description", "plan.task" })
	public void testAddPlanLabelWithSpecialChar(String label, String description, String task) {
		label = "sdaf;test";
		this.openExecutionPlanMenu();
		this.clickWaitForElementPresent("//button[text()='Add plan']");
		this.typeString("idExecutionPlanPlanFormLabelInput", label);
		this.waitForElementPresent("//img[@class='gwt-Image x-component ' and @role='alert']", WAIT_TIME);
		this.typeString("idExecutionPlanPlanFormDescInput", description);
		this.selectDropDownList("String idExecutionPlanPlanFormTaskComboBox",
				task);
		selenium.mouseDown("//span[@class='x-fieldset-header-text' and text()='Execution Plan']/ancestor::div[@class=' x-panel x-component']//button[@id='idFormSaveButton']");
		selenium.click("//span[@class='x-fieldset-header-text' and text()='Execution Plan']/ancestor::div[@class=' x-panel x-component']//button[@id='idFormSaveButton']");
		selenium.mouseUp("//span[@class='x-fieldset-header-text' and text()='Execution Plan']/ancestor::div[@class=' x-panel x-component']//button[@id='idFormSaveButton']");
		this.waitForTextPresent("Fix errors in form before save", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);

	}
	

}
