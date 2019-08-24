package com.test.CommanLibrary;

import com.test.Configurations.DeviceSetUp;
import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Element {

    public void tapItem(By locator){
        MobileElement element = DeviceSetUp.driver.findElement(locator);
        element.click();
    }

    public boolean isElementDisplayed(By locator){
        MobileElement element = DeviceSetUp.driver.findElement(locator);
        try{
            if(element.isDisplayed())
                return element.isDisplayed();
            }catch (ElementNotVisibleException ex) {
                 return false;
            }
        return false;
    }

    public void waitAndClickElement(By locator){
        WebDriverWait wait = new WebDriverWait(DeviceSetUp.driver, 15); //wait set for 15 seconds for this call {Explicit wait}
        MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void enterText(By locator,String textValue){
        WebDriverWait wait = new WebDriverWait(DeviceSetUp.driver, 15); //wait set for 15 seconds for this call {Explicit wait}
        MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.sendKeys(textValue);
    }
}
