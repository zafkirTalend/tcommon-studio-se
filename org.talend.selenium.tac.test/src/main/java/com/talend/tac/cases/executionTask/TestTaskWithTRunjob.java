package com.talend.tac.cases.executionTask;

//import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestTaskWithTRunjob extends Login {
	@Test
	@Parameters({ "TaskWithtRunjob" })
	public void testTrunjob(String tasklabel) throws InterruptedException {
//		String tasklabel = "testTask";
		this.clickWaitForElementPresent("!!!menu.executionTasks.element!!!");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"
				+ rb.getString("menu.jobConductor") + "']"));
		// select a exist task
		selenium.mouseDown("//span[text()='"+tasklabel+"']");
		boolean ok= runtask(tasklabel);
		if(ok){
		Assert.assertTrue(getLogsValue().contains("23"),
				"default context test failed");
		}
		else{
			Assert.assertTrue(getLogsValue().contains("Exception"),
			"task run failed with exception");
			Assert.fail("task TRunjob run failed!");
		}

	}

	public boolean runtask(String tasklabel) throws InterruptedException {
		selenium.refresh();
		this.waitForElementPresent("//span[text()='" + tasklabel + "']",
				WAIT_TIME);
		selenium.mouseDown("//span[text()='" + tasklabel + "']");
		Thread.sleep(3000);
		selenium.click("//button[@id='idJobConductorTaskRunButton()'  and @class='x-btn-text ' and text()='Run']");
//		Date start = new Date();
		boolean success = (waitForCondition("//label[text()='Ok']", 30));
		// close the pop window
		selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
		// System.out.println(checkContextValue(start));
        return success;
	}
	public boolean waitForCondition(String locator,int seconds) throws InterruptedException{
		boolean conditionPresent = true;
		for (int second = 0;; second++) {
			if (second >= seconds){
				conditionPresent = false;
				break;
			}
			
				if (selenium.isElementPresent(locator)){
					break;
				}
				else{
				Thread.sleep(1000);
			    } 
		}
		
		return conditionPresent;
	}

	public String getLogsValue() {
		String logs = null;
		selenium.click("//span//span[text()='Logs']");
		selenium.setSpeed(MID_SPEED);
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-startDate x-component sort-desc ']//a[@class='x-grid3-hd-btn']");
		selenium.setSpeed(MIN_SPEED);
		selenium.click("//a[@class=' x-menu-item x-component' and text()='Sort Descending']");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-startDate']",
				WAIT_TIME);
//		String time = selenium
//				.getText("//div[@class='x-grid3-cell-inner x-grid3-col-startDate']");
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-startDate']");
		selenium.setSpeed(MID_SPEED);
		logs = selenium
				.getValue("//table[@class=' x-btn x-form-file-btn x-component x-btn-text-icon']/ancestor::div[@class='x-form-item ']//textarea");
		selenium.setSpeed(MIN_SPEED);
		System.out.println(logs);
		return logs;
	}

	public void checkBoxChecked(String checkboxXpath) {
		selenium.mouseDown(checkboxXpath);
	}

	public void checkBoxUnchecked(String checkboxXpath) {
		selenium.mouseDown(checkboxXpath);

	}

	public boolean isCheckBoxChecked(String checkBoxOnXpath) {
		boolean checked = false;
		try {
			if (selenium.isElementPresent(checkBoxOnXpath))
				checked = true;
		} catch (Exception e) {
		}
		return checked;
	}
}
