package com.talend.tac.cases.grid;


import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Parameters;


public class TestGridExecutionsPageDisplay extends Grid {

	@Test
	@Parameters({"grid.task.label","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testGridGoToFirstPage(String label, String description, String projectName, String branchName, String jobName, String version, String context, String serverName, String statisticName) {
	    this.cleanTask();
		this.addTask(label, description, projectName, branchName, jobName, version, context, serverName, statisticName);
		String label2 = label+"second";
		this.addTask(label2, description, projectName, branchName, jobName, version, context, serverName, statisticName);
        this.runTask(label, 11);
        this.runTask(label2,11);
        this.openGridMenu();
        this.sleep(5000);
        Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()+selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label2+"']").intValue()==2,"Grid past execution display failed!");
	    String containsPages = selenium.getText("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//div[contains(text(),'of')]");
        System.out.println(containsPages);
	    String totalPage = containsPages.substring(containsPages.indexOf(" ")+1);
        System.out.println(totalPage);
	    Assert.assertTrue(totalPage.equals("3"), "total page display wrong!");
	    selenium.type("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']", "1");
	    selenium.keyDown("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']","\\13");
	    this.sleep(5000);
	    Assert.assertTrue(selenium.getValue("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']").equals("1"), "go to first page failed!");
	    Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()+selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label2+"']").intValue()==10,"Grid past execution display failed!");
        	
	}
	
//	@Test//(dependsOnMethods={"testGridGoToFirstPage"})
	@Parameters({"grid.task.label","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testGridGoToLastPage(String label, String description, String projectName, String branchName, String jobName, String version, String context, String serverName, String statisticName) {
		String label2 = label+"second";
        this.openGridMenu();
        this.sleep(5000);
        selenium.type("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']", "1");
	    selenium.keyDown("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']","\\13");
	    this.sleep(5000);
	    Assert.assertTrue(selenium.getValue("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']").equals("1"), "go to first page failed!");
	    Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()+selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label2+"']").intValue()==10,"Grid past execution display failed!");
       selenium.setSpeed(MID_SPEED);
       selenium.mouseDown("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//td[9]");
	    selenium.click("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//td[9]");
        this.sleep(5000);
	    Assert.assertTrue(selenium.getValue("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']").equals("3"), "go to last page failed!");
	    Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()+selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label2+"']").intValue()==2,"Grid past execution display failed!");
        	
	}
	
//	@Test
	@Parameters({"grid.task.label","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testGridGoToFirstPageByUIButton(String label, String description, String projectName, String branchName, String jobName, String version, String context, String serverName, String statisticName) {
		String label2 = label+"second";
        this.openGridMenu();
        this.sleep(5000);
        Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()+selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label2+"']").intValue()==2,"Grid past execution display failed!");
	    Assert.assertTrue(selenium.getValue("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']").equals("3"));
	    selenium.click("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//td[@class='x-toolbar-left']//tr[@class='x-toolbar-left-row']//td[1]");
	    this.sleep(5000);
	    Assert.assertTrue(selenium.getValue("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']").equals("1"),"Go to first page by button failed!");
	}
	
	
//	@Test//(dependsOnMethods={"testGridGoToFirstPageByUIButton"})
	@Parameters({"grid.task.label","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testGridGoToPreviousPage(String label, String description, String projectName, String branchName, String jobName, String version, String context, String serverName, String statisticName) {
		String label2 = label+"second";
        this.openGridMenu();
	    Assert.assertTrue(selenium.getValue("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']").equals("3"), "go to last page failed!");
	    Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()+selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label2+"']").intValue()==2,"Grid past execution display failed!");
        selenium.click("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//td[@class='x-toolbar-left']//tr[@class='x-toolbar-left-row']//td//table[@class=' x-btn x-component x-unselectable  x-btn-icon ']");
        this.sleep(3000);
        Assert.assertTrue(selenium.getValue("//div[@class=' x-small-editor x-toolbar x-component x-toolbar-layout-ct ']//input[@class='gwt-TextBox x-component ']").equals("2"), "go to last page failed!");
	    Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()+selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label2+"']").intValue()==10,"Grid past execution display failed!");
        
	}
	
	
}
