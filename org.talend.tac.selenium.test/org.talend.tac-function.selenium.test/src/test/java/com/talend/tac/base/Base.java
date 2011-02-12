package com.talend.tac.base;

import java.util.Locale;
import java.util.ResourceBundle;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Base {
	public Selenium selenium;

	public Locale currentLocale = new Locale("en", "US");	// set the locale that you want to test
	public ResourceBundle rb = ResourceBundle.getBundle("messages", currentLocale);

	@BeforeTest
	@Parameters({"server", "port", "browser", "url", "language", "country", "root"})
	public void initSelenium(String server, String port, String browser, String url, String language, String country, String root) {
		server = this.setDefaultValue(server, "localhost");
		port = this.setDefaultValue(port, 4444+"");
		browser = this.setDefaultValue(browser, "*firefox");
		url = this.setDefaultValue(url, "http://www.google.com");
		
		language = this.setDefaultValue(language, "en");
		country = this.setDefaultValue(country, "US");
		currentLocale = new Locale(language, country);
		rb = ResourceBundle.getBundle("messages", currentLocale);
		
System.out.println("Server: " + server + ", port: " + port + ", broser: " + browser + ", " + url);
System.out.println("language: " + language + ", country: " + country);
		selenium = new DefaultSelenium(server, Integer.parseInt(port), browser, url);  //4444 is default server port

		selenium.start();
		selenium.open(root);
	}
	
	public void assertTrue(boolean matches) {
		
	}
	
	public String setDefaultValue(String variable, String value) {
		String setValue = variable;
		if("".equals(variable) || variable == null) {
			setValue = value;
		}
		return setValue;
	}
}
