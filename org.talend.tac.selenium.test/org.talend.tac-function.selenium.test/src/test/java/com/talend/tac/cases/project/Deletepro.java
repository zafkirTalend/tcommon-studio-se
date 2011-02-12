package com.talend.tac.cases.project;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;

public class Deletepro extends Login {

	public void cancelDelete() {
		selenium.click("!!!menu.project.element!!!");
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label']");
		selenium.chooseCancelOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		assert (selenium.getConfirmation()
				.matches("^Are you sure you want to remove the selected user [\\s\\S]$"));
		// selenium.setSpeed("3000");
	}

	public void okDelete() {
		selenium.click("!!!menu.project.element!!!");
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label']");
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		assert (selenium.getConfirmation()
				.matches("^Are you sure you want to remove the selected user [\\s\\S]$"));

	}

	@Test
	public void testAddpro() throws Exception {

		selenium.setSpeed("5000");
		cancelDelete();
		okDelete();

	}

	@AfterMethod
	public void tearDown() throws Exception {
		selenium.stop();
	}

}
