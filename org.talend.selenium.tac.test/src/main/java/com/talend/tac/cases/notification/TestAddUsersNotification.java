package com.talend.tac.cases.notification;

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddUsersNotification extends Login {
    
	 @Test(groups={"AddUserNotification"})
	 public void clearAllNotifications() {
    	 
         this.clickWaitForElementPresent("!!!menu.notification.element!!!");	
    
    	 for(int i=0;;i++) {
    		    if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-category']")) {
    		    	selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-category']");
      				selenium.chooseOkOnNextConfirmation();
      				selenium.click("idSubModuleDeleteButton");
      				selenium.setSpeed(MID_SPEED);
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
	@Parameters({"categoryUser","eventNewUser","descriptionNewUser"})
	public void testAddUsersMailNewUserNotification(String categoryUser, String eventNewUser, 
			String descriptionNewUser) {
		
		addNotification(2, categoryUser, 1, eventNewUser, descriptionNewUser);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventNewUser+"']/" +
        		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));		
 		selenium.setSpeed(MIN_SPEED);
	}
	//add a user notification(UserCreationNotification)
	@Test(dependsOnMethods={"testAddUsersMailNewUserNotification"})
	@Parameters({"categoryUser","eventUserCreation","descriptionUserCreation"})
	public void testAddUsersUserCreationNotification(String categoryUser, String eventUserCreation,
			String descriptionUserCreation) {
		
		addNotification(2, categoryUser, 2,  eventUserCreation, descriptionUserCreation);
		selenium.setSpeed(MID_SPEED);
		selenium.click("//i[text()='Suscribe to receive a mail when a user is created']/parent::div/parent::td/parent::tr/parent::tbody" +
				"//div[text()='Recipients: ']/parent::td/parent::tr//button");
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");//choose event trigger users
		selenium.click("//button[text()='Apply']");
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventUserCreation+"']/" +
        		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));		
 		selenium.setSpeed(MIN_SPEED);
 		
	}
	//add a user notification(UserDeletionNotification)
	@Test(dependsOnMethods={"testAddUsersUserCreationNotification"})
	@Parameters({"categoryUser","eventUserDeletion","descriptionUserDeletion"})
	public void testAddUsersUserDeletionNotification(String categoryUser, String eventUserDeletion,
			String descriptionUserDeletion) {
		
		addNotification(2, categoryUser, 3, eventUserDeletion, descriptionUserDeletion);
		selenium.setSpeed(MID_SPEED);
		selenium.click("//i[text()='Subscribe to receive a mail when a user is deleted']/parent::div/parent::td/parent::tr" +
				"/parent::tbody//button");
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");//choose event trigger users
		selenium.click("//button[text()='Apply']");
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventUserDeletion+"']/" +
        		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));		
 		selenium.setSpeed(MIN_SPEED);
 		
	}
	//add a user notification(Uncheck Active)
	@Test(dependsOnMethods={"testAddUsersUserDeletionNotification"})
	@Parameters({"categoryUser","eventNewUser","descriptionNewUser"})
	public void testAddUsersNotificationUncheckActive(String categoryUser, String eventNewUser, 
			String descriptionNewUser) {
		
		addNotification(2, categoryUser, 1, eventNewUser, descriptionNewUser);
		selenium.click("idActiveInput");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isChecked("idActiveInput"));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventNewUser+"']/" +
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
		selenium.click("idLabelInput");//choose a notification type
		selenium.mouseDownAt("//div[@role='listitem']["+LabelInput+"]", ""+KeyEvent.VK_ENTER);
		Assert.assertEquals(selenium.getValue("idLabelInput"), LabelInputValue);
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

		
		
	}
}
