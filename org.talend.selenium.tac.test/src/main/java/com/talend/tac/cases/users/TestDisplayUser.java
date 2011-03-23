package com.talend.tac.cases.users;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDisplayUser extends Login {

	    
	//modify the users--->>according to role display
	@Test(groups={"DisplayUser"},dependsOnGroups={"DuplicateUser"})
	@Parameters({"userName"})
	public void testDidplayUsers(String userName) throws Exception {
	
		this.clickWaitForElementPresent("idMenuUserElement");
		
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.setSpeed(MID_SPEED);
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-login x-component sort-asc ']/a[1] ");
		selenium.setSpeed(MID_SPEED);
		selenium.click("//a[text()='Show in Groups']");
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
        selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-role x-component']/a[@class='x-grid3-hd-btn'][1]");
	    
        selenium.click("//a[text()='Group By This Field']");
        Assert.assertTrue(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
        selenium.setSpeed(MIN_SPEED);
	}
   
	@Test(dependsOnMethods={"testDidplayUsers"})
	@Parameters({"userName"})
	public void testModifyDisplayUsersColumns(String userName) throws Exception {
		
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-login x-component sort-asc ']/a[1] ");
        selenium.setSpeed(MAX_SPEED);
		selenium.mouseOver("//a[text()='Columns']");
	    selenium.setSpeed(MIN_SPEED);
		modifyDisplayUsersColumns("Login");
		
		modifyDisplayUsersColumns("Role");
		
		modifyDisplayUsersColumns("Role");
		
		modifyDisplayUsersColumns("Last name");
		
        modifyDisplayUsersColumns("First name");
        
        modifyDisplayUsersColumns("Type");
        
        modifyDisplayUsersColumns("Active");
        
        modifyDisplayUsersColumns("Creation");
        
	}
    
    //Modify the user display order  
	@Test(dependsOnMethods={"testModifyDisplayUsersColumns"})
	@Parameters({"userName","LoginNameChooseMulripleRoles","CopyUser","LastName","_LastName","FirstName","_FirstName"})
	public void testModifyDisplayUsersOrder(String userName,String LoginNameChooseMulripleRoles,String CopyUser,
			String lastName,String _lastName,String firstName,String _firstName) throws Exception {
		
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(userName));
	
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-login x-component sort-asc ']/a[1] ");
		selenium.click("//a[text()='Show in Groups']");
		selenium.setSpeed(MIN_SPEED);
		   
		modifyDisplayUsersOrder("login",LoginNameChooseMulripleRoles,CopyUser);
		
		modifyDisplayUsersOrder("role",rb.getString("menu.role.viewer")+"/"+rb.getString("menu.role.designer"),
				rb.getString("menu.role.administrator"));
     
	}
    //hidden users/display users
	@Test(dependsOnMethods={"testModifyDisplayUsersOrder"})
	@Parameters({"userName","LoginName","LoginNameNotChooseActive","LoginNameChooseMulripleRoles"})
	public void testHiddenDidplayUsers(String userName,String LoginName,
			String LoginNameNotChooseActive,String LoginNameChooseMulripleRoles) throws Exception {
		
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium.isTextPresent(userName));
	    selenium.setSpeed(MIN_SPEED);
	    
	    hiddenDisplayUsers(userName, "Role: Administrator (1 Member)");
        hiddenDisplayUsers(LoginName, "Role: Designer (1 Member)");
	    hiddenDisplayUsers(LoginNameNotChooseActive, "Role: Viewer (2 Members)");
	    hiddenDisplayUsers(LoginNameChooseMulripleRoles, "Role: Viewer/Designer (1 Member)");
	    
	    hiddenDisplayUsers(userName, "Role: Administrator (1 Member)");
	    hiddenDisplayUsers(LoginName, "Role: Designer (1 Member)");
	    hiddenDisplayUsers(LoginNameNotChooseActive, "Role: Viewer (2 Members)");
	    hiddenDisplayUsers(LoginNameChooseMulripleRoles, "Role: Viewer/Designer (1 Member)");
	 
	    
	}
	//hidden users/display users    
	public void hiddenDisplayUsers(String userName,String title) {
	 
		if(selenium.isTextPresent(userName) && selenium.isTextPresent(title)) {
			
			if(selenium.isVisible("//div[text()='"+userName+"']")) {
			 
				 selenium.mouseDown("//div[text()='"+title+"']");
				 selenium.setSpeed(MAX_SPEED);
				 Assert.assertFalse(selenium.isVisible("//div[text()='"+userName+"']"));
				 selenium.setSpeed(MIN_SPEED);
			} else {
				 
				 selenium.mouseDown("//div[text()='"+title+"']");
				 selenium.setSpeed(MAX_SPEED);
				 Assert.assertTrue(selenium.isVisible("//div[text()='"+userName+"']"));
				 selenium.setSpeed(MIN_SPEED);
			}
			
		} else {
			
			System.out.println("User does not exist or wrong title");
		}
		
		
	}	
	//modify the users dispaly columns
	public void modifyDisplayUsersColumns(String xpathName) throws Exception {
		

		if(selenium.isElementPresent("//span[text()='"+xpathName+"']")) {
			selenium.click("//a[text()='"+xpathName+"']/img[@class=' x-menu-item-icon']");
			selenium.setSpeed(MAX_SPEED);
		    Assert.assertFalse(selenium.isElementPresent("//span[text()='"+xpathName+"']"));
		    selenium.setSpeed(MIN_SPEED);
		} else {
			
			selenium.setSpeed(MAX_SPEED);
			selenium.click("//a[text()='"+xpathName+"']/img[@class=' x-menu-item-icon']");
		    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+xpathName+"']"));
		    selenium.setSpeed(MIN_SPEED);
		}
		

	}
	//Modify the user display order 
	public void modifyDisplayUsersOrder(String xpathName,String value,String value1) {
		
		selenium.setSpeed(MAX_SPEED);  
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-"+xpathName+" x-component']/a[1]");
		selenium.click("//a[text()='Sort Descending']");
		System.out.println(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+xpathName+"']"));
		Assert.assertEquals(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+xpathName+"']"), value);
        selenium.setSpeed(MIN_SPEED);
       
        selenium.setSpeed(MAX_SPEED);
        selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-"+xpathName+" x-component sort-desc ']/a[1]");
		selenium.click("//a[text()='Sort Ascending']");
		System.out.println(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+xpathName+"']"));
		Assert.assertEquals(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+xpathName+"']"), value1);
		selenium.setSpeed(MIN_SPEED);                                   

	}
}