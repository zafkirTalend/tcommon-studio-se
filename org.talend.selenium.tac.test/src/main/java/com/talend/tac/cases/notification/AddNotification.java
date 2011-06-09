package com.talend.tac.cases.notification;

import java.awt.event.KeyEvent;

import org.testng.Assert;

import com.talend.tac.cases.Login;

public class AddNotification extends Login {
  
	//add method for add user notification
	public void addUserNotification(int LabelInput,String LabelInputValue,int DescriptionInput,
			String DescriptionInputValue,String notificationInformation,String LoginUser) {
		
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
        selenium.setSpeed(MIN_SPEED);
        this.selectDropDownList("//input[@id='idLabelInput']", LabelInput);//choose a notification type
        
        if(selenium.isElementPresent("//i[text()='"+notificationInformation+"']//ancestor::tbody//" +
				"button[@id='idNotificationRepUserButton']")) {
        	
        	selenium.click("//i[text()='"+notificationInformation+"']//ancestor::tbody//" +
				"button[@id='idNotificationRepUserButton']");
			selenium.setSpeed(MID_SPEED);
			selenium.mouseDown("//div[text()='"+LoginUser+"']/parent::td/preceding-sibling::td");//choose event trigger users
			selenium.setSpeed(MIN_SPEED);
			selenium.click("//button[text()='Apply']");
			Assert.assertTrue(selenium.isElementPresent("//i[text()=' - "+LoginUser+"']"));
			selenium.setSpeed(MIN_SPEED);
    		
        }
        
	}
	
	//add method for add task notification
	public void addTaskNotification(int LabelInput,String LabelInputValue,int DescriptionInput,
			String DescriptionInputValue,String notificationInformation,String taskName) {
		
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
	
		selenium.click("//i[text()='"+notificationInformation+"']//ancestor::tbody//" +
				"button[@id='idNotificationRepUserButton']");
		selenium.mouseDown("//div[text()='testMulripleRoles@company.com']/parent::td/preceding-sibling::td");//choose event trigger users
		selenium.click("//button[text()='Apply']");
		selenium.click("idNotificationTaskButton");
		selenium.mouseDown("//div[text()='"+taskName+"']/parent::td/preceding-sibling::td");//choose event trigger task
		selenium.click("//button[text()='Apply']");	
	}

	//add method for add task notification
	public void addJobServerNotification(int LabelInput,String LabelInputValue,int DescriptionInput,
			String DescriptionInputValue,String notificationInformation,String jobServer) {
		
		this.clickWaitForElementPresent("!!!menu.notification.element!!!");//into notification
		selenium.click("idSubModuleAddButton");//click add button
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
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idLabelInput");
		selenium.mouseDownAt("//div[@role='listitem']["+LabelInput+"]", ""+KeyEvent.VK_ENTER);
		
		selenium.click("//i[text()='"+notificationInformation+"']//ancestor::tbody//" +
		"button[@id='idNotificationRepUserButton']");//choose event trigger users
		selenium.mouseDown("//div[text()='testChooseTypeDataQuality@126.com']/parent::td/preceding-sibling::td");//choose event trigger users
		selenium.click("//button[text()='Apply']");
		selenium.click("idNotificationJobserverButton");
		selenium.mouseDown("//div[text()='"+jobServer+"']/parent::td/preceding-sibling::td");//choose event trigger jobServer
		selenium.click("//button[text()='Apply']");	


	}
	
}
