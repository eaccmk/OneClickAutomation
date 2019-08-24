package com.test.Runners;

//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.test.Configurations.SetUpDeviceAndCapabilities;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "html:target/cucumberHtmlReport","Runner.Initialization"},
        features = "src/test/resources/features/",
        glue = {"com.test.Stepdefinitions"})

public class RunCukesTest {
    SetUpDeviceAndCapabilities s = new SetUpDeviceAndCapabilities();

    @BeforeClass
    private void setUpDeviceForRun() {
        s.setUpDevice();
    }


}
