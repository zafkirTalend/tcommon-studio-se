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

	
	@Test(description = "Add a operation with the defined project",dependsOnMethods = { "testAddSoaManagerService" },alwaysRun=true)
	@Parameters ({"AddcommonProjectname","soaManager.service.name","soaManager.operation.name","soaManager.operation.UsedJob_name"})
	public void testAddOperationToService(String projectName,String serviceName,String operationName,String UsedJobName) {

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
		
//		// select job, the job name should be parameterd latter
//		this.clickWaitForElementPresent("//input[@id='idCommonJobListBox']/following-sibling::div");
//		//if we confirm the job name we can use ://div[@role='listitem' and text()='EndRunningJob']
//		waitForElementPresent("//div[@role='listitem'][1]", WAIT_TIME);
//		assertTrue(selenium.isElementPresent("//div[@role='listitem'][1]"));
//		selenium.mouseDown("//div[@role='listitem'][1]");	
//		assertFalse(selenium.isElementPresent("//div[@role='listitem'][1]"));
		this.selectDropDownList("idCommonJobListBox", UsedJobName);

		// select version
		this.selectDropDownList("idCommonVersionListBox", "0.1");
		// select context
		this.selectDropDownList("idCommonContextListBox", "Default");
		selenium.click("idSoaOperationSave");
	}
	
	@Test(description = "duplicate a service",dependsOnMethods = { "testAddSoaManagerService" },alwaysRun=true)
	@Parameters ({"soaManager.service.name"})
	public void testDuplicateSoaManagerService(String serviceName){
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service 
		this.clickWaitForElementPresent("idSubModuleDuplicateButton");
//		assertEquals(selenium.getText("idSoaServiceNameInput"), "Copy_of_TestService");
		selenium.click("idFormSaveButton");
		
	}
	@Test(description = "duplicate a operation,there should be a warning and duplication can't be done",dependsOnMethods = { "testAddOperationToService" },alwaysRun=true)
	@Parameters ({"soaManager.service.name","soaManager.operation.name"})
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
	@Test (enabled = true,description = "generate,deploy,start a operation",dependsOnMethods = { "testAddOperationToService" ,"testDuplicateSoaManagerOperation"},alwaysRun=true)
	@Parameters ({"soaManager.service.name","soaManager.operation.name"})
	public void testGenerateService(String serviceName,String operationName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service
		selenium.click("idSoaServiceGenerateButton");
		this.waitForElementPresent("//img[@title='GENERATED']",MAX_WAIT_TIME);
		
	}
	@Test (enabled = true,description = "generate,deploy,start a operation")
	@Parameters ({"soaManager.service.name","soaManager.operation.name"})
	public void testDeployService(String serviceName,String operationName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service
		selenium.click("idSoaServiceDeployButton    ");
		this.waitForElementPresent("//img[@title='DEPLOYED']",MAX_WAIT_TIME);
	}
	@Test (enabled = true,description = "generate,deploy,start a operation")
	@Parameters ({"soaManager.service.name","soaManager.operation.name"})
	public void testStartService(String serviceName,String operationName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service
		this.clickWaitForElementPresent("idSoaServiceRunButton    ");
		this.waitForElementPresent("//img[@title='RUNNING']",MAX_WAIT_TIME);
	}
	
	@Test (enabled = true,description = "generate,deploy,start a operation")
	@Parameters ({"soaManager.service.name","soaManager.operation.name"})
	public void testStopService(String serviceName,String operationName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service
		this.clickWaitForElementPresent("idSoaServiceStopButton");
		this.waitForElementPresent("//img[@title='DEPLOYED']",MAX_WAIT_TIME);
	}
	//
	@Test (description = "Delete a soaManager operation",dependsOnMethods = { "testAddSoaManagerService","testAddOperationToService" ,"testDuplicateSoaManagerOperation"},alwaysRun=true)
	@Parameters ({"soaManager.service.name","soaManager.operation.name"})
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
	
	@Test (description = "Delete a soaManager service",dependsOnMethods = { "testAddSoaManagerService","testDuplicateSoaManagerService" },alwaysRun=true)
	@Parameters ({"soaManager.service.name"})
	public void testDeleteSoaManagerService(String serviceName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='" + serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service
		selenium.chooseOkOnNextConfirmation();
		this.clickWaitForElementPresent("idSubModuleDeleteButton");
		
		selenium.getConfirmation().equals("Are you sure you want to remove the selected item(s) ?");
	}
	@Test (description = "Modify soaManager service",dependsOnMethods = { "testAddSoaManagerService","testDuplicateSoaManagerService" },alwaysRun=true)
	@Parameters ({"soaManager.service.name"})
	public void testModifyService(String serviceName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");//select the service 
		assertEquals(selenium.getValue("idSoaServiceNameInput"), serviceName);
		this.typeAndBlur("idSoaServiceNameInput", "ServiceNameAfterModification");
		selenium.click("idFormSaveButton");
		waitForElementPresent("//*[text()='ServiceNameAfterModification']", WAIT_TIME);
		selenium.mouseDown("//*[text()='ServiceNameAfterModification']");//select the service 
		assertEquals(selenium.getValue("idSoaServiceNameInput"), "ServiceNameAfterModification");
		this.typeAndBlur("idSoaServiceNameInput", serviceName);
		selenium.click("idFormSaveButton");
		
	}
	@Test(description = "Add a operation with the defined project",dependsOnMethods = { "testAddSoaManagerService" },alwaysRun=true)
	@Parameters ({"AddcommonProjectname","soaManager.service.name","soaManager.operation.name","soaManager.operation.UsedJob_name"})
	public void testModifyOperation(String projectName,String serviceName,String operationName,String UsedJobName) {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		waitForElementPresent("//*[text()='"+serviceName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+serviceName+"']");
		waitForElementPresent("//*[text()='"+operationName+"']", WAIT_TIME);
		selenium.mouseDown("//*[text()='"+operationName+"']");
		this.typeAndBlur("idSoaOperationDescInput", "This is testOperationAfterModification");
		selenium.click("idSoaOperationSave");
		selenium.mouseDown("//*[text()='"+operationName+"']");
		assertEquals(selenium.getValue("idSoaOperationDescInput"), "This is testOperationAfterModification");
		//can do some other modifications
		
	}
}
