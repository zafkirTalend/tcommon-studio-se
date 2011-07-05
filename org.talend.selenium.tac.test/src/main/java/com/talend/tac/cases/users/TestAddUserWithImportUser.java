package com.talend.tac.cases.users;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddUserWithImportUser extends Login{
    
	//creation method for add user with import user
	public void addUserWithImportUser(String filePath, String infoAfterUpload) {
		
		this.clickWaitForElementPresent("idMenuUserElement");
		Assert.assertTrue(selenium.isTextPresent("admin@company.com"));
		
		selenium.click("//button[text()='Import users']");//click import user button
		
		this.waitForElementPresent("//input[@name='Path']", WAIT_TIME);
		selenium.type("//input[@name='Path']", filePath);//select import file
		selenium.click("idLicenseUploadButton");//click upload user
		selenium.setSpeed(MID_SPEED);
		Assert.assertTrue(selenium.isTextPresent(infoAfterUpload));
		selenium.setSpeed(MIN_SPEED);
		selenium.click("//button[text()='OK']");//click OK
		selenium.click("//button[text()='Close']");
		
	}
	
	//test import user with a '.cvs' file and check info
	@Test(groups={"importUser"})
	@Parameters({"filePathOfCvsFile","addUserOfCvsFileInfo"})
	public void testAddUserImportUserWithCVSFile(String filePath,String info) {
		
		addUserWithImportUser(filePath, info);
		
		Assert.assertTrue(selenium.isElementPresent("//div[text()='userWithCvsFile@talend.com']"));
		
	}	
	
	//test import user with a '.cvs' file, use a exist label and check info
	@Test(dependsOnMethods={"testAddUserImportUserWithCVSFile"})
	@Parameters({"filePathOfCvsFile","addUserUseExistLabelOfCvsFileInfo"})
	public void testAddUserImportUserWithUseExistLabelOfCVSFile(String filePath,String info) {
		
		addUserWithImportUser(filePath, info);
		
	}
	
	//test import user with a '.xml' file and check info
	@Test(dependsOnMethods={"testAddUserImportUserWithCVSFile"})
	@Parameters({"filePathOfXMLFile","addUserOfXmlFileInfo"})
	public void testAddUserImportUserWithXMLFile(String filePath,String info) {
		
		addUserWithImportUser(filePath, info);

		Assert.assertTrue(selenium.isElementPresent("//div[text()='userWithXMLFile@talend.com']"));
		
	}	
	
	//test import user with a '.txt' file and check info
	@Test(dependsOnMethods={"testAddUserImportUserWithCVSFile"})
	@Parameters({"filePathOfTXTFile","addUserOfTXTFileInfo"})
	public void testAddUserImportUserWithTxtFile(String filePath,String info) {
		
		addUserWithImportUser(filePath, info);

		Assert.assertTrue(selenium.isElementPresent("//div[text()='userWithTXTFile@talend.com']"));
		
	}
	 
	
	//test import user with a '.doc' file and check warn info
	@Test(dependsOnMethods={"testAddUserImportUserWithCVSFile"})
	@Parameters({"filePathOfDOCFile","addUserOfDOCFileInfo"})
	public void testAddUserImportUserWithDOCFile(String filePath,String info) {
		
		addUserWithImportUser(filePath, info);
		
	}
	
	//test import user with a wrong label file and check warn info
	@Test(dependsOnMethods={"testAddUserImportUserWithCVSFile"})
	@Parameters({"filePathOfWrongLabelValueFile","addUserOfWrongLabelValueFileInfo"})
	public void testAddUserImportUserWithWrongLabelValueFile(String filePath,String info) {
		
		addUserWithImportUser(filePath, info);
		
	}	
	
	//test import user with file of type is not exist  and check warn info
	@Test(dependsOnMethods={"testAddUserImportUserWithCVSFile"})
	@Parameters({"filePathOfSetTypeIsNotExistFile","addUserOfSetTypeIsNotExistFileInfo"})
	public void testAddUserImportUserWithSetTypeIsNotExistFile(String filePath,String info) {
		
		addUserWithImportUser(filePath, info);
		
	}
	
}
