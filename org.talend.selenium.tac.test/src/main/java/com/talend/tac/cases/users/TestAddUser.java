package com.talend.tac.cases.users;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;


public class TestAddUser extends Login {
    
	public void testAddUsers(String user,String firstname,String lastname,String password,String SvnLogin,String SvnPassWord) throws Exception {
		
		selenium.setSpeed(MAX_SPEED);
		selenium.click("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent("admin@company.com"));

		selenium.click("idSubModuleAddButton");//add a user/Mysql
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		selenium.setSpeed(MIN_SPEED);
		selenium.type("idUserLoginInput", user);//user name
		selenium.fireEvent("idUserLoginInput", "blur");
		selenium.type("idUserFirstNameInput", firstname);
		selenium.fireEvent("idUserFirstNameInput", "blur");
		selenium.type("idUserLastNameInput", lastname);
		selenium.fireEvent("idUserLastNameInput", "blur");
		selenium.type("idUserPasswordInput", password);
		selenium.fireEvent("idUserPasswordInput", "blur");
		selenium.type("//input[@name='authenticationLogin']", SvnLogin);
		selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
		selenium.type("//input[@name='authenticationPassword']", SvnPassWord);
		selenium.fireEvent("//input[@name='authenticationPassword']", "blur");	
	
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
    @Parameters({"userNameAllRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddUserAllRoles(String userName,String FirstName,String LastName,String PassWord,String SvnLogin,
			String SvnPassWord) throws Exception {
    	String roles = rb.getString("menu.role.administrator")+"/"+rb.getString("menu.role.viewer")+"/"
		+rb.getString("menu.role.operationManager")+"/"+rb.getString("menu.role.designer");
    	
    	testAddUsers(userName, FirstName, LastName, PassWord,SvnLogin,SvnPassWord);
		selenium.click("idRoleButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
		
		selenium.click("idValidateButton");
        Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
		
        selenium.click("idFormSaveButton");
	}
	
	@Test(dependsOnMethods={"testAddUserAllRoles"})
	@Parameters({"userName","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddExistUser(String userName,String FirstName,String LastName,String PassWord,
			String SvnLogin,String SvnPassWord) throws Exception {
	
	    testAddUsers(userName, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);
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
	@Parameters({"LoginNameChooseAdministratorRole","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddNewUserRoleAdministrator(String LoginNameChooseAdministratorRole,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord) throws Exception {
	    testAddUsers(LoginNameChooseAdministratorRole, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);
		
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
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseAdministratorRole+"']"));
		selenium.setSpeed(MIN_SPEED);
			

	}
	
	
	@Test(dependsOnMethods={"testAddNewUserRoleAdministrator"})
	@Parameters({"LoginNameChooseMulripleRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddUserMulripleRoles(String LoginNameChooseMulripleRoles,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord) throws Exception {
		testAddUsers(LoginNameChooseMulripleRoles, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);
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
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseMulripleRoles+"']"));
        selenium.setSpeed(MIN_SPEED);

	}
	
	@Test(dependsOnMethods={"testAddUserMulripleRoles"})
	@Parameters({"LoginNameNotChooseActive","FirstName","LastName","PassWord","SvnLogin","SvnPassWord","LoginNameNotChooseActive"})
	public void testAddUserNotChooseActive(String LoginNameNotChooseActive,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord,String LoginNameNotChooseActive1) throws Exception {
	    
		testAddUsers(LoginNameNotChooseActive, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);
			
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+ rb.getString("menu.role.viewer")+"')]");
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.viewer"));

		selenium.click("//input[@name='active']");
		Assert.assertFalse(selenium.isChecked("//input[@name='active']"));
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameNotChooseActive1+"']"));
		selenium.setSpeed(MIN_SPEED);

    }
	   
	@Test(dependsOnMethods={"testAddUserNotChooseActive"})
	@Parameters({"LoginName","FirstName","LastName","PassWordww","SvnLogin","SvnPassWord","LoginName"})
	public void testMysqlAddUserActive(String LoginName,String FirstName,String LastName,
			String PassWordww,String SvnLogin,String SvnPassWord,String LoginName1) throws Exception {
		testAddUsers(LoginName, FirstName, LastName, PassWordww, SvnLogin, SvnPassWord);
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+  rb.getString("menu.role.designer")+"')]");//choose a  role
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.designer"));
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginName1+"']"));
		selenium.setSpeed(MIN_SPEED);
	}
	
}
