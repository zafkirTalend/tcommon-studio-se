package com.talend.cases.dashboard.connection;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import com.talend.tac.cases.Login;

public class TestConnectionCheck extends Login {
	@Test
	@Parameters( { "Mysql_Connectionlabel", "Mysql_Dbname", "Mysql_Dbtype",
			"Mysql_Host", "Mysql_Serverport", "Mysql_Username",
			"Mysql_Password", "Mysql_Datasourse", "Mysql_Additional",
			"Mysql_Stattable", "Mysql_Logstable" })
	public void testCheckConnectionMysqlWithRightParamters(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		inputConnectionParamters(label, dbname, dbtype, host, serverport, username,
				password, datasourse, additional, stat, logs);
		//check connections status
		selenium.click("idCheckUrlButton");
		waitForCheckConnectionStatus(4);
		this.clickWaitForElementPresent("//span[text()='Check connection']/preceding-sibling::div//div");
		
	}

	@Test
	@Parameters( { "Mysql_Connectionlabel", "Mysql_Dbname", "Mysql_Dbtype",
			"Mysql_Host", "Mysql_Serverport", "Mysql_Username",
			"Mysql_Password", "Mysql_Datasourse", "Mysql_Additional",
			"Mysql_Stattable", "Mysql_Logstable" })
	public void testConnectionCheckWithStringPort(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		serverport = "testport";
		inputConnectionParamters(label, dbname, dbtype, host, serverport, username,
				password, datasourse, additional, stat, logs);
		selenium.click("idCheckUrlButton");
		Assert.assertTrue(this.waitForTextPresent("Cannot connect to database (cause Illegal connection port value '"+serverport+"')", WAIT_TIME),"check connection with string ports failed!");

	}
	
	@Test
	@Parameters( { "Mysql_Connectionlabel", "Mysql_Dbname", "Mysql_Dbtype",
			"Mysql_Host", "Mysql_Serverport", "Mysql_Username",
			"Mysql_Password", "Mysql_Datasourse", "Mysql_Additional",
			"Mysql_Stattable", "Mysql_Logstable" })
	public void testConnectionCheckWithWrongPassword(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		password = "testpass";
		inputConnectionParamters(label, dbname, dbtype, host, serverport, username,
				password, datasourse, additional, stat, logs);
		selenium.click("idCheckUrlButton");
		Assert.assertTrue(this.waitForTextPresent("Cannot connect to database (cause Access denied for user", WAIT_TIME),"check connection with wrong password failed!");
	}

	@Test
	@Parameters( { "Mysql_Connectionlabel", "Mysql_Dbname", "Mysql_Dbtype",
			"Mysql_Host", "Mysql_Serverport", "Mysql_Username",
			"Mysql_Password", "Mysql_Datasourse", "Mysql_Additional",
			"Mysql_Stattable", "Mysql_Logstable" })
	public void testConnectionCheckWithTableNotExist(String label, String dbname, String dbtype,
			String host, String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		stat = "testtable";
		inputConnectionParamters(label, dbname, dbtype, host, serverport, username,
				password, datasourse, additional, stat, logs);
		selenium.click("idCheckUrlButton");
		Assert.assertTrue(this.waitForTextPresent("Table '"+dbname+"."+stat+"' doesn't exist", WAIT_TIME),"check connection with table not exist failed!");

	}


	public void inputConnectionParamters(String label, String dbname, String dbtype,
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
		selenium.setSpeed(MIN_SPEED);
		selenium.click(other.getString("inputname.id.connection.add.logTable"));
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
