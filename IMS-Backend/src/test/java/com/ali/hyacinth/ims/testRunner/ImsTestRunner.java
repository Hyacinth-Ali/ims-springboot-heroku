package com.ali.hyacinth.ims.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions( 
		features = "src/main/resources/com/ali/hyacinth/ims/features",
		glue = {"com/ali/hyacinth/ims/stepdefinitions"},
		plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty", 
				"json:target/cucumber-reports/CucumberTestReport.json",
				"rerun:target/cucumber-reports/rerun.txt"})
public class ImsTestRunner {
	

}
