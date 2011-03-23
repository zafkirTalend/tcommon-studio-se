package com.talend.tac.cases.users;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@Test(groups={"DuplicateUser"},dependsOnGroups={"DeleteUser"})
public class TestDuplicateUser extends Login {
    
	
	
	@Test
	@Parameters({"userName","DulicateUser","PassWord","DulicatedUser"})
	public void testDuplicateUser(String userName,String DulicateUser,String Password,String DulicatedUser) throws Exception {
	    
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent(userName));
		
		if(selenium.isTextPresent("Copy_of_aaa@gmail.com")) {
			System.out.println("Copy_of_aaa@gmail.com is exist");
			
		} else {

			selenium.mouseDown(DulicateUser);//Select an existing user
			selenium.click("idSubModuleDuplicateButton");
			selenium.type("idUserPasswordInput", Password);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.click("idUserPasswordInput");
	///****/
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][1]", ""+Event.ENTER);
	///****/  
			selenium.click("idFirstAdminConnInput");
			selenium.setSpeed(MID_SPEED);
			selenium.click("idFormSaveButton");
			Assert.assertTrue(selenium.isElementPresent(DulicatedUser));
			selenium.setSpeed(MIN_SPEED);

		}
		
	}
	
}
