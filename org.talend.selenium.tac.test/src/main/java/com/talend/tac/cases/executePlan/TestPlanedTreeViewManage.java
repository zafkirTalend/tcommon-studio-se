package com.talend.tac.cases.executePlan;

import java.awt.event.KeyEvent;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestPlanedTreeViewManage extends Plan{
  @Test
  @Parameters({ "treeviewmanage.plan.label", "treeviewmanage.plan.roottask", "treeviewmanage.plan.taskok", "treeviewmanage.plan.taskerror","treeviewmanage.plan.taskafter" })
  public void testTreeManage(String planLabel,String rootTask,String taskOk,String taskError,String taskAfter) {
	  this.addPlan(planLabel, rootTask, "treeManagePlan");
	  selenium.mouseDown("//span[text()='"
				+ planLabel + "']");
	  this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='"+rootTask+"']", WAIT_TIME);
	  this.addOnOK(rootTask, "ok");
	  this.addOnError(rootTask, "error");
	  
	  
  }
  public void addOnOK(String okTask,String taskToadd){
	  this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='"+okTask+"']", WAIT_TIME);
	  this.sleep(2000);
	  selenium.mouseDown("//span[@class='x-tree3-node-text' and text()='"+okTask+"']");
	  this.sleep(2000);
//	  selenium.setSpeed(MID_SPEED);
	  selenium.click("idExecutionPlanTreeViewOnOkButton");
	  this.waitForElementPresent("//span[@class='x-fieldset-header-text' and text()='Add OnOk for planned task']", WAIT_TIME);
	  this.selectDropDownList("idExecutionPlanTreeFormTaskComboBox", taskToadd);
//      selenium.keyPressNative(""+KeyEvent.VK_TAB);
//      selenium.keyDownNative(""+KeyEvent.VK_ENTER);
	  selenium.click("//div[@class='header-title' and text()='Execution Plan']//ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//span[@class='x-fieldset-header-text' and text()='Add OnOk for planned task']/ancestor::form[@class=' x-form-label-left']//button[text()='Save']");

	  this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='OnOk -> "+taskToadd+"']", WAIT_TIME);
	  selenium.setSpeed(MIN_SPEED);
  }
  public void addOnError(String errorTask,String taskToadd){
	  this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='"+errorTask+"']", WAIT_TIME);
	  this.sleep(2000);
	  selenium.mouseDown("//span[@class='x-tree3-node-text' and text()='"+errorTask+"']");
	  this.sleep(2000);
	  selenium.click("idExecutionPlanTreeViewOnErrorButton");
	  this.waitForElementPresent("//span[@class='x-fieldset-header-text' and text()='Add OnError for planned task']", WAIT_TIME);
	  this.selectDropDownList("idExecutionPlanTreeFormTaskComboBox", taskToadd);
	  selenium.click("//span[@class='x-fieldset-header-text' and text()='Add OnError for planned task']/ancestor::form[@class=' x-form-label-left']//button[text()='Save']");
	  this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='OnError -> "+taskToadd+"']", WAIT_TIME);
	  
  }
  public void addAfter(String finishedTaskXpath,String followTask){
	  this.waitForElementPresent(finishedTaskXpath, WAIT_TIME);
	  selenium.mouseDown(finishedTaskXpath);
	  selenium.click("idExecutionPlanTreeViewAfterButton");
	  this.selectDropDownList("idExecutionPlanTreeFormTaskComboBox", followTask);
	  selenium.click("//span[@class='x-fieldset-header-text' and text()='Add After for planned task']/ancestor::form[@class=' x-form-label-left']//button[text()='Save']");
	  this.waitForElementPresent("//span[@class='x-tree3-node-text' and text()='After -> "+followTask+"']", WAIT_TIME);
  }
}
