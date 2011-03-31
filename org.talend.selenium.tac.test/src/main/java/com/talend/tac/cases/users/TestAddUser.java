package com.talend.tac.cases.users;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;


public class TestAddUser extends Login {
    
	//creat a method of 'add user'
	public void addUser(String user,String firstname,String lastname,String password,String SvnLogin,String SvnPassWord) throws Exception {
		

		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent("admin@company.com"));
		selenium.setSpeed(MID_SPEED);
		selenium.click("idSubModuleAddButton");//add a user
		selenium.setSpeed(MID_SPEED);
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
	
	//clear all users---modify firstname and lastname to "admin,admin" ---user'role change to 'administrator'
    @Test(groups={"AddUser"})
    @Parameters({"userName"})
    public void clearAllUsers(String userName) {
    	 List<String> users = new ArrayList<String>(); 
    	 this.clickWaitForElementPresent("idMenuUserElement");
    	 selenium.setSpeed(MID_SPEED);
    	 if(selenium.isElementPresent("//i[text()='Administrator/Viewer/Operation manager/Designer']")) {
			 selenium.setSpeed(MIN_SPEED);
    		 selenium.mouseDown("//div[text()='"+userName+"']");
			 selenium.click("idRoleButton");
			 Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
			 selenium.click("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+rb.getString("menu.role.administrator")+"')]");
			 selenium.click("idValidateButton");

			 selenium.click("idFormSaveButton");
		
    	 } else {
    		 System.out.println("user role is administrator");
    		 selenium.setSpeed(MIN_SPEED);
    	 }
    	 selenium.setSpeed(MID_SPEED);
      	 if(!selenium.isElementPresent("//b[text()='admin, admin']")) {
			 selenium.setSpeed(MIN_SPEED);
      		 selenium.mouseDown("//div[text()='"+userName+"']");
			 selenium.fireEvent("idUserLoginInput", "blur");
			 selenium.type("idUserFirstNameInput", "admin");
			 selenium.fireEvent("idUserFirstNameInput", "blur");
			 selenium.type("idUserLastNameInput", "admin");
			 selenium.fireEvent("idUserLastNameInput", "blur");
			 selenium.setSpeed(MID_SPEED);
			 selenium.click("idFormSaveButton");
			 selenium.setSpeed(MIN_SPEED);
    	 } else {
    		 System.out.println("user lastname is admin");
    		 selenium.setSpeed(MIN_SPEED);
    	 }
    	 
    	 users = this.findSpecialMachedStrings(".*@[a-zA-Z0-9]*\\.com");
    	 for(int i=0;i<users.size();i++) {
    		 System.out.println(users.get(i));
    		 if(!"admin@company.com".equals(users.get(i))) {
    			selenium.mouseDown("//div[text()='"+users.get(i)+"']");
  				selenium.chooseOkOnNextConfirmation();
  				selenium.click("idSubModuleDeleteButton");
  				selenium.setSpeed(MID_SPEED);
  			    Assert.assertTrue(selenium.getConfirmation().matches("^"+other.getString("delete.User.confirmation")+" [\\s\\S]$"));
    		    selenium.setSpeed(MIN_SPEED);
    		 } 
    	 }
    	 selenium.setSpeed(MIN_SPEED);
    }
    
    /***add user choose all roles--->>save failed***/
    //add a user of user'role are all roles
    @Test(dependsOnMethods={"clearAllUsers"})
    @Parameters({"userNameAllRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddUserAllRoles(String userName,String FirstName,String LastName,String PassWord,String SvnLogin,
			String SvnPassWord) throws Exception {
    	String roles = rb.getString("menu.role.administrator")+"/"+rb.getString("menu.role.viewer")+"/"
		+rb.getString("menu.role.operationManager")+"/"+rb.getString("menu.role.designer");
    	
    	addUser(userName, FirstName, LastName, PassWord,SvnLogin,SvnPassWord);
 	   
	    System.out.println("************************choose a type");
	    selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER); 
	    Assert.assertEquals(selenium.getValue("idTypeInput"), "Data Integration");
        
        selenium.click("idRoleButton");
	    selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
	    selenium.setSpeed(MIN_SPEED);
	    selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
        
	    selenium.click("idValidateButton");
        Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
        
        selenium.click("idFormSaveButton");
	}
    
    /***add method(addUserAllRoles) for execution next case assure pass***/
    //add a user of user'role are all roles
    @Test(dependsOnMethods={"clearAllUsers"})
    @Parameters({"userNameAllRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void addUserAllRoles(String userName,String FirstName,String LastName,String PassWord,String SvnLogin,
			String SvnPassWord) throws Exception {
    	String roles = rb.getString("menu.role.administrator")+"/"+rb.getString("menu.role.viewer")+"/"
		+rb.getString("menu.role.operationManager")+"/"+rb.getString("menu.role.designer");
    	
    	addUser(userName, FirstName, LastName, PassWord,SvnLogin,SvnPassWord);
 	   
 	    selenium.click("idTypeInput");
	    System.out.println("************************choose a type");
	    selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER); 
	    
	    Assert.assertEquals(selenium.getValue("idTypeInput"), "Data Integration");
	    
 	    selenium.click("idRoleButton");
	    selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
	    selenium.setSpeed(MIN_SPEED);
	    selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+ rb.getString("menu.role.operationManager")+"')]");
		selenium.click("idValidateButton");
        selenium.click("idFormSaveButton");
        selenium.setSpeed(MID_SPEED);
        selenium.mouseDown("//div[text()='"+userName+"']");
        selenium.setSpeed(MIN_SPEED);
        selenium.click("idRoleButton");

	    Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
	    selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");
        
	    selenium.click("idValidateButton");
        Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
        
        selenium.click("idFormSaveButton");
	}
	
    //add a exist user(admin@company.com)
	@Test(dependsOnMethods={"addUserAllRoles"})
	@Parameters({"userName","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddExistUser(String userName,String FirstName,String LastName,String PassWord,
			String SvnLogin,String SvnPassWord) throws Exception {
	
	    addUser(userName, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);
		
	    selenium.click("idTypeInput");
	    System.out.println("************************choose a type");
	    selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER); 
	    
	    Assert.assertEquals(selenium.getValue("idTypeInput"), "Data Integration");
	    
	    selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+ rb.getString("menu.role.operationManager")+"')]");

		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.operationManager"));
		
		selenium.setSpeed(MID_SPEED);
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.error.uniqueLogin")));
        selenium.setSpeed(MIN_SPEED);
	}
	
    //add a user of role is 'administrator'
	@Test(dependsOnMethods={"testAddExistUser"})
	@Parameters({"LoginNameChooseAdministratorRole","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddNewUserRoleAdministrator(String LoginNameChooseAdministratorRole,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord) throws Exception {
	    addUser(LoginNameChooseAdministratorRole, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);

		selenium.click("idTypeInput");
	    System.out.println("************************choose a type");
	    selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER); 
		    
	    Assert.assertEquals(selenium.getValue("idTypeInput"), "Data Integration");

	    selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+rb.getString("menu.role.administrator")+"')]");
		selenium.click("idValidateButton");

		selenium.setSpeed(MID_SPEED);
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseAdministratorRole+"']"));
		selenium.setSpeed(MIN_SPEED);
			

	}
	
	/***add user choose mulriple roles--->>save failed***/
	//add a user of choose mulriple roles
	@Test(dependsOnMethods={"testAddNewUserRoleAdministrator"})
	@Parameters({"LoginNameChooseMulripleRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddUserMulripleRoles(String LoginNameChooseMulripleRoles,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord) throws Exception {
		addUser(LoginNameChooseMulripleRoles, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);
		
	    selenium.click("idTypeInput");
	    System.out.println("************************choose a type");
	    selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER); 
		    
	    Assert.assertEquals(selenium.getValue("idTypeInput"), "Data Integration");
		
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
        + rb.getString("menu.role.viewer") + "']");
		selenium.click("idValidateButton");

		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseMulripleRoles+"']"));    
        
        selenium.mouseDown("//div[text()='"+LoginNameChooseMulripleRoles+"']");
        
        selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
	
		selenium.setSpeed(MID_SPEED);
		selenium.controlKeyDown();
	    selenium.setSpeed(MIN_SPEED);
		selenium.click("//td[not(contains(@style,'display: none'))]/div[text()='"
        + rb.getString("menu.role.designer") + "']");
		
		selenium.controlKeyUp();
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.viewer")+"/"+rb.getString("menu.role.designer"));
        
        selenium.click("idFormSaveButton");

	}
	
	/***add method(addUserMulripleRoles) for execution next case assure pass***/
	//add a user of choose mulriple roles
	@Test(dependsOnMethods={"testAddNewUserRoleAdministrator"})
	@Parameters({"LoginNameChooseMulripleRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void addUserMulripleRoles(String LoginNameChooseMulripleRoles,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord) throws Exception {
		addUser(LoginNameChooseMulripleRoles, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);

	    selenium.click("idTypeInput");
	    System.out.println("************************choose a type");
	    selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER); 
		    
	    Assert.assertEquals(selenium.getValue("idTypeInput"), "Data Integration");
		
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
        + rb.getString("menu.role.viewer") + "']");
		selenium.click("idValidateButton");

		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseMulripleRoles+"']"));       
        selenium.setSpeed(MIN_SPEED);
        selenium.mouseDown("//div[text()='"+LoginNameChooseMulripleRoles+"']");
        
        selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
	
		selenium.setSpeed(MID_SPEED);
		selenium.controlKeyDown();
	    selenium.setSpeed(MIN_SPEED);
		selenium.click("//td[not(contains(@style,'display: none'))]/div[text()='"
        + rb.getString("menu.role.designer") + "']");
		
		selenium.controlKeyUp();
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.viewer")+"/"+rb.getString("menu.role.designer"));
        
        selenium.click("idFormSaveButton");

	}
	
	//add a user of uncheck 'Active'
	@Test(dependsOnMethods={"addUserMulripleRoles"})
	@Parameters({"LoginNameNotChooseActive","FirstName","LastName","PassWord","SvnLogin","SvnPassWord","LoginNameNotChooseActive"})
	public void testAddUserNotChooseActive(String LoginNameNotChooseActive,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord,String LoginNameNotChooseActive1) throws Exception {
	    
		addUser(LoginNameNotChooseActive, FirstName, LastName, PassWord, SvnLogin, SvnPassWord);

	    selenium.click("idTypeInput");
	    System.out.println("************************choose a type");
	    selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER); 
	    Assert.assertEquals(selenium.getValue("idTypeInput"), "Data Integration");
		
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+ rb.getString("menu.role.viewer")+"')]");
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.viewer"));

		selenium.click("//input[@name='active']");
		Assert.assertFalse(selenium.isChecked("//input[@name='active']"));

		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameNotChooseActive1+"']"));
		selenium.setSpeed(MIN_SPEED);

    }
	
	//add a user under mysql configuration
	@Test(dependsOnMethods={"testAddUserNotChooseActive"})
	@Parameters({"LoginName","FirstName","LastName","PassWordww","SvnLogin","SvnPassWord","LoginName"})
	public void testMysqlAddUserActive(String LoginName,String FirstName,String LastName,
			String PassWordww,String SvnLogin,String SvnPassWord,String LoginName1) throws Exception {
		addUser(LoginName, FirstName, LastName, PassWordww, SvnLogin, SvnPassWord);
		
	    selenium.click("idTypeInput");
	    System.out.println("************************choose a type");
	    selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER); 
	    Assert.assertEquals(selenium.getValue("idTypeInput"), "Data Integration");
		
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+  rb.getString("menu.role.designer")+"')]");//choose a  role
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.designer"));
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginName1+"']"));
		selenium.setSpeed(MIN_SPEED);
	}
	
}
