package com.talend.tac.cases.project;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;
public class TestModification extends Login {

	@Test(groups = { "Second" },dependsOnGroups={"Add"})
	public void isModifyenable() {
		  if(selenium == null){
			  System.out.println("selenium Ϊ��!");
		  }
		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");
		
//		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label']", 60);
		
		selenium.setSpeed(MAX_SPEED);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label']");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		if (selenium.isEditable("idLabelInput")
//				|| selenium.isEditable("idLanguageInput")) {
//			assert false : "modification test failed";
//		} else {
//			assert true;
//		}
		Assert.assertTrue((!selenium.isEditable("idLabelInput"))
				&& (!selenium.isEditable("idLanguageInput")), "idLableInput or idLanguageInput is editable!");
		
		selenium.setSpeed(MIN_SPEED);
	}
}
