/**
 * @author: https://github.com/eaccmk
 * */

package com.test.Configurations;

import com.test.Utils.JavaUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class DeviceSetUp {

    public static DesiredCapabilities caps = new DesiredCapabilities();
    private JavaUtils javaUtilsObject = new JavaUtils();

    String androidAppApk = ".\\src\\test\\resources\\TestApp\\MyApp.apk";
    String iosAppIpa  = ".\\src\\test\\resources\\TestApp\\MyApp.ipa";

    String device_UDID = System.getProperty("_udid");
    String device_port = System.getProperty("_port");
    String device_wda = System.getProperty("_wda");

    String serverUrl;

    /**
     * Appium Driver is Instantiated here
     */
    public static AppiumDriver<MobileElement> driver;

    //    @Before
    public void setUpDevice(){
        System.setProperty("DeviceName","Android"); //ToDo paramaterize this form CLI or script

        String devicePlatformName = System.getProperty("DeviceName");

        //        http://appium.io/docs/en/writing-running-appium/caps/
        System.out.println("Setting capabalities for Device >>> " + devicePlatformName );

        if (devicePlatformName.equalsIgnoreCase("ANDROID")){
            javaUtilsObject.printThis("We are Running this test for an Android device");
            caps.setCapability("platformName", "android"); //Which mobile OS platform to use
            caps.setCapability("noReset",true);
//                    caps.setCapability("bundleId", "com.qantas.assure.UAT");
            caps.setCapability("appWaitDuration",20000);
            caps.setCapability("skipServerInstallation",true);
            // caps.setCapability("autoLaunch",false);
            caps.setCapability("uiautomator2ServerLaunchTimeout",20000);
            caps.setCapability("uiautomator2ServerInstallTimeout",20000);
            caps.setCapability("platformVersion", "9"); // Mobile OS version
            caps.setCapability("deviceName","test Device"); //The kind of mobile device or emulator to use
            caps.setCapability("automationName","UiAutomator2");
//            caps.setCapability("automationName",""); // Which automation engine to use (OPTIONAL for android)
//            caps.setCapability("bundleId", "com.google.android.calculator");
            caps.setCapability("appPackage", "com.google.android.calculator");
//            caps.setCapability("appPackage", "com.qantas.assure.UAT");
            caps.setCapability("newCommandTimeout", 860);
            caps.setCapability("launchTimeout", 200000);
// This package name of your app (you can get it from apk info app)
            caps.setCapability("appActivity","com.android.calculator2.Calculator"); // This is Launcher activity of your app (you can get it from apk info app)
//            caps.setCapability("appActivity","com.qantas.assure.ui.universal.activity.LauncherActivity"); // This is Launcher activity of your app (you can get it from apk info app)
            // caps.setCapability("udid", device_UDID);
            caps.setCapability(MobileCapabilityType.UDID, device_UDID);
//            caps.setCapability("wdaLocalPort",device_wda);
//            caps.setCapability("app","D:\\DevUser\\gitRepos\\appium_only\\AppiumCucumber\\src\\test\\resources\\TestApp");


            serverUrl = String.format("http://localhost:"+device_port+"/wd/hub",device_wda);
            try{
                driver = new AndroidDriver(new URL(serverUrl),caps);

            } catch (NullPointerException | MalformedURLException e){
                javaUtilsObject.printThis("There is an Exception while starting Appium Driver on Android Device, reason is :");
                javaUtilsObject.printThis(e.getCause().getMessage());
            }

        } else {
            javaUtilsObject.printThis("We are Running this test for an iOS device");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
            caps.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
            caps.setCapability(MobileCapabilityType.APP, iosAppIpa);
        }
    }

    /*
     *   Stop Appium Server Programmatically before each scenario
     */
   /* @After
    public void stopServer() {
        driver.quit();
    }*/
}
