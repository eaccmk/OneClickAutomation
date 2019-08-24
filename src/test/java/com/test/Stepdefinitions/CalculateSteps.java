package com.test.Stepdefinitions;

import com.test.AppFunctions.Functionality;
import  cucumber.api.java.en.Given;

public class SearchSteps {

    Functionality  functionalityObj = new Functionality();


    @Given("^User is on Search Screen$")
    public void addition() {
        System.out.println("I am Inside feature file");
//        functionalityObj.addition();
    }

}
