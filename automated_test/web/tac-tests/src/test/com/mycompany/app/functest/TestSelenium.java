import com.thoughtworks.selenium.*;

public class TestSelenium extends SeleneseTestCase {

	public void setUp() throws Exception {
		setUp("http://localhost:8080/", "*firefox");
	}

	public void testTestSelenium() throws Exception {
		selenium.open("/org.talend.administrator/");
		selenium.type("ext-gen16", "admin@company.com");
		selenium.type("ext-gen18", "admin");
		selenium.click("ext-gen83");
		selenium.waitForPageToLoad("30000");
		selenium.waitForPopUp("_self", "30000");
		selenium.click("ext-gen315");
		selenium.waitForPopUp("_self", "30000");
	}

}
