package com.talend.tac.cases.notification;

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTasksNotification extends Login {
    
    //add a task'notification(TaskFailedNotification)
	@Test(groups={"AddTasksNotification"},dependsOnGroups={"AddUsersNotification"})
	public void testAddTasksTaskFailedNotification() {
		
		addNotification(1, "Tasks", 1, "TaskFailedNotification", "Suscribe to receive a mail when a user is created");
		selenium.setSpeed(MAX_SPEED);

		selenium.click("idFormSaveButton");
		selenium.setSpeed(MIN_SPEED);
	}
	//add a task'notification(Uncheck Active)
	@Test(dependsOnMethods={"testAddTasksTaskFailedNotification"})
	public void testAddTasksNotificationUncheckActive() {
		
		addNotification(1, "Tasks", 1, "TaskFailedNotification", "Suscribe to receive a mail when a user is created");

		selenium.click("idActiveInput");
		Assert.assertFalse(selenium.isChecked("idActiveInput"));
		selenium.setSpeed(MAX_SPEED);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MIN_SPEED);
	}
	
	public void addNotification(int LabelInput,String LabelInputValue,int DescriptionInput,
			String DescriptionInputValue,String notificationInformation) {
		
		this.clickWaitForElementPresent("!!!menu.notification.element!!!");//into notification
		selenium.click("idSubModuleAddButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idLabelInput");//choose notification type
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

		selenium.setSpeed(MAX_SPEED);
		selenium.click("//table[@class=' x-btn x-component x-btn-icon']");
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");//choose event trigger users
		selenium.click("//button[text()='Apply']");
//		selenium.click("//tr[2]/td[2]/table/tbody/tr/td/table[@class=' x-btn x-component x-btn-icon ']/tbody/tr[2]/td[2]/em/button");
//		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
//		selenium.click("//button[text()='Apply']");	
	}
}
