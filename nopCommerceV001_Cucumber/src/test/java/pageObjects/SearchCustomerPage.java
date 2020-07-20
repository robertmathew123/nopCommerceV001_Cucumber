package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage 
{
	
public WebDriver driver;
WaitHelper waithelper;

public SearchCustomerPage(WebDriver rdriver) 
{
		
this.driver = rdriver;
PageFactory.initElements(rdriver, this);
waithelper = new WaitHelper(driver);

}//SearchCustomerPage	

@FindBy(how = How.ID, using = "SearchEmail")
@CacheLookup
WebElement txtEmail;

@FindBy(how = How.ID, using = "SearchFirstName")
@CacheLookup
WebElement txtFirstName;

@FindBy(how = How.ID, using = "SearchLastName")
@CacheLookup
WebElement txtLastName;

@FindBy(how = How.ID, using = "search-customers")
@CacheLookup
WebElement btnSearch;

@FindBy(how = How.XPATH, using = "//table[@role='grid']")
@CacheLookup
WebElement tblSearchResults;

@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
WebElement table;

@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
List<WebElement> tableRows;

@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
List<WebElement> tableColumns;

public void setEmail(String email) 
{

waithelper.WaitForElement(txtEmail, 30);
txtEmail.clear();
txtEmail.sendKeys(email);
    
}//setEmail

public void setFirstName(String fname) 
{

waithelper.WaitForElement(txtFirstName, 30);
txtFirstName.clear();
txtFirstName.sendKeys(fname);
    
}//setFirstName

public void setLastName(String lname) 
{

waithelper.WaitForElement(txtLastName, 30);
txtLastName.clear();
txtLastName.sendKeys(lname);

}//setLastName

public void clickSearch() 
{
	
btnSearch.click();
waithelper.WaitForElement(btnSearch, 30);

}//clickSearch

public int getNoOfRows() 
{
	
return (tableRows.size());

}//getNoOfRows

public int getNoOfColumns() 
{
	
return (tableColumns.size());
    
}//getNoOfColumns

public boolean searchCustomerByEmail(String email) 
{
boolean flag = false;

for (int i = 1; i <= getNoOfRows(); i++) 
{
        
String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]")).getText();

System.out.println(emailid);

if (emailid.equals(email)) 
{
	
flag = true;
break;

}//if

}//for

return flag;

}//searchCustomerByEmail


public boolean searchCustomerByName(String Name) 
{
	
boolean flag = false;

for (int i = 1; i <= getNoOfRows(); i++) 
{
	
String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]")).getText();


if (Name.equals(name)) 
{
	
flag = true;
break;

}//if

}//for

return flag;

}//searchCustomerByName
	

}//SearchCustomerPage
