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
	
	/**
	 * test audit process, projectName is the name of project,branches: we can define it as "trunk" or "branch"
	 * @param projectName
	 * @param branches
	 */
	public void auditProcess(String projectName,String branches){
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
	
	public boolean checkAuditInfo(String projectName){
		
	boolean ok = false;
	ok = this.waitForTextPresent("The Audit process has terminated successfully", MAX_WAIT_TIME);
	if(this.waitForTextPresent("The Audit process has terminated with error(s)", WAIT_TIME)){
		 ok = false;
	 }
	return ok;
	
	}
	public int checkAuditListLink(String projectName){
		
		int linksNum = this.selenium.getXpathCount("//a[contains(text(),'Audit for project "+'"'+projectName.toUpperCase()+'"'+" created at')]").intValue();
		return linksNum;
	}
	public void changeCommandLineConfig(String hostAddress) {
		System.err.println(hostAddress);
		selenium.refresh();
		selenium.setSpeed("1000");
		this.clickWaitForElementPresent("idMenuConfigElement");//into Configuration page
		this.waitForElementPresent("//div[contains(text(),'Command line/primary')]", WAIT_TIME);
		selenium.mouseDown("//div[contains(text(),'Command line/primary')]");
		this.clickWaitForElementPresent("//div[contains(text(),'Command line/primary')]//ancestor::div[@class='x-grid-group ']" +
				"//div[text()='Host']//ancestor::tr[@role='presentation']//img[@title='Click to edit']");
		this.waitForElementPresent("//div[@class=' x-form-field-wrap  x-component']//input", WAIT_TIME); 
		System.out.println("*--------------*");
		this.typeString("//div[contains(text(),' Command line/primary (')]/ancestor::div[@class='x-grid3-body']/following-sibling::div/div/input",hostAddress);
		selenium.setSpeed(MIN_SPEED);
	}
}
