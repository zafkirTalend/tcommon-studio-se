package com.talend.cases.configuration;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.talend.tac.base.Base;

import com.talend.tac.cases.Login;
public class TestConfigurations extends Login {

	public String locatorOfAllInputTags = other.getString("commandline.conf.all.input");
	
	/**
	 * type a value in configuration menu.click the edit button firstly to wait for the input to appear.
	 * @param locatorOfEditButton
	 * @param locatorOfInput
	 * @param value
	 */
	public void typeWordsInConfigurationMenu(String locatorOfEditButton,String locatorOfInput,String value){
		 this.clickWaitForElementPresent(locatorOfEditButton);//click the edit button to make the input tag shown.
		 this.typeWaitForElementPresent(locatorOfInput, value);
		
	}
	/**
	 * assertions,check the value in input tag is as expected,and check the status icon.
	 * @param locatorOfEditButton
	 * @param locatorOfInput	
	 * @param value
	 */
		public void AssertEqualsInConfigurationMenu(String locatorOfEditButton,String locatorOfInput,String value,String statusIconLocator){
		this.AssertEqualsInConfigurationMenu(locatorOfEditButton, locatorOfInput, value);
		this.waitForElementPresent(statusIconLocator, WAIT_TIME);//wait and check the icon status.
	}
	public void AssertEqualsInConfigurationMenu(String locatorOfEditButton,String locatorOfInput,String value){
		this.clickWaitForElementPresent(locatorOfEditButton);//click the edit button to make the input tag shown.
//		this.waitForElementPresent(locatorOfInput, Base.WAIT_TIME);
		this.MouseDownWaitForElementPresent(locatorOfInput);
		assertEquals(selenium.getValue(locatorOfInput), value);
	}
	
  @Test
  @Parameters({"commandline.conf.primary.host","commandline.conf.primary.port","commandline.conf.primary.archivePath"})
  public void testSetCommandlinePrimary(String commandlineHost,String commandlinePort,String commandlinePath) {
	
	  this.MouseDownWaitForElementPresent("//div[contains(text(),' Command line/primary')]");
	  this.typeWordsInConfigurationMenu(other.getString("commandline.conf.primary.host.editButton"),other.getString("commandline.conf.all.input"), commandlineHost);
	  this.typeWordsInConfigurationMenu(other.getString("commandline.conf.primary.port.editButton"),other.getString("commandline.conf.all.input"), commandlinePort);
	  this.typeWordsInConfigurationMenu(other.getString("commandLine.conf.primary.archivePath.editButton"),other.getString("commandline.conf.all.input"), commandlinePath);

	  this.AssertEqualsInConfigurationMenu(other.getString("commandline.conf.primary.host.editButton"),other.getString("commandline.conf.all.input"), commandlineHost,other.getString("commandLine.conf.primary.host.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("commandline.conf.primary.port.editButton"),other.getString("commandline.conf.all.input"), commandlinePort,other.getString("commandline.conf.primary.port.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("commandLine.conf.primary.archivePath.editButton"),other.getString("commandline.conf.all.input"), commandlinePath);
//	  this.waitForElementPresent(other.getString("commandline.conf.primary.genralStatusIcon"), WAIT_TIME);
	  //->THIS IS NOT SUIT FOR AUTOMATION CURRENTLY,BECAUSE THE GENERAL ICON STATUS DID'T CHANGE IN TIME, AUTHOUGHT THE PARAMETERS ARE ALL CORRECT.

	  this.MouseDownWaitForElementPresent("//div[contains(text(),' Command line/primary')]");
  }
  @Test
  @Parameters ({"commandline.conf.secondary.host","commandline.conf.secondary.port","commandline.conf.secondary.archivePath"})
  public void testSetCommandlineSecondary(String commandlineHost,String commandlinePort,String commandlinePath) {
	 
	  this.MouseDownWaitForElementPresent("//div[contains(text(),' Command line/secondary')]");
	  this.typeWordsInConfigurationMenu(other.getString("commandline.conf.secondary.host.editButton"),other.getString("commandline.conf.all.input"), commandlineHost);
	  this.typeWordsInConfigurationMenu(other.getString("commandline.conf.secondary.port.editButton"),other.getString("commandline.conf.all.input"), commandlinePort);
	  this.typeWordsInConfigurationMenu(other.getString("commandLine.conf.secondary.archivePath.editButton"),other.getString("commandline.conf.all.input"), commandlinePath);
	  
	  this.AssertEqualsInConfigurationMenu(other.getString("commandline.conf.secondary.host.editButton"),other.getString("commandline.conf.all.input"), commandlineHost,other.getString("commandLine.conf.secondary.host.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("commandline.conf.secondary.port.editButton"),other.getString("commandline.conf.all.input"), commandlinePort,other.getString("commandline.conf.secondary.port.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("commandLine.conf.secondary.archivePath.editButton"),other.getString("commandline.conf.all.input"), commandlinePath);
//	  this.waitForElementPresent(other.getString("commandline.conf.secondary.generalStatusIcon"), WAIT_TIME);
	 
	  this.MouseDownWaitForElementPresent("//div[contains(text(),' Command line/secondary')]");
	  
  }
  
  @Test
  @Parameters ({"esb.conf.zookeeperServer","esb.conf.serviceActivityMonitorServer"})
  public void testSetESB(String zookeeperServer,String serviceActivityMonitorServer){
		  
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Log4j (2')]");
	  this.typeWordsInConfigurationMenu(other.getString("esb.conf.ZookeeperServer.editButton"), other.getString("commandline.conf.all.input"), zookeeperServer);
	  this.typeWordsInConfigurationMenu(other.getString("esb.conf.serviceActivityMonitorServer.editButton"), other.getString("commandline.conf.all.input"), serviceActivityMonitorServer);
	  
	  this.AssertEqualsInConfigurationMenu(other.getString("esb.conf.ZookeeperServer.editButton"), other.getString("commandline.conf.all.input"),
			  zookeeperServer, other.getString("esb.conf.ZookeeperServerStatusIconLocator"));
	  this.AssertEqualsInConfigurationMenu(other.getString("esb.conf.serviceActivityMonitorServer.editButton"), other.getString("commandline.conf.all.input"),
			  serviceActivityMonitorServer, other.getString("esb.conf.ServiceActivityMonitorServerStatusIconLocator"));
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Log4j (2')]");
	  this.clickWaitForElementPresent("//div[contains(text(),'ESB (2 Parameters)')]");  
	  
  }
  
  @Test
  @Parameters ({"LDAP.conf.useLDAPAutentication","LDAP.conf.ldapServerIp","LDAP.conf.ldapServerPort","LDAP.conf.ldapRoot","LDAP.conf.ldapPrincipalDNPrefix",
	  "LDAP.conf.ldapAdminPassword","LDAP.conf.ldap.fields.mail","LDAP.conf.ldap.fields.firstname","LDAP.conf.ldap.fields.lastname"})
  public void testSetLDAP(String useLDAPAutentication,String ldapServerIp,String ldapServerPort,String ldapRoot,String ldapPrincipalDNPrefix,String ldapAdminPassword,String ldapFieldsMail,String ldapFieldsFirstName,String ldapFieldsLastName ) {
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'LDAP (9 Parameters')]");
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.useLDAPAutentication.editButton"), other.getString("commandline.conf.all.input"), useLDAPAutentication);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapServerIp.editButton"), other.getString("commandline.conf.all.input"), ldapServerIp);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapServerPort.editButton"), other.getString("commandline.conf.all.input"), ldapServerPort);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapRoot.editButton"), other.getString("commandline.conf.all.input"), ldapRoot);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapPrincipalDNPrefix.editButton"), other.getString("commandline.conf.all.input"), ldapPrincipalDNPrefix);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapAdminPassword.editButton"), other.getString("commandline.conf.all.input"), ldapAdminPassword);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.mail.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsMail);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.firstname.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsFirstName);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.lastname.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsLastName);
	
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.useLDAPAutentication.editButton"), other.getString("commandline.conf.all.input"), useLDAPAutentication);
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.ldapServerIp.editButton"), other.getString("commandline.conf.all.input"), ldapServerIp);
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.ldapServerPort.editButton"), other.getString("commandline.conf.all.input"), ldapServerPort);
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.ldapRoot.editButton"), other.getString("commandline.conf.all.input"), ldapRoot);
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.ldapPrincipalDNPrefix.editButton"), other.getString("commandline.conf.all.input"), ldapPrincipalDNPrefix);
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.ldapAdminPassword.editButton"), other.getString("commandline.conf.all.input"), ldapAdminPassword);
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.mail.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsMail);
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.firstname.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsFirstName);
	  this.AssertEqualsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.lastname.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsLastName);
	
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'LDAP (9 Parameters')]");
  }
  @Test
  @Parameters ({"log4j.conf.logsPath","log4j.conf.logsName"})
  public void testSetLog4j(String logsPath,String logsName){
	  	  
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Log4j (2')]");
	  this.typeWordsInConfigurationMenu(other.getString("log4j.conf.logsPath.editButton"), other.getString("commandline.conf.all.input"), logsPath+logsName);
 
	  this.AssertEqualsInConfigurationMenu(other.getString("log4j.conf.logsPath.editButton"), other.getString("commandline.conf.all.input"),
			  logsPath+logsName, other.getString("log4j.conf.TalendAppenderStatusIconLocator"));
	  assertTrue(selenium.isElementPresent(other.getString("log4j.conf.ThresholdStatusIconLocator")));
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Log4j (2')]");
	  this.clickWaitForElementPresent("//div[contains(text(),'Log4j (2 Parameters)')]");  
  }
  @Test
  @Parameters ({"smtp.conf.useSmtp","smtp.conf.mailServerHost","smtp.conf.mailServerPort","smtp.conf.mailUserName","smtp.conf.mailPassword","smtp.conf.serverRequireSSL"})
  public void testSetSMTP(String useSmtp,String mailServerHost,String mailServerPort,String mailUserName,String mailPassword,String serverRequireSSL){//String serverRequireSSL
	  this.clickWaitForElementPresent("//div[contains(text(),'SMTP (6 Parameters')]");
	  this.clickWaitForElementPresent("//div[contains(text(),'SMTP (6 Parameters')]");  
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.useSmtp.editButton"), other.getString("commandline.conf.all.input"), useSmtp);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.mailServerHost.editButton"), other.getString("commandline.conf.all.input"), mailServerHost);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.mailServerPort.editButton"), other.getString("commandline.conf.all.input"), mailServerPort);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.mailUserName.editButton"), other.getString("commandline.conf.all.input"), mailUserName);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.mailPassword.editButton"), other.getString("commandline.conf.all.input"), mailPassword);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.serverRequireSSL.editButton"), other.getString("commandline.conf.all.input"), serverRequireSSL);
	  
	  this.AssertEqualsInConfigurationMenu(other.getString("smtp.conf.useSmtp.editButton"), other.getString("commandline.conf.all.input"), useSmtp,other.getString("smtp.conf.useSmtp.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("smtp.conf.mailServerHost.editButton"), other.getString("commandline.conf.all.input"), mailServerHost,other.getString("smtp.conf.mailServerHost.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("smtp.conf.mailServerPort.editButton"), other.getString("commandline.conf.all.input"), mailServerPort,other.getString("smtp.conf.mailServerPort.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("smtp.conf.mailUserName.editButton"), other.getString("commandline.conf.all.input"), mailUserName,other.getString("smtp.conf.mailUserName.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("smtp.conf.mailPassword.editButton"), other.getString("commandline.conf.all.input"), mailPassword,other.getString("smtp.conf.mailPassword.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("smtp.conf.serverRequireSSL.editButton"), other.getString("commandline.conf.all.input"), serverRequireSSL,other.getString("smtp.conf.serverRequireSSL.statusIcon"));
//	  this.waitForElementPresent(other.getString("smtp.conf.generalStatusIcon"), WAIT_TIME);
	  
	  this.clickWaitForElementPresent("//div[contains(text(),'SMTP (6 Parameters')]");  
	//assertEquals
  }
  
  @Test
  @Parameters ({"scheduler.conf.ArchivedPath","scheduler.conf.LogsPath"})
  public void testSetScheduler(String ArchivedPath,String logsPath){
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Scheduler (')]"); 
	  this.typeWordsInConfigurationMenu(other.getString("scheduler.conf.ArchivedPath.editButton"), other.getString("commandline.conf.all.input"), ArchivedPath);
	  this.typeWordsInConfigurationMenu(other.getString("scheduler.conf.LogsPath.editButton"), other.getString("commandline.conf.all.input"), logsPath);
	  this.AssertEqualsInConfigurationMenu(other.getString("scheduler.conf.ArchivedPath.editButton"), other.getString("commandline.conf.all.input"), ArchivedPath,other.getString("scheduler.conf.ArchivedPath.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("scheduler.conf.LogsPath.editButton"), other.getString("commandline.conf.all.input"), logsPath,other.getString("scheduler.conf.LogsPath.statusIcon"));
//	  this.waitForElementPresent(other.getString("scheduler.conf.generalStatusIcon"), WAIT_TIME);
	 
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Scheduler (')]"); 
	//assertEquals
  }
  @Test
  @Parameters ({"soaManager.conf.jobsDeployPath","soaManager.conf.serverAddress","soaManager.conf.serverPort"})
  public void testSetSoaManager(String soaJobDeployedPath,String soaMangerHost,String soaMangerProt){
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Soa manager (')]"); 
	  this.typeWordsInConfigurationMenu(other.getString("soaManager.conf.jobsDeployPath.editButton"), other.getString("commandline.conf.all.input"), soaJobDeployedPath);
	  this.typeWordsInConfigurationMenu(other.getString("soaManager.conf.serverAddress.editButton"), other.getString("commandline.conf.all.input"), soaMangerHost);
	  this.typeWordsInConfigurationMenu(other.getString("soaManager.conf.serverPort.editButton"), other.getString("commandline.conf.all.input"), soaMangerProt);
		
	  this.AssertEqualsInConfigurationMenu(other.getString("soaManager.conf.jobsDeployPath.editButton"), other.getString("commandline.conf.all.input"), soaJobDeployedPath,other.getString("soaManager.conf.jobsDeployPath.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("soaManager.conf.serverAddress.editButton"), other.getString("commandline.conf.all.input"), soaMangerHost,other.getString("soaManager.conf.serverAddress.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("soaManager.conf.serverPort.editButton"), other.getString("commandline.conf.all.input"), soaMangerProt,other.getString("soaManager.conf.serverPort.statusIcon"));
//	  this.waitForElementPresent(other.getString("soaManager.conf.generalStatusIcon"), WAIT_TIME);
	 
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Soa manager (')]"); 
	//assertEquals
  }
  
  @Test
  @Parameters ({"svn.conf.serverLocationURL","svn.conf.serverUser","svn.conf.serverPassword"})
  public void testSetSVN(String svnServerLocationUrl,String svnServerUser,String svnServerPassword){
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Svn (')]"); 
	  this.typeWordsInConfigurationMenu(other.getString("svn.conf.serverLocationURL.editButton"), other.getString("commandline.conf.all.input"), svnServerLocationUrl);
	  this.typeWordsInConfigurationMenu(other.getString("svn.conf.serverUser.editButton"), other.getString("commandline.conf.all.input"), svnServerUser);
	  this.typeWordsInConfigurationMenu(other.getString("svn.conf.serverPassword.editButton"), other.getString("commandline.conf.all.input"), svnServerPassword);
		
	  this.AssertEqualsInConfigurationMenu(other.getString("svn.conf.serverLocationURL.editButton"), other.getString("commandline.conf.all.input"), svnServerLocationUrl,other.getString("svn.conf.serverLocationURL.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("svn.conf.serverUser.editButton"), other.getString("commandline.conf.all.input"), svnServerUser,other.getString("svn.conf.serverUser.statusIcon"));
	  this.AssertEqualsInConfigurationMenu(other.getString("svn.conf.serverPassword.editButton"), other.getString("commandline.conf.all.input"), svnServerPassword,other.getString("svn.conf.serverPassword.statusIcon"));
//	  this.waitForElementPresent(other.getString("svn.conf.generalStatusIcon"), WAIT_TIME);//assertion of the general icon.It should be green if all the parameters set correctly
	 
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Svn (')]"); 	
	//assertEquals
  }
  
  @Test(enabled=false)
  @Parameters ({"suite.link.dqportal","suite.link.drools","suite.link.mdm"})
  public void testLinkToTalendSuite(String dqportal,String drools,String mdm){
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Talend suite')]"); 
	  
//	  this.typeWordsInConfigurationMenu(other.getString("suite.link.dqportal.editButton"), other.getString("commandline.conf.all.input"), dqportal);
//	  this.typeWordsInConfigurationMenu(other.getString("suite.link.drools.editButton"), other.getString("commandline.conf.all.input"), drools);
//	  this.typeWordsInConfigurationMenu(other.getString("suite.link.mdm.editButton"), other.getString("commandline.conf.all.input"), mdm);
	
	  this.typeWordsInConfigurationMenu(other.getString("suite.link.dqportal.editButton"), other.getString("commandline.conf.all.input"), dqportal);
	  this.waitForElementPresent("!!!menu.dqportal.element!!!",WAIT_TIME);
	  this.typeWordsInConfigurationMenu(other.getString("suite.link.drools.editButton"), other.getString("commandline.conf.all.input"), drools);
	  this.waitForElementPresent("!!!menu.drools.element!!!",WAIT_TIME);
	  this.typeWordsInConfigurationMenu(other.getString("suite.link.mdm.editButton"), other.getString("commandline.conf.all.input"), mdm);
	  this.waitForElementPresent("!!!menu.mdm.element!!!",WAIT_TIME);
	  //assertEquals
  }
  
  /**
   * using firefox profile to start server, this is mainly to avoid the popup windows when loading a file.
   * java -jar selenium-server.jar -firefoxProfileTemplate C:\Talend\ff\profile
   * (copy mimeTypes.rdf and prefs.js to this folder C:\Talend\ff\profile\)
   * @param downloadPath
   * @param logsName
   */
		  
	@Test//(enabled=true,dependsOnMethods="testSetLog4j")
	 @Parameters ({"firefox.download.path","log4j.conf.logsName"})
	public void testDownloadLog(String downloadPath,String logsName) {
		this.MouseDownWaitForElementPresent("//div[contains(text(),' Command line/primary')]");
		this.clickWaitForElementPresent("//button[text()='Download Log']");
//		File file = new File(
//				"C:\\Users\\Administrator\\Downloads\\422NBS.txt.zip");
		File file = new File(
				downloadPath+logsName+".zip");
		for (int seconds = 0;; seconds++) {
			if (seconds >= WAIT_TIME) {
				assertTrue(file.exists());
			}
			if (file.exists()) {
				System.out.println(seconds + "' used to download");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test//(enabled=false)
	@Parameters ({"firefox.download.path"})
	public void testExportParameters(String downloadPath) {
		this.MouseDownWaitForElementPresent("//div[contains(text(),' Command line/primary')]");
		this.clickWaitForElementPresent("//button[text()='Export parameters']");
		File file = new File(
				downloadPath + "administrator_config.txt");
		for (int seconds = 0;; seconds++) {
			if (seconds >= WAIT_TIME) {
				assertTrue(file.exists());
			}
			if (file.exists()) {
				System.out.println(seconds + "' used to download");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@BeforeClass
	@Override
	@Parameters( { "userName", "userPassword" })
	public void login(String user, String password) {
		super.login(user, password);
		this.clickWaitForElementPresent("idMenuConfigElement");
//		selenium.setSpeed("500");
	}

	@AfterClass
	@Override
	public void logout() {
		selenium.click("idLeftMenuTreeLogoutButton");
		selenium.stop();
	}

	@Override
	public void typeWaitForElementPresent(String locator,String value) {
		this.waitForElementPresent(locator, Base.WAIT_TIME);
		selenium.type(locator,value);
		selenium.keyPress(locator, "\\13");
	}

	@Override
	public void killBroswer() {
	}
}
