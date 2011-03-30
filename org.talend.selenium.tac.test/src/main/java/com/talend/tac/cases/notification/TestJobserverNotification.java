package com.talend.tac.cases.notification;

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestJobserverNotification extends Login {
    
    //add a jobserver'notification(JobServerAlertNotification)
	@Test(groups={"AddJobServersNotification"},dependsOnGroups={"AddTasksNotification"})
	public void testAddJobserversJobServerAlertNotification() {
		
		addNotification(3, "JobServers", 1, "JobServerAlertNotification", "Subscribe to receive a mail when jobserve has changed status");
		selenium.setSpeed(MAX_SPEED);

		selenium.click("idFormSaveButton");
		selenium.setSpeed(MIN_SPEED);
	}
	//add a jobserver'notification(uncheck Active)
	@Test(dependsOnMethods={"testAddJobserversJobServerAlertNotification"})
	public void testAddJobserversNotificationUncheckActive() {
		
		addNotification(3, "JobServers", 1, "JobServerAlertNotification", "Subscribe to receive a mail when jobserve has changed status");

		selenium.click("idActiveInput");//uncheck  'Active'
		Assert.assertFalse(selenium.isChecked("idActiveInput"));
		selenium.setSpeed(MAX_SPEED);
		selenium.click("idFormSaveButton");//clcik 'save'
		selenium.setSpeed(MIN_SPEED);
	}
	
	public void addNotification(int LabelInput,String LabelInputValue,int DescriptionInput,
			String DescriptionInputValue,String notificationInformation) {
		
		this.clickWaitForElementPresent("!!!menu.notification.element!!!");//into notification
		selenium.click("idSubModuleAddButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idLabelInput");//choose a notification type
		selenium.mouseDownAt("//div[@role='listitem']["+LabelInput+"]", ""+KeyEvent.VK_ENTER);
		selenium.setSpeed(MID_SPEED);
		Assert.assertEquals(selenium.getValue("idLabelInput"), LabelInputValue);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idDescriptionInput");//choose a event
		selenium.mouseDownAt("//div[@role='listitem']["+DescriptionInput+"]", ""+KeyEvent.VK_ENTER);
		String description = selenium.getValue("idDescriptionInput");
		System.out.println(description);
		selenium.setSpeed(MID_SPEED);
		Assert.assertEquals(description , DescriptionInputValue);
		Assert.assertTrue(selenium.isTextPresent(notificationInformation));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idLabelInput");
		selenium.mouseDownAt("//div[@role='listitem']["+LabelInput+"]", ""+KeyEvent.VK_ENTER);

		selenium.click("//table[@class=' x-btn x-component x-btn-icon']");//choose event trigger users
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
		selenium.click("//button[text()='Apply']");
//		selenium.click("//tr[2]/td[2]/table/tbody/tr/td/table[@class=' x-btn x-component x-btn-icon ']/tbody/tr[2]/td[2]/em/button");
//		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
//		selenium.click("//button[text()='Apply']");	
	}
}
