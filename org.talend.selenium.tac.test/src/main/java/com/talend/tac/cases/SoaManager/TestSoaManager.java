package com.talend.tac.cases.SoaManager;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.talend.tac.cases.Login;

public class TestSoaManager extends Login {
	@Test(description="Add a service")
	@Parameters({"soaManager.service.name"})
	public void testAddSoaManagerService(String serviceName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		selenium.click("idSubModuleAddButton");
		selenium.type("idSoaServiceNameInput", serviceName);
		
		selenium.fireEvent("idSoaServiceNameInput", "blur");
		selenium.type("idSoaServiceContactInput", "contact TestService");
		selenium.fireEvent("idSoaServiceContactInput", "blur");
		selenium.type("idSoaServiceContactInput", "This is TestService");
		selenium.fireEvent("idSoaServiceContactInput", "blur");
		// selenium.type("idSoaServicePortInput", "8881");
		selenium.click("idFormSaveButton");
	}

	
	@Test(description = "Add a operation with the defined project",dependsOnMethods = { "testAddSoaManagerService" })
	@Parameters ({"AddcommonProjectname","soaManager.service.name","soaManager.operation.UsedTrunJob.name"})
	public void testAddOperationToService(String projectName,String serviceName,String operationName) {
		// selenium.mouseDown("//")
		selenium.click("!!!menu.soamanager.element!!!");//*[text()='"+serviceName+"']
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");
		this.clickWaitForElementPresent("idSoaOperationAdd");
		this.typeAndBlur("idSoaOperationNameInput", operationName);
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
	@Parameters ({"soaManager.service.name"})
	public void testDuplicateSoaManagerService(String serviceName){
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service 
		this.clickWaitForElementPresent("idSubModuleDuplicateButton");
//		assertEquals(selenium.getText("idSoaServiceNameInput"), "Copy_of_TestService");
		selenium.click("idFormSaveButton");
		
	}
	@Test(description = "duplicate a operation,there should be a warning and duplication can't be done",dependsOnMethods = { "testAddOperationToService" })
	@Parameters ({"soaManager.service.name","soaManager.operation.UsedTrunJob.name"})
	public void testDuplicateSoaManagerOperation(String serviceName,String operationName){
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service
		waitForElementPresent("//*[text()='"+operationName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+operationName+"']");
		this.clickWaitForElementPresent("idSoaOperationDuplicate");
		selenium.click("idSoaOperationSave");
		//this.waitForElementPresent(rb.getString("soamanager.parameter.error"), WAIT_TIME);
	}
	//
	//
	@Test (enabled = false,description = "generate,deploy,run a operation",dependsOnMethods = { "testAddSoaManagerService","testAddOperationToService" ,"testDuplicateSoaManagerOperation"})
	@Parameters ({"soaManager.service.name","soaManager.operation.UsedTrunJob.name"})
	public void testRunService(String serviceName,String operationName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service
		// set a proper assertion of very period.
	}
	//
	@Test (description = "Delete a soaManager operation",dependsOnMethods = { "testAddSoaManagerService","testAddOperationToService" ,"testDuplicateSoaManagerOperation"})
	@Parameters ({"soaManager.service.name","soaManager.operation.UsedTrunJob.name"})
	public void testDeleteSoaManagerOperation(String serviceName,String operationName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service
		waitForElementPresent("//*[text()='"+operationName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+operationName+"']");
		selenium.chooseOkOnNextConfirmation();
		this.clickWaitForElementPresent("idSoaOperationDelete");
		
		selenium.getConfirmation().equals("Are you sure you want to remove the selected item(s) ?");
	}
	@Test (description = "Delete a soaManager service",dependsOnMethods = { "testAddSoaManagerService","testDuplicateSoaManagerService" })
	@Parameters ({"soaManager.service.name"})
	public void testDeleteSoaManagerService(String serviceName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='Copy_of_"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='Copy_of_"+serviceName+"']");//select the service
		selenium.chooseOkOnNextConfirmation();
		this.clickWaitForElementPresent("idSubModuleDeleteButton");
		
		selenium.getConfirmation().equals("Are you sure you want to remove the selected item(s) ?");
	}
}
