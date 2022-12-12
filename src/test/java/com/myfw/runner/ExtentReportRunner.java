package com.myfw.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = {"src/test/resources/AppFeatures"},
        glue = {"stepDefinition", "Apphooks"},
        tags = "@dosh",
        plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "html:target/cucumber-htmlreport/cucumber.html"})

public class ExtentReportRunner extends AbstractTestNGCucumberTests {


}
