package com.talend.tac.cases.notification;

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddJobserverNotification extends Login {
	
    //add a jobserver'notification(JobServerAlertNotification)
	@Test(groups={"AddJobserverNotification"}, dependsOnGroups={"AddUserNotification"})
	@Parameters({"categoryJobServer","eventJobServerAlert","descriptionJobServerAlert"})
	public void testAddJobserversJobServerAlertNotification(String categoryJobServer, String eventJobServerAlert,
			String descriptionJobServerAlert) {
		
		addNotification(3, categoryJobServer, 1, eventJobServerAlert, descriptionJobServerAlert);
	
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventJobServerAlert+"']/" +
		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));
		selenium.setSpeed(MIN_SPEED);
	}
	//add a jobserver'notification(uncheck Active)
	@Test(dependsOnMethods={"testAddJobserversJobServerAlertNotification"})
	@Parameters({"categoryJobServer","eventJobServerAlert","descriptionJobServerAlert"})
	public void testAddJobserversNotificationUncheckActive(String categoryJobServer, String eventJobServerAlert,
			String descriptionJobServerAlert) {
		
		addNotification(3, categoryJobServer, 1, eventJobServerAlert, descriptionJobServerAlert);

		selenium.click("idActiveInput");//uncheck  'Active'
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isChecked("idActiveInput"));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idFormSaveButton");//clcik 'save'
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventJobServerAlert+"']/" +
		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));
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

		selenium.click("//i[text()='Subscribe to receive a mail when jobserve has changed status']/parent::div/parent::td" +
				"/parent::tr/parent::tbody//div[text()='Recipients: ']/parent::td/parent::tr//button");//choose event trigger users
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
		selenium.click("//button[text()='Apply']");
		selenium.click("//div[text()='jobServers: ']/parent::td/parent::tr//button");
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
		selenium.click("//button[text()='Apply']");	
	}
}
