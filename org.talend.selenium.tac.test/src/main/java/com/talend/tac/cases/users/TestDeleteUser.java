package com.talend.tac.cases.users;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeleteUser extends Login {
    
	@Parameters({"userName"})
	public void deleteUser(String userName,String deleteUserName){
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.mouseDown("//div[text()='"+deleteUserName+"']");//Select an existing user
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
	    Assert.assertTrue(selenium.getConfirmation().matches("^"+other.getString("delete.User.confirmation")+" [\\s\\S]$"));
	  
	}

	
	
	@Test(groups={"DeleteUser"},dependsOnGroups={"AddUser"})
	@Parameters({"userName","LoginNameChooseAdministratorRole","LoginNameChooseAdministratorRole"})
	public void testCancleDeleteUser(String userName,String DeleteUser,String DeleteUser1) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.mouseDown("//div[text()='"+DeleteUser+"']");//Select an existing user
		selenium.chooseCancelOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
	    Assert.assertTrue(selenium.getConfirmation().matches("^"+other.getString("delete.User.confirmation")+" [\\s\\S]$"));
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+DeleteUser1+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
	
	@Test(dependsOnMethods={"testCancleDeleteUser"})
	@Parameters({"userName","LoginNameChooseAdministratorRole","LoginNameChooseAdministratorRole"})
	public void testDeleteUser(String userName,String DeleteUser,String DeleteUser1) throws Exception {
		deleteUser(userName, DeleteUser);
	    Assert.assertFalse(selenium.isElementPresent("//div[text()='"+DeleteUser1+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}
    
	
	@Test(dependsOnMethods={"testDeleteUser"})
	@Parameters({"userName","userName"})
	public void testDeleteLoginUser(String userName,String userName1) throws Exception {
        deleteUser(userName,userName1);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.error.deleteCurrentLoggedUser")));
		selenium.click("//button[text()='" +other.getString("delete.LoginUser.fail")+"']");
		selenium.setSpeed(MIN_SPEED);

	}
}
