package com.talend.tac.cases.bussinessModeler;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.talend.tac.cases.Login;

public class TestBusinessModeler extends Login {
	 
	@Test
	@Parameters( { "AddcommonProjectname","branches" ,"bussinesModel.name","Mysql_Connectionlabel"})
	public void testBusinessModeler(String project,String branches,String modelName,String connectionName) {
		this.clickWaitForElementPresent("!!!menu.businessModeler.element!!!");
		//select
		this.selectDropDownList("idProjectListBox", project);
		this.selectDropDownList("idBranchListBox", branches);
		this.selectDropDownList("idBusinessListBox", modelName); 
		this.selectDropDownList("idBusinessConnectionsListBox", connectionName);
		//load 
		selenium.click("//button[text()='load']");
		assertEquals(selenium.getValue("idProjectListBox"),project);
		assertEquals(selenium.getValue("idBranchListBox"),branches);
//		assertEquals(selenium.getValue("idBusinessListBox"),modelName);
		assertEquals(selenium.getValue("idBusinessConnectionsListBox"),connectionName);
		//edit ?
		//save
		selenium.click("//button[text()='save']");
		selenium.getAlert();
		//close
		selenium.click("//button[text()='close']");
		assertEquals(selenium.getValue("idProjectListBox"),"Select a Project");
		assertEquals(selenium.getValue("idBranchListBox"),"Select a Branch");
		assertEquals(selenium.getValue("idBusinessListBox"),"Select a Business model");
		assertEquals(selenium.getValue("idBusinessConnectionsListBox"),"");
	}

	@Override
	public void selectDropDownList(String id, String itemName) {
		// TODO Auto-generated method stub
		selenium.click("//input[@id='"+id+"']"
				+ "/following-sibling::div");
		this.waitForElementPresent("//div[contains(text(),'" + itemName + "')]", WAIT_TIME);
		selenium.mouseDown("//div[contains(text(),'" + itemName + "')]");
		selenium.fireEvent("//input[@id='"+id+"']", "blur");
	}
	
}
