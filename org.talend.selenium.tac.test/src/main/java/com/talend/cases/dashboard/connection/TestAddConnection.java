package com.talend.cases.dashboard.connection;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.awt.Event;

import com.talend.tac.cases.Login;

public class TestAddConnection extends Login {
	@Test(groups = "AddConnection")
	@Parameters({ "Mysql_Connectionlabel", "Mysql_Dbname","Mysql_Dbtype" ,"Mysql_Host",
			"Mysql_Serverport", "Mysql_Username", "Mysql_Password",
			"Mysql_Datasourse", "Mysql_Additional", "Mysql_Stattable",
			"Mysql_Logstable" })
	public void addConnectionMysql(String label, String dbname, String dbtype,String host,
			String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		addConnection(label, dbname, dbtype,host, serverport, username, password,
				datasourse, additional, stat, logs);
	}

	@Test(groups = "AddConnection")
	@Parameters({ "MSSQL_Connectionlabel", "MSSQL_Dbname","MSSQL_Dbtype", "MSSQL_Host",
			"MSSQL_Serverport", "MSSQL_Username", "MSSQL_Password",
			"MSSQL_Datasourse", "MSSQL_Additional", "MSSQL_Stattable",
			"MSSQL_Logstable" })
	public void addConnectionMSSQL(String label, String dbname,String dbtype, String host,
			String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		addConnection(label, dbname,dbtype, host, serverport, username, password,
				datasourse, additional, stat, logs);

	}
	@Test(groups = "AddConnection")
	@Parameters({ "Oracle_Connectionlabel", "Oracle_Dbname", "Oracle_Dbtype","Oracle_Host",
			"Oracle_Serverport", "Oracle_Username", "Oracle_Password",
			"Oracle_Datasourse", "Oracle_Additional", "Oracle_Stattable",
			"Oracle_Logstable" })
	public void addConnectionOracle(String label, String dbname,String dbtype, String host,
			String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		addConnection(label, dbname,dbtype, host, serverport, username, password,
				datasourse, additional, stat, logs);

	}

	public void addConnection(String label, String dbname,String dbtype, String host,
			String serverport, String username, String password,
			String datasourse, String additional, String stat, String logs) {
		this.waitForElementPresent("!!!menu.connections.element!!!", 30);
		selenium.click("!!!menu.connections.element!!!");
		this.waitForElementPresent("idSubModuleAddButton", 30);
		selenium.click("idSubModuleAddButton");
		this.waitForElementPresent("//span[text()='Database connection']", 30);
		
		// configure database parameters
		// DB label
		selenium.type(other.getString("inputname.id.connection.add.label"),
				label);
		selenium.fireEvent(
				other.getString("inputname.id.connection.add.label"), "blur");
		
		// database server port
		selenium.type(other.getString("inputname.id.connection.add.port"),
				serverport);
		selenium.fireEvent(other.getString("inputname.id.connection.add.port"),
				"blur");
		// database host
		selenium.type(other.getString("inputname.id.connection.add.host"), host);
		selenium.fireEvent(other.getString("inputname.id.connection.add.host"),
				"blur");
		// database name
		selenium.type(other.getString("inputname.id.connection.add.database"),
				dbname);
		selenium.fireEvent(
				other.getString("inputname.id.connection.add.database"), "blur");
		// database user name
		selenium.type(other.getString("inputname.id.connection.add.username"),
				username);
		selenium.fireEvent(
				other.getString("inputname.id.connection.add.username"), "blur");
		// database password
		selenium.type(other.getString("inputname.id.connection.add.password"),
				password);
		selenium.fireEvent(
				other.getString("inputname.id.connection.add.password"), "blur");
		// data source
		selenium.type(
				other.getString("inputname.id.connection.add.dataSource"),
				datasourse);
		selenium.fireEvent(
				other.getString("inputname.id.connection.add.dataSource"),
				"blur");
		// additional parameters
		selenium.type(other
				.getString("inputname.id.connection.add.additionnalParams"),
				additional);
		selenium.fireEvent(other
				.getString("inputname.id.connection.add.additionnalParams"),
				"blur");
		// database state table
		selenium.type(other.getString("inputname.id.connection.add.statTable"),
				stat);
		selenium.fireEvent(
				other.getString("inputname.id.connection.add.statTable"),
				"blur");
		// database logs table
		selenium.type(other.getString("inputname.id.connection.add.logTable"),
				logs);
		selenium.fireEvent(
				other.getString("inputname.id.connection.add.logTable"), "blur");		
		
		selenium.setSpeed(MID_SPEED);
		// select db type 
		selenium.click(other.getString("inputname.id.connection.add.dbtype"));
		
		selenium.mouseDown("//div[@role='listitem']["+dbtype+"]");
		
		selenium.fireEvent(
				other.getString("inputname.id.connection.add.dbtype"), "blur");
		selenium.click(other.getString("inputname.id.connection.add.logTable"));
		
		// save button
		selenium.mouseDown("idFormSaveButton");
		
		selenium.keyPressNative(Event.ENTER+"");
		selenium.keyUpNative(Event.ENTER+"");
		
		//check whether add success
		Assert.assertTrue(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+label+"')]"), "DBConnection type: "+dbtype+" added failed!");
	}

}
