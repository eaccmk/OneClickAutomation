/**
 * @author: https://github.com/eaccmk
 * */

package com.test.Stepdefinitions;

import com.test.Functions.AppFunctions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class CalculateSteps {

    private AppFunctions appFunctions = new AppFunctions();


    @Given("User delete's the old values")
    public void user_deletes_the_old_values() {
        System.out.println("I am Inside addition()");
        appFunctions.del();
    }

    @Given("^User adds \"([^\"]*)\" with \"([^\"]*)\"$")
    public void userAddsWith(String arg0, String arg1) throws Throwable {
        appFunctions.add(arg0, arg1);
        throw new PendingException();
    }
}
