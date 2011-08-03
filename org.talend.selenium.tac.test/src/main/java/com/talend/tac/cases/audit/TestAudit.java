package com.talend.tac.cases.audit;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
/**
 * This is only the steps of how to test audit.It depends on the command line(not included here) and the time.
 * It will cause a long time to audit a project,
 * so,when doing automation test, the second audit may not "really" be successful.
 * @author lwang
 *
 */
public class TestAudit extends Login {
	@Test
	@Parameters({"AddcommonProjectname"})
	public void testAuditTrunk(String projectName){
		testAuditProcess(projectName, "trunk");
		Assert.assertTrue(checkAudit(projectName),"TestAudit audit trunk failed!");
	}
	
	@Test
	@Parameters({"AddcommonProjectname"})
	public void testAuditBranch(String projectName){
		testAuditProcess(projectName, "branch");
		Assert.assertTrue(checkAudit(projectName),"TestAudit audit branch failed!");
	}
	/**
	 * test audit process, projectName is the name of project,branches: we can define it as "trunk" or "branch"
	 * @param projectName
	 * @param branches
	 */
	public void testAuditProcess(String projectName,String branches){
		this.clickWaitForElementPresent("!!!menu.audit.element!!!");
		//project
		this.clickWaitForElementPresent("//label[text()='Project:']/following-sibling::div//table//div//div");
		this.waitForElementPresent("//div[@role='listitem' and text()='"+projectName+"']",WAIT_TIME+70);
		selenium.mouseDown("//div[@role='listitem' and text()='"+projectName+"']");
		//branches
		this.clickWaitForElementPresent("//label[text()='Branch:']/following-sibling::div//div/div");
		this.waitForElementPresent("//div[@role='listitem' and contains(text(),'"+branches+"')][1]", WAIT_TIME);
		selenium.mouseDown("//div[@role='listitem' and contains(text(),'"+branches+"')][1]");
		//start
		this.waitForElementPresent("//button[text()='Start audit']",WAIT_TIME);
		selenium.click("//button[text()='Start audit']");
		//audit is started,but it need a very long time to be finished. we can think and check the result.
		
	
	}
	
	public boolean checkAudit(String projectName){
		
	boolean ok = false;
	ok = this.waitForTextPresent("The Audit process has terminated successfully", MAX_WAIT_TIME);
	return ok;
	
	}
}
