package com.talend.tac.cases.bussinessModeler;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.talend.tac.cases.Login;

public class TestBusinessModeler extends Login {
	
	@Test(enabled=false,description="disable this case now, there are same ids")
	@Parameters( { "AddcommonProjectname","branches" ,"bussinesModel.name","Mysqlconnection"})
	public void testBusinessModeler(String project,String branches,String modelName,String connectionName) {
		this.clickWaitForElementPresent("!!!menu.businessModeler.element!!!");
		this.selectDropDownList("idProjectListBox", project);
		this.selectDropDownList("idBranchListBox", branches);
//		this.selectDropDownList("idBusinessListBox", modelName); id same
//		this.selectDropDownList("idBusinessListBox", connectionName); id same
		selenium.click("//button[text()='load");
		selenium.click("//button[text()='save");
		selenium.getAlert();
		selenium.click("//button[text()='close'");
	}
	
}
