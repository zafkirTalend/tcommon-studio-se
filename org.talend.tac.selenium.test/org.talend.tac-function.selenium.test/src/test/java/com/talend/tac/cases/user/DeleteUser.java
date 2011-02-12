package com.talend.tac.cases.user;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class DeleteUser extends Login {

	@Test
	@Parameters({"User"})
	public void testDeleteUser(String User) throws Exception {

		selenium.click("idMenuUserElement");
		selenium.mouseDown(User);//Select an existing user
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected user [\\s\\S]$"));
	}
}
