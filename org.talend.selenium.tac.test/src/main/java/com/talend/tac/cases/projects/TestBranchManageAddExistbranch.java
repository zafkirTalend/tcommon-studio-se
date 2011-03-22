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
	
		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");//
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]", 30);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]");// the selected project's id
		selenium.click("idBranchManagementButton");
		selenium.setSpeed("5000");
		selenium.click("idBranchManagementSourceInput");
		selenium.mouseDown("//div[text()='trunk']");
		selenium.fireEvent("idBranchManagementSourceInput", "blur");
		selenium.setSpeed("0");
		selenium.click("idBranchManagementTargetInput");
		selenium.type("idBranchManagementTargetInput", branchname);
		selenium.fireEvent("idBranchManagementTargetInput", "blur");
		selenium.setSpeed("3000");
		selenium.click("idBranchManagementCreateButton");
		
		
		
		
		// System.out.println(this.getString("project.branchManagement.create.confirm",
		// new String[]{"trunk",branchname}));

		if (selenium.isElementPresent("//span[text()='"
				+ other.getString("project.branchmanage.add.conform") + "']")) {
		
			selenium.click("//button[text()='"
					+ other.getString("project.branchmanage.add.conform.ok")
					+ "']");
			selenium.setSpeed("5000");

			if (selenium
					.isElementPresent("//div[@class='ext-mb-icon  ext-mb-warning']")) {
				
				selenium.click("//button[text()='"
						+ other.getString("project.branchmanage.add.conform.ok.warning")
						+ "']");
			}
		}
		//delete the exist branch
		selenium.setSpeed("5000");
		selenium.mouseDown("//span[text()='" + branchname + "']");
		selenium.contextMenu("//span[text()='" + branchname + "']");
		selenium.mouseOver("delete-item-branch");
		selenium.click("delete-item-branch");
	    Assert.assertTrue(selenium.isElementPresent("//button[text()='Yes']"),"ok button can not be seen!");
		selenium.click("//button[text()='"+other.getString("project.branchmanage.delete.confor.ok")+"']");
		//******************************************

		selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");// close the branch window
		selenium.setSpeed("0");

	}

}
