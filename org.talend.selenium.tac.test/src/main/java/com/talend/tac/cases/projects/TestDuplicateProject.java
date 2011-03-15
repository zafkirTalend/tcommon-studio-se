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

import com.talend.tac.cases.Login;

public class TestDuplicateProject extends Login {
	@Test(groups = { "Second" },dependsOnGroups = { "Add" })
	@Parameters({ "duplicateproname" ,"ProjectType"})
	public void f(String duplicateproname,String type) {
		
		
		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");//
		selenium.setSpeed("5000");
		if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ "Copy_of_" + duplicateproname + "')]")){
			return ;
			
		}
		selenium.setSpeed("0");
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ duplicateproname + "')]",30);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ duplicateproname + "')]");// the selected project's id
		selenium.click("idSubModuleDuplicateButton");
		selenium.setSpeed("5000");
		//*********************add the type select option
//		//*********************add the type select option
//		selenium.setSpeed("2000");
//		if(selenium.isElementPresent("idProjectTypeComboBox")){
//		selenium.click("idProjectTypeComboBox");
//		selenium.mouseDown("//div[text()='"+type+"']");
//		selenium.setSpeed("0");
//		}
		//*********************
		
		selenium.setSpeed("3000");
		selenium.click("idDescriptionInput");

		//*********************
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
		selenium.setSpeed("0");
	}
}
