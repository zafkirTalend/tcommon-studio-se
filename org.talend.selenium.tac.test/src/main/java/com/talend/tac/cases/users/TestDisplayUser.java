package com.talend.tac.cases.users;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@Test(groups={"DisplayUser"},dependsOnGroups={"DuplicateUser"})
public class TestDisplayUser extends Login {
    
	
	@Test
	@Parameters({"userName"})
	public void testDidplayUsers(String userName) throws Exception {
		waitForElementPresent("idMenuUserElement", 20);
		
		selenium.click("idMenuUserElement");
		
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.setSpeed(MAX_SPEED);
		selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-login x-component sort-asc ']/a[1] ");
		selenium.setSpeed(MAX_SPEED);
		selenium.click("//a[text()='Show in Groups']");
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
        selenium.click("//div[@class=' x-grid3-hd-inner x-grid3-hd-role x-component']/a[@class='x-grid3-hd-btn'][1]");
	    
        selenium.click("//a[text()='Group By This Field']");
        Assert.assertTrue(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
        selenium.setSpeed(MIN_SPEED);
	}

    
}
