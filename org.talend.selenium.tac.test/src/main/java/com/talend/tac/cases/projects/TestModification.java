package com.talend.tac.cases.projects;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;
public class TestModification extends Login {

	@Test(groups = { "Second" },dependsOnGroups={"Add"})
	public void testIsModifyenable() {
		
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);
		selenium.click("!!!menu.project.element!!!");
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label']", Base.WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label']");
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue((!selenium.isEditable("idLabelInput"))
				&& (!selenium.isEditable("idLanguageInput")), "idLableInput or idLanguageInput is editable!");
		selenium.setSpeed(MIN_SPEED);
		
	}
}
