package com.talend.tac.cases.executePlan;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;

public class TestAddTriggerAddFileTrigger extends Login {
	@Test
	// (groups={"plan.addtrigger.fi"})
	@Parameters({ "plan.toaddfiletrigger.label",
			"plan.filetrigger.exist.label",
			"plan.filetrigger.exist.forderpath",
			"plan.filetrigger.exist.timeinterval",
			"plan.filetrigger.exist.filemask", "plan.filetrigger.server" })
	public void testAddTriggerAddFileTriggerExist(String plantoaddfiletrigger,
			String filetriggerlabel, String foldpath, String interval,
			String mask, String serverName) throws InterruptedException {
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent(
				"//div[@class='header-title' and text()='Execution Plan']",
				WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
		addFileTrigger(plantoaddfiletrigger, filetriggerlabel, foldpath,
				interval, mask, serverName, 0);
		triggerExistCheck(filetriggerlabel);
	}

	@Test
	@Parameters({ "plan.toaddfiletrigger.label",
			"plan.filetrigger.create.label",
			"plan.filetrigger.create.forderpath",
			"plan.filetrigger.create.timeinterval",
			"plan.filetrigger.create.filemask", "plan.filetrigger.server" })
	public void testAddTriggerAddFileTriggerCreate(String plantoaddfiletrigger,
			String filetriggerlabel, String foldpath, String interval,
			String mask, String serverName) throws InterruptedException {
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent(
				"//div[@class='header-title' and text()='Execution Plan']",
				WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
		addFileTrigger(plantoaddfiletrigger, filetriggerlabel, foldpath,
				interval, mask, serverName, 1);
		System.out.println("THE FIRST TIME:");
		this.waitForElementPresent("//span[text()='Running...']",
				Base.WAIT_TIME);
		this.waitForElementPresent("//span[text()='Ended...']", Base.WAIT_TIME);
		System.out.println("THE FIRST TIME ENDED:");
		if(new FileTrigger().createNewFile(foldpath)){
			System.out.println("fiLE CREATED!");
		}
		else{
			System.out.println("fiLE CREATED  failed!");
		}
		triggerCreateCheck(filetriggerlabel);
		
	}

	@Test
	@Parameters({ "plan.toaddfiletrigger.label",
			"plan.filetrigger.modify.label",
			"plan.filetrigger.modify.forderpath",
			"plan.filetrigger.modify.timeinterval",
			"plan.filetrigger.modify.filemask", "plan.filetrigger.server" })
	public void testAddTriggerAddFileTriggerModify(String plantoaddfiletrigger,
			String filetriggerlabel, String foldpath, String interval,
			String mask, String serverName) throws InterruptedException {
		this.clickWaitForElementPresent("!!!menu.executionPlan.element!!!");
		this.waitForElementPresent(
				"//div[@class='header-title' and text()='Execution Plan']",
				WAIT_TIME);
		Assert.assertTrue(selenium
				.isElementPresent("//div[@class='header-title' and text()='Execution Plan']"));
		addFileTrigger(plantoaddfiletrigger, filetriggerlabel, foldpath,
				interval, mask, serverName, 2);

	}

	public void addFileTrigger(String planLabel, String fileTriggerLabel,
			String filePath, String seconds, String fileMarks,
			String serverName, int triggerType) throws InterruptedException {
		String checkboxid = null;
		if (triggerType == 0) {
			checkboxid = "idJobConductorFileTriggerFtExitCheckBox";
		}
		if (triggerType == 1) {
			checkboxid = "idJobConductorFileTriggerFtCreateCheckBox";
		}
		if (triggerType == 2) {
			checkboxid = "idJobConductorFileTriggerFtModifiedCheckBox";
		}
		this.waitForElementPresent("//span[text()='" + planLabel + "']",
				WAIT_TIME);
		selenium.mouseDown("//span[text()='" + planLabel + "']");
		selenium.click("//button[text()='Add trigger...']");// add a trigger
		selenium.click("//a[text()='Add file trigger']");// add a SimpleTrigger
		this.typeString("idJobConductorFileTriggerLabelInput", fileTriggerLabel);
		this.typeString("idJobConductorFileTriggerCheckTimeIntervalInput",
				seconds);
		this.typeString("idJobConductorFileTriggerFolderPathInput", filePath);
		this.typeString("idJobConductorFileTriggerFileMaskInput", fileMarks);
		inputCheckBoxChecked(checkboxid);
		this.selectDropDownList("idJobConductorFileTriggerFileServerListBox",
				serverName);
		selenium.click("idFileTriggerSave");
		this.waitForElementPresent("//span[text()='" + fileTriggerLabel + "']",
				WAIT_TIME);
//		this.waitForElementPresent("//span[text()='Running...']",
//				Base.WAIT_TIME);
//		this.waitForElementPresent("//span[text()='Ended...']", Base.WAIT_TIME);
//		selenium.mouseDown("//span[text()='" + fileTriggerLabel + "']");
//		selenium.chooseOkOnNextConfirmation();
//		Thread.sleep(2000);
//		selenium.click("idTriggerDelete");
//		selenium.getConfirmation();	
//		Thread.sleep(3000);
//		Assert.assertFalse(
//				selenium.isElementPresent("//span[text()='" + fileTriggerLabel
//						+ "']"), "trigger delete failed!");

	}

	public void triggerCreateCheck(String fileTriggerLabel) {
		this.waitForElementPresent("//span[text()='Running...']",
				Base.WAIT_TIME);
		this.waitForElementPresent("//span[text()='Ready to run']", Base.WAIT_TIME);
		selenium.mouseDown("//span[text()='" + fileTriggerLabel + "']");
		selenium.chooseOkOnNextConfirmation();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("idTriggerDelete");
		selenium.getConfirmation();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(
				selenium.isElementPresent("//span[text()='" + fileTriggerLabel
						+ "']"), "trigger delete failed!");
	}
	public void triggerExistCheck(String fileTriggerLabel) {
		this.waitForElementPresent("//span[text()='Running...']",
				Base.WAIT_TIME);
		this.waitForElementPresent("//span[text()='Ended...']", Base.WAIT_TIME);
		selenium.mouseDown("//span[text()='" + fileTriggerLabel + "']");
		selenium.chooseOkOnNextConfirmation();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("idTriggerDelete");
		selenium.getConfirmation();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(
				selenium.isElementPresent("//span[text()='" + fileTriggerLabel
						+ "']"), "trigger delete failed!");
	}

	public void inputCheckBoxChecked(String xpathinputBox) {
		if (!selenium.getValue(xpathinputBox).equals("on")) {
			selenium.click(xpathinputBox);
		}
	}

	public void inputCheckBoxUncheck(String xpathinputBox) {
		if (selenium.getValue(xpathinputBox).equals("on")) {
			selenium.click(xpathinputBox);
		}
	}



	class FileTrigger {
		public boolean createNewFile(String path) {
			boolean createSuccess = false;
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
			String s = df.format(date);// system date
			String filename = s.replaceAll(":", "-").replaceAll(" ", "-");
			filename = filename+".txt";
			 File newFile = new File(path,filename);
			if((new File(path)).isDirectory()){
			    if(newFile.exists()){
			    	System.out.println("file already exist!");
			    }
			    else{
			    	try {
						newFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			    }
			    }
				else{
					System.out.println("the path is not a directory!");
				}
            if(newFile.exists()){
            	createSuccess = true;
            }
          
			return createSuccess;

		}

		public boolean modifyFile(String path) {
			boolean modifySuccess = false;

			return modifySuccess;
		}
	}
	
}
