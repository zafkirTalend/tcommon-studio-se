/*
 * CaseName:
 * DuplicateProject
 * Steps:
 1. select a project exist in the projects list;
 2. click the duplicate button;
 * expect result:
    1. can create a project from the Duplicate button
 *Auto test problem:
    
 */
package com.talend.tac.cases.project;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDuplicateProject extends Login {
	@Test(groups = { "Second" },dependsOnGroups = { "Add" })
	@Parameters({ "duplicateproname" })
	public void f(String duplicateproname) {
		if (selenium == null) {
			System.out.println("selenium Ϊ��!");
		}
		
		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");//
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ duplicateproname + "')]",30);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ duplicateproname + "')]");// the selected project's id
		selenium.click("idSubModuleDuplicateButton");
		selenium.setSpeed("5000");
		selenium.click("idFormSaveButton");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ "Copy_of_" + duplicateproname + "')]"),
				"project duplicated failed");
	}
}
