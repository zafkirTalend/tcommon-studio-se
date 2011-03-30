package com.talend.tac.cases.notification;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeleteNotification extends Login {
    
	//delete a notification(cancel Delete Notification)
	@Test(dependsOnGroups={"AddJobServersNotification"})
	public void testCancelDeleteNotification() {
		
		this.clickWaitForElementPresent("!!!menu.notification.element!!!");
		selenium.setSpeed(MAX_SPEED);
		
		if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-category']")) {
	    	selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-category']");//click a exist notification
			selenium.chooseCancelOnNextConfirmation();
			selenium.click("idSubModuleDeleteButton");//click 'Delete' button
			selenium.setSpeed(MAX_SPEED);
		    Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected notification [\\s\\S]$"));
	        selenium.setSpeed(MIN_SPEED);
		 
	    } else {
	    	
	    	System.out.println("element not exist");

	    }
		
	}
	//delete a notification(Delete Notification)
	@Test(dependsOnMethods={"testCancelDeleteNotification"})
	public void testDeleteNotification() {
		
		this.clickWaitForElementPresent("!!!menu.notification.element!!!");//into notification
		selenium.setSpeed(MAX_SPEED);
		
		if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-category']")) {
	    	selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-category']");//choose a notification type
			selenium.chooseOkOnNextConfirmation();
			selenium.click("idSubModuleDeleteButton");//click 'Delete' button
			selenium.setSpeed(MAX_SPEED);
		    Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected notification [\\s\\S]$"));
	        selenium.setSpeed(MIN_SPEED);
		 
	    } else {
	    	
	    	System.out.println("element not exist");

	    }
		
	}
	
}
