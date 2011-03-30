package com.talend.tac.cases.executePlan;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class AddPlan extends Login {
     
	//add a method(add Plan)
	public void addPlan(String label,String description) {
		
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
	    selenium.setSpeed(MID_SPEED);
		selenium.click("//button[text()='Add plan']");
	    
	    selenium.type("//input[@class=' x-form-field x-form-text x-form-invalid' and @name='label']", label);
	    selenium.fireEvent("//input[@name='label']", "blur");
		
	    selenium.type("//input[@class=' x-form-field x-form-text' and @name='description']", description);
	    selenium.fireEvent("//input[@name='description']", "blur");
	    
	}      
	
	
	//clear all plan
	@Test(groups={"addPlan"})
	public void clearplan() {
		
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		Assert.assertTrue(selenium.isElementPresent("//div[@class='header-title' and text()='Execution plan']"));
		selenium.click("idSubModuleRefreshButton");
		String xpathPlan = "//div[@class='x-grid3-cell-inner x-grid3-col-label']";
			
		for(int i =0;;i++) {
			
			if(selenium.isElementPresent(xpathPlan)) {
				
				selenium.mouseDown(xpathPlan);
				selenium.click("idSubModuleDeleteButton");
				Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the " +
						"selected execution plan [\\s\\S]$"));
			} else {
				
				System.out.println("plan is no exist");
				break;
				
			}
		} 
				
	}
	
	//add a plan 
	@Test(dependsOnMethods={"clearplan"})
	@Parameters({"plan.label","plan.description"})
	public void testAddPlan(String label,String description) {
		
		addPlan(label, description);
	    
	    selenium.click("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isElementPresent("//div[text()='"+label+"']"));
	    selenium.setSpeed(MIN_SPEED);
	}   
	
	//add a exist plan 
	@Test(dependsOnMethods={"testAddPlan"})
	@Parameters({"plan.label","plan.description"})
	public void testAddExistPlan(String label,String description) {
		
	    addPlan(label, description);
	    
	    selenium.click("//button[@class='x-btn-text ' and @id='idFormSaveButton']");
	    selenium.setSpeed(MID_SPEED);
	    Assert.assertTrue(selenium.isTextPresent(rb.getString("executionPlan.error.uniqueLabel")));
	    selenium.setSpeed(MIN_SPEED);
	}  
	
}
