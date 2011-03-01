package com.talend.tac.cases.user;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@Test(groups={"failed"})
public class TestAddUserMulripleRoles extends Login {
    
	@Test
	@Parameters({"userName","LoginNameChooseMulripleRoles","FirstName","LastName","PassWord","SvnLogin","SvnPassWord","LoginNameChooseMulripleRoles"})
	public void testAddUserMulripleRoles(String userName,String LoginNameChooseMulripleRoles,String FirstName,String LastName,String PassWord,String SvnLogin,String SvnPassWord,String LoginNameChooseMulripleRoles1) throws Exception {
		
//		selenium.click("idMenuUserElement");
//		selenium.setSpeed(Base.MIN_SPEED);
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.click("idSubModuleAddButton");
		Assert.assertTrue(selenium.isElementPresent("//img[@class='gwt-Image x-component ']"));
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
		selenium.mouseDown("//div[text()='"+rb.getString("menu.role.designer")+"']");
		selenium.mouseUp("//div[text()='"+rb.getString("menu.role.designer")+"']");
		selenium.setSpeed("3000");
		selenium.keyPressNative(""+KeyEvent.VK_CONTROL);
		selenium.setSpeed("3000");
		selenium.mouseDown("//div[text()='"+rb.getString("menu.role.operationManager")+"']");
		selenium.setSpeed("3000");
		selenium.click("idValidateButton");
		selenium.getValue("idActiveInput");
		System.out.println(selenium.getValue("idActiveInput"));
		Assert.assertEquals(selenium.getValue("idActiveInput"), rb.getString("menu.role.designer")+"/"+rb.getString("menu.role.operationManager"));
		selenium.setSpeed("3000");
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+LoginNameChooseMulripleRoles1+"']"));

	}
    
}
