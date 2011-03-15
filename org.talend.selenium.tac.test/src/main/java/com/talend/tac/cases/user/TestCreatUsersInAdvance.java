package com.talend.tac.cases.user;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

import static org.testng.Assert.*;

public class TestCreatUsersInAdvance extends Login {
	@Test
	@Parameters( { "user.admin.login", "user.admin.firstName",
			"user.admin.lastName", "user.admin.passwd", "user.allRole.svnLogin",
			"user.allRole.svnPasswd", "user.admin.userRole" })
	public void testCreateRoleAdministrator(String LoginName, String FirstName,
			String LastName, String Passwd, String SvnLogin, String SvnPasswd,
			String UserRole) {

		creatUserProcess(LoginName, FirstName, LastName, Passwd, SvnLogin,
				SvnPasswd, UserRole);

	}

	@Test
	@Parameters( { "user.operationManager.login",
			"user.operationManager.firstName",
			"user.operationManager.lastName", "user.operationManager.passwd",
			"user.allRole.svnLogin",
			"user.allRole.svnPasswd", "user.operationManager.userRole" })
	public void testCreateRoleOperationManager(String LoginName,
			String FirstName, String LastName, String Passwd, String SvnLogin,
			String SvnPasswd, String UserRole) {

		creatUserProcess(LoginName, FirstName, LastName, Passwd, SvnLogin,
				SvnPasswd, UserRole);

	}

	@Test
	@Parameters( { "user.designer.login", "user.designer.firstName",
			"user.designer.lastName", "user.designer.passwd",
			"user.allRole.svnLogin", "user.allRole.svnPasswd",
			"user.designer.userRole" })
	public void testCreateRoleDesigner(String LoginName, String FirstName,
			String LastName, String Passwd, String SvnLogin, String SvnPasswd,
			String UserRole) {

		creatUserProcess(LoginName, FirstName, LastName, Passwd, SvnLogin,
				SvnPasswd, UserRole);

	}

	@Test
	@Parameters( { "user.viewer.login", "user.viewer.firstName",
			"user.viewer.lastName", "user.viewer.passwd",
			"user.allRole.svnLogin", "user.allRole.svnPasswd",
			"user.viewer.userRole" })
	public void testCreateRoleviewer(String LoginName, String FirstName,
			String LastName, String Passwd, String SvnLogin, String SvnPasswd,
			String UserRole) {

		creatUserProcess(LoginName, FirstName, LastName, Passwd, SvnLogin,
				SvnPasswd, UserRole);

	}

	/*
	 * The steps to create a normal account.
	 */
	public void creatUserProcess(String LoginName, String FirstName,
			String LastName, String Passwd, String SvnLogin, String SvnPasswd,
			String UserRole) {
		this.clickWaitForElementPresent("idMenuUserElement");
		selenium.click("idSubModuleAddButton");
		Assert.assertTrue(selenium
				.isElementPresent("//img[@class='gwt-Image x-component ']"));
		selenium.type("idUserLoginInput", LoginName);
		selenium.fireEvent("idUserLoginInput", "blur");
		selenium.type("idUserFirstNameInput", FirstName);
		selenium.fireEvent("idUserFirstNameInput", "blur");
		selenium.type("idUserLastNameInput", LastName);
		selenium.fireEvent("idUserLastNameInput", "blur");
		selenium.type("idUserPasswordInput", Passwd);
		selenium.fireEvent("idUserPasswordInput", "blur");
		selenium.type("//input[@name='authenticationLogin']", SvnLogin);
		selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
		selenium.type("//input[@name='authenticationPassword']", SvnPasswd);
		selenium.fireEvent("//input[@name='authenticationPassword']", "blur");
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb
				.getString("user.roles.title")));
		/*
		 * The Role columns also contains the text(),but with the attribute: style="*;display:none "
		 */
		selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='" + rb.getString(UserRole) + "']");
		selenium.click("idValidateButton");
		selenium.setSpeed("3000");
		selenium.click("idFormSaveButton");
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"
				+ LoginName + "']"));
		selenium.setSpeed("0");
	}

}
