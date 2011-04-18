package com.talend.tac.cases.dbConfig;

import com.talend.tac.base.Base;
import static org.testng.Assert.*;

public class DbConfig extends Base {

	/**
	 * the process of dbConfig(input url,userName,userPasswd,driver, then check
	 * it.)
	 * 
	 * @param url
	 * @param userName
	 * @param userPassWd
	 * @param driver
	 */
	public void DbConfigProcess(String url, String userName, String userPassWd,
			String driver) {
		selenium.windowMaximize();
		waitForPageReactivity();// wait and judge: if no license set,tac will load directly to dbConfig page
		if (selenium.isElementPresent("idDbConfigLoginPasswordInput")) {
		} else {
			waitForElementPresent("idLoginOpenDbConfigButton", WAIT_TIME);
			selenium.click("idLoginOpenDbConfigButton");
		}

		selenium.type("idDbConfigLoginPasswordInput", "admin");
		selenium.click("idDbConfigSubmitButton");
		selenium.type("idDbConfigUrlInput", url);
		selenium.type("idDbConfigUserNmeInput", userName);
		selenium.type("idDbConfigPasswordInput", userPassWd);
		selenium.type("idDbConfigDriverInput", driver);
		selenium.click("idDbConfigCheckButton");
		// some other action like "save" ......
	}

	/**
	 * The first time to open TAC ,there may be a delay. wait and judge this two
	 * condition: 
	 * 1.No license ever set to the current db ,Tac will directly load to dbConfig page
	 * 2.If license has already been set, loginPage will be seen.
	 */
	public void waitForPageReactivity() {
		boolean flag = selenium.isElementPresent("idDbConfigLoginPasswordInput")
				|| selenium.isElementPresent("idLoginOpenDbConfigButton");
		while (flag == false) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flag = selenium.isElementPresent("idDbConfigLoginPasswordInput")
					|| selenium.isElementPresent("idLoginOpenDbConfigButton");
		}
	}

	/**
	 * wait and check the db Connection status
	 */
	public void waitForCheckConnectionStatus( int OK_Num) {
		boolean flag = false;
		int seconds_Counter = 0;
		while (flag == false) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			flag = selenium.getXpathCount("//td[@align='LEFT']/div").intValue()==5;
			flag = selenium.getXpathCount("//div[text()='OK']").intValue() >= OK_Num;
			seconds_Counter++;
			if(seconds_Counter >= WAIT_TIME)
				assertTrue(selenium.getXpathCount("//div[text()='OK']").intValue() >= OK_Num);
		}

	}
}
