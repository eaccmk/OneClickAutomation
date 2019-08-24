/**
 * @author: https://github.com/eaccmk
 * */

package com.test.Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty","com.test.Runners.Initialization"},
        features = "src/test/resources/features/",
        glue = {"com.test.Stepdefinitions"})

public class RunCukesTest {

/**
 * consider this as a main() method
 * It is Like a `producer` of a movie who never
 * comes in Picture but is the starting point of everything :)
**/

}
