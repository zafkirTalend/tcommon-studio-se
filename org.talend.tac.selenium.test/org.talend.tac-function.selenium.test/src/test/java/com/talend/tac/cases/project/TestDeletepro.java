package com.talend.tac.cases.project;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;
import com.thoughtworks.selenium.Selenium;

public class TestDeletepro extends Login {

	public void cancelDelete(String name) {
		selenium.click("!!!menu.project.element!!!");
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+name+"')]", 60);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+name+"')]");
		selenium.chooseCancelOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		assert (selenium.getConfirmation()
				.matches(other.getString("delete.project.warning")));
		// selenium.setSpeed("3000");
	}

	public void okDelete(String name) {
		selenium.click("!!!menu.project.element!!!");
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+name+"')]", 60);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+name+"')]");
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		assert (selenium.getConfirmation()
				.matches(other.getString("delete.project.warning")));

	}

	@Test(groups = { "Second" },dependsOnGroups={"Add"})
	@Parameters({ "DeleteProjectname" })
	public void testDeletepro(String deleteProname) throws Exception {
		  if(selenium == null){
			  System.out.println("selenium Ϊ��!");
		  }
		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		cancelDelete(deleteProname);
		okDelete(deleteProname);
		Assert.assertFalse(!selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+deleteProname+"')]"), "Project delete failed!");
	}

	@AfterMethod
	public void tearDown() throws Exception {
//		selenium.stop();
	}

}
