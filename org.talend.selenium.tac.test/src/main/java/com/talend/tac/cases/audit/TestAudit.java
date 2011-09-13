package com.talend.tac.cases.audit;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestAudit extends Audit {
	@Test
	@Parameters({"AddcommonProjectname"})
	public void testAuditTrunk(String projectName){
		auditProcess(projectName, "trunk");
		int linksbefore = checkAuditListLink(projectName);
		Assert.assertTrue(checkAuditInfo(projectName),"TestAudit audit trunk failed!");
		this.sleep(5000);
		Assert.assertTrue((checkAuditListLink(projectName)==linksbefore +1),"TestAudit audit trunk failed,not create links!");
	}
	
	@Test
	@Parameters({"AddcommonProjectname"})
	public void testAuditTrunkStop(String projectName){
		auditProcess(projectName, "trunk");
		Assert.assertTrue(this.waitForTextPresent("Running...", WAIT_TIME));
		this.sleep(2000);
		selenium.click("//button[text()='Stop']");
		Assert.assertTrue(this.waitForTextPresent("Stopping...", WAIT_TIME));
		Assert.assertFalse(this.waitForTextPresent("Saving reports... !", WAIT_TIME),"TestAudit stop audit trunk failed!");
	}
	
	@Test
	@Parameters({"AddcommonProjectname"})
	public void testAuditBranch(String projectName){
		auditProcess(projectName, "branch");
		int linksbefore = checkAuditListLink(projectName);
		Assert.assertTrue(checkAuditInfo(projectName),"TestAudit audit branch failed!");
		this.sleep(5000);
		Assert.assertTrue((checkAuditListLink(projectName)==linksbefore +1),"TestAudit audit branch failed,not create links!");
	}
	
	@Test
	@Parameters({"AddcommonProjectname","remotehostAddress","remotehostAddressWithWrong"})
	public void testAuditWithOutStartCommondline(String projectName,String normalCommondline,String wrongCommondline){
		System.err.println(wrongCommondline);	
		this.changeCommandLineConfig(wrongCommondline);
		selenium.refresh();
		this.sleep(5000);
		auditProcess(projectName, "branch");
		Assert.assertTrue(this.waitForTextPresent("Failed to connect to CommandLine", MAX_WAIT_TIME));
		Assert.assertTrue(this.waitForTextPresent("org.talend.administrator.common.exception.SystemException: Failed to connect to CommandLine", MAX_WAIT_TIME));
		this.sleep(5000);
		this.changeCommandLineConfig(normalCommondline);
		selenium.refresh();
		this.sleep(5000);
	}
}
