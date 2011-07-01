package com.talend.tac.cases.grid;



import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import com.talend.tac.cases.executePlan.TriggerDate;


public class TestGridUICalendarFilter extends Grid {
	TriggerDate date = new TriggerDate();
	@Test
	@Parameters({"grid.task.label","labelDescription","AddcommonProjectname","branchNameTrunk","jobNameTJava","version0.1",
		"context","ServerForUseAvailable","statisticEnabled"})
	public void testGridCalendarFilter(String label, String description, String projectName, String branchName, String jobName, String version, String context, String serverName, String statisticName) {
	    this.cleanTask();
		this.addTask(label, description, projectName, branchName, jobName, version, context, serverName, statisticName);
        this.openGridMenu();
        this.sleep(5000);
        Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()==0,"Grid past execution display failed!");
        this.openTaskMenu();
        this.runTask(label, 2);
        this.openGridMenu();
        this.sleep(5000);
        Assert.assertTrue(selenium.getXpathCount("//div[@class='x-grid3-cell-inner x-grid3-col-taskLabel' and text()='Task: "+label+"']").intValue()==2,"Grid past execution display failed!");
        this.addSimpleTriggerForTask(label, "testSimpleTrigger", "3600", "100");
        this.sleep(15000);
        this.openGridMenu();
        this.sleep(5000);
        Assert.assertTrue(selenium.getXpathCount("//span[@title='Waiting for triggering...' and text()='Waiting for triggering...']").intValue()==7,"Grid Future execution display failed!");
       this.clickWaitForElementPresent("//div[@title='Go to date...']//img");
       if(TriggerDate.isClickFutureMonthButton(date.getFuture(24))){
   		this.clickWaitForElementPresent("//div[contains(@class,'x-icon-btn x-nodrag x-date-right-icon x-component')]");
       }
       this.clickWaitForElementPresent("//td[@class='x-date-active']/a/span[text()='" + date.getFuture(24).days + "']");
       this.sleep(5000);
       Assert.assertTrue(selenium.getXpathCount("//span[@title='Waiting for triggering...' and text()='Waiting for triggering...']").intValue()==10,"Grid Future execution display failed!");
       this.clickWaitForElementPresent("//div[@title='Go to date...']//img");
       this.clickWaitForElementPresent("//button[@class='x-btn-text ' and text()='Today']");
       this.sleep(5000);
       Assert.assertTrue(selenium.getXpathCount("//span[@title='Waiting for triggering...' and text()='Waiting for triggering...']").intValue()==7,"Grid Future execution display failed!");
       this.clickWaitForElementPresent("//div[@title='Go to date...']//img");
       if(TriggerDate.isClickPastMonthButton(date.getPast(48))){
    	   this.clickWaitForElementPresent("//div[contains(@class,'x-icon-btn x-nodrag x-date-left-icon x-component')]");
       }
       this.clickWaitForElementPresent("//td[@class='x-date-active']/a/span[text()='"+date.getPast(48).days+"']");
	   this.sleep(5000);
	   Assert.assertTrue(selenium.getXpathCount("//span[@title='Waiting for triggering...' and text()='Waiting for triggering...']").intValue()==7,"Grid Future execution display failed!");
	   Assert.assertTrue(selenium.isElementPresent("//div[@class='x-panel-tbar x-panel-tbar-noheader x-panel-tbar-noborder']//table[@class='x-toolbar-right-ct']//div[@class='my-paging-display x-component ' and text()='Items 1 to 10']"));
	
	}
	
	
	
}
