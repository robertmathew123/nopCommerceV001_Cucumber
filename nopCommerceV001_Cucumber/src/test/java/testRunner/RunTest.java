package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(
		
features=".//Features",	
glue="stepDefinitions",
dryRun = false,
monochrome = true,
tags = ("@sanity"),
plugin={"pretty",
	   "html:target/cucumber/Report.html",
	   "json:target/json/file.json",
	   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		


)

public class RunTest 
{
	
	
	
	

}//TestRun
