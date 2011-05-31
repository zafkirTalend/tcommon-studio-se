package com.talend.tac.cases.executePlan;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class Plan extends Login {

public void deleteTrigger(String fileTriggerLabel){
	this.waitForElementPresent("//span[text()='" + fileTriggerLabel + "']", WAIT_TIME);
	selenium.mouseDown("//span[text()='" + fileTriggerLabel + "']");
	selenium.chooseOkOnNextConfirmation();
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	selenium.click("idTriggerDelete");
	selenium.getConfirmation();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Assert.assertFalse(
			selenium.isElementPresent("//span[text()='" + fileTriggerLabel
					+ "']"), "trigger delete failed!");
}

}
