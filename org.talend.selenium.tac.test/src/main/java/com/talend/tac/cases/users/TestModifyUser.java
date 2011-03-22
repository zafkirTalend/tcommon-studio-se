package com.talend.tac.cases.users;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@Test(groups={"ModifyUser"},dependsOnGroups={"DisplayUser"})
public class TestModifyUser extends Login {


	@Test
	@Parameters({"userName"})
	public void testLastLoginUserNotChooseActive(String userName) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
        selenium.mouseDown("//div[text()='"+userName+"']");
        
		selenium.click("//input[@name='active']");
        
		Assert.assertFalse(selenium.isChecked("//input[@name='active']"));
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.error.lastActiveAdmin")));
	    selenium.click("//button[text()='" +other.getString("modify.lastUserAdministrator.role.fail")+"']");
	    selenium.setSpeed(MIN_SPEED);
	}
	
	@Test
	@Parameters({"userName"})
	public void testModifyLastAdministrationUserRole(String userName) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.setSpeed(MIN_SPEED);
        selenium.mouseDown("//div[text()='"+userName+"']");
		selenium.click("idRoleButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.setSpeed(MIN_SPEED);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+rb.getString("menu.role.designer")+"')]");	
		selenium.click("idValidateButton");
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.error.roleModification.lastAdministrator")));
	    selenium.click("//button[text()='"+other.getString("modify.lastUserAdministrator.role.fail")+"']");
	    
	    selenium.setSpeed(MIN_SPEED);
	}
	
	@Test
	@Parameters({"userName","FirstName","LastName"})
	public void testModity_admin_LastName(String userName, String FirstName,String LastName) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.mouseDown("//div[text()='"+userName+"']");
		selenium.type("idUserFirstNameInput", FirstName);
		selenium.fireEvent("idUserFirstNameInput", "blur");
	
		selenium.type("idUserLastNameInput", LastName);
		selenium.fireEvent("idUserLastNameInput", "blur");
		
		selenium.click("idFormSaveButton");
		Assert.assertEquals(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-lastName']"), LastName);
		selenium.setSpeed(MIN_SPEED);
		
		selenium.click("idSubModuleRefreshButton");
		selenium.setSpeed(MAX_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//b[text()='jack, zhang']"));
	    selenium.setSpeed(MIN_SPEED);
	}
    
	
	@Test(dependsOnMethods={"testDuplicateUser"})
	@Parameters({"userName","ModifiyUserName","DulicateUser"})
	public void testModifyUser(String userName,String ModifiyUserName,String DulicateUser) throws Exception {
	    
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
		if(selenium.isTextPresent(ModifiyUserName)) {
			System.out.println(DulicateUser +  " is exist user");
			
		} else {
			selenium.mouseDown(DulicateUser);
			selenium.type("idUserLoginInput", ModifiyUserName);
			selenium.fireEvent("idUserLoginInput", "blur");
			
			selenium.click("idFormSaveButton");
			selenium.setSpeed(MAX_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//div[text()='"+ModifiyUserName+"']"));
			selenium.setSpeed(MIN_SPEED);
		}
		
	}
	
}
