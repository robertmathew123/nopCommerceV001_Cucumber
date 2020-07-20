Feature: Customers

Background: backround steps
Given User Launch Chrome browser
When User opens URL "http://admin-demo.nopcommerce.com/login"
And  User enter Email "admin@yourstore.com" and Password "admin"
And Click on Login
Then User can view Dashboad


@sanity
Scenario: Add new Customer
When User click on customers Menu
And click on customers Menu Item
And click on Add new button
Then User can view Add new customer page
When User enter customer info
And click on Save button
Then User can view confirmation message "The new customer has been added successfully."
And close browser

@sanity
Scenario: Search Customer by EMailID
When User click on customers Menu
And click on customers Menu Item
And Enter customer EMail
When Click on search button
Then User should found Email in the Search table
And close browser

@sanity
Scenario: Search Customer by Name
When User click on customers Menu
And click on customers Menu Item
And Enter customer FirstName
And Enter customer LastName
When Click on search button
Then User should found Name in the Search table
And close browser