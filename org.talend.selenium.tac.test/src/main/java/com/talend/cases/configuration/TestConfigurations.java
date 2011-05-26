package com.talend.cases.configuration;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.talend.tac.cases.Login;

public class TestConfigurations extends Login {
  @Test
  public void testSetCommandlinePrimary(String commandlineHost,String commandlinePort,String commandlinePath) {
	  this.clickWaitForElementPresent("idMenuConfigElement");
	  this.clickWaitForElementPresent("//div[contains(text(),' Command line/primary')]");
	  this.typeWaitForElementPresent("HOstId", commandlineHost);
	  this.typeWaitForElementPresent("portId", commandlinePort);
	  this.typeWaitForElementPresent("pathId", commandlinePath);
	  //assertEquals
	  assertEquals(selenium.getValue("HOstId"), commandlineHost);
	  assertEquals(selenium.getValue("portId"), commandlinePort);
	  assertEquals(selenium.getValue("pathId"),commandlinePath);
  }
  @Test
  public void testSetCommandlineSecondary(String commandlineHost,String commandlinePort,String commandlinePath) {
	  this.clickWaitForElementPresent("idMenuConfigElement");
	  this.clickWaitForElementPresent("//div[contains(text(),' Command line/secondary')]");
	  this.typeWaitForElementPresent("HOstId", commandlineHost);
	  this.typeWaitForElementPresent("portId", commandlinePort);
	  this.typeWaitForElementPresent("pathId", commandlinePath);
	  //assertEquals......
  }
  @Test
  public void testSetLDAP(String useLDAPAutentication,String ldapServerIp,String ldapServerPort,String ldapRoot,String ldapPrincipalDNPrefix,String ldapAdminPassword,String ldapFieldsMail,String ldapFieldsFirstName,String ldapFieldsLastName ) {
	  this.clickWaitForElementPresent("idMenuConfigElement");
	  this.clickWaitForElementPresent("//div[contains(text(),'LDAP (9 Parameters)')]");
	  this.typeWaitForElementPresent("",useLDAPAutentication );
	  this.typeWaitForElementPresent("",ldapServerIp );
	  this.typeWaitForElementPresent("",ldapServerPort );
	  this.typeWaitForElementPresent("", ldapRoot);
	  this.typeWaitForElementPresent("",ldapPrincipalDNPrefix );
	  this.typeWaitForElementPresent("",ldapAdminPassword );
	  this.typeWaitForElementPresent("", ldapFieldsMail);
	  this.typeWaitForElementPresent("",ldapFieldsFirstName );
	  this.typeWaitForElementPresent("", ldapFieldsLastName);
//	  this.typeWaitForElementPresent("", ldapRoot);
	//assertEquals
  }

  @Test
  public void testSetLog4j(String logsPath){
	  this.clickWaitForElementPresent("idMenuConfigElement");
	  this.clickWaitForElementPresent("//div[contains(text(),'Log4j')]"); 
	  this.typeWaitForElementPresent("", logsPath);
  }
  @Test
  public void testSetSMTP(String useSmtp,String mailServerHost,String mailServerPort,String mailUserName,String mailPassword){//String serverRequireSSL
	  this.clickWaitForElementPresent("idMenuConfigElement");
	  this.clickWaitForElementPresent("//div[contains(text(),'SMTP (6 Parameters)')]");  
	  this.typeWaitForElementPresent("", useSmtp);
	  this.typeWaitForElementPresent("", mailServerHost);
	  this.typeWaitForElementPresent("", mailServerPort);
	  this.typeWaitForElementPresent("", mailUserName);
	  this.typeWaitForElementPresent("", mailPassword);
	//assertEquals
  }
  
  @Test
  public void testSetSoaManager(String soaJobDeployedPath,String soaMangerHost,String soaMangerProt){
	  this.clickWaitForElementPresent("idMenuConfigElement");
	  this.clickWaitForElementPresent("//div[contains(text(),'Soa manager (')]"); 
	  this.typeWaitForElementPresent("", soaJobDeployedPath);
	  this.typeWaitForElementPresent("", soaMangerHost);
	  this.typeWaitForElementPresent("", soaMangerProt);
//	  this.typeWaitForElementPresent("", );
	//assertEquals
  }
  
  @Test
  public void testSetSVN(String svnServerLocationUrl, String svnServer,String svnServerUser,String svnServerPassword){
	  this.clickWaitForElementPresent("idMenuConfigElement");
	  this.clickWaitForElementPresent("//div[contains(text(),'Svn (')]"); 
	  this.typeWaitForElementPresent("",svnServerLocationUrl );
	  this.typeWaitForElementPresent("", svnServer);
	  this.typeWaitForElementPresent("", svnServerUser);
	  this.typeWaitForElementPresent("", svnServerPassword);
	//assertEquals
  }
  
  @Test
  public void testLinkToTalendSuite(String dqportal,String drools,String mdm){
	  this.clickWaitForElementPresent("idMenuConfigElement");
	  this.clickWaitForElementPresent("//div[contains(text(),'Talend suite')]"); 
	  this.typeWaitForElementPresent("",dqportal );
	  this.waitForElementPresent("!!!menu.dqportal.element!!!",WAIT_TIME);
	  this.typeWaitForElementPresent("",drools );
	  this.waitForElementPresent("!!!menu.drools.element!!!",WAIT_TIME);
	  this.typeWaitForElementPresent("",mdm );
	  this.waitForElementPresent("!!!menu.mdm.element!!!",WAIT_TIME);
	  //assertEquals
  }
}
