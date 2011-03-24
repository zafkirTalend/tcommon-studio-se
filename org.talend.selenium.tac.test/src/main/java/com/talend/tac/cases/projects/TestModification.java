package com.talend.tac.cases.projects;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;
public class TestModification extends Login {

	@Test(groups = { "Second" },dependsOnGroups={"Add"})
	public void testIsModifyenable() {
		
		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");
		selenium.setSpeed(MID_SPEED);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label']");
		Assert.assertTrue((!selenium.isEditable("idLabelInput"))
				&& (!selenium.isEditable("idLanguageInput")), "idLableInput or idLanguageInput is editable!");
		selenium.setSpeed(MIN_SPEED);
		
	}
}
