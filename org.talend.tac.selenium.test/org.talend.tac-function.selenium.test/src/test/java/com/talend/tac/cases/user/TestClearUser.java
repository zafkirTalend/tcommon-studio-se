package com.talend.tac.cases.user;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;
@Test(dependsOnGroups={"two"},dependsOnMethods={"testMysqlAddUserActive"})
public class TestClearUser extends Login{
    
	@Test
	@Parameters({"userName","LoginName","ModifiyUserName","DulicatedUser"})
	public void testClearUser(String userName,String LoginName,String ModifiyUserName,String DulicatedUser) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
//		selenium.click("idMenuUserElement");
//		
		selenium.setSpeed(Base.MAX_SPEED);
		
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.mouseDown("//div[text()='"+LoginName+"']");//Select an existing user
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
	    Assert.assertTrue(selenium.getConfirmation().matches("^"+other.getString("delete.User.confirmation")+" [\\s\\S]$"));
	    selenium.mouseDown("//div[text()='"+ModifiyUserName+"']");//Select an existing user
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
	    Assert.assertTrue(selenium.getConfirmation().matches("^"+other.getString("delete.User.confirmation")+" [\\s\\S]$"));
	    selenium.mouseDown(DulicatedUser);//Select an existing user
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		
	    Assert.assertTrue(selenium.getConfirmation().matches("^"+other.getString("delete.User.confirmation")+" [\\s\\S]$"));

	    selenium.setSpeed(Base.MIN_SPEED);
	}
}
