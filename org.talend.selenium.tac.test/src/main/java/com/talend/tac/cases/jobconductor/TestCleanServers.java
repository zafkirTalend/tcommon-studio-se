package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestCleanServers extends Login{
  @Test(groups = { "CleanServer" },dependsOnGroups = { "DeleteServer" })
  public void cleanServers() {
	  selenium.setSpeed(MAX_SPEED);
	  selenium.click("!!!menu.executionServers.element!!!");
	  int n = (Integer)(selenium.getXpathCount("//td[text()='Used CPU']"));
	  for(int i = 0; i < n; i++){
		  selenium.refresh();//in order to light on the delete button
		  selenium.chooseOkOnNextConfirmation();
		  selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label']");
		  selenium.click("idSubModuleDeleteButton");
		  selenium.getConfirmation();
	  }
  }
}
