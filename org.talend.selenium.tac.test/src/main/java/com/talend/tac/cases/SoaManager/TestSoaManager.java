package com.talend.tac.cases.SoaManager;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.talend.tac.cases.Login;

public class TestSoaManager extends Login {
	@Test(description="Add a service")
	public void testAddSoaManagerService() {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		selenium.click("idSubModuleAddButton");
		selenium.type("idSoaServiceNameInput", "TestService");
		
		selenium.fireEvent("idSoaServiceNameInput", "blur");
		selenium.type("idSoaServiceContactInput", "contact TestService");
		selenium.fireEvent("idSoaServiceContactInput", "blur");
		selenium.type("idSoaServiceContactInput", "This is TestService");
		selenium.fireEvent("idSoaServiceContactInput", "blur");
		// selenium.type("idSoaServicePortInput", "8881");
		selenium.click("idFormSaveButton");
	}

	
	@Test(description = "Add a operation with the defined project",dependsOnMethods = { "testAddSoaManagerService" })
	@Parameters ({"AddcommonProjectname"})
	public void testAddOperationToService(String projectName) {
		// selenium.mouseDown("//")
		selenium.click("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='TestService']", WAIT_TIME);
		selenium.mouseDown("//*[text()='TestService']");
		this.clickWaitForElementPresent("idSoaOperationAdd");
		this.typeAndBlur("idSoaOperationNameInput", "TestOperation");
		this.typeAndBlur("idSoaOperationDescInput", "This is testOperation");
		// select project
		this.selectDropDownList("idCommonProjectListBox", projectName);
		// select trunk,branches 
		this.selectDropDownList("idCommonBranchListBox", "trunk");
		// select job, the job name should be parameterd latter
		selenium
		.click("//input[@id='idCommonJobListBox']/following-sibling::div");
		//if we confirm the job name we can use ://div[@role='listitem' and text()='EndRunningJob']
		waitForElementPresent("//div[@role='listitem'][1]", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@role='listitem'][1]"));
		selenium.mouseDown("//div[@role='listitem'][1]");
		assertFalse(selenium.isElementPresent("//div[@role='listitem'][1]"));

		// select version
		this.selectDropDownList("idCommonVersionListBox", "0.1");
		// select context
		this.selectDropDownList("idCommonContextListBox", "Default");
		selenium.click("idSoaOperationSave");
		
	}
	
	@Test(description = "duplicate a service",dependsOnMethods = { "testAddSoaManagerService" })
	public void testDuplicateSoaManagerService(){
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		this.waitForElementPresent("//div[text()='TestService']", WAIT_TIME);
		selenium.mouseDown("//div[text()='TestService']");//select the service 
		this.clickWaitForElementPresent("idSubModuleDuplicateButton");
//		assertEquals(selenium.getText("idSoaServiceNameInput"), "Copy_of_TestService");
		selenium.click("idFormSaveButton");
		
	}
	@Test(description = "duplicate a operation,there should be a warning and duplication can't be done",dependsOnMethods = { "testAddOperationToService" })
	public void testDuplicateSoaManagerOperation(){
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		this.waitForElementPresent("//div[text()='TestService']", WAIT_TIME);
		selenium.mouseDown("//div[text()='TestService']");//select the service 
		this.waitForElementPresent("//div[text()='TestOperation']",WAIT_TIME);
		selenium.mouseDown("//div[text()='TestOperation']");
		this.clickWaitForElementPresent("idSoaOperationDuplicate");
		selenium.click("idSoaOperationSave");
		//this.waitForElementPresent(rb.getString("soamanager.parameter.error"), WAIT_TIME);
	}
	@Test (description = "Delete a soaManager service",dependsOnMethods = { "testAddSoaManagerService","testDuplicateSoaManagerService" })
	public void testDeleteSoaManagerService() {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		this.waitForElementPresent("//div[text()='Copy_of_TestService']", WAIT_TIME);
		selenium.mouseDown("//div[text()='Copy_of_TestService']");//select the service 
		selenium.chooseOkOnNextConfirmation();
		this.clickWaitForElementPresent("idSubModuleDeleteButton");
		
		selenium.getConfirmation().equals("Are you sure you want to remove the selected item(s) ?");
	}
}
