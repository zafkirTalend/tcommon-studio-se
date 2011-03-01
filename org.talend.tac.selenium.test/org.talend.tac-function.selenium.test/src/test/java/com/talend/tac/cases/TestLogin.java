package com.talend.tac.cases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import com.talend.tac.base.Base;

public class TestLogin extends Base {

	@Test
	@Parameters( { "login.invalidLogin", "login.validPasswd" })
	public void testWrongLogin(String user, String password) {
		waitForElementPresent("idLoginInput", 20);
		waitForElementPresent("idLoginPasswordInput", 20);
		selenium.windowMaximize();
		selenium.type("idLoginInput", user);
		selenium.type("idLoginPasswordInput", password);
		selenium.keyDown("idLoginPasswordInput", "\\13");

		selenium.setSpeed(MAX_SPEED);
		assertTrue(selenium.isTextPresent(this.getString(
				"login.error.unknownUser", user)));// unknown user
		selenium.setSpeed(Base.MIN_SPEED);
	}

//	@Test
//	@Parameters( { "login.validLogin", "login.InvalidPasswd" })
//	public void testWrongPasswd(String user, String password) {
//		waitForElementPresent("idLoginInput", 20);
//		waitForElementPresent("idLoginPasswordInput", 20);
//		selenium.windowMaximize();
//		selenium.type("idLoginInput", user);
//		selenium.type("idLoginPasswordInput", password);
//		selenium.keyDown("idLoginPasswordInput", "\\13");
//		
//		selenium.setSpeed(Base.MAX_SPEED);
//
//		assertTrue(selenium.isTextPresent(rb
//				.getString("login.error.badPassword")));// Incorrect password
//		selenium.setSpeed(Base.MIN_SPEED);
//	}
//
//	@Test
//	@Parameters( { "login.validLogin", "login.validPasswd" })
//	public void testLogin(String user, String password) {
//		waitForElementPresent("idLoginInput", 20);
//		waitForElementPresent("idLoginPasswordInput", 20);
//		selenium.windowMaximize();
//		selenium.type("idLoginInput", user);
//		selenium.type("idLoginPasswordInput", password);
//		selenium.keyDown("idLoginPasswordInput", "\\13");
//
//		// wait for this HTML page fully loaded
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		if (selenium.isTextPresent("Failed to log on: user " + user
//				+ " already logged on to webapp")) {
//			selenium.click("idLoginForceLogoutButton");
//			selenium.keyDown("idLoginPasswordInput", "\\13");
//		}
//		// assertTrue(selenium.isElementPresent("idLeftMenuTreeLogoutButton"));
////		this.waitForElementPresent("idLeftMenuTreeLogoutButton", 20);
//		selenium.setSpeed(MAX_SPEED);
//		
//		selenium.click("idLeftMenuTreeLogoutButton");
//		
//		selenium.setSpeed(MIN_SPEED);
//	}

	@AfterTest
	public void logout() {
		// selenium.click("idLeftMenuTreeLogoutButton");
		selenium.stop();
	}

}
