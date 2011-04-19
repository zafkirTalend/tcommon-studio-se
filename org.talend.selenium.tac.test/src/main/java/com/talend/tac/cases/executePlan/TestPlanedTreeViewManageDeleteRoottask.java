package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestPlanedTreeViewManageDeleteRoottask extends Login {
	@Test
	@Parameters({ "plan.tasktreeviewmanage.label" })
	public void testDeleteRoottask(String plan) {
		selenium.setSpeed(MID_SPEED);
		selenium.click("!!!menu.executionPlan.element!!!");
		selenium.mouseDown("//div[text()='" + plan + "']");
		String rootTask1 = null;
		// mouse down a plan
		rootTask1 = selenium
				.getText("//div[@class='x-grid3-cell-inner x-grid3-col-rootTask']");
		// mouse down the root task
		selenium.mouseDown("//span[@class='x-tree3-node-text' and text()='"
				+ rootTask1 + "']");
		// selenium.chooseOkOnNextConfirmation();
		// click delete button
		selenium.click("//div[@class='x-panel-tbar']/div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct']/table[@class='x-toolbar-ct']/tbody/tr/td[@class='x-toolbar-left']/table/tbody/tr[@class='x-toolbar-left-row']/td[@class='x-toolbar-cell']/table[@class=' x-btn x-component x-btn-text-icon ']/tbody[@class='x-btn-small x-btn-icon-small-left']/tr/td[@class='x-btn-mc']/em/button[text()='Delete']");
		Assert.assertTrue(
				selenium.getAlert()
						.equals(other
								.getString("warningmessage.delete.executionplan.plantasktreeviewmanage.delete.roottask")),
				"root task delete failed!");
		Assert.assertTrue(
				selenium.isElementPresent("//span[@class='x-tree3-node-text' and text()='"
						+ rootTask1 + "']"), "root task delete failed!");
	}

}
