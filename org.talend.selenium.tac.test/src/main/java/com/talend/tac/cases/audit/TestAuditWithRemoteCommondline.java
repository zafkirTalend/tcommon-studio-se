package com.talend.tac.cases.audit;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class TestAuditWithRemoteCommondline extends Audit {
	@Test
	@Parameters({"AddcommonProjectname"})
	public void testAuditTrunkWithRemoteCommondline(String projectName){
		auditProcess(projectName, "trunk");
		int linksbefore = checkAuditListLink(projectName);
		Assert.assertTrue(checkAuditInfo(projectName),"TestAudit audit trunk failed!");
		this.sleep(5000);
		Assert.assertTrue((checkAuditListLink(projectName)==linksbefore +1),"TestAudit audit trunk failed,not create links!");
	}
	
}
