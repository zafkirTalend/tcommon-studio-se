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
package com.talend.tac.cases.projects;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;
import com.thoughtworks.selenium.Selenium;

public class TestDuplicateProject extends Login {
	@Test(groups = { "Second" },dependsOnGroups = { "Add" })
	@Parameters({ "duplicateproname" ,"ProjectType"})
	public void testDuplicateProject(String duplicateproname,String type) throws InterruptedException {
		Thread.sleep(5000);
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);
		selenium.click("!!!menu.project.element!!!");//
		selenium.setSpeed(MAX_SPEED);
		if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ "Copy_of_" + duplicateproname + "')]")){
			return ;
			
		}
		selenium.setSpeed(MID_SPEED);
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ duplicateproname + "')]",Base.WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ duplicateproname + "')]");// the selected project's id
		selenium.setSpeed(MID_SPEED);
		selenium.click("idSubModuleDuplicateButton");
		
/*		// add the type select option
		// The feature is removed
		selenium.setSpeed("2000");
		if(selenium.isElementPresent("idProjectTypeComboBox")){
		selenium.click("idProjectTypeComboBox");
		selenium.mouseDown("//div[text()='"+type+"']");
		selenium.setSpeed("0");
		}
*/
		
		selenium.click("idDescriptionInput");
		selenium.click("idFormSaveButton");
		
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ "Copy_of_" + duplicateproname + "')]"),
				"project duplicated failed");
		selenium.setSpeed(MIN_SPEED);
	}
	public void duplicateProject(Selenium selenium,String proname) throws InterruptedException{
		selenium.setSpeed(MAX_SPEED);
		selenium.refresh();
//		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
//				+ duplicateproname + "')]",30);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ proname + "')]");// the selected project's id
		selenium.setSpeed(MID_SPEED);
		selenium.click("idSubModuleDuplicateButton");
		
/*		// add the type select option
		// The feature is removed
		selenium.setSpeed("2000");
		if(selenium.isElementPresent("idProjectTypeComboBox")){
		selenium.click("idProjectTypeComboBox");
		selenium.mouseDown("//div[text()='"+type+"']");
		selenium.setSpeed("0");
		}
*/
		
		selenium.click("idDescriptionInput");
		selenium.click("idFormSaveButton");
		Thread.sleep(5000);
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ "Copy_of_" + proname + "')]"),
				"project duplicated failed");
		selenium.setSpeed(MIN_SPEED);
		
	}
}
