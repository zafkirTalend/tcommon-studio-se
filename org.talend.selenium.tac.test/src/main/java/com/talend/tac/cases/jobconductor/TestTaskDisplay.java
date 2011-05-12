package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import com.talend.tac.cases.Login;

public class TestTaskDisplay extends Login {
	@Test(description="")
	@Parameters({"labelReferenceproTjava"})
	public void testTaskDisplayGroupedByProject(String TaskLable){
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		
		this.waitForElementPresent("//div[contains(text(),'Project: referencepro')]", WAIT_TIME);
		//click the group of task
		this.waitForElementPresent("//span[text()='"+TaskLable+"']", WAIT_TIME);
		//make a task selected
		selenium.mouseDown("//span[text()='"+TaskLable+"']");
		//collapsed the group and assert the display(by attribute) 
		assertFalse(selenium.isElementPresent("//div[@class='x-grid3-row  x-unselectable-single ']//span[text()='"+TaskLable+"']"));
		selenium.mouseDown("//div[contains(text(),'Project: referencepro')]");
		selenium.refresh();
		this.waitForElementPresent("//div[contains(text(),'Project: referencepro')]", WAIT_TIME);
		//expand the group
		selenium.mouseDown("//div[contains(text(),'Project: referencepro')]");
		assertTrue(selenium.isElementPresent("//div[@class='x-grid3-row  x-unselectable-single ']//span[text()='"+TaskLable+"']"));
		
	}

	@Test(description="input a key work and search task")
	@Parameters({"labelReferenceproTjava"})
	public void testSearchTask (String TaskLable){
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		
		this.waitForElementPresent("//div[contains(text(),'Project: referencepro')]", WAIT_TIME);
		//click the group of task
		this.waitForElementPresent("//span[text()='"+TaskLable+"']", WAIT_TIME);
		//wait and input a keyWord
		this.waitForElementPresent("//button[text()='Filter tasks']/ancestor::td/following-sibling::td//input", WAIT_TIME);
		selenium.typeKeys("//button[text()='Filter tasks']/ancestor::td/following-sibling::td//input","input a keyword");
		//setSpeed,wait the tasks disappears and make sure the assertFalse works well.
		selenium.setSpeed(MID_SPEED);
		assertFalse(selenium.isElementPresent("//span[text()='"+TaskLable+"']"));
		selenium.setSpeed(MIN_SPEED);
		//clear the keyWrods
		selenium.click("//button[text()='Filter tasks']/ancestor::td/following-sibling::td//input/following-sibling::div");
		assertTrue(selenium.isElementPresent("//span[text()='"+TaskLable+"']"));
		//type a the name of task as a keyword
		selenium.typeKeys("//button[text()='Filter tasks']/ancestor::td/following-sibling::td//input",TaskLable);
		assertTrue(selenium.isElementPresent("//span[text()='"+TaskLable+"']"));
	}
	
	@Test(description="change the statistic status of task, this is not an urgent case ")
	@Parameters({"labelReferenceproTjava"})
	public void testTaskChangeStatisticStatus (String TaskLable){
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		this.waitForElementPresent("//span[text()='"+TaskLable+"']", WAIT_TIME);
		selenium.mouseDown("//span[text()='"+TaskLable+"']");
		//change the status of statistic 
		this.selectDropDownList("idJobConductorTaskStatisticsListBox()", "disabled");
		assertEquals(selenium.getValue("idJobConductorTaskStatisticsListBox()"), "disabled");
		this.selectDropDownList("idJobConductorTaskStatisticsListBox()", "enabled");
		assertEquals(selenium.getValue("idJobConductorTaskStatisticsListBox()"), "enabled");
	}
	
}
