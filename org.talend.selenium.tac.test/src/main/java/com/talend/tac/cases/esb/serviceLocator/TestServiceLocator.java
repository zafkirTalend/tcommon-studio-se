package com.talend.tac.cases.esb.serviceLocator;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.talend.tac.base.Karaf;
import com.talend.tac.cases.Login;

public class TestServiceLocator extends Login {
	
	Karaf karaf = new Karaf("localhost", "D:/jar");
    
	//test display service of 'live services only'/'all services'	
	@Test
	public void testDisplayAllService() {		
		
		//install all job and start
		startAllService();
		
		//go to 'ServiceLocator' page
		this.clickWaitForElementPresent("!!!menu.servicelocator.element!!!");
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		//verify service start success
		assertStartService("jobFirstProvider");
		assertStartService("jobSecondProvider");
		assertStartService("jobThirdProvider");
//		assertStartService("jobFourthProvider");
//		assertStartService("jobFiveProvider");
//		assertStartService("jobSixProvider");	
		
	}
	
	//test check 'live services only', check display whether only dispaly live services
	@Test
	public void testDisplayLiveServicesOnly() {
		
		//stop job	
		stopService("jobFirstProvider");
		stopService("jobSecondProvider");
		
		//just only display live service 
		selenium.click("//label[text()='live services only']//preceding-sibling::input");
		
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		
		//just only display live service 
		assertStartService("jobThirdProvider");
//		assertStartService("jobFourthProvider");
//		assertStartService("jobFiveProvider");
//		assertStartService("jobSixProvider");	
		
	}
    
	//stop all services, check info of click 'all services' page
	@Test
	public void testDisplayLiveServicesOnlyAfterStopAllServices() {

		//stop all jobs	
		stopService("jobThirdProvider");
//		stopService("jobFourthProvider");
//		stopService("jobFiveProvider");
//		stopService("jobSixProvider");
		
		//click 'Refresh' button
		selenium.click("//b[text()='Refresh']");
		
		//stop job
		assertStopService("jobFirstProvider");
		assertStopService("jobSecondProvider");
		assertStopService("jobThirdProvider");
//		assertStopService("jobFourthProvider");
//		assertStopService("jobFiveProvider");
//		assertStopService("jobSixProvider");
		
		//just only display live service 
		selenium.click("//label[text()='live services only']//preceding-sibling::input");
		
		//verify display result
		this.waitForTextPresent("There are no services available. Please check your filter and click" +
				" refresh button to retry.", WAIT_TIME);
		
		
	}
	
	public void startAllService() {
		
		installStratService("jobFirstProvider");
		installStratService("jobSecondProvider");
		installStratService("jobThirdProvider");
//		installStratService("jobFourthProvider");
//		installStratService("jobFiveProvider");
//		installStratService("jobSixProvider");
		
	}
	
	public void stopAllService() {
		
		stopService("jobFirstProvider");
		stopService("jobSecondProvider");
		stopService("jobThirdProvider");
//		stopService("jobFourthProvider");
//		stopService("jobFiveProvider");
//		stopService("jobSixProvider");
		
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
	
}
