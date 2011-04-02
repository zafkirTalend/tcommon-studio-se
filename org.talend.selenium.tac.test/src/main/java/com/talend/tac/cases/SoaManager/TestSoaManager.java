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
		waitForElementPresent("//button[@id='idSubModuleAddButton' and @aria-disabled='false']", WAIT_TIME);
		selenium.click("//button[@id='idSubModuleAddButton' and @aria-disabled='false']");
		selenium.type("idSoaOperationNameInput", "TestOperation");
		selenium.fireEvent("idSoaOperationNameInput", "blur");
		selenium.type("idSoaOperationDescInput", "This is testOperation");
		selenium.fireEvent("idSoaOperationDescInput", "blur");

		// //div[not(contains(@class,'disabled'))]/input[@id='idCommonProjectListBox']
		// select project
		selenium
				.click("//div[not(contains(@class,'disabled'))]/input[@id='idCommonProjectListBox']/following-sibling::div");
		waitForElementPresent("//div[@role='listitem' and text()='"+projectName+"']", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@role='listitem' and text()='"+projectName+"']"));
		selenium.mouseDown("//div[@role='listitem' and text()='"+projectName+"']");
		assertFalse(selenium.isElementPresent("//div[@role='listitem' and text()='"+projectName+"']"));

		// select trunk,branches
		selenium
				.click("//div[not(contains(@class,'disabled'))]/input[@id='idCommonBranchListBox()']/following-sibling::div");
		waitForElementPresent("//div[@role='listitem' and text()='trunk']", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@role='listitem' and text()='trunk']"));
		selenium.mouseDown("//div[@role='listitem' and text()='trunk']");
		assertFalse(selenium.isElementPresent("//div[@role='listitem' and text()='trunk']"));
		// select job, the job name should be parameterd latter
		selenium
				.click("//div[not(contains(@class,'disabled'))]/input[@id='idCommonJobListBox()']/following-sibling::div");
		//if we confirm the job name we can use ://div[@role='listitem' and text()='EndRunningJob']
		waitForElementPresent("//div[@role='listitem'][1]", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@role='listitem'][1]"));
		selenium.mouseDown("//div[@role='listitem'][1]");
		assertFalse(selenium.isElementPresent("//div[@role='listitem'][1]"));

		// select version
		waitForElementPresent("//div[not(contains(@class,'disabled'))]/input[@id='idCommonContextListBox()' and @name='jobVersion']/following-sibling::div", WAIT_TIME);
		selenium
				.click("//div[not(contains(@class,'disabled'))]/input[@id='idCommonContextListBox()' and @name='jobVersion']/following-sibling::div");
		waitForElementPresent("//div[@role='listitem' and text()='0.1']", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@role='listitem' and text()='0.1']"));
		selenium.mouseDown("//div[@role='listitem' and text()='0.1']");
		assertFalse(selenium.isElementPresent("//div[@role='listitem' and text()='0.1']"));

		// select context
		waitForElementPresent("//div[not(contains(@class,'disabled'))]/input[@id='idCommonContextListBox()' and @name='contextName']/following-sibling::div", WAIT_TIME);
		selenium
				.click("//div[not(contains(@class,'disabled'))]/input[@id='idCommonContextListBox()' and @name='contextName']/following-sibling::div");
		waitForElementPresent("//div[@role='listitem' and text()='Default']", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@role='listitem' and text()='Default']"));
		selenium.mouseDown("//div[@role='listitem' and text()='Default']");
		assertFalse(selenium.isElementPresent("//div[@role='listitem' and text()='Default']"));
		selenium.click("idFormSaveButton");
		System.out.println("save clicked");
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
	@Test(enabled =false,description = "duplicate a operation, NOW, the ID is the same")
	public void testDuplicateSoaManagerOperation(){
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		selenium.mouseDown("//div[text()='TestService']");//select the service 
		this.clickWaitForElementPresent("//div[text()='TestOperation']");
		this.clickWaitForElementPresent("idSubModuleDuplicateButton");
	}
	@Test (description = "Delete a soaManager service",dependsOnMethods = { "testAddSoaManagerService","testDuplicateSoaManagerService" })
	public void testDeleteSoaManagerService() {
		this.clickWaitForElementPresent("!!!menu.soamanager.element!!!");
		this.waitForElementPresent("//div[text()='TestService']", WAIT_TIME);
		selenium.mouseDown("//div[text()='TestService']");//select the service 
		selenium.chooseOkOnNextConfirmation();
		this.clickWaitForElementPresent("idSubModuleDeleteButton");
		
		selenium.getConfirmation().equals("Are you sure you want to remove the selected item(s) ?");
		//selenium.waitForFrameToLoad("selenium.isTextPresent(\"Delete Done\")", WAIT_TIME*1000+"");
		//delete done finished,should improve this also.
	}
}
