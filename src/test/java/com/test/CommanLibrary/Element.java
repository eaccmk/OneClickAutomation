package com.test.CommanLibrary;

import com.test.Configurations.DeviceSetUp;
import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;

public class Element {

    public void tapItem(By locator){
        MobileElement element = DeviceSetUp.driver.findElement(locator);
        element.click();
    }

    public boolean isElementDisplayed(By locator){
        MobileElement element = DeviceSetUp.driver.findElement(locator);
        return element.isDisplayed();
    }

    //TODO wait method
}
