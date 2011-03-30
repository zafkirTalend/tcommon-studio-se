package com.talend.tac.cases.SoaManager;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.talend.tac.cases.Login;

public class TestSoaManager extends Login {
	@Test
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

	@Test
	@Parameters( { "project.automationProject.name" })
	public void testAddToService(String projectName) {
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
		waitForElementPresent("//div[@role='listitem' and text()=projectName]", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@role='listitem' and text()=projectName]"));
		selenium.mouseDown("//div[@role='listitem' and text()=projectName]");
		assertFalse(selenium.isElementPresent("//div[@role='listitem' and text()=projectName]"));

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
		waitForElementPresent("//div[@role='listitem' and text()='EndRunningJob']", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@role='listitem' and text()='EndRunningJob']"));
		selenium.mouseDown("//div[@role='listitem' and text()='EndRunningJob']");
		assertFalse(selenium.isElementPresent("//div[@role='listitem' and text()='EndRunningJob']"));

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
	}
}
