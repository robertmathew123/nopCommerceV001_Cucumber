package stepDefinitions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import org.apache.commons.io.FileUtils;

public class Steps extends BaseClass
{

public boolean flag;
public Scenario scn;
	
@Before
public void setUp(Scenario s) throws IOException
{
// Load config.properties file
configProp = new Properties();
FileInputStream configfile = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\config.properties");
configProp.load(configfile);
// end of loading gconfig.properties file

this.scn = s;

if ((new File(screenshotdir)).exists())
FileUtils.cleanDirectory(new File(screenshotdir));

}//setUp


@AfterStep
public void AfterStepScreenShot(Scenario scenario) throws InterruptedException, IOException 
{

if(scenario.isFailed())
{
ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getBase64Screenshot());
scenario.log("Screenshot attached");
}//isFailed

}//AfterStepScreenShot

@After
public void CleanUp() 
{

driver.quit();
	
}//CleanUp


@Given("User Launch Chrome browser")
public void user_Launch_Chrome_browser() 
{
if(configProp.getProperty("browser").equals("chrome"))
{
		
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver();

}//chrome

else if(configProp.getProperty("browser").equals("firefox"))
{
		
WebDriverManager.firefoxdriver().setup();
driver = new FirefoxDriver();

}//firefox

else if(configProp.getProperty("browser").equals("ie"))
{
		
WebDriverManager.iedriver().setup();  
driver = new InternetExplorerDriver();

}//ie

logger.info("Launching browser");
lp = new LoginPage(driver);	
scn.log("Launching browser");
		
}//user_Launch_Chrome_browser


@When("User opens URL {string}")
public void user_opens_URL(String url) 
{
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
logger.info("Opening URL: " +url);
scn.log("Opening URL: " +url);
driver.get(url);
driver.manage().window().maximize();

	
}//user_opens_URL

@When("^User enters Email as (.+) and Password as (.+)$")
public void user_enters_email_as_and_password_as(String email, String password)
{
	
logger.info("Proving user info");
scn.log("Proving user info");
lp.setUserName(email);
lp.setPassword(password);
		
}//user_enters_Email_as_and_Password_as

@When("User enter Email {string} and Password {string}")
public void user_enter_email_as_something_and_password_as_something(String email, String password) throws Throwable 
{
	
logger.info("Proving user info");
scn.log("Proving user info");
lp.setUserName(email);
lp.setPassword(password);	
    
}//user_enter_email_as_something_and_password_as_something

	
@When("Click on Login")
public void click_on_Login() throws InterruptedException
{

	
lp.clickLogin();
	
}//click_on_Login

@Then("Page Title should be {string}")
public void page_Title_should_be(String exptitle) throws InterruptedException, IOException
{
	
if(driver.getPageSource().contains("Login was unsuccessful"))
{
	
logger.info("Login Failed");
scn.log("Login Failed");
Assert.assertTrue(false);
flag = false;	
}//if
	
else
{
	
logger.info("Login successfull");
scn.log("Login successfull");
Assert.assertEquals(exptitle, driver.getTitle());
flag = true;		
}//else

Thread.sleep(5000);	

		
}//page_Title_should_be

@When("User click on Log out link")
public void user_click_on_Log_out_link() throws InterruptedException 
{

if(flag==true)
{
logger.info("Logout from application");
scn.log("Logout from application");
lp.clickLogout();
Thread.sleep(3000);
}//flag

}//user_click_on_Log_out_link

@Then("close browser")
public void close_browser() 
{
	
logger.info("Closing application");
scn.log("Closing application");

	
}//close_browser

//Customer feature step definitions..........................................
//Adding Customer

@Then("User can view Dashboad")
public void user_can_view_Dashboad() 
{

logger.info("Adding Customer Scenario started");
addCust=new AddCustomerPage(driver);
logger.info("Dashboard Display validation");
scn.log("Dashboard Display validation");
Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());

}//user_can_view_Dashboad

@When("User click on customers Menu")
public void user_click_on_customers_Menu() throws InterruptedException
{
	
Thread.sleep(3000);
addCust.clickOnCustomersMenu();

}//user_click_on_customers_Menu

@When("click on customers Menu Item")
public void click_on_customers_Menu_Item() throws InterruptedException
{
	
Thread.sleep(2000);
addCust.clickOnCustomersMenuItem();

}//click_on_customers_Menu_Item

@When("click on Add new button")
public void click_on_Add_new_button() 
{
	
addCust.clickOnAddnew();
    
}//click_on_Add_new_button

@Then("User can view Add new customer page")
public void user_can_view_Add_new_customer_page() 
{
	
Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
 
}//user_can_view_Add_new_customer_page

@When("User enter customer info")
public void user_enter_customer_info() throws InterruptedException
{
	
logger.info("Providing Customer details");
scn.log("Providing Customer details");
String email = randomString() + "@gmail.com";
addCust.setEmail(email);
addCust.setPassword("test123");
// Registered - default
// The customer cannot be in both 'Guests' and 'Registered' customer roles
// Add the customer to 'Guests' or 'Registered' customer role
addCust.setCustomerRoles("Guest");
Thread.sleep(3000);

addCust.setManagerOfVendor("Vendor 2");
addCust.setGender("Male");
addCust.setFirstName("Pavan");
addCust.setLastName("Kumar");
addCust.setDob("7/05/1985"); // Format: D/MM/YYY
addCust.setCompanyName("busyQA");
addCust.setAdminContent("This is for testing.........");

}//user_enter_customer_info

@When("click on Save button")
public void click_on_Save_button() 
{
	
addCust.clickOnSave();

}//click_on_Save_button

@Then("User can view confirmation message {string}")
public void user_can_view_confirmation_message(String string) 
{
	
logger.info("Add customer validation");
Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
            .contains("The new customer has been added successfully"));
    
}//user_can_view_confirmation_message

//Searching customers using EMail ID ...................................

@When("Enter customer EMail")
public void enter_customer_EMail() 
{
	
logger.info("Search Customer by Email ID Scenario started");
scn.log("Search Customer by Email ID Scenario started");
searchCust=new SearchCustomerPage(driver);
searchCust.setEmail("victoria_victoria@nopCommerce.com");

}//enter_customer_EMail

@When("Click on search button")
public void click_on_search_button() throws InterruptedException
{
	
searchCust.clickSearch();
Thread.sleep(3000);
    
}//click_on_search_button

@Then("User should found Email in the Search table")
public void user_should_found_Email_in_the_Search_table() 
{
	
logger.info("Search customer by email validation");
scn.log("Search customer by email validation");
boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
Assert.assertEquals(true, status);

}//user_should_found_Email_in_the_Search_table	

//Searching customers using Name ...................................
@When("Enter customer FirstName")
public void enter_customer_FirstName() 
{
	
logger.info("Seqarch custoemr by Name Scenario started");
scn.log("Search custoemr by Name Scenario started");
searchCust=new SearchCustomerPage(driver);
searchCust.setFirstName("Victoria");
    
}//enter_customer_FirstName

@When("Enter customer LastName")
public void enter_customer_LastName() 
{
	
logger.info("Providing customer name");
scn.log("Providing customer name");
searchCust.setLastName("Terces");

}//enter_customer_LastName

@Then("User should found Name in the Search table")
public void user_should_found_Name_in_the_Search_table() 
{
	
logger.info("Search customer by name validation");
scn.log("Search customer by name validation");
boolean status=searchCust.searchCustomerByName("Victoria Terces");
Assert.assertEquals(true, status);

}//user_should_found_Name_in_the_Search_table
	
	
}//Steps
