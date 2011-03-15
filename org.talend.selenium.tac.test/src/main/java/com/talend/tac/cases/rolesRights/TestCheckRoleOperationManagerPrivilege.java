package com.talend.tac.cases.rolesRights;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestCheckRoleOperationManagerPrivilege extends Login {

	@Test(description="can be improved ")
	public void testCheckRolePrivilege() {
		waitForElementPresent("idLeftMenuTreeLogoutButton",WAIT_TIME );
		assertTrue(selenium.isElementPresent("!!!menu.project.element!!!"));//projects
		assertTrue(selenium.isElementPresent("idMenuConfigElement"));//configurations
//		assertTrue(selenium.isElementPresent("!!!menu.notification.element!!!"));//notification
		assertTrue(selenium.isElementPresent("idMenuChangePasswordElement"));//user Settings
		

	}
}
