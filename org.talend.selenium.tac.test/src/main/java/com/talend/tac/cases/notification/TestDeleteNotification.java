package com.talend.tac.cases.notification;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeleteNotification extends Login {
    
	//delete a notification(cancel Delete Notification)
	@Test(groups={"DeleteNotification"})
//	,dependsOnGroups={"AddJobserverNotification"})
	@Parameters({"eventNewUser"})
	public void testCancelDeleteNotification(String eventNewUser) {
		
		this.clickWaitForElementPresent("!!!menu.notification.element!!!");
		
	  	selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-category']");//click a exist notification
		selenium.chooseCancelOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");//click 'Delete' button
		selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected notification [\\s\\S]$"));
        selenium.setSpeed(MIN_SPEED);
	    selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventNewUser+"']/" +
		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));		
		selenium.setSpeed(MIN_SPEED);
		
	}
	//delete a notification(Delete Notification)
	@Test(dependsOnMethods={"testCancelDeleteNotification"})
	@Parameters({"eventNewUser"})
	public void testDeleteNotification(String eventNewUser) {
		
		this.clickWaitForElementPresent("!!!menu.notification.element!!!");//into notification
		selenium.setSpeed(MAX_SPEED);
				
    	selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-category']");//choose a notification type
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");//click 'Delete' button
		selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected notification [\\s\\S]$"));
        selenium.setSpeed(MIN_SPEED);
	 
        selenium.setSpeed(MID_SPEED);
        Assert.assertFalse(selenium.isElementPresent("//div[text()='"+eventNewUser+"']/" +
		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));		
		selenium.setSpeed(MIN_SPEED);
		
	}
	
}
