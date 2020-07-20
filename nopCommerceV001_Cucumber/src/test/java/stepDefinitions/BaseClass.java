package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;


public class BaseClass 
{

public static WebDriver driver;
public LoginPage lp;
public AddCustomerPage addCust;
public SearchCustomerPage searchCust;
  
public Logger logger = LogManager.getLogger(this.getClass()); 
Properties configProp;
public static String screenshotdir = System.getProperty("user.dir") + "/test-output/screenshots/";

public String randomString()
{

String generatedstring = RandomStringUtils.randomAlphabetic(8);
return generatedstring;
	
}//randomString

public String randomNumber()
{
	
String generatednumber = RandomStringUtils.randomNumeric(4);
return generatednumber;

}//randomNumber

public static String getBase64Screenshot() throws IOException 
{
String Base64StringOfScreenshot="";
File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");
String sDate = sdf.format(date);
FileUtils.copyFile(src, new File(screenshotdir + "image_" + sDate + ".png"));
		    
byte[] fileContent = FileUtils.readFileToByteArray(src);
Base64StringOfScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
return Base64StringOfScreenshot;

}//getBase64Screenshot
    

}//BaseClass
