package com.kate.mentoring.java.stepdefs;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        strict = true,
        plugin = {"json:target/cucumber-report.json",
                "html:target/cucumber-report",
        },
        tags = {"@Search", "@Excel", "@Calendar", "@ProjectList", "@FillingTime", "@MonthView"},
        features = {"src/test/resources/features/Panel.feature"})

public class TestRunner {

}
