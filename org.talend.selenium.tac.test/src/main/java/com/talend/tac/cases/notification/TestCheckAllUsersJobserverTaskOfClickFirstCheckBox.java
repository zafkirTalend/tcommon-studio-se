package com.talend.tac.cases.notification;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestCheckAllUsersJobserverTaskOfClickFirstCheckBox extends 
CheckAllUsersJobserverTaskOfClickFirstCheckBox {
    
	//test select all users of click first checkbox
	@Test
	@Parameters({"categoryUser","eventUserDeletion","descriptionUserDeletion","userName",
		"LoginNameChooseMulripleRoles"})
	public void testCheckAllUsersOfClickFirstCheckBox(String categoryUser, String eventUserDeletion,
			String descriptionUserDeletion, String userName, String userNameMulripleRoles) throws InterruptedException {
		
		checkAllUsersJobserverTaskOfClickFirstCheckBox(2, categoryUser, 3, 
				eventUserDeletion, descriptionUserDeletion, "idNotificationRepUserButton");
		
		Thread.sleep(3000);
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- admin@company.com')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- userWithCvsFile@talend.com')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- testMulripleRoles@company.com')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- account@company.com')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- jackzhang@gamil.com')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- testChooseTypeDataQuality@126.com')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- Copy_of_testUserUncheckActive@gmail.com')]"));		
		 		
	}
	
	//test select all tasks of click first checkbox
	@Test
	@Parameters({"categoryTask","eventTaskFailed","descriptionTaskFailed",
		"labelAddJVMParametersForTask", "TaskBaseBranch"})
	public void testCheckAllTasksOfClickFirstCheckBox(String categoryTask, String eventTaskFailed,
			String descriptionTaskFailed, String tasklabel, String tasklabelOfBranch) throws InterruptedException {
		
		checkAllUsersJobserverTaskOfClickFirstCheckBox(1, categoryTask, 1, 
				eventTaskFailed, descriptionTaskFailed, "idNotificationTaskButton");
		
		Thread.sleep(3000);
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- BranchTask')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- testAddsimpleTask')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- testModifyTask')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- testTaskRunTRunJob')]"));
 		
	}
	
	
	//test select all jobservers of click first checkbox
	@Test
	@Parameters({"categoryJobServer","eventJobServerAlert","descriptionJobServerAlert",
		"ServerForUseUnavailable","ServerForUseAvailable"})
	public void testCheckAllJobServersOfClickFirstCheckBox(String categoryJobServer, String eventJobServerAlert,
			String descriptionJobServerAlert, String jobServer, String jobserverAvailable) throws InterruptedException  {
		
		checkAllUsersJobserverTaskOfClickFirstCheckBox(3, categoryJobServer, 1, 
				eventJobServerAlert, descriptionJobServerAlert, "idNotificationJobserverButton");
		
		Thread.sleep(3000);
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- use_server_available')]"));
		Assert.assertTrue(selenium.isElementPresent("//i[contains(text(),'- use_server_unactive')]"));
 		
	}
	
}
