package com.talend.tac.cases.executionTask;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDuplicateTrigger extends Login {
    
	@Test(groups={"DuplicateTrigger"},dependsOnGroups={"addFileTrigger"})
	public void testDuplicateTrigger() {
	   
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
    	selenium.setSpeed(MID_SPEED);
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='"+rb.getString("menu.jobConductor")+"']"));
    	selenium.mouseDown("//div[text()='Copy_of_testTask']");//select a exist task
//    	selenium.mouseDown("//div[text()='a']");//select a exist task
    	selenium.mouseDown("//div[text()='TestSimpleTrigger']");//select a exist task
    	
    	selenium.click("idSubModuleDuplicateButton");
    	selenium.click("//input[@name='repeatInterval']");
    	selenium.click("//div[@class=' x-panel x-component ']/div[2]/div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button[@id='idFormSaveButton']");
    	Assert.assertTrue(selenium.isElementPresent("//div[text()='Copy_of_TestSimpleTrigger']"));
    	
	}
}
