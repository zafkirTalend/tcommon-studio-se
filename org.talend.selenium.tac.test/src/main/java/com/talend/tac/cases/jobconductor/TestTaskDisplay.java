package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import com.talend.tac.cases.Login;

public class TestTaskDisplay extends Login {
	@Test
	@Parameters({"labelReferenceproTjava"})
	public void testTaskDisplay(String TaskLable){
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		
		this.waitForElementPresent("//div[contains(text(),'Project: "+ TaskLable + "')]", WAIT_TIME);
		//click the group of task
		this.waitForElementPresent("//span[text()='testTask2']", WAIT_TIME);
		//make a task selected
		selenium.mouseDown("//span[text()='testTask2']");
		//assert the display(by attribute)
		assertFalse(selenium.isElementPresent("//div[@class='x-grid3-row  x-unselectable-single ']//span[text()='testTask2']"));
		selenium.mouseDown("//div[contains(text(),'Project: "+ TaskLable + "')]");
		selenium.refresh();
		this.waitForElementPresent("//div[contains(text(),'Project: "+ TaskLable + "')]", WAIT_TIME);
		//the task of this group is extended
		selenium.mouseDown("//div[contains(text(),'Project: "+ TaskLable + "')]");
		assertTrue(selenium.isElementPresent("//div[@class='x-grid3-row  x-unselectable-single ']//span[text()='testTask2']"));
		
	}

}
