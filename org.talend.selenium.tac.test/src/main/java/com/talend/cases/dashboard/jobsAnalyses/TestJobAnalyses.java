package com.talend.cases.dashboard.jobsAnalyses;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.awt.Event;

import com.talend.tac.cases.Login;

public class TestJobAnalyses extends Login {
	@Test
	public void testJobAnalyses() {
		this.clickWaitForElementPresent("!!!menu.dashjobs.element!!!");
		//wait and select the first "connection"
		this.clickWaitForElementPresent("//label[text()='Active connection:']/following-sibling::div//input");
		this.waitForElementPresent("//div[contains(@class,'x-combo-list-item ')][1]", WAIT_TIME);
		selenium.mouseDown("//div[contains(@class,'x-combo-list-item ')][1]");
		this.waitForElementPresent("//img[@title='Ok']", WAIT_TIME);
		//select a connection and simulate a click
		selenium.mouseDown("//img[@title='Ok']");
		//not less than one "Ok"s are displayed
		assertTrue(selenium.getXpathCount("//img[@title='Ok']").intValue() > 1);
		//System.out.println(selenium.getXpathCount("//img[@title='Ok']"));
	}
}