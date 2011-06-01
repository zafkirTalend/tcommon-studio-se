package com.talend.cases.configuration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;

import com.talend.tac.cases.Login;
/*
 * opening configuration pages causes a long time ,
 * so this class extends Login_NotLogoutUntilAllTestsFinished,
 * then all the cases of configurations
 * are executed with only once login and logout.
 */
public class TestConfigurations extends Login {

	public String locatorOfAllInputTags = other.getString("commandline.conf.all.input");
	
	@BeforeClass
	@Override
	@Parameters( { "userName", "userPassword" })
	public void login(String user, String password) {
		super.login(user, password);
		this.clickWaitForElementPresent("idMenuConfigElement");
		selenium.setSpeed("500");
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
	
//	@BeforeClass
//	public void OpenConfigurationMenuPage(){
//		  this.clickWaitForElementPresent("idMenuConfigElement");
//		  selenium.setSpeed("500");
//	}
	
  @Test
  @Parameters ({"commandline.conf.primary.host","commandline.conf.primary.port","commandline.conf.primary.archivePath"})
  public void testSetCommandlinePrimary(String commandlineHost,String commandlinePort,String commandlinePath) {
	
	  this.MouseDownWaitForElementPresent("//div[contains(text(),' Command line/primary')]");
	  this.typeWordsInConfigurationMenu(other.getString("commandline.conf.primary.host.editButton"),other.getString("commandline.conf.all.input"), commandlineHost);
	  this.typeWordsInConfigurationMenu(other.getString("commandline.conf.primary.port.editButton"),other.getString("commandline.conf.all.input"), commandlinePort);
	  this.typeWordsInConfigurationMenu(other.getString("commandLine.conf.primary.archivePath.editButton"),other.getString("commandline.conf.all.input"), commandlinePath);

  }
  @Test
  @Parameters ({"commandline.conf.secondary.host","commandline.conf.secondary.port","commandline.conf.secondary.archivePath"})
  public void testSetCommandlineSecondary(String commandlineHost,String commandlinePort,String commandlinePath) {
	 
	  this.MouseDownWaitForElementPresent("//div[contains(text(),' Command line/secondary')]");
	  this.typeWordsInConfigurationMenu(other.getString("commandline.conf.secondary.host.editButton"),other.getString("commandline.conf.all.input"), commandlineHost);
	  this.typeWordsInConfigurationMenu(other.getString("commandline.conf.secondary.port.editButton"),other.getString("commandline.conf.all.input"), commandlinePort);
	  this.typeWordsInConfigurationMenu(other.getString("commandLine.conf.secondary.archivePath.editButton"),other.getString("commandline.conf.all.input"), commandlinePath);
	  
  }
  @Test
  @Parameters ({"LDAP.conf.useLDAPAutentication","LDAP.conf.ldapServerIp","LDAP.conf.ldapServerPort","LDAP.conf.ldapRoot","LDAP.conf.ldapPrincipalDNPrefix",
	  "LDAP.conf.ldapAdminPassword","LDAP.conf.ldap.fields.mail","LDAP.conf.ldap.fields.firstname","LDAP.conf.ldap.fields.lastname"})
  public void testSetLDAP(String useLDAPAutentication,String ldapServerIp,String ldapServerPort,String ldapRoot,String ldapPrincipalDNPrefix,String ldapAdminPassword,String ldapFieldsMail,String ldapFieldsFirstName,String ldapFieldsLastName ) {
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'LDAP (9 Parameters)')]");
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.useLDAPAutentication.editButton"), other.getString("commandline.conf.all.input"), useLDAPAutentication);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapServerIp.editButton"), other.getString("commandline.conf.all.input"), ldapServerIp);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapServerPort.editButton"), other.getString("commandline.conf.all.input"), ldapServerPort);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapRoot.editButton"), other.getString("commandline.conf.all.input"), ldapRoot);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapPrincipalDNPrefix.editButton"), other.getString("commandline.conf.all.input"), ldapPrincipalDNPrefix);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldapAdminPassword.editButton"), other.getString("commandline.conf.all.input"), ldapAdminPassword);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.mail.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsMail);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.firstname.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsFirstName);
	  this.typeWordsInConfigurationMenu(other.getString("LDAP.conf.ldap.fields.lastname.editButton"), other.getString("commandline.conf.all.input"), ldapFieldsLastName);
	
	//assertEquals
  }

  @Test
  @Parameters ({"log4j.conf.logsPath"})
  public void testSetLog4j(String logsPath){
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Log4j (2')]");
	  this.typeWordsInConfigurationMenu(other.getString("log4j.conf.logsPath.editButton"), other.getString("commandline.conf.all.input"), logsPath);
  }
  @Test
  @Parameters ({"smtp.conf.useSmtp","smtp.conf.mailServerHost","smtp.conf.mailServerPort","smtp.conf.mailUserName","smtp.conf.mailPassword","smtp.conf.serverRequireSSL"})
  public void testSetSMTP(String useSmtp,String mailServerHost,String mailServerPort,String mailUserName,String mailPassword,String serverRequireSSL){//String serverRequireSSL
	 
	  this.clickWaitForElementPresent("//div[contains(text(),'SMTP (6 Parameters)')]");  
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.useSmtp.editButton"), other.getString("commandline.conf.all.input"), useSmtp);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.mailServerHost.editButton"), other.getString("commandline.conf.all.input"), mailServerHost);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.mailServerPort.editButton"), other.getString("commandline.conf.all.input"), mailServerPort);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.mailUserName.editButton"), other.getString("commandline.conf.all.input"), mailUserName);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.mailPassword.editButton"), other.getString("commandline.conf.all.input"), mailPassword);
	  this.typeWordsInConfigurationMenu(other.getString("smtp.conf.serverRequireSSL.editButton"), other.getString("commandline.conf.all.input"), serverRequireSSL);
	  
	//assertEquals
  }
  
  @Test
  @Parameters ({"soaManager.conf.jobsDeployPath","soaManager.conf.serverAddress","soaManager.conf.serverPort"})
  public void testSetSoaManager(String soaJobDeployedPath,String soaMangerHost,String soaMangerProt){
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Soa manager (')]"); 
	  this.typeWordsInConfigurationMenu(other.getString("soaManager.conf.jobsDeployPath.editButton"), other.getString("commandline.conf.all.input"), soaJobDeployedPath);
	  this.typeWordsInConfigurationMenu(other.getString("soaManager.conf.serverAddress.editButton"), other.getString("commandline.conf.all.input"), soaMangerHost);
	  this.typeWordsInConfigurationMenu(other.getString("soaManager.conf.serverPort.editButton"), other.getString("commandline.conf.all.input"), soaMangerProt);
		
	//assertEquals
  }
  
  @Test
  @Parameters ({"svn.conf.serverLocationURL","svn.conf.serverUser","svn.conf.serverPassword"})
  public void testSetSVN(String svnServerLocationUrl,String svnServerUser,String svnServerPassword){
	  this.MouseDownWaitForElementPresent("//div[contains(text(),'Svn (')]"); 
	  this.typeWordsInConfigurationMenu(other.getString("svn.conf.serverLocationURL.editButton"), other.getString("commandline.conf.all.input"), svnServerLocationUrl);
	  this.typeWordsInConfigurationMenu(other.getString("soaManager.conf.serverPort.editButton"), other.getString("commandline.conf.all.input"), svnServerUser);
	  this.typeWordsInConfigurationMenu(other.getString("svn.conf.serverPassword.editButton"), other.getString("commandline.conf.all.input"), svnServerPassword);
		
	//assertEquals
  }
  
  @Test
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
}
