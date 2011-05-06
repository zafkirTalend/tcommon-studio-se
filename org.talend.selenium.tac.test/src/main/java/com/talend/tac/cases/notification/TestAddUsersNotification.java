package com.talend.tac.cases.notification;

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
		selenium.click("//i[text()='"+descriptionUserCreation+"']/parent::div/parent::td/parent::tr/parent::tbody" +
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
		selenium.click("//i[text()='"+descriptionUserDeletion+"']/parent::div/parent::td/parent::tr" +
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
	@Test(dependsOnMethods={"testAddUsersUserCreationNotification"})
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
	
/**add a  user notificaton of user 'jackzhang@gmail.com', then into user page and 
	delete 'jackzhang@gmail.com', return notification page and check corresponding
	 notification is deleted **/
//	@Test
//	(dependsOnMethods={"testAddUsersNotificationUncheckActive"})
	@Parameters({"categoryUser","eventUserCreation","descriptionUserCreation","LoginName"})
	public void testAaddUserNotificationOfLoginUser(String categoryUser, String eventUserCreation, 
			String descriptionUserCreation, String loginName) {
		
		addNotification(2, categoryUser, 2, eventUserCreation, descriptionUserCreation);
		selenium.click("idActiveInput");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isChecked("idActiveInput"));
		selenium.setSpeed(MID_SPEED);
		selenium.click("//i[text()='Suscribe to receive a mail when a user is created']/parent::div/parent::td/parent::tr" +
				"/parent::tbody//button");
		selenium.setSpeed(MID_SPEED);
		selenium.mouseDown("//div[text()='jackzhang@gamil.com']/parent::td/preceding-sibling::td");//choose event trigger users
		selenium.setSpeed(MIN_SPEED);
		selenium.click("//button[text()='Apply']");
		Assert.assertTrue(selenium.isElementPresent("//i[text()=' - jackzhang@gamil.com']"));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventUserCreation+"']/" +
        		"parent::td/parent::tr//img[@class='gwt-Image' and @title='false']"));		
 		selenium.setSpeed(MIN_SPEED);
 		this.clickWaitForElementPresent("idMenuUserElement");
 		selenium.setSpeed(MID_SPEED);
		selenium.mouseDown("//div[text()='"+loginName+"']");//Select an existing user
		selenium.setSpeed(MIN_SPEED);
		selenium.chooseOkOnNextConfirmation();
		selenium.mouseDown("//div[text()='Users']/ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idSubModuleDeleteButton']");//delete a user 
		selenium.mouseUp("//div[text()='Users']/ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idSubModuleDeleteButton']");//delete a user 
		selenium.click("//button[@id='idSubModuleDeleteButton']");//delete a user 
		selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected user [\\s\\S]$"));
	    selenium.setSpeed(MIN_SPEED);
	    this.clickWaitForElementPresent("!!!menu.notification.element!!!");//into notification
 		selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(!selenium.isElementPresent("//div[text()='"+eventUserCreation+"']/" +
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
        selenium.setSpeed(MIN_SPEED);
        this.selectDropDownList("//input[@id='idLabelInput']", LabelInput);//choose a notification type

		
		
	}
}
