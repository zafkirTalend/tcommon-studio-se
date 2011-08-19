package com.talend.cases.esb;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Karaf;
import com.talend.tac.cases.Login;


public class Esb extends Login {
	
	Karaf karaf = null;
//		new Karaf("192.168.0.94", "D:/jar");
	
	
	/**
	 * this method is only to open activity monitor page
	 */
	public void openServiceActivityMonitor(){
		this.clickWaitForElementPresent("!!!menu.serviceactivity.element!!!");
		this.waitForElementPresent("//div[@class='header-title' and text()='Service Activity Monitoring']", WAIT_TIME);
	}
	
	public void sendServiceRequest(String karafURL,String jarPath,String consumerName){
		karaf = new Karaf(karafURL, jarPath);
		System.out.println("service request send-->  start!");
		karaf.karafAction("job:start "+consumerName+"", 10000);
		System.out.println("service request send-->  ended!");
		
	}
	
	/**
	 * this method is used to wait something disappear
	 * @param element: element that should disappear
	 * @param timeout: max wait time
	 */
	public void waitForElementDispear(String element, int timeout) {
		if (selenium.isElementPresent(element)) {
			for (int second = 0;; second++) {
				if (second >= timeout)
					Assert.assertFalse(selenium.isElementPresent(element));
				try {
					if ((!selenium.isElementPresent(element))) {
						break;
					} else {
						this.sleep(1000);
					}
				} catch (Exception e) {
				}

			}
		}

	}
	
}
