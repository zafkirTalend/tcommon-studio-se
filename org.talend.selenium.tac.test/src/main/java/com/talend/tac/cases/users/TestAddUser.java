package com.talend.tac.cases.users;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
import com.thoughtworks.selenium.Selenium;

public class TestAddUser extends Login {
    
	public void testAddUserActiveAllRoles(Selenium s,String user,String firstname,String lastname,String password) throws Exception {
		String roles = rb.getString("menu.role.administrator")+"/"+rb.getString("menu.role.viewer")+"/"
		+rb.getString("menu.role.operationManager")+"/"+rb.getString("menu.role.designer");
		s.setSpeed(MAX_SPEED);
		s.click("idMenuUserElement");
		Assert.assertTrue(s.isTextPresent("admin@company.com"));
		if(s.isTextPresent("talend@company.com")) {
			System.out.println("talend@company.com is exist");
		} else {
			s.click("idSubModuleAddButton");//add a user/Mysql
			Assert.assertTrue(s.isElementPresent("//img[@class='gwt-Image x-component ']"));
			s.setSpeed(MIN_SPEED);
			s.type("idUserLoginInput", user);//user name
			s.fireEvent("idUserLoginInput", "blur");
			s.type("idUserFirstNameInput", firstname);
			s.fireEvent("idUserFirstNameInput", "blur");
			s.type("idUserLastNameInput", lastname);
			s.fireEvent("idUserLastNameInput", "blur");
			s.type("idUserPasswordInput", password);
			s.fireEvent("idUserPasswordInput", "blur");
				
			s.click("idRoleButton");
			s.setSpeed(MAX_SPEED);
			Assert.assertTrue(s.isTextPresent(rb.getString("user.roles.title")));
			s.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
			
			s.click("idValidateButton");
	        Assert.assertEquals(s.getValue("idActiveInput"), roles);
			
			s.click("idFormSaveButton");
		}
		
	}
	
    @Test(groups={"AddUser"})
    @Parameters({"userName"})
    public void testClearAllUsers(String userName) {
    	 List<String> users = new ArrayList<String>(); 
    	 this.clickWaitForElementPresent("idMenuUserElement");
    	 users = this.findSpecialMachedStrings(".*@[a-zA-Z0-9]*\\.com");
    	 for(int i=0;i<users.size();i++) {
    		 System.out.println(users.get(i));
    		 if(!"admin@company.com".equals(users.get(i))) {
    			selenium.mouseDown("//div[text()='"+users.get(i)+"']");
  				selenium.chooseOkOnNextConfirmation();
  				selenium.click("idSubModuleDeleteButton");
  				selenium.setSpeed(MAX_SPEED);
  			    Assert.assertTrue(selenium.getConfirmation().matches("^"+other.getString("delete.User.confirmation")+" [\\s\\S]$"));
    		    selenium.setSpeed(MIN_SPEED);
    		 } 
    	 }
    }
    
    @Test(dependsOnMethods={"testClearAllUsers"})
    @Parameters({"userNameAllRoles","FirstName","LastName","PassWord"})
	public void testAddUserAllRoles(String userName,String FirstName,String LastName,String PassWord) throws Exception {
		testAddUserActiveAllRoles(selenium,userName, FirstName, LastName, PassWord);
	}
	
	@Test(dependsOnMethods={"testAddUserAllRoles"})
	@Parameters({"userName","userName","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddExistUser(String userName,String userName1,String FirstName,String LastName,String PassWord,String SvnLogin,String SvnPassWord) throws Exception {
	
		this.clickWaitForElementPresent("idMenuUserElement");
		System.out.println("add a exist user");
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.click("idSubModuleAddButton");
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		selenium.type("idUserLoginInput", userName1);
		selenium.fireEvent("idUserLoginInput", "blur");
		selenium.type("idUserFirstNameInput", FirstName);
		selenium.fireEvent("idUserFirstNameInput", "blur");
		selenium.type("idUserLastNameInput", LastName);
		selenium.fireEvent("idUserLastNameInput", "blur");
		selenium.type("idUserPasswordInput", PassWord);
		selenium.fireEvent("idUserPasswordInput", "blur");
		selenium.type("//input[@name='authenticationLogin']", SvnLogin);//svnUserName
		selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
		selenium.type("//input[@name='authenticationPassword']", SvnPassWord);//svnPassWord
		selenium.fireEvent("//input[@name='authenticationPassword']", "blur");
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+ rb.getString("menu.role.operationManager")+"')]");
//		Assert.assertTrue(selenium.isChecked("//div[text()='"+ rb.getString("menu.role.operationManager")+"']"));
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.operationManager"));

		if(selenium.isElementPresent("idTypeInput")) {
/****/
			selenium.click("idTypeInput");
			selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
/****/  
			selenium.click("idFirstAdminConnInput");
		} else {

		}
		
		selenium.setSpeed(MAX_SPEED);
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.error.uniqueLogin")));
		
        selenium.click("//button[text()='" +other.getString("add.ExistUser.fail")+"']");
        selenium.setSpeed(MIN_SPEED);
	}
	

	@Test(dependsOnMethods={"testAddExistUser"})
	@Parameters({"userName","LoginNameChooseAdministratorRole","FirstName","LastName","PassWord","SvnLogin","SvnPassWord","LoginNameChooseAdministratorRole"})
	public void testAddNewUserRoleAdministrator(String userName,String LoginNameChooseAdministratorRole,String FirstName,String LastName,String PassWord,String SvnLogin,String SvnPassWord,String LoginNameChooseAdministratorRole1) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.click("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.click("idSubModuleAddButton");
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		
		if(selenium.isTextPresent(LoginNameChooseAdministratorRole)) {
			System.out.println("LoginNameChooseAdministratorRole is exist");
			
		} else {

			selenium.type("idUserLoginInput", LoginNameChooseAdministratorRole1);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", FirstName);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", LastName);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", PassWord);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", SvnLogin);
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", SvnPassWord);
			selenium.fireEvent("//input[@name='authenticationPassword']", "blur");
			selenium.click("idRoleButton");
			Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+rb.getString("menu.role.administrator")+"')]");
			selenium.click("idValidateButton");

			if(selenium.isElementPresent("idTypeInput")) {
	/****/
				selenium.click("idTypeInput");
				selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
	/****/  
				selenium.click("idFirstAdminConnInput");
			} else {

			}
			
			selenium.setSpeed(MAX_SPEED);
			selenium.click("idFormSaveButton");
			Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseAdministratorRole1+"']"));
			selenium.setSpeed(MIN_SPEED);
			
		}
		
	}
	
	
	@Test(dependsOnMethods={"testAddNewUserRoleAdministrator"})
	@Parameters({"userName","LoginNameChooseMulripleRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord","LoginNameChooseMulripleRoles"})
	public void testAddUserMulripleRoles(String userName,String LoginNameChooseMulripleRoles,String FirstName,String LastName,String PassWord,String SvnLogin,String SvnPassWord,String LoginNameChooseMulripleRoles1) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
		
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.click("idSubModuleAddButton");
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		
		if(selenium.isTextPresent(LoginNameChooseMulripleRoles)) {
			System.out.println("LoginNameChooseMulripleRoles is exist");
			
		} else {

			selenium.type("idUserLoginInput", LoginNameChooseMulripleRoles);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", FirstName);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", LastName);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", PassWord);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", SvnLogin);
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", SvnPassWord);
			selenium.fireEvent("//input[@name='authenticationPassword']", "blur");

			selenium.click("idRoleButton");
			Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
			selenium.setSpeed(MAX_SPEED);
			selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
		
			selenium.setSpeed(MAX_SPEED);
			selenium.controlKeyDown();
			selenium.click("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+rb.getString("menu.role.administrator")+"')]");
			selenium.click("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+ rb.getString("menu.role.operationManager")+"')]");
			selenium.controlKeyUp();
			selenium.click("idValidateButton");
			Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getObject("menu.role.viewer")+"/"+rb.getString("menu.role.designer"));

			if(selenium.isElementPresent("idTypeInput")) {
	/****/
				selenium.click("idTypeInput");
				selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
	/****/  
				selenium.click("idFirstAdminConnInput");
			} else {

			}
			
			selenium.setSpeed(MAX_SPEED);
			selenium.click("idFormSaveButton");
			Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseMulripleRoles1+"']"));
	        selenium.setSpeed(MIN_SPEED);
	        
		}
		
	}
	
	@Test(dependsOnMethods={"testAddUserMulripleRoles"})
	@Parameters({"userName","LoginNameNotChooseActive","FirstName","LastName","PassWord","SvnLogin","SvnPassWord","LoginNameNotChooseActive"})
	public void testAddUserNotChooseActive(String userName,String LoginNameNotChooseActive,String FirstName,String LastName,String PassWord,String SvnLogin,String SvnPassWord,String LoginNameNotChooseActive1) throws Exception {
	    
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.click("idSubModuleAddButton");
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		
		if(selenium.isTextPresent(LoginNameNotChooseActive)) {
			System.out.println("LoginNameNotChooseActive is exist");
			
		} else {

			selenium.type("idUserLoginInput", LoginNameNotChooseActive);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", FirstName);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", LastName);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", PassWord);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", SvnLogin);
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", SvnPassWord);
			selenium.fireEvent("//input[@name='authenticationPassword']", "blur");
			
			selenium.click("idRoleButton");
			Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+ rb.getString("menu.role.viewer")+"')]");
			selenium.click("idValidateButton");
			Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.viewer"));
	///****/
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
	///****/
//			selenium.click("idFirstAdminConnInput");
			selenium.click("//input[@name='active']");
			Assert.assertFalse(selenium.isChecked("//input[@name='active']"));
			selenium.click("idFormSaveButton");
			selenium.setSpeed(MAX_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameNotChooseActive1+"']"));
			selenium.setSpeed(MIN_SPEED);

		}
    }
	   
	@Test(dependsOnMethods={"testAddUserNotChooseActive"})
	@Parameters({"userName","LoginName","FirstName","LastName","PassWordww","SvnLogin","SvnPassWord","LoginName"})
	public void testMysqlAddUserActive(String userName,String LoginName,String FirstName,String LastName,String PassWordww,String SvnLogin,String SvnPassWord,String LoginName1) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
	
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.click("idSubModuleAddButton");//add a user/Mysql
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		
		if(selenium.isTextPresent(LoginName)) {
			System.out.println("LoginName is exist");
			
		} else {

			selenium.type("idUserLoginInput", LoginName);//user name
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", FirstName);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", LastName);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", PassWordww);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", SvnLogin);
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", SvnPassWord);
			selenium.fireEvent("//input[@name='authenticationPassword']", "blur");
			selenium.click("idRoleButton");
			Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+  rb.getString("menu.role.designer")+"')]");//choose a  role
			selenium.click("idValidateButton");
			Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.designer"));
	///****/
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
	///****/  
//			selenium.click("idFirstAdminConnInput");
			
			selenium.click("idFormSaveButton");
			selenium.setSpeed(MAX_SPEED);
			Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginName1+"']"));
			selenium.setSpeed(MIN_SPEED);
		}
		
	}

}
