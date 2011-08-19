package com.talend.tac.cases.esb.serviceLocator;

import static org.testng.Assert.assertEquals;

import java.awt.Event;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.base.AntAction;
import com.talend.tac.base.Base;
import com.talend.tac.base.Karaf;
import com.talend.tac.cases.Login;

public class TestServiceLocator extends Login {
	
	Karaf karaf = new Karaf("localhost", "D:/jar");
	public String locatorOfAllInputTags = other.getString("commandline.conf.all.input");
	//test display service of 'live services only'/'all services'	
	@Test
	@Parameters({"jobFirstProvider", "jobSecondProvider", "jobThirdProvider"})
	public void testDisplayAllService(String jobFirstProvider, String jobSecondProvider,
			String jobThirdProvider) {		
		
		//install all job and start
		startAllService(jobFirstProvider, jobSecondProvider, jobThirdProvider);
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		//verify service start success
		assertStartService(jobFirstProvider);
		assertStartService(jobSecondProvider);
		assertStartService(jobThirdProvider);
		
	}
	
	//test check 'live services only', check display whether only dispaly live services
	@Test
	@Parameters({"jobFirstProvider", "jobSecondProvider", "jobThirdProvider"})
	public void testDisplayLiveServicesOnly(String jobFirstProvider, String jobSecondProvider,
			String jobThirdProvider) {
		
		//stop job	
		stopService(jobFirstProvider);
		stopService(jobSecondProvider);
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		
		//just only display live service 
		selenium.click("//label[text()='live services only']//preceding-sibling::input");
		
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		
		//just only display live service 
		assertStartService(jobThirdProvider);
		
	}
    
	//stop all services, check info of click 'all services' page
	@Test
	@Parameters({"jobFirstProvider", "jobSecondProvider", "jobThirdProvider"})
	public void testDisplayLiveServicesOnlyAfterStopAllServices(String jobFirstProvider,
			String jobSecondProvider, String jobThirdProvider) {

		//stop all jobs	
		stopService(jobThirdProvider);
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		
		//stop job
		assertStopService(jobFirstProvider);
		assertStopService(jobSecondProvider);
		assertStopService(jobThirdProvider);
		
		//just only display live service 
		selenium.click("//label[text()='live services only']//preceding-sibling::input");
		
		//verify display result
		this.waitForTextPresent("There are no services available. Please check your filter and click" +
				" refresh button to retry.", WAIT_TIME);
		
		
	}
	
	//check service value display whether normal per columns
	@Test
	@Parameters({"jobFirstProvider"})
	public void testServicePerColumnsValue(String jobFirstProvider) {
		
		String xpathOfFirstProviderServiceEndpoint = "//table[@class='x-grid3-row-table']//div[text()='http://127.0.0.1:9999/esb/provider']";
		String xpathOfFirstProviderServiceUptime = "//div[contains(text(),'"+jobFirstProvider+"')]//ancestor::div[contains(@class,'x-grid-group')]" +
				"//span[contains(text(),'Last seen')]";
		String xpathOfFirstProviderServiceHttp = "//div[text()='http://127.0.0.1:9999/esb/provider']//ancestor::table[@class='x-grid3-row-table']" +
				"//td[1]//img[@class='gwt-Image x-component']";
		String xpathOfFirstProviderServiceSOAP11 = "//div[text()='http://127.0.0.1:9999/esb/provider']//ancestor::table[@class='x-grid3-row-table']" +
		"//td[2]//img[@class='gwt-Image x-component']";
		String xpathOfFirstProviderServiceNameSpace = "//div[text()='http://127.0.0.1:9999/esb/provider']//ancestor::table[@class='x-grid3-row-table']" +
				"//div[text()='http://talend.org/esb/service/job']";
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		
		Assert.assertTrue(selenium.isElementPresent(xpathOfFirstProviderServiceEndpoint));
		Assert.assertTrue(selenium.isElementPresent(xpathOfFirstProviderServiceUptime));
		Assert.assertTrue(selenium.isElementPresent(xpathOfFirstProviderServiceHttp));
		Assert.assertTrue(selenium.isElementPresent(xpathOfFirstProviderServiceSOAP11));
		Assert.assertTrue(selenium.isElementPresent(xpathOfFirstProviderServiceNameSpace));		
				
	}
	
	//check refresh button is well work
	@Test
	@Parameters({"jobFirstProvider", "jobSecondProvider", "jobThirdProvider"})
	public void testCheckRefresh(String jobFirstProvider,
			String jobSecondProvider, String jobThirdProvider) {
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		
		//verify status of all services
		assertStopService(jobFirstProvider);
		assertStopService(jobSecondProvider);
		assertStopService(jobThirdProvider);
		
		installStratService(jobFirstProvider);
		
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		/*check the well refresh
		  change service status is successful */
		assertStartService(jobFirstProvider);
		assertStopService(jobSecondProvider);
		assertStopService(jobThirdProvider);
		
	}
	
	//check display service cancel groups
	@Test
	public void testDisplayServiceNotWithGroups() {
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
				
		this.clickWaitForElementPresent("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-endpoint')]/a");		
		selenium.setSpeed(MID_SPEED);
		selenium.click("//a[text()='Show in Groups']");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//check services display order of per columns sort ascending and sort descending
	@Test
	public void testCheckSortAscendingSortDescending() {		

		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
				
		this.clickWaitForElementPresent("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-endpoint')]/a");		
		selenium.setSpeed(MID_SPEED);
		selenium.click("//a[text()='Show in Groups']");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
		selenium.setSpeed(MIN_SPEED);
		
		checkSortAscendingSortDescending("name", "TEST_jobThirdProvider", "TEST_jobFirstProvider");
		checkSortAscendingSortDescending("endpoint", "http://127.0.0.1:9999/esb/provider",
				"http://127.0.0.1:9996/esb/provider");
		
	}
	
	//group service display
	@Test
	public void testGroupDisplayService() {		

		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
			
		changeGroupDisplayService("endpoint", "name", "TEST_jobFirstProvider");
		changeGroupDisplayService("endpoint", "isAlive", "false");	
		changeGroupDisplayService("name", "endpoint", "http://127.0.0.1:9997/esb/provider");
		changeGroupDisplayService("name", "transport_protocol", "http://talend.org/esb/service/job");
		
		this.clickWaitForElementPresent("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-endpoint')]/a");		
		selenium.setSpeed(MID_SPEED);
		selenium.click("//a[text()='Show in Groups']");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	//hide service display service
	@Test
	public void testHiddenDisplayService() {
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		
		hiddenDisplayServices("http://127.0.0.1:9999/esb/provider", "TEST_jobFirstProvider");
		hiddenDisplayServices("http://127.0.0.1:9999/esb/provider", "TEST_jobFirstProvider");
		
	}
	
	//modify the services display columns
	@Test
    public void testModifyDisplayServicesColumns() {
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		this.clickWaitForElementPresent("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-endpoint')]/a");

		selenium.setSpeed(MID_SPEED);
		selenium.mouseOver("//a[text()='Columns']");
	    selenium.setSpeed(MIN_SPEED);
		modifyDisplayServicesColumns("Status");		
		modifyDisplayServicesColumns("Service Endpoint");		
        
		modifyDisplayServicesColumns("Status");		
		modifyDisplayServicesColumns("Service Endpoint");
        selenium.setSpeed(MID_SPEED);
		selenium.click("//a[text()='Sort Ascending']/img[@class=' x-menu-item-icon']");
		selenium.setSpeed(MIN_SPEED);
	}
    
	//test sort table by click on column header
	@Test
	public void testSortTableByClickOnColumnHeader() {		

		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
				
		this.clickWaitForElementPresent("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-endpoint')]/a");		
		selenium.setSpeed(MID_SPEED);
		selenium.click("//a[text()='Show in Groups']");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
		selenium.setSpeed(MIN_SPEED);
		
		checkSortAscendingSortDescending("name", "TEST_jobThirdProvider", "TEST_jobFirstProvider");
		checkSortAscendingSortDescending("endpoint", "http://127.0.0.1:9999/esb/provider",
				"http://127.0.0.1:9996/esb/provider");
		
	}
	
	//Stop and restart a service connected to Service Locator and check correct status display in server table
	@Test
	@Parameters({"jobFourthProvider"})
	public void testCheckServiceLocationPageStopZkServerRestart(String jobFourthProvider) {
		 
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		
		installStratService(jobFourthProvider);		
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");		
		//start job
		assertStartService(jobFourthProvider);
		Assert.assertFalse(selenium.isElementPresent("//div[text()='http://127.0.0.1:9996/esb/provider']" +
				"//ancestor::table[@class='x-grid3-row-table']//span[@style='color: red;']"));
		
		stopService("jobFourthProvider");
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");		
		//stop job
		assertStopService(jobFourthProvider);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='http://127.0.0.1:9996/esb/provider']" +
				"//ancestor::table[@class='x-grid3-row-table']//span[@style='color: red;']"));
		
		installStratService(jobFourthProvider);		
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");		
		//start job
		assertStartService(jobFourthProvider);
		Assert.assertFalse(selenium.isElementPresent("//div[text()='http://127.0.0.1:9996/esb/provider']" +
				"//ancestor::table[@class='x-grid3-row-table']//span[@style='color: red;']"));
		
	}	
	
	//stop zkserver, check status info of configuration page and serviceLocation page  
	@Test
	@Parameters({"esb.conf.zookeeperServer", "esb.conf.zookeeperServerWithWrong"})
	public void testStopStartZkServerCheckPageStatus(String zookeeperServer, String zookeeperServerWithWrong) {
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		//verify service start success
		Assert.assertTrue(selenium.isElementPresent("//div[text()='TEST_jobFirstProvider']"));
		Assert.assertTrue(selenium.isElementPresent("//div[text()='TEST_jobSecondProvider']"));
		Assert.assertTrue(selenium.isElementPresent("//div[text()='TEST_jobThirdProvider']"));
		
		modifyEsbConfigurationInConfigurationPage(zookeeperServerWithWrong, other.getString("esb.conf.StopZookeeperServerStatusIconLocator"));
        
		selenium.setSpeed(MAX_SPEED);
	  	Assert.assertTrue(selenium.isElementPresent("//div[contains(text(),'Zookeeper for this url is unavailable')]"));
	  	selenium.setSpeed(MIN_SPEED);
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		//verify display result
		this.waitForTextPresent("There are no services available. Please check your filter and click" +
				" refresh button to retry.", WAIT_TIME);		

		modifyEsbConfigurationInConfigurationPage(zookeeperServer, other.getString("esb.conf.ZookeeperServerStatusIconLocator"));
		  	
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		
		selenium.setSpeed(MAX_SPEED);
		//verify service start success
		Assert.assertTrue(selenium.isElementPresent("//div[text()='TEST_jobFirstProvider']"));
		selenium.setSpeed(MIN_SPEED);
		Assert.assertTrue(selenium.isElementPresent("//div[text()='TEST_jobSecondProvider']"));
		Assert.assertTrue(selenium.isElementPresent("//div[text()='TEST_jobThirdProvider']"));
		
	}
	
	//set sam-server url in configuration
    @Test
    @Parameters ({"esb.conf.serviceActivityRemoteMonitorServer", "esb.conf.serviceActivityMonitorServer"})
	public void testSetESBSamServer(String remoteMonitorServer, String localServer){
    	
	   //go to configuration page 
	   this.clickWaitForElementPresent("idMenuConfigElement");
		  
	   this.MouseDownWaitForElementPresent("//div[contains(text(),'ESB')]");	   
	   
	   modifySAMServer(remoteMonitorServer, other.getString("esb.conf.ServiceActivityMonitorServerStatusIconLocator"));
	   
	   modifySAMServer(localServer, other.getString("esb.conf.ServiceActivityMonitorServerStatusIconLocator"));
	   
	}
	
  //set sam-server url with wrong in configuration
    @Test
    @Parameters ({"esb.conf.serviceActivityMonitorServerWithWrong", "esb.conf.serviceActivityMonitorServer"})
	public void testSetESBSamServerWithWrongUrl(String remoteMonitorServerWithWrongUrl, String localServer){	   
    	
	   //go to configuration page 
	   this.clickWaitForElementPresent("idMenuConfigElement");
		  
	   this.MouseDownWaitForElementPresent("//div[contains(text(),'ESB')]");	   

	   modifySAMServer(remoteMonitorServerWithWrongUrl, other.getString("esb.conf.ServiceActivityMonitorServerStatusIconLocator.wrongURL"));
	   
	   selenium.setSpeed(MAX_SPEED);
	   Assert.assertTrue(selenium.isElementPresent("//div[contains(text(),'SAM Server for this url is unavailable')]"));
	   selenium.setSpeed(MIN_SPEED);
	   
	   modifySAMServer(localServer, other.getString("esb.conf.ServiceActivityMonitorServerStatusIconLocator"));
	   
	}
    
	public void startAllService(String jobFirstProvider, String jobSecondProvider, 
			String jobThirdProvider) {
		
		installStratService(jobFirstProvider);
		installStratService(jobSecondProvider);
		installStratService(jobThirdProvider);
		
	}	
	
	public void stopAllService(String jobFirstProvider, String jobSecondProvider, 
			String jobThirdProvider) {
		
		stopService(jobFirstProvider);
		stopService(jobSecondProvider);
		stopService(jobThirdProvider);
		
	}
	
	public void installStratService(String jobName) {
		
		System.out.println("**--**start");
		karaf.karafAction("install -s file:///D:/produck/fgzhang/dd/"+jobName+"_0.1.jar", 10000);
		System.out.println("**--**end");
		karaf.karafAction("job:start "+jobName+"", 10000);
		System.out.println("**--**end");
		
	}
	
	
	public void stopService(String jobName) {
		
		System.out.println("**--**start");
		karaf.karafAction("stop "+jobName+"", 10000);
		System.out.println("**--**end");
		
	}
	
	public void assertStartService(String jobName) {
		
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(!selenium.isElementPresent("//div[text()='TEST_"+jobName+"']//ancestor::div[contains(@class,'x-grid-group')]" +
		"//span[contains(text(),'Last seen')]"));
		selenium.setSpeed(MIN_SPEED);
			
	}
	
	public void assertStopService(String jobName) {
		
		this.waitForElementPresent("//div[contains(text(),'"+jobName+"')]//ancestor::div[contains(@class,'x-grid-group')]" +
				"//span[contains(text(),'Last seen')]", WAIT_TIME);
		Assert.assertTrue(selenium.isElementPresent("//div[contains(text(),'"+jobName+"')]//ancestor::div[contains(@class,'x-grid-group')]" +
				"//span[contains(text(),'Last seen')]"));
		                       
	}
	
	public void checkSortAscendingSortDescending(String xpathName, String value, String value1) {
				
		selenium.setSpeed(MID_SPEED);
        selenium.click("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-"+xpathName+"')]/a");
        selenium.setSpeed(MIN_SPEED);  
		selenium.click("//a[text()='Sort Descending']");
		selenium.setSpeed(MID_SPEED);
		System.out.println(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+xpathName+"']"));
		Assert.assertEquals(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+xpathName+"']"), value);       
        selenium.setSpeed(MIN_SPEED);
        
        selenium.setSpeed(MID_SPEED);
        selenium.click("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-"+xpathName+"')]/a");
        selenium.setSpeed(MIN_SPEED);  
		selenium.click("//a[text()='Sort Ascending']");
		selenium.setSpeed(MID_SPEED);
		System.out.println(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+xpathName+"']"));
		Assert.assertEquals(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+xpathName+"']"), value1);
		selenium.setSpeed(MIN_SPEED);
		
	}
	
	public void changeGroupDisplayService(String filedName, String newFiledName, String xpath) {
		
		
		this.clickWaitForElementPresent("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-"+filedName+"')]/a");		
		
		selenium.setSpeed(MID_SPEED);
		//cancel group display
		selenium.click("//a[text()='Show in Groups']");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid-group-div']"));
		selenium.setSpeed(MIN_SPEED);
		
		this.clickWaitForElementPresent("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-"+newFiledName+"')]/a");
		selenium.setSpeed(MID_SPEED);
		//select filed, service of according to the filed group display	
		selenium.click("//a[text()='Group By This Field']");
		
		Assert.assertTrue(selenium.isElementPresent("//div[text()='"+xpath+"']"));
		selenium.setSpeed(MIN_SPEED);
		
		
	}
	
	//hidden services/display service    
	public void hiddenDisplayServices(String serviceEndPoint,String serviceName) {
	 
		
		if(selenium.isTextPresent(serviceEndPoint) && selenium.isTextPresent(serviceName)) {
			
			if(selenium.isVisible("//div[text()='"+serviceEndPoint+"']")) {
			 
				 selenium.mouseDown("//div[text()='"+serviceName+"']");
				 selenium.setSpeed(MID_SPEED);
				 Assert.assertFalse(selenium.isVisible("//div[text()='"+serviceEndPoint+"']"));
				 selenium.setSpeed(MIN_SPEED);
			} else {
				 
				 selenium.mouseDown("//div[text()='"+serviceName+"']");
				 selenium.setSpeed(MID_SPEED);
				 Assert.assertTrue(selenium.isVisible("//div[text()='"+serviceEndPoint+"']"));
				 selenium.setSpeed(MIN_SPEED);
			}
			
		} else {
			
			System.out.println("services does not exist or wrong title");
		}
		
		
	}
	
	//modify the services display columns
	public void modifyDisplayServicesColumns(String xpathName) {
		

		if(selenium.isElementPresent("//span[text()='"+xpathName+"']")) {
			selenium.setSpeed(MID_SPEED);
			selenium.click("//a[text()='"+xpathName+"']/img[@class=' x-menu-item-icon']");
			Assert.assertFalse(selenium.isElementPresent("//span[text()='"+xpathName+"']"));
		    selenium.setSpeed(MIN_SPEED);
		} else {
			
			selenium.setSpeed(MID_SPEED);
			selenium.click("//a[text()='"+xpathName+"']/img[@class=' x-menu-item-icon']");
		    Assert.assertTrue(selenium.isElementPresent("//span[text()='"+xpathName+"']"));
		    selenium.setSpeed(MIN_SPEED);
		}
		

	}
	
	//sort table by click on column header
	public void SortTableByClickOnColumnHeader(String columnName, String value, String value1) {
		
		selenium.click("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-name x-component sort-asc')]");
		System.out.println(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+columnName+"']"));
		Assert.assertEquals(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+columnName+"']"), value1);
		selenium.click("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-name x-component sort-desc')]");		
		System.out.println(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+columnName+"']"));
		Assert.assertEquals(selenium.getText("//div[@class='x-grid3-cell-inner x-grid3-col-"+columnName+"']"), value1);
		
	}
	
	//start zookeeper server
	public void startStopZkServer(String controlZkServer) {
		
		 AntAction aa = new AntAction();
	     Hashtable<String, String> properties = new Hashtable<String, String>();
	     properties.put("server.home", "D:\\produck\\fgzhang\\talend-esb-4.2\\zookeeper");
	     properties.put("command", "zkServer.cmd");
	     properties.put("action", controlZkServer);
	     aa.runTarget("Server.xml", properties);
		
	}
    
	public void modifyEsbConfigurationInConfigurationPage(String ServiceLocation, String zookeeperServerStatusIconLocator) {	
		
		//go to configuration page 
		this.clickWaitForElementPresent("idMenuConfigElement");
		  
		this.MouseDownWaitForElementPresent("//div[contains(text(),'ESB')]");
		this.typeWordsInConfigurationMenu(other.getString("esb.conf.ZookeeperServer.editButton"), locatorOfAllInputTags, ServiceLocation);
	  	
	  	this.AssertEqualsInConfigurationMenu(other.getString("esb.conf.ZookeeperServer.editButton"), locatorOfAllInputTags,
	  			ServiceLocation, zookeeperServerStatusIconLocator);	    
		
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
		 selenium.keyDownNative(""+Event.ENTER);
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
		this.waitForElementPresent(locatorOfInput, Base.WAIT_TIME);
		assertEquals(selenium.getValue(locatorOfInput), value);
		selenium.fireEvent(locatorOfInput, "blur");
	}
	
	public void modifySAMServer(String MonitorServer, String MonitorServerStatusIconLocator) {					     
		  
		this.typeWordsInConfigurationMenu(other.getString("esb.conf.serviceActivityMonitorServer.editButton"), locatorOfAllInputTags, MonitorServer);
		  
	    this.AssertEqualsInConfigurationMenu(other.getString("esb.conf.serviceActivityMonitorServer.editButton"), locatorOfAllInputTags,
			   MonitorServer, MonitorServerStatusIconLocator);
		
	}
	
//	public static void main(String[] args) {
//		
//		String controlZkServer = "stop";
//		
//		 AntAction aa = new AntAction();
//	     Hashtable<String, String> properties = new Hashtable<String, String>();
//	     properties.put("server.home", "D:\\produck\\fgzhang\\talend-esb-4.2\\zookeeper");
//	     properties.put("command", "zkServer.cmd");
//	     properties.put("action", controlZkServer);
//	     aa.runTarget("Server.xml", properties);
//	}
}
