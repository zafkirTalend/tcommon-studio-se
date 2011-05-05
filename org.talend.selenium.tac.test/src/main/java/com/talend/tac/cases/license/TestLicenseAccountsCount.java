package com.talend.tac.cases.license;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;

public class TestLicenseAccountsCount extends Login {

	
//	@Test
	public void testAddDIChangeToDQ() throws InterruptedException{
		cleanAllExceptAdmin();
		int n = getDQcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = "";
		String name = "";
		for (int i = 0; i < n - 1; i++) {
			username = "DQ_" + i + "@talend.com";
			// System.out.println("ดÞ"+username);
			name = "DQ_" + i;
			//
			selenium.refresh();
			Thread.sleep(3000);
			selenium.click("idSubModuleAddButton");
			selenium.type("idUserLoginInput", username);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", name);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", name);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", name);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", "admin");
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", "admin");
			selenium.fireEvent("//input[@name='authenticationPassword']",
					"blur");
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][2]", "" + Event.ENTER);
			this.selectDropDownList("idTypeInput", 2);
			Assert.assertEquals(selenium.getValue("idTypeInput"),
					"Data Quality");
			String roles = rb.getString("menu.role.administrator") + "/"
					+ rb.getString("menu.role.viewer") + "/"
					+ rb.getString("menu.role.operationManager") + "/"
					+ rb.getString("menu.role.designer");
			selenium.click("idRoleButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isTextPresent(rb
					.getString("user.roles.title")));
			selenium.setSpeed(MIN_SPEED);
			selenium.controlKeyDown();
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.administrator")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.viewer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.operationManager")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.designer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.controlKeyUp();
			selenium.click("idValidateButton");
			selenium.setSpeed(MAX_SPEED);
			Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
			//
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			// System.out.println("ีย"+username);
			Assert.assertTrue(selenium.isTextPresent(username),
					"testAddMaxAmountsDQUsersAllowed  failed!");
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(3000);
			Assert.assertTrue(selenium.getValue("//input[@name='active']")
					.equals("on"));
			selenium.setSpeed(MIN_SPEED);
		}
		selenium.refresh();
		Thread.sleep(3000);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']");
		Thread.sleep(2000);
//		selenium.click("idTypeInput");
//		selenium.mouseDownAt("//div[@role='listitem'][2]", "" + Event.ENTER);
		this.selectDropDownList("idTypeInput", 2);
		Assert.assertEquals(selenium.getValue("idTypeInput"),
				"Data Quality");
		selenium.setSpeed(MID_SPEED);
		selenium.click("idFormSaveButton");
//		Assert.assertTrue(selenium.isTextPresent("Save failed: You are using "+(n-1)+" DQ users, but your license allows only "+(n-1)+", please contact your talend account manager -- For more information see your log file"));
//		selenium.setSpeed(MIN_SPEED);
//		Thread.sleep(5000);
		selenium.refresh();
		Thread.sleep(3000);
		selenium.setSpeed(MIN_SPEED);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']");
		Thread.sleep(5000);
		Assert.assertEquals(selenium.getValue("idTypeInput"),
		"Data Integration");
		cleanAllExceptAdmin();
	}
//	@Test
	public void testAddDQChangeToDI() throws InterruptedException{
		cleanAllExceptAdmin();
		int n = getDQcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = "";
		String name = "";
		for (int i = 0; i < n - 1; i++) {
			username = "DQ_" + i + "@talend.com";
			// System.out.println("ดÞ"+username);
			name = "DQ_" + i;
			//
			selenium.refresh();
			Thread.sleep(3000);
			selenium.click("idSubModuleAddButton");
			selenium.type("idUserLoginInput", username);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", name);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", name);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", name);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", "admin");
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", "admin");
			selenium.fireEvent("//input[@name='authenticationPassword']",
					"blur");
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][2]", "" + Event.ENTER);
			this.selectDropDownList("idTypeInput", 2);
			Assert.assertEquals(selenium.getValue("idTypeInput"),
					"Data Quality");
			String roles = rb.getString("menu.role.administrator") + "/"
					+ rb.getString("menu.role.viewer") + "/"
					+ rb.getString("menu.role.operationManager") + "/"
					+ rb.getString("menu.role.designer");
			selenium.click("idRoleButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isTextPresent(rb
					.getString("user.roles.title")));
			selenium.setSpeed(MIN_SPEED);
			selenium.controlKeyDown();
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.administrator")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.viewer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.operationManager")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.designer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.controlKeyUp();
			selenium.click("idValidateButton");
//			selenium.setSpeed(MAX_SPEED);
			Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
			//
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			// System.out.println("ีย"+username);
			Assert.assertTrue(selenium.isTextPresent(username),
					"testAddMaxAmountsDQUsersAllowed  failed!");
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(3000);
			Assert.assertTrue(selenium.getValue("//input[@name='active']")
					.equals("on"));
			selenium.setSpeed(MIN_SPEED);
		}
		//change DQ users added type from DI to DQ
		for (int i = 0; i < n-1; i++) {
			username = "DQ_" + i + "@talend.com";
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(2000);
			Assert.assertEquals(selenium.getValue("idTypeInput"),
			"Data Quality");
			String valuebefore =selenium.getValue("idTypeInput");
			Assert.assertTrue(valuebefore.equals("Data Quality"));
			selenium.setSpeed(MID_SPEED);
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][1]", "" + Event.ENTER);
//			selenium.fireEvent("idTypeInput", "blur");
			this.selectDropDownList("idTypeInput", 1);
			System.out.println("นþนþ"+selenium.getValue("idTypeInput"));
			Assert.assertEquals(selenium.getValue("idTypeInput"),
					"Data Integration");
			
			
			selenium.click("idRoleButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isTextPresent(rb
					.getString("user.roles.title")));
			selenium.setSpeed(MIN_SPEED);
			selenium.controlKeyDown();
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.administrator")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.viewer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.operationManager")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
//			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
//					+ rb.getString("menu.role.designer")
//					+ "']"
//					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.controlKeyUp();
			selenium.click("idValidateButton");
			
			
			selenium.click("idFormSaveButton");
			Thread.sleep(3000);
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(3000);
			String valueafter = selenium.getValue("idTypeInput");
			System.out.println("nishi :"+valueafter);
			if (i == n - 2) {
				// Assert.assertTrue(selenium.isTextPresent("Save failed: You are using "+(n-1)+" DQ users, but your license allows only "+(n-1)+", please contact your talend account manager -- For more information see your log file"),
				// "test failed!");
				Assert.assertFalse(valueafter.equals("Data Integration"));
			} else {
				Assert.assertTrue(valueafter.equals("Data Integration"));
			}
			selenium.setSpeed(MIN_SPEED);
		}
		cleanAllExceptAdmin();
		
	}
//	@Test
	public void testAddMaxAmountsDIUsersAllowed() throws InterruptedException {
		cleanAllExceptAdmin();
		int n = getDIcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = selenium
				.getText("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']");
		for (int i = 0; i < n - 2; i++) {
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(2000);
			username = "Copy_of_" + username;
			// System.out.println(username);
			selenium.click("idSubModuleDuplicateButton");
			Thread.sleep(2000);
			this.typeString("idUserPasswordInput", "admin");
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			Assert.assertTrue(selenium.isTextPresent(username),
					"testAddMaxAmountsDIUsersAllowed  failed!");
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(3000);
			Assert.assertTrue(selenium.getValue("//input[@name='active']")
					.equals("on"));
		}
		cleanAllExceptAdmin();
	}

//	@Test
	public void testAddMaxAmountsDQUsersAllowed() throws InterruptedException {
		cleanAllExceptAdmin();
		int n = getDQcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = "";
		String name = "";
		for (int i = 0; i < n - 1; i++) {
			username = "DQ_" + i + "@talend.com";
			// System.out.println("ดÞ"+username);
			name = "DQ_" + i;
			//
			selenium.refresh();
			Thread.sleep(3000);
			selenium.click("idSubModuleAddButton");
			selenium.type("idUserLoginInput", username);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", name);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", name);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", name);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", "admin");
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", "admin");
			selenium.fireEvent("//input[@name='authenticationPassword']",
					"blur");
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][2]", "" + Event.ENTER);
			this.selectDropDownList("idTypeInput", 2);
			Assert.assertEquals(selenium.getValue("idTypeInput"),
					"Data Quality");
			String roles = rb.getString("menu.role.administrator") + "/"
					+ rb.getString("menu.role.viewer") + "/"
					+ rb.getString("menu.role.operationManager") + "/"
					+ rb.getString("menu.role.designer");
			selenium.click("idRoleButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isTextPresent(rb
					.getString("user.roles.title")));
			selenium.setSpeed(MIN_SPEED);
			selenium.controlKeyDown();
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.administrator")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.viewer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.operationManager")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.designer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.controlKeyUp();
			selenium.click("idValidateButton");
//			selenium.setSpeed(MAX_SPEED);
			Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
			//
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			// System.out.println("ีย"+username);
			Assert.assertTrue(selenium.isTextPresent(username),
					"testAddMaxAmountsDQUsersAllowed  failed!");
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(3000);
			Assert.assertTrue(selenium.getValue("//input[@name='active']")
					.equals("on"));
			selenium.setSpeed(MIN_SPEED);
		}

		cleanAllExceptAdmin();
	}

	@Test
	public void testDIUsersMoreOver() throws InterruptedException {
		cleanAllExceptAdmin();
		int n = getDIcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = selenium
				.getText("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']");
		for (int i = 0; i < n - 1; i++) {
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(2000);
			username = "Copy_of_" + username;
			// System.out.println(username);
			selenium.click("idSubModuleDuplicateButton");
			Thread.sleep(2000);
			this.typeString("idUserPasswordInput", "admin");
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			if (i != n - 2) {
				Assert.assertTrue(selenium.isTextPresent(username),
						"test di users more over failed!");
			} else {
//				Assert.assertTrue(
//						selenium.isTextPresent("Save failed: You are using "
//								+ (n - 1)
//								+ " DI users, but your license allows only "
//								+ (n - 1)
//								+ ", please contact your talend account manager -- For more information see your log file"),
//						"test failed!");
				Assert.assertFalse(selenium.isTextPresent(username),
						"test di users more over failed!");
			}
		}
		cleanAllExceptAdmin();
	}

//	@Test
	public void testDIUsersMoreOverUnactive() throws InterruptedException {
		cleanAllExceptAdmin();
		int n = getDIcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = selenium
				.getText("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']");
		for (int i = 0; i < n + 5; i++) {
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(2000);
			username = "Copy_of_" + username;
			// System.out.println(username);
			selenium.click("idSubModuleDuplicateButton");
			Thread.sleep(2000);
			this.typeString("idUserPasswordInput", "admin");
			// uncheck active
			if (i == 0) {
				selenium.click("//input[@name='active']");
			}

			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			Assert.assertTrue(selenium.isTextPresent(username),
					"user duplicated failed!");
		}
		cleanAllExceptAdmin();
	}

//	@Test
	public void testDIUsersMoreOverUnactiveReactive()
			throws InterruptedException {
		cleanAllExceptAdmin();
		int n = getDIcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = selenium
				.getText("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']");
		String usernameReuse = username;
		for (int i = 0; i < n + 5; i++) {
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(2000);
			username = "Copy_of_" + username;
			// System.out.println(username);
			selenium.click("idSubModuleDuplicateButton");
			Thread.sleep(2000);
			this.typeString("idUserPasswordInput", "admin");
			// uncheck active
			if (i == 0) {
				selenium.click("//input[@name='active']");
			}
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			Assert.assertTrue(selenium.isTextPresent(username),
					"user duplicated failed!");
		}
		for (int j = 0; j < n - 1; j++) {
			selenium.refresh();
			Thread.sleep(3000);
			usernameReuse = "Copy_of_" + usernameReuse;

			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ usernameReuse + "']");
			Thread.sleep(2000);
			String valuebefore = selenium.getValue("//input[@name='active']");
			// System.out.println(valuebefore);
			Assert.assertTrue(valuebefore.equals("off"));
			selenium.click("//input[@name='active']");
			selenium.setSpeed(MID_SPEED);
			selenium.click("idFormSaveButton");
			Thread.sleep(3000);
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ usernameReuse + "']");
			Thread.sleep(3000);
			String valueafter = selenium.getValue("//input[@name='active']");
			// System.out.println(valueafter);
			if (j == n - 2) {// the message needs to be make sure whether is
								// right
				// Assert.assertTrue(selenium.isTextPresent("Save failed: You are using "+(n-1)+" DI users, but your license allows only "+(n-1)+", please contact your talend account manager -- For more information see your log file"),
				// "test failed!");
				Assert.assertFalse(valueafter.equals("on"));
			} else {
				Assert.assertTrue(valueafter.equals("on"));
			}
			selenium.setSpeed(MIN_SPEED);

		}
		cleanAllExceptAdmin();
	}

	@Test
	public void testDQUsersMoreOver() throws InterruptedException {
		cleanAllExceptAdmin();
		int n = getDQcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = "";
		String name = "";
		for (int i = 0; i < n; i++) {
			username = "DQ_" + i + "@talend.com";
			name = "DQ_" + i;
			//
			selenium.refresh();
			this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
			selenium.click("idSubModuleAddButton");
			selenium.type("idUserLoginInput", username);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", name);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", name);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", name);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", "admin");
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", "admin");
			selenium.fireEvent("//input[@name='authenticationPassword']",
					"blur");
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][2]", "" + Event.ENTER);
			this.selectDropDownList("idTypeInput", 2);
			Assert.assertEquals(selenium.getValue("idTypeInput"),
					"Data Quality");
			String roles = rb.getString("menu.role.administrator") + "/"
					+ rb.getString("menu.role.viewer") + "/"
					+ rb.getString("menu.role.operationManager") + "/"
					+ rb.getString("menu.role.designer");
			selenium.click("idRoleButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isTextPresent(rb
					.getString("user.roles.title")));
			selenium.setSpeed(MIN_SPEED);
			selenium.controlKeyDown();
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.administrator")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.viewer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.operationManager")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.designer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.controlKeyUp();
			selenium.click("idValidateButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
			//
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			if (i != n - 1) {
				Assert.assertTrue(selenium.isTextPresent(username),
						"test dq users more over failed!");
			} else {
//				Assert.assertTrue(
//						selenium.isTextPresent("Save failed: You are using "
//								+ (n - 1)
//								+ " DQ users, but your license allows only "
//								+ (n - 1)
//								+ ", please contact your talend account manager -- For more information see your log file"),
//						"test failed!");
				Assert.assertFalse(selenium.isTextPresent(username),
						"test dq users more over failed!");
			}
			selenium.setSpeed(MIN_SPEED);
		}

		cleanAllExceptAdmin();

	}

//	@Test
	public void testDQUsersMoreOverUnactive() throws InterruptedException {
		cleanAllExceptAdmin();
		int n = getDQcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = "";
		String name = "";
		for (int i = 0; i < n + 5; i++) {
			username = "DQ_" + i + "@talend.com";
			name = "DQ_" + i;
			//
			selenium.refresh();
			this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
			selenium.click("idSubModuleAddButton");
			selenium.type("idUserLoginInput", username);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", name);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", name);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", name);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", "admin");
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", "admin");
			selenium.fireEvent("//input[@name='authenticationPassword']",
					"blur");
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][2]", "" + Event.ENTER);
			this.selectDropDownList("idTypeInput", 2);
			Assert.assertEquals(selenium.getValue("idTypeInput"),
					"Data Quality");
			String roles = rb.getString("menu.role.administrator") + "/"
					+ rb.getString("menu.role.viewer") + "/"
					+ rb.getString("menu.role.operationManager") + "/"
					+ rb.getString("menu.role.designer");
			selenium.click("idRoleButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isTextPresent(rb
					.getString("user.roles.title")));
			selenium.setSpeed(MIN_SPEED);
			selenium.controlKeyDown();
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.administrator")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.viewer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.operationManager")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.designer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.controlKeyUp();
			selenium.click("idValidateButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
			selenium.click("//input[@name='active']");
			//
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			Assert.assertTrue(selenium.isTextPresent(username),
					"user duplicated failed!");
			selenium.setSpeed(MIN_SPEED);
		}
		cleanAllExceptAdmin();
	}

//	@Test
	public void testDQUsersMoreOverUnactiveReactive()
			throws InterruptedException {
		cleanAllExceptAdmin();
		int n = getDQcounts();
		this.waitForElementPresent("idMenuUserElement", WAIT_TIME);
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idMenuUserElement");
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='admin@company.com']",
				WAIT_TIME);
		String username = "";
		String name = "";
		for (int i = 0; i < n + 5; i++) {
			username = "DQ_" + i + "@talend.com";
			name = "DQ_" + i;
			//
			selenium.refresh();
			this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
			selenium.click("idSubModuleAddButton");
			selenium.type("idUserLoginInput", username);
			selenium.fireEvent("idUserLoginInput", "blur");
			selenium.type("idUserFirstNameInput", name);
			selenium.fireEvent("idUserFirstNameInput", "blur");
			selenium.type("idUserLastNameInput", name);
			selenium.fireEvent("idUserLastNameInput", "blur");
			selenium.type("idUserPasswordInput", name);
			selenium.fireEvent("idUserPasswordInput", "blur");
			selenium.type("//input[@name='authenticationLogin']", "admin");
			selenium.fireEvent("//input[@name='authenticationLogin']", "blur");
			selenium.type("//input[@name='authenticationPassword']", "admin");
			selenium.fireEvent("//input[@name='authenticationPassword']",
					"blur");
//			selenium.click("idTypeInput");
//			selenium.mouseDownAt("//div[@role='listitem'][2]", "" + Event.ENTER);
			this.selectDropDownList("idTypeInput", 2);
			Assert.assertEquals(selenium.getValue("idTypeInput"),
					"Data Quality");
			String roles = rb.getString("menu.role.administrator") + "/"
					+ rb.getString("menu.role.viewer") + "/"
					+ rb.getString("menu.role.operationManager") + "/"
					+ rb.getString("menu.role.designer");
			selenium.click("idRoleButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertTrue(selenium.isTextPresent(rb
					.getString("user.roles.title")));
			selenium.setSpeed(MIN_SPEED);
			selenium.controlKeyDown();
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.administrator")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.viewer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.operationManager")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.mouseDown("//td[not(contains(@style,'display: none'))]/div[text()='"
					+ rb.getString("menu.role.designer")
					+ "']"
					+ "/parent::td/preceding-sibling::td//div[@class='x-grid3-row-checker']");
			selenium.controlKeyUp();
			selenium.click("idValidateButton");
			selenium.setSpeed(MID_SPEED);
			Assert.assertEquals(selenium.getValue("idActiveInput"), roles);
			selenium.click("//input[@name='active']");
			//
			selenium.click("idFormSaveButton");
			Thread.sleep(5000);
			Assert.assertTrue(selenium.isTextPresent(username),
					"user duplicated failed!");
			selenium.setSpeed(MIN_SPEED);
		}
		for (int i = 0; i < n; i++) {
			username = "DQ_" + i + "@talend.com";
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(2000);
			String valuebefore = selenium.getValue("//input[@name='active']");
			Assert.assertTrue(valuebefore.equals("off"));
			selenium.click("//input[@name='active']");
			selenium.click("idFormSaveButton");
			Thread.sleep(3000);
			selenium.refresh();
			Thread.sleep(3000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-login' and text()='"
					+ username + "']");
			Thread.sleep(3000);
			String valueafter = selenium.getValue("//input[@name='active']");
			if (i == n - 1) {
				// Assert.assertTrue(selenium.isTextPresent("Save failed: You are using "+(n-1)+" DQ users, but your license allows only "+(n-1)+", please contact your talend account manager -- For more information see your log file"),
				// "test failed!");
				Assert.assertFalse(valueafter.equals("on"));
			} else {
				Assert.assertTrue(valueafter.equals("on"));
			}
		}
		cleanAllExceptAdmin();
	}

	public int getDIcounts() {
		float moreOver = 0.25f;
		this.waitForElementPresent("idMenuLicenseElement", WAIT_TIME);
		selenium.click("idMenuLicenseElement");
		this.waitForElementPresent(
				"//span[@class='x-panel-header-text' and text()='License Parameters']",
				WAIT_TIME);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String allowedDI = selenium
				.getText("//div[@class='x-progress-text x-progress-text-back']//div[text()='Defined Data Integration users 1 / 5']");
		String num = allowedDI.split("/")[1];
		num = num.substring(num.indexOf(" ") + 1, num.length());
		return ((int) Math.ceil((Integer.parseInt(num)) * (moreOver + 1.0)));

	}

	public int getDQcounts() {
		float moreOver = 0.25f;
		this.waitForElementPresent("idMenuLicenseElement", WAIT_TIME);
		selenium.click("idMenuLicenseElement");
		this.waitForElementPresent(
				"//span[@class='x-panel-header-text' and text()='License Parameters']",
				WAIT_TIME);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String allowedDI = selenium
				.getText("//div[@class='x-progress-text x-progress-text-back']//div[text()='Defined Data Quality users 0 / 5']");
		String num = allowedDI.split("/")[1];
		num = num.substring(num.indexOf(" ") + 1, num.length());
		return ((int) Math.ceil((Integer.parseInt(num)) * (moreOver + 1.0)));
	}

	public void cleanAllExceptAdmin() {
		List<String> users = new ArrayList<String>();
		this.clickWaitForElementPresent("idMenuUserElement");
		users = this.findSpecialMachedStrings(".*@[a-zA-Z0-9]*\\.com");
		// users = this.findSpecialMachedStrings(".[A-Za-z]*\\ to ");
		for (int i = 0; i < users.size(); i++) {
			// System.out.println(users.get(i));
			if (!"admin@company.com".equals(users.get(i))) {
				selenium.mouseDown("//div[text()='" + users.get(i) + "']");
				selenium.chooseOkOnNextConfirmation();
				selenium.click("idSubModuleDeleteButton");
				selenium.setSpeed(MAX_SPEED);
				Assert.assertTrue(selenium.getConfirmation().matches(
						"^" + other.getString("delete.User.confirmation")
								+ " [\\s\\S]$"));
				selenium.setSpeed(MIN_SPEED);
			}

		}
	}
}
