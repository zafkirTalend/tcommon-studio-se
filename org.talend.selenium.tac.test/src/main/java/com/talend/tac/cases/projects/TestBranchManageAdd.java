package com.talend.tac.cases.projects;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;

public class TestBranchManageAdd extends Login {
	@Test(groups = { "AddBranch" }, dependsOnGroups = { "else" })
	@Parameters({ "BranchProject", "BranchName" })
	public void testAddProjectbranch(String project, String branchname) throws InterruptedException {
		// first add a branch to a project
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);

		selenium.click("!!!menu.project.element!!!");//
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]", Base.WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]");// the selected project's id
		selenium.click("idBranchManagementButton");
		selenium.setSpeed(MID_SPEED);
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
			this.waitForElementPresent("//span[text()='" + branchname + "']", WAIT_TIME);
			selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
//			Thread.sleep(20000);
//			if (selenium
//					.isElementPresent("//span[text()='" + branchname + "']")) {
//				selenium.click("//div[@class=' x-nodrag x-tool-close x-tool x-component']");
//			} else {
//				Assert.fail("branch added failed");
//				selenium.click("//button[text()='"
//						+ other.getString("project.branchmanage.add.conform.ok.warning")
//						+ "']");
//			}
		} else {
			Assert.fail("Can not found the conformation!");
		}
		selenium.setSpeed(MIN_SPEED);
		//
		selenium.refresh();
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]", Base.WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ project + "')]");// the selected project's id
		selenium.click("idBranchManagementButton");
		selenium.setSpeed(MID_SPEED);
		Thread.sleep(3000);
		selenium.click("idBranchManagementSourceInput");
		selenium.mouseDown("//div[text()='trunk']");
		selenium.fireEvent("idBranchManagementSourceInput", "blur");
		selenium.setSpeed(MIN_SPEED);
		selenium.click("idBranchManagementTargetInput");
		selenium.type("idBranchManagementTargetInput", "test_"+branchname);
		selenium.fireEvent("idBranchManagementTargetInput", "blur");
		selenium.setSpeed(MID_SPEED);
		selenium.click("idBranchManagementCreateButton");
		
		if (selenium.isElementPresent("//span[text()='"
				+ other.getString("project.branchmanage.add.conform") + "']")) {
			
			selenium.click("//button[text()='"
					+ other.getString("project.branchmanage.add.conform.ok")
					+ "']");
			if (selenium
					.isElementPresent("//span[text()='" +"test_"+ branchname + "']")) {
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
		//
		selenium.setSpeed(MIN_SPEED);

	}
}