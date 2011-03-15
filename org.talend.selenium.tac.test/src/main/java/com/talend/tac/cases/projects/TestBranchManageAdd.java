package com.talend.tac.cases.projects;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;

public class TestBranchManageAdd extends Login {
	@Test(groups = { "AddBranch" }, dependsOnGroups = { "Add" })
	@Parameters({ "BranchProject", "BranchName" })
	public void addProjectbranch(String project, String branchname) {
		// first add a branch to a project
	
	    this.waitForElementPresent("!!!menu.project.element!!!", 30);

		selenium.click("!!!menu.project.element!!!");//
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]", 30);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]");// the selected project's id
		selenium.click("idBranchManagementButton");
		selenium.setSpeed("9000");
		selenium.click("idBranchManagementSourceInput");
		selenium.mouseDown("//div[text()='trunk']");
		selenium.fireEvent("idBranchManagementSourceInput", "blur");
		selenium.click("idBranchManagementTargetInput");
		selenium.type("idBranchManagementTargetInput", branchname);
		selenium.fireEvent("idBranchManagementTargetInput", "blur");
		selenium.click("idBranchManagementCreateButton");
		selenium.setSpeed("3000");
		if (selenium.isElementPresent("//span[text()='"
				+ other.getString("project.branchmanage.add.conform") + "']")) {
			
			selenium.click("//button[text()='"
					+ other.getString("project.branchmanage.add.conform.ok")
					+ "']");
			selenium.setSpeed("5000");
			if (selenium
					.isElementPresent("//span[text()='" + branchname + "']")) {
				selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
			} else {
				Assert.fail("branch added failed");
				selenium.click("//button[text()='"
						+ other.getString("project.branchmanage.add.conform.ok.warning")
						+ "']");
			}
		} else {
			Assert.fail("Can not found the conformation!");
		}
		

	}
}