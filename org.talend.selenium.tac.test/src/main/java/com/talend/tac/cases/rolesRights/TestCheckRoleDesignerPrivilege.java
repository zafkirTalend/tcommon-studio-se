package com.talend.tac.cases.rolesRights;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestCheckRoleDesignerPrivilege extends Login {

	@Test(description="can be improved ")
	public void testCheckRoleDesignerPrivilege() {
		waitForElementPresent("idLeftMenuTreeLogoutButton",WAIT_TIME );
		assertTrue(selenium.isElementPresent("!!!menu.project.element!!!"));//projects
		assertTrue(selenium.isElementPresent("idMenuConfigElement"));//configurations
		assertTrue(selenium.isElementPresent("idMenuChangePasswordElement"));//user Settings
		

	}
}
