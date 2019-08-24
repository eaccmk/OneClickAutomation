/**
 * @author: https://github.com/eaccmk
 * */

package com.test.AppObjects;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class ObjectRepository {

    //Android native Calculator App Objects
    public By delete = new MobileBy.ByAccessibilityId("Delete");
    public By one = By.id("com.google.android.calculator:id/digit_1");

}
