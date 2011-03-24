package com.talend.tac.cases.projects;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestBranchManageAddExistbranch extends Login {
	@Test(dependsOnGroups = { "AddBranch" })
	@Parameters({ "BranchProject", "BranchName" })
	public void addProjectbranchexist(String project, String branchname) {
		// first add a branch to a project
		selenium.setSpeed(MAX_SPEED);
		selenium.click("!!!menu.project.element!!!");//
//		System.out.println(selenium.getBodyText());
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]");// the selected project's id
		selenium.click("idBranchManagementButton");
		selenium.setSpeed(MAX_SPEED);
		selenium.click("idBranchManagementSourceInput");
		selenium.mouseDown("//div[text()='trunk']");
		selenium.fireEvent("idBranchManagementSourceInput", "blur");
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idBranchManagementTargetInput");
		selenium.type("idBranchManagementTargetInput", branchname);
		selenium.fireEvent("idBranchManagementTargetInput", "blur");
		selenium.setSpeed(MID_SPEED);
		selenium.click("idBranchManagementCreateButton");

		if (selenium.isElementPresent("//span[text()='"
				+ other.getString("project.branchmanage.add.conform") + "']")) {

			selenium.click("//button[text()='"
					+ other.getString("project.branchmanage.add.conform.ok")
					+ "']");
			selenium.setSpeed(MAX_SPEED);

			if (selenium
					.isElementPresent("//div[@class='ext-mb-icon  ext-mb-warning']")) {

				selenium.click("//button[text()='"
						+ other.getString("project.branchmanage.add.conform.ok.warning")
						+ "']");
			}
		}
		// delete the exist branch
		selenium.setSpeed(MAX_SPEED);
		selenium.mouseDown("//span[text()='" + branchname + "']");
		selenium.contextMenu("//span[text()='" + branchname + "']");
		selenium.mouseOver("delete-item-branch");
		selenium.click("delete-item-branch");
		Assert.assertTrue(selenium.isElementPresent("//button[text()='Yes']"),
				"ok button can not be seen!");
		selenium.click("//button[text()='"
				+ other.getString("project.branchmanage.delete.confor.ok")
				+ "']");

		selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");// close
																					// the
																					// branch
																					// window
		selenium.setSpeed(MIN_SPEED);
		//check the new close button
		selenium.refresh();
		closeButton(project, branchname);

	}

	public void closeButton(String project, String branchname) {
		selenium.setSpeed(MAX_SPEED);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]");// the selected project's id
		selenium.click("idBranchManagementButton");
		
		
		
		selenium.click("idBranchManagementSourceInput");
		selenium.mouseDown("//div[text()='trunk']");
		selenium.fireEvent("idBranchManagementSourceInput", "blur");
		
		selenium.click("idBranchManagementTargetInput");
		selenium.type("idBranchManagementTargetInput", branchname);
		selenium.fireEvent("idBranchManagementTargetInput", "blur");
		selenium.click("idBranchManagementCloseButton");
		Assert.assertFalse(
				selenium.isElementPresent("idBranchManagementCloseButton"),
				"Button idBranchManagementCloseButton have no effect!");
		selenium.setSpeed(MIN_SPEED);
	}

}
