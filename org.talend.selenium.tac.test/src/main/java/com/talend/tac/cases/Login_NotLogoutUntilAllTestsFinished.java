package com.talend.tac.cases;

import java.awt.Event;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.talend.tac.base.Base;
/*
 * for the tests which don't need logout every a test/method.
 */
public class Login_NotLogoutUntilAllTestsFinished extends Base {

	@BeforeClass
	@Parameters( { "userName", "userPassword" })
	public void login(String user, String password) {

//		selenium.setSpeed(MID_SPEED);
		
		selenium.windowMaximize();
		this.waitForElementPresent("idLoginInput", WAIT_TIME);
		selenium.type("idLoginInput", user);
		
		String pwValue = selenium.getValue("idLoginPasswordInput");
		if( pwValue==null || "".equals(pwValue) ) {
			selenium.typeKeys("idLoginPasswordInput", password);
			selenium.type("idLoginPasswordInput", password);
		} 
		selenium.keyPressNative(Event.TAB +"");
		this.waitForElementPresent("idLoginInput", Base.WAIT_TIME);
		selenium.click("idLoginButton");
		selenium.setSpeed(MID_SPEED);
		if (selenium
				.isTextPresent("Failed to log on: user admin@company.com already logged on to webapp")) {
			selenium.click("idLoginForceLogoutButton");
			selenium.click("idLoginButton");
		}
		selenium.setSpeed(MIN_SPEED);
		// selenium.waitForPageToLoad("30000");// no need
	}

	@AfterClass
	public void killBroswer() {
		selenium.click("idLeftMenuTreeLogoutButton");
		selenium.stop();
	}

	
}
