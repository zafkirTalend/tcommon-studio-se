package com.talend.tac.cases.user;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;


public class AddUser extends Login {
	
	@Test
	@Parameters({"LoginName","FirstName","LastName","PassWord","SvnLogin","SvnPassWord"})
	public void testAddUser(String LoginName,String FirstName,String LastName,String PassWord,String SvnLogin,String SvnPassWord) throws Exception {
	    
		selenium.click("idMenuUserElement");
		selenium.click("idSubModuleAddButton");
		selenium.type("idUserLoginInput", LoginName);
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
		selenium.mouseDown("//div[text()='Designer']");
		selenium.click("idValidateButton");
		selenium.click("idFormSaveButton");

	}
    
}
