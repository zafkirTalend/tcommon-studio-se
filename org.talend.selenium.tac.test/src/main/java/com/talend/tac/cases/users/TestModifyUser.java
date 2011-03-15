package com.talend.tac.cases.users;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@Test(groups={"ModifyUser"},dependsOnGroups={"DisplayUser"})
public class TestModifyUser extends Login {


	@Test
	@Parameters({"userName","userName"})
	public void testLastLoginUserNotChooseActive(String userName,String userName1) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
        selenium.mouseDown("//div[text()='"+userName1+"']");
///****/
//		selenium.click("idTypeInput");
//		selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
///****/ 
//
		selenium.click("//input[@name='active']");
        
		Assert.assertFalse(selenium.isChecked("//input[@name='active']"));
		
//		selenium.click("idFirstAdminConnInput");
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.error.lastActiveAdmin")));
	    selenium.click("//button[text()='" +other.getString("modify.lastUserAdministrator.role.fail")+"']");
	    selenium.setSpeed(MIN_SPEED);
	}
	
	@Test
	@Parameters({"userName","userName"})
	public void testModifyLastAdministrationUserRole(String userName,String userName1) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
        selenium.mouseDown("//div[text()='"+userName1+"']");
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.click("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+rb.getString("menu.role.designer")+"')]");	
		selenium.click("idValidateButton");

///****/
//		selenium.click("idTypeInput");
//		selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
///****/ 
//		selenium.click("idFirstAdminConnInput");
//		
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.error.roleModification.lastAdministrator")));
	    selenium.click("//button[text()='"+other.getString("modify.lastUserAdministrator.role.fail")+"']");
	    
	    selenium.setSpeed(MIN_SPEED);
	}
	
	@Test
	@Parameters({"userName","userName","FirstName","LastName","LastName"})
	public void testModity_admin_LastName(String userName,String userName1, String FirstName,String LastName,String LastName1) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.mouseDown("//div[text()='"+userName1+"']");
		selenium.type("idUserFirstNameInput", FirstName);
		selenium.fireEvent("idUserFirstNameInput", "blur");
	
		selenium.type("idUserLastNameInput", LastName);
		selenium.fireEvent("idUserLastNameInput", "blur");
		
///****/
//		selenium.click("idTypeInput");
//		selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
///****/ 
//		selenium.click("idFirstAdminConnInput");
		
		selenium.click("idFormSaveButton");
		Assert.assertEquals(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-lastName']"), LastName1);
		selenium.setSpeed(MIN_SPEED);
	}
    
	
	@Test(dependsOnMethods={"testDuplicateUser"})
	@Parameters({"userName","ModifiyUserName","DulicateUser","ModifiyUserName","ModifiyUserName"})
	public void testModifyUser(String userName,String ModifiyUserName,String DulicateUser,String ModifiyUserName1,String ModifiyUserName2) throws Exception {
	    
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
		if(selenium.isTextPresent(ModifiyUserName)) {
			System.out.println("Copy_of_aaa@gmail.com is exist user");
			
		} else {
			selenium.mouseDown(DulicateUser);
			selenium.type("idUserLoginInput", ModifiyUserName1);
			selenium.fireEvent("idUserLoginInput", "blur");
			
	///****/
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
	///****/  
//			selenium.click("idFirstAdminConnInput");
			
			selenium.click("idFormSaveButton");
			selenium.setSpeed(MAX_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//div[text()='"+ModifiyUserName2+"']"));
			selenium.setSpeed(MIN_SPEED);
		}
		
	}
	
}
