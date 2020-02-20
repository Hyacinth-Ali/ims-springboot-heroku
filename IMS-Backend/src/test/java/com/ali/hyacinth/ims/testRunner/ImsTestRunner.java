package com.ali.hyacinth.ims.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions( 
		features = "src/main/resources/com/ali/hyacinth/ims/features",
		plugin = { "pretty"})
public class ImsTestRunner {
	

}
