package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage 
{
    
public WebDriver driver;

public AddCustomerPage(WebDriver driver)
{
	
this.driver=driver;
PageFactory.initElements(driver, this);

}//AddCustomerPage

By lnkCustomers_menu=By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
By lnkCustomers_menuitem=By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");

By btnAddnew=By.xpath("//a[@class='btn bg-blue']"); //Add new

By txtEmail=By.xpath("//input[@id='Email']");
By txtPassword=By.xpath("//input[@id='Password']");

By txtcustomerRoles=By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");


By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
By rdMaleGender=By.id("Gender_Male");
By rdFeMaleGender=By.id("Gender_Female");

By txtFirstName=By.xpath("//input[@id='FirstName']");
By txtLastName=By.xpath("//input[@id='LastName']");
By txtDob=By.xpath("//input[@id='DateOfBirth']");

By txtCompanyName=By.xpath("//input[@id='Company']");
By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");

By btnSave=By.xpath("//button[@name='save']");

//Actions Methods


public String getPageTitle()
{
	
return driver.getTitle();
        
}//getPageTitle

public void clickOnCustomersMenu()
{
	
driver.findElement(lnkCustomers_menu).click();

}//clickOnCustomersMenu


public void clickOnCustomersMenuItem()
{
	
driver.findElement(lnkCustomers_menuitem).click();
        
}//clickOnCustomersMenuItem


public void clickOnAddnew() 
{
	
driver.findElement(btnAddnew).click();
    
}//clickOnAddnew

   
public void setEmail(String email)
{
        
driver.findElement(txtEmail).sendKeys(email);
	
}//setEmail


public void setPassword(String password)
{
        
driver.findElement(txtPassword).sendKeys(password);

}//setPassword


public void setCustomerRoles(String role) throws InterruptedException
{
	
if(!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
{
            
driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
	
}//if

driver.findElement(txtcustomerRoles).click();
WebElement listitem;

Thread.sleep(3000);

if(role.equals("Administrators"))
{
	
listitem=driver.findElement(lstitemAdministrators);

}//if
        
else if(role.equals("Guests"))
{
	
listitem=driver.findElement(lstitemGuests);
            
}//else if
       
else if(role.equals("Registered"))
{
listitem=driver.findElement(lstitemRegistered);
            
}//else if

else if(role.equals("Vendors"))
{
           
listitem=driver.findElement(lstitemVendors);
            
 }//else if

else
{
          
listitem=driver.findElement(lstitemGuests);

}//else

       

JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", listitem);

}//setCustomerRoles

public void setManagerOfVendor(String value)
{
	
Select drp=new Select(driver.findElement(drpmgrOfVendor));
drp.selectByVisibleText(value);

}//setManagerOfVendor

public void setGender(String gender)
{
	
if(gender.equals("Male"))
{
         
driver.findElement(rdMaleGender).click();
	
}//if

else if(gender.equals("Female"))
{
	
driver.findElement(rdFeMaleGender).click();
            
}//else if

else
{
	
driver.findElement(rdMaleGender).click();//Default
            
}//else

}//setGender

    
public void setFirstName(String fname)
{
	
driver.findElement(txtFirstName).sendKeys(fname);
        
}//setFirstName

    
public void setLastName(String lname)
{
	
driver.findElement(txtLastName).sendKeys(lname);

}//setLastName


public void setDob(String dob)
{
	
driver.findElement(txtDob).sendKeys(dob);
        
}//setDob

    
public void setCompanyName(String comname)
{
	
driver.findElement(txtCompanyName).sendKeys(comname);
    
   
}//setCompanyName
 
    
public void setAdminContent(String content)
{
	
driver.findElement(txtAdminContent).sendKeys(content);

}//setAdminContent

 public void clickOnSave()
{
	 
 driver.findElement(btnSave).click();
 
}//clickOnSave
 
}//AddCustomerPage

