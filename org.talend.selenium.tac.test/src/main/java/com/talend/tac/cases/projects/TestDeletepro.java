package com.talend.tac.cases.projects;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;
import com.thoughtworks.selenium.Selenium;

public class TestDeletepro extends Login {
	TestDuplicateProject duplicater = new TestDuplicateProject();

	public void cancelDelete(String name) {
		selenium.click("!!!menu.project.element!!!");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ name + "')]", Base.WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ name + "')]");
		selenium.chooseCancelOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		assert (selenium.getConfirmation().matches(other
				.getString("delete.project.warning")));
		// selenium.setSpeed("3000");
	}

	public void okDelete(String name) {
		selenium.click("!!!menu.project.element!!!");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ name + "')]", Base.WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ name + "')]");
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		assert (selenium.getConfirmation().matches(other
				.getString("delete.project.warning")));

	}

	public void okDelete(Selenium selenium, String name)
			throws InterruptedException {
		// s.click("!!!menu.project.element!!!");
		selenium.setSpeed(MAX_SPEED);
		
		selenium.refresh();

		if (selenium
				.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ name + "')]")) {
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
					+ name + "')]");
			selenium.chooseOkOnNextConfirmation();
			selenium.click("idSubModuleDeleteButton");
			selenium.getConfirmation();
			Thread.sleep(5000);
			Assert.assertFalse(
					selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
							+ name + "')]"), "delete project " + name
							+ " failed!");
		}
		selenium.setSpeed(MIN_SPEED);
	}

	@Test(groups = { "Second" }, dependsOnGroups = { "Add" })
	@Parameters({ "DeleteProjectname" })
	public void testDeletepro(String deleteProname) throws Exception {
		//
		selenium.setSpeed(MAX_SPEED);
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);
		selenium.click("!!!menu.project.element!!!");
		//this.waitForElementPresent("!!!menu.project.element!!!", 30);
		duplicater.duplicateProject(selenium,deleteProname);
		cancelDelete("Copy_of_"+deleteProname);
		okDelete("Copy_of_"+deleteProname);
		Assert.assertFalse(
				!selenium
						.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
								+"Copy_of_"+ deleteProname + "')]"),
				"Project delete failed!");
	}

	@AfterMethod
	public void tearDown() throws Exception {
		// selenium.stop();
	}

}
