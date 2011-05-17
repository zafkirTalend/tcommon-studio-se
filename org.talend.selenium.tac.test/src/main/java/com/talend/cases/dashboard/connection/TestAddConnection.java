package com.talend.cases.dashboard.connection;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.awt.Event;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;

public class TestAddConnection extends Login {
	@Test(groups = "AddConnection")
	@Parameters( { "Mysql_Connectionlabel", "Mysql_Dbname", "Mysql_Dbtype",
			"Mysql_Host", "Mysql_Serverport", "Mysql_Username",
			"Mysql_Password", "Mysql_Datasourse", "Mysql_Additional",
			"Mysql_Stattable", "Mysql_Logstable" })
	public void addConnectionMysql(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		addConnection(label, dbname, dbtype, host, serverport, username,
				password, datasourse, additional, stat, logs);
	}

	@Test(groups = "AddConnection")
	@Parameters( { "MSSQL_Connectionlabel", "MSSQL_Dbname", "MSSQL_Dbtype",
			"MSSQL_Host", "MSSQL_Serverport", "MSSQL_Username",
			"MSSQL_Password", "MSSQL_Datasourse", "MSSQL_Additional",
			"MSSQL_Stattable", "MSSQL_Logstable" })
	public void addConnectionMSSQL(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		addConnection(label, dbname, dbtype, host, serverport, username,
				password, datasourse, additional, stat, logs);

	}

//	@Test(groups = "AddConnection")
	@Parameters( { "Oracle_Connectionlabel", "Oracle_Dbname", "Oracle_Dbtype",
			"Oracle_Host", "Oracle_Serverport", "Oracle_Username",
			"Oracle_Password", "Oracle_Datasourse", "Oracle_Additional",
			"Oracle_Stattable", "Oracle_Logstable" })
	public void addConnectionOracle(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		addConnection(label, dbname, dbtype, host, serverport, username,
				password, datasourse, additional, stat, logs);

	}
	@Test
	@Parameters( { "Mysql_Connectionlabel", "Mysql_Dbname", "Mysql_Dbtype",
		"Mysql_Host", "Mysql_Serverport", "Mysql_Username",
		"Mysql_Password", "Mysql_Datasourse", "Mysql_Additional",
		"Mysql_Stattable", "Mysql_Logstable" })
	public void deleteConnection(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		addConnection("test_"+label, dbname, dbtype, host, serverport, username,
				password, datasourse, additional, stat, logs);
		String warningmessage = other.getString("delete.connetion.warning");
		//delete choose cancel
		selenium.refresh();
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='test_"+ label + "')]", Base.WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='test_"+ label + "')]");
		selenium.chooseCancelOnNextConfirmation();
		selenium.setSpeed(MID_SPEED);
		selenium.click("//div[text()='Connections']/ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idSubModuleDeleteButton']");
//		selenium.getConfirmation();
		assert (selenium.getConfirmation().equals(warningmessage));
		Assert.assertTrue(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='test_"+ label + "')]"), "delete cancel failed!");
		selenium.setSpeed(MIN_SPEED);
		//delete choose ok
		selenium.refresh();
		this.waitForElementPresent(
				"//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='test_"+ label + "')]", Base.WAIT_TIME);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='test_"+ label + "')]");
		selenium.chooseOkOnNextConfirmation();
		selenium.setSpeed(MAX_SPEED);
		selenium.click("//div[text()='Connections']/ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idSubModuleDeleteButton']");
//		System.out.println(selenium.getConfirmation());
		assert (selenium.getConfirmation().equals(warningmessage));
		selenium.refresh();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='test_"+ label + "')]"), "delete choose ok failed!");
		selenium.setSpeed(MIN_SPEED);
		
		
		
	}

	public void addConnection(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		this.waitForElementPresent("!!!menu.connections.element!!!", 30);
		selenium.click("!!!menu.connections.element!!!");
		this.waitForElementPresent("idSubModuleAddButton", 30);
		selenium.click("idSubModuleAddButton");
		this.waitForElementPresent("//span[text()='Database connection']", 30);

		// configure database parameters
		// DB label
		typeAndBlur(other.getString("inputname.id.connection.add.label"), label);
		// database server port
		typeAndBlur(other.getString("inputname.id.connection.add.port"), serverport);
		// database host
		typeAndBlur(other.getString("inputname.id.connection.add.host"), host);
		// database name
		typeAndBlur(other.getString("inputname.id.connection.add.database"), dbname);
		// database user name
		typeAndBlur(other.getString("inputname.id.connection.add.username"), username);
		// database password
		typeAndBlur(other.getString("inputname.id.connection.add.password"), password);
		// data source
		typeAndBlur(other.getString("inputname.id.connection.add.dataSource"),
				datasourse);
		// additional parameters
		typeAndBlur(other.getString("inputname.id.connection.add.additionnalParams"),
				additional);
		// database state table
		typeAndBlur(other.getString("inputname.id.connection.add.statTable"), stat);
		// database logs table
		typeAndBlur(other.getString("inputname.id.connection.add.logTable"), logs);
		selenium.setSpeed(MID_SPEED);
		// select db type
		selenium.click(other.getString("inputname.id.connection.add.dbtype"));
		selenium.mouseDown("//div[@role='listitem'][" + dbtype + "]");
		selenium.fireEvent(other.getString("inputname.id.connection.add.dbtype"), "blur");
		selenium.click(other.getString("inputname.id.connection.add.logTable"));
		//check connections status
		selenium.click("idCheckUrlButton");
		waitForCheckConnectionStatus(4);
		this.clickWaitForElementPresent("//span[text()='Check connection']/preceding-sibling::div//div");
		// save button
		selenium.mouseDown("idFormSaveButton");
//		selenium.keyPressNative(Event.ENTER + "");
//		selenium.keyUpNative(Event.ENTER + "");
		selenium.click("idFormSaveButton");
		selenium.mouseUp("idFormSaveButton");
		// check whether add success
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.refresh();
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+ label + "')]", WAIT_TIME);
		assertTrue(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+ label + "')]"), "DBConnection type: "+ dbtype + " added failed!");
	}

	public void waitForCheckConnectionStatus(int OK_Num) {
		boolean flag = false;
		int seconds_Counter = 0;
		while (flag == false) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = selenium.getXpathCount("//div[text()='OK' or contains(text(),'Ok (')]").intValue() >= OK_Num;
			seconds_Counter++;
			if (seconds_Counter >= WAIT_TIME)
				assertTrue(selenium.getXpathCount("//div[text()='OK' or contains(text(),'Ok (')]")
						.intValue() >= OK_Num);
		}

	}
}
