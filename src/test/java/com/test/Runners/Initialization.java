/**
 * @author: https://github.com/eaccmk
 * */

package com.test.Runners;

import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestRunStarted;
import cucumber.api.event.TestRunFinished;
import cucumber.api.formatter.Formatter;

import org.springframework.scheduling.annotation.EnableAsync;

import com.test.Configurations.DeviceSetUp;
import com.test.Functions.NonAppFunctions;
import com.test.Utils.FigletHelper;

@EnableAsync
public class Initialization implements Formatter {
    private DeviceSetUp device = new DeviceSetUp();
    private FigletHelper figletHelper = new FigletHelper();
    private NonAppFunctions nonAppFunctions = new NonAppFunctions();

private EventHandler<TestRunStarted> setup = event -> {

    // clear previous run data
    nonAppFunctions.clearFilesBeforeStart(); // cleaning old screen shots before starting the test

    device.setUpDevice();

    figletHelper.figletPrint("ALL SET 2 Go.. !");
};
    

private EventHandler<TestRunFinished> teardown = event -> {

    figletHelper.figletPrint("Finishing this test Run.. !");
    if(DeviceSetUp.driver.toString().contains("null")) {
        System.out.print("All Device Driver's are closed ");
    } else {
        System.out.print("Still the Device Driver's is running..");
        DeviceSetUp.driver.quit();
//        device.driver = null;  //optional to set the instance of this driver = null

    }

};
    
    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class,setup);
        eventPublisher.registerHandlerFor(TestRunFinished.class,teardown);
    }
}
