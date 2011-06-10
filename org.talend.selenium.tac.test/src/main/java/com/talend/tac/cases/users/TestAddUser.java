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
	public void addUser(String user,String firstname,String lastname,String password,
			String SvnLogin,String SvnPassWord,String typeName) throws Exception {
		

		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent("admin@company.com"));
		selenium.setSpeed(MID_SPEED);
		selenium.click("idSubModuleAddButton");//add a user
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
		selenium.setSpeed(MIN_SPEED);
		this.typeString("idUserLoginInput", user);//user name
		this.typeString("idUserFirstNameInput", firstname);
		this.typeString("idUserLastNameInput", lastname);
		this.typeString("idUserPasswordInput", password);
		this.typeString("idSvnLogin", SvnLogin);
		this.typeString("idSvnPwd", SvnPassWord);
	    if(selenium.isVisible("//label[text()='Type:']/parent::div//div[@class='x-form-trigger x-form-trigger-arrow']")) {
	    	
	    	selenium.click("//label[text()='Type:']/parent::div//div[@class='x-form-trigger x-form-trigger-arrow']");
			this.waitForElementPresent("//div[contains(@class, 'x-combo-list')]/" +
					"descendant::div[contains(@class, 'x-combo-list-item')][text()='"+typeName+"']", WAIT_TIME);
			if(selenium.isElementPresent("//div[contains(@class, 'x-combo-list')]/" +
					"descendant::div[contains(@class, 'x-combo-list-item')][text()='"+typeName+"']")) {
				selenium.mouseDownAt("//div[contains(@class, 'x-combo-list')]/" +
					"descendant::div[contains(@class, 'x-combo-list-item')][text()='"+typeName+"']", ""+Event.ENTER); 
			    Assert.assertEquals(selenium.getValue("idTypeInput"), typeName);
			}
		    
	    }
		
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
			 this.typeString("idUserFirstNameInput", "admin");
			 this.typeString("idUserLastNameInput", "admin");
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
    
    //add a user of user'role are all roles
    @Test(dependsOnMethods={"clearAllUsers"})
    @Parameters({"userNameAllRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddUserAllRoles(String userName,String FirstName,String LastName,String PassWord,String SvnLogin,
			String SvnPassWord) throws Exception {
    	String roles = rb.getString("menu.role.administrator")+"/"+rb.getString("menu.role.viewer")+"/"
		+rb.getString("menu.role.operationManager")+"/"+rb.getString("menu.role.designer");
    	
    	addUser(userName, FirstName, LastName, PassWord,SvnLogin,SvnPassWord,"Data Integration");
        
        selenium.click("idRoleButton");
	    selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
	   
	    selenium.click("//span[text()='Role']/parent::div[not(contains(@style,'display: none'))]/" +
	    		"parent::td[not(contains(@style,'display: none'))]/preceding-sibling::td" +
	    		"[not(contains(@style,'display: none'))]/div[@class=' x-grid3-hd-inner x-grid3-hd-checker x-component']");

	    selenium.setSpeed(MIN_SPEED);
	    selenium.click("idValidateButton");
	    selenium.setSpeed(MID_SPEED);
        Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
        selenium.click("idFormSaveButton");
        Assert.assertTrue(selenium.isTextPresent(userName));
        selenium.setSpeed(MIN_SPEED);
	}
    
   
    //add a exist user(admin@company.com)
	@Test(dependsOnMethods={"testAddUserAllRoles"})
	@Parameters({"userName","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddExistUser(String userName,String FirstName,String LastName,String PassWord,
			String SvnLogin,String SvnPassWord) throws Exception {
	
	    addUser(userName, FirstName, LastName, PassWord, SvnLogin, SvnPassWord,"Data Integration");
		
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
	    addUser(LoginNameChooseAdministratorRole, FirstName, LastName, PassWord, SvnLogin, SvnPassWord,"Data Integration");
	    
	    selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+rb.getString("menu.role.administrator")+"')]");
		selenium.click("idValidateButton");

		selenium.setSpeed(MID_SPEED);
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseAdministratorRole+"']"));
		selenium.setSpeed(MIN_SPEED);
			

	}
	
	//add a user of choose mulriple roles
	@Test(dependsOnMethods={"testAddNewUserRoleAdministrator"})
	@Parameters({"LoginNameChooseMulripleRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddUserMulripleRoles(String LoginNameChooseMulripleRoles,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord) throws Exception {
		addUser(LoginNameChooseMulripleRoles, FirstName, LastName, PassWord, SvnLogin, SvnPassWord,"Data Integration");
		
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.controlKeyDown();
		selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"+ rb.getString("menu.role.viewer") + "']" +
				"/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
		selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"+ rb.getString("menu.role.designer") + "']" +
		"/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
		selenium.controlKeyUp();
		selenium.click("idValidateButton");
         
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseMulripleRoles+"']"));    
        selenium.setSpeed(MIN_SPEED);
        
	}
	
	
	//add a user of uncheck 'Active'
	@Test(dependsOnMethods={"testAddUserMulripleRoles"})
	@Parameters({"LoginNameNotChooseActive","FirstName","LastName","PassWord","SvnLogin","SvnPassWord","LoginNameNotChooseActive"})
	public void testAddUserNotChooseActive(String LoginNameNotChooseActive,String FirstName,String LastName,
			String PassWord,String SvnLogin,String SvnPassWord,String LoginNameNotChooseActive1) throws Exception {
	    
		addUser(LoginNameNotChooseActive, FirstName, LastName, PassWord, SvnLogin, SvnPassWord,"Data Integration");
		
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
	
	//add a user of type choose "Data Integration"
	@Test(dependsOnMethods={"testAddUserNotChooseActive"})
	@Parameters({"LoginName","FirstName","LastName","PassWordww","SvnLogin","SvnPassWord"})
	public void testAddUserRoleDesignerTypeDataIntegration(String LoginName,String FirstName,String LastName,
			String PassWordww,String SvnLogin,String SvnPassWord) throws Exception {
		addUser(LoginName, FirstName, LastName, PassWordww, SvnLogin, SvnPassWord,"Data Integration");

		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+  rb.getString("menu.role.designer")+"')]");//choose a  role
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.designer"));
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginName+"']"));
		Assert.assertTrue(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-userType']" +
		"/img[@alt='Data Integration']"));
		selenium.setSpeed(MIN_SPEED);
	}
	
	//add a user of type choose "Data Quality"
	@Test(dependsOnMethods={"testAddUserRoleDesignerTypeDataIntegration"})
	@Parameters({"LoginNameChooseTypeDataQuality","FirstName","LastName",
		"PassWordww","SvnLogin","SvnPassWord"})
	public void testAddUserTypeChooseDataQuality(String LoginName,String FirstName,String LastName,
			String PassWordww,String SvnLogin,String SvnPassWord) throws Exception {
		addUser(LoginName, FirstName, LastName, PassWordww, SvnPassWord, SvnPassWord,"Data Quality");
        if("Data Quality".equals(selenium.getValue("idTypeInput"))) {
        	
        	selenium.click("idRoleButton");
    		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
    		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"
    				+  rb.getString("menu.role.operationManager")+"')]");//choose a  role
    		selenium.click("idValidateButton");
    		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.operationManager"));
    		selenium.click("idFormSaveButton");
    		selenium.setSpeed(MID_SPEED);
    		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginName+"']"));
    		Assert.assertTrue(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-userType']" +
    				"/img[@alt='Data Quality']"));
    		selenium.setSpeed(MIN_SPEED);
        	
        }
		
	}
	
	//add a user of a wrong username
	@Test(dependsOnMethods={"testAddUserTypeChooseDataQuality"})
	@Parameters({"LoginNameWrongForm","FirstName","LastName","PassWordww","SvnLogin","SvnPassWord"})
    public void testAddUserLoginWrongForm(String LoginName,String FirstName,String LastName,
			String PassWordww,String SvnLogin,String SvnPassWord) throws Exception {
		addUser(LoginName, FirstName, LastName, PassWordww, SvnLogin, SvnPassWord,"Data Integration");
        
    	selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"
				+  rb.getString("menu.role.operationManager")+"')]");//choose a  role
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
	    selenium.setSpeed(MIN_SPEED);
        
	}
	
	//add a user under MySql setting
	@Test
	@Parameters({"LoginNameUnderMySql","FirstName","LastName","PassWordww","SvnLogin","SvnPassWord"})
	public void testUnderMysqlAddUser(String LoginName,String FirstName,String LastName,
			String PassWordww,String SvnLogin,String SvnPassWord) throws Exception {
		addUser(LoginName, FirstName, LastName, PassWordww, SvnLogin, SvnPassWord,"Data Integration");

		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-name' and (text()='"+  rb.getString("menu.role.designer")+"')]");//choose a  role
		selenium.click("idValidateButton");
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.designer"));
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginName+"']"));
		Assert.assertTrue(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-userType']" +
		"/img[@alt='Data Integration']"));
		selenium.setSpeed(MIN_SPEED);
	}
	
}
