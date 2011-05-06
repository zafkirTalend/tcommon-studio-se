package com.talend.tac.cases.notification;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddTasksNotification extends Login {
    
    //add a task'notification(TaskFailedNotification)
	@Test(groups={"AddTaskNotification"}, dependsOnGroups={"AddUserNotification"})
	@Parameters({"categoryTask","eventTaskFailed","descriptionTaskFailed"})
	public void testAddTaskFailedNotification(String categoryTask, String eventTaskFailed, String descriptionTaskFailed) {
		
		addNotification(1, categoryTask, 1, eventTaskFailed, descriptionTaskFailed);	
		
		selenium.click("//i[text()='Suscribe to receive a mail when specified tasks failed']/parent::div/parent::td/parent::tr/" +
				"parent::tbody//div[text()='Recipients: ']/parent::td/parent::tr//button");
		selenium.mouseDown("//div[text()='testMulripleRoles@company.com']/parent::td/preceding-sibling::td");//choose event trigger users
		selenium.click("//button[text()='Apply']");
		selenium.click("//div[text()='Tasks: ']/parent::td/parent::tr//button");
		selenium.mouseDown("//div[text()='testModifyTask']/parent::td/preceding-sibling::td");//choose event trigger task
		selenium.click("//button[text()='Apply']");	
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventTaskFailed+"']/" +
		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));
		selenium.setSpeed(MIN_SPEED);
		
	}

	//add a task'notification(Uncheck Active)
	@Test(dependsOnMethods={"testAddTaskFailedNotification"})
	@Parameters({"categoryTask","eventTaskFailed","descriptionTaskFailed"})
	public void testAddTaskNotificationUncheckActive(String categoryTask, String eventTaskFailed, String descriptionTaskFailed) {
		
		addNotification(1, categoryTask, 1, eventTaskFailed, descriptionTaskFailed);
		selenium.click("idActiveInput");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isChecked("idActiveInput"));
		selenium.setSpeed(MIN_SPEED);
		
		selenium.click("//i[text()='Suscribe to receive a mail when specified tasks failed']/parent::div/parent::td/parent::tr/" +
		"parent::tbody//div[text()='Recipients: ']/parent::td/parent::tr//button");
		
		selenium.mouseDown("//div[text()='testMulripleRoles@company.com']/parent::td/preceding-sibling::td");//choose event trigger users
		selenium.click("//button[text()='Apply']");
		selenium.click("//div[text()='Tasks: ']/parent::td/parent::tr//button");
		selenium.mouseDown("//div[text()='testModifyTask']/parent::td/preceding-sibling::td");//choose event trigger task
		selenium.click("//button[text()='Apply']");	
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventTaskFailed+"']/" +
		"parent::td/parent::tr//img[@class='gwt-Image' and @title='false']"));
		selenium.setSpeed(MIN_SPEED);
	}
	
	public void addNotification(int LabelInput,String LabelInputValue,int DescriptionInput,
			String DescriptionInputValue,String notificationInformation) {
		
		this.clickWaitForElementPresent("!!!menu.notification.element!!!");//into notification
		selenium.click("idSubModuleAddButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		selenium.setSpeed(MIN_SPEED);
		this.selectDropDownList("//input[@id='idLabelInput']", LabelInput);//choose a notification type
		Assert.assertEquals(selenium.getValue("idLabelInput"), LabelInputValue);
		this.selectDropDownList("//input[@id='idDescriptionInput']", DescriptionInput);//choose a event
		String description = selenium.getValue("idDescriptionInput");
		System.out.println(description);
		selenium.setSpeed(MID_SPEED);
		Assert.assertEquals(description , DescriptionInputValue);
		Assert.assertTrue(selenium.isTextPresent(notificationInformation));
	
		
	}
}
