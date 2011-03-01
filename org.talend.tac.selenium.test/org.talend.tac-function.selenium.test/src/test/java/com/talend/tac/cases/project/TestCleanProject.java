package com.talend.tac.cases.project;

import junit.framework.Assert;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestCleanProject extends Login{
	public void okDelete() {
		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label']", 60);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label']");
		selenium.chooseOkOnNextConfirmation();
		
		System.out.println(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-label']"));
		
		selenium.click("idSubModuleDeleteButton");
		assert (selenium.getConfirmation()
				.matches(other.getString("delete.project.warning")));

	}
  @Test(dependsOnGroups={"Second"})
  @Parameters({ "NumbersOfprojects" })
  public void deleteAll(String numOfprojectsTodelete) {
	  selenium.setSpeed("3000");
	  int n = 0;
	  try{
	  n = Integer.parseInt(numOfprojectsTodelete);
	  }catch(Exception e){
		  Assert.fail("�������");
	  }
	  for(int i = 0; i < n; i++){
		
		okDelete();
	  }
	  selenium.setSpeed("0");
  }
}
