package com.talend.cases.dashboard.connection;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.awt.Event;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;
import com.thoughtworks.selenium.Selenium;

public class TestDuplicateConnection extends Login {
	@Test
	// (groups = "AddConnection")
	@Parameters({ "Mysql_Connectionlabel" })
	public void testDuplicateConnectionMysql(String label)
			throws InterruptedException {
		this.waitForElementPresent("!!!menu.connections.element!!!", 30);
		selenium.click("!!!menu.connections.element!!!");
		this.waitForElementPresent("idSubModuleAddButton", 30);
		Connection toDuplicate = new Connection(this.selenium, label);
		duplicateConnection(label);
		Connection afterDuplicate = new Connection(this.selenium, "Copy_of_"
				+ label);
		Assert.assertTrue(afterDuplicate.isSameWithConnection(toDuplicate));

	}

	public void duplicateConnection(String label) throws InterruptedException {
		selenium.refresh();
		selenium.setSpeed(MID_SPEED);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ label + "')]");
		Thread.sleep(3000);
		selenium.click("//div[text()='Connections']/ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idSubModuleDuplicateButton']");
		selenium.click("idFormSaveButton");
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='Copy_of_"
						+ label + "')]"), "duplicate connection failed!");
		selenium.setSpeed(MIN_SPEED);
	}

	class Connection extends Base {
		private String label;
		private String dbtype;
		private String host;
		private String port;
		private String database;
		private String username;
		private String password;
		private String datasource;
		private String additional;
		private String stat;
		private String logs;

		public Connection(Selenium selenium, String label)
				throws InterruptedException {

			selenium.refresh();
			// this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+label+"')]",
			// WAIT_TIME);
			selenium.setSpeed(MID_SPEED);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
					+ label + "')]");
			Thread.sleep(3000);
			selenium.setSpeed(MIN_SPEED);
			setDatabase(selenium.getValue(other
					.getString("inputname.id.connection.add.database")));
			
			setUsername(selenium.getValue(other
					.getString("inputname.id.connection.add.username")));
			setPassword(selenium.getValue(other
					.getString("inputname.id.connection.add.password")));
			setHost(selenium.getValue(other
					.getString("inputname.id.connection.add.host")));
			setPort(selenium.getValue(other
					.getString("inputname.id.connection.add.port")));
			setDbtype(selenium.getValue(other
					.getString("inputname.id.connection.add.dbtype")));
			setDatasource(selenium.getValue(other
					.getString("inputname.id.connection.add.dataSource")));
			setLogs(selenium.getValue(other
					.getString("inputname.id.connection.add.logTable")));
			setStat(selenium.getValue(other
					.getString("inputname.id.connection.add.statTable")));
		}

		public boolean isSameWithConnection(Connection con) {
			boolean same = true;
			if (!(this.database.equals(con.getDatabase())
					&& this.host.equals(con.getHost())
					&& this.dbtype.equals(con.getDbtype())
					&& this.username.equals(con.getUsername())
					&& this.password.equals(con.getPassword()) && this.port
					.equals(con.getPort()))) {
				same = false;
			}
			return same;

		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getDbtype() {
			return dbtype;
		}

		public void setDbtype(String dbtype) {
			this.dbtype = dbtype;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getDatabase() {
			return database;
		}

		public void setDatabase(String database) {
			this.database = database;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getDatasource() {
			return datasource;
		}

		public void setDatasource(String datasource) {
			this.datasource = datasource;
		}

		public String getAdditional() {
			return additional;
		}

		public void setAdditional(String additional) {
			this.additional = additional;
		}

		public String getStat() {
			return stat;
		}

		public void setStat(String stat) {
			this.stat = stat;
		}

		public String getLogs() {
			return logs;
		}

		public void setLogs(String logs) {
			this.logs = logs;
		}

	}

}
