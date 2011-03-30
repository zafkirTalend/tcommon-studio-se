package com.talend.tac.cases.notification;

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddUsersNotification extends Login {
    
	 @Test(groups={"AddUsersNotification"})
	 public void clearAllNotifications() {
    	 
         this.clickWaitForElementPresent("!!!menu.notification.element!!!");	
    
    	 for(int i=0;;i++) {
    		    if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-category']")) {
    		    	selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-category']");
      				selenium.chooseOkOnNextConfirmation();
      				selenium.click("idSubModuleDeleteButton");
      				selenium.setSpeed(MAX_SPEED);
      			    Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected notification [\\s\\S]$"));
        		    selenium.setSpeed(MIN_SPEED);
        		 
    		    } else {
    		    	
    		    	System.out.println("element not exist");
    		    	break;
    		    }
    			
    	 }
    }
	//add a user notification(MailNewUserNotification)
	@Test(dependsOnMethods={"clearAllNotifications"})
	public void testAddUsersMailNewUserNotification() {
		
		addNotification(2, "Users", 1, "MailNewUserNotification", "Send a mail when to a new user at creation");
		selenium.click("idFormSaveButton");
	}
	//add a user notification(UserCreationNotification)
	@Test(dependsOnMethods={"testAddUsersMailNewUserNotification"})
	public void testAddUsersUserCreationNotification() {
		
		addNotification(2, "Users", 2, "UserCreationNotification", "Suscribe to receive a mail when a user is created");
		selenium.setSpeed(MAX_SPEED);
		selenium.click("//table[@class=' x-btn x-component x-btn-icon']");
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");//choose event trigger users
		selenium.click("//button[text()='Apply']");
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MIN_SPEED);
	}
	//add a user notification(UserDeletionNotification)
	@Test(dependsOnMethods={"testAddUsersUserCreationNotification"})
	public void testAddUsersUserDeletionNotification() {
		
		addNotification(2, "Users", 3, "UserDeletionNotification", "Subscribe to receive a mail when a user is deleted");
		selenium.setSpeed(MAX_SPEED);
		selenium.click("//table[@class=' x-btn x-component x-btn-icon']");
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");//choose event trigger users
		selenium.click("//button[text()='Apply']");
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MIN_SPEED);
	}
	//add a user notification(Uncheck Active)
	@Test(dependsOnMethods={"testAddUsersUserDeletionNotification"})
	public void testAddUsersNotificationUncheckActive() {
		
		addNotification(2, "Users", 1, "MailNewUserNotification", "Send a mail when to a new user at creation");
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
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idLabelInput");//choose a notification type
		selenium.mouseDownAt("//div[@role='listitem']["+LabelInput+"]", ""+KeyEvent.VK_ENTER);
		Assert.assertEquals(selenium.getValue("idLabelInput"), LabelInputValue);
		selenium.click("idDescriptionInput");//choose a event
		selenium.mouseDownAt("//div[@role='listitem']["+DescriptionInput+"]", ""+KeyEvent.VK_ENTER);
		String description = selenium.getValue("idDescriptionInput");
		System.out.println(description);
		Assert.assertEquals(description , DescriptionInputValue);
		Assert.assertTrue(selenium.isTextPresent(notificationInformation));

		selenium.click("idLabelInput");
		selenium.mouseDownAt("//div[@role='listitem']["+LabelInput+"]", ""+KeyEvent.VK_ENTER);

		
		
	}
}
