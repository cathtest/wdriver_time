package com.kate.mentoring.java.stepdefs;


import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
        strict = true,
        plugin = {"json:target/cucumber-report.json",
                "html:target/cucumber-report",
        },
        tags={"@Search"},
        features = {"src/test/resources/features/Panel.feature"})

public class TestRunner {

}
