/**
 * @author: https://github.com/eaccmk
 * */

package com.test.Functions;

import com.test.Configurations.DeviceSetUp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NonAppFunctions {

    int screenCount = 0 ;

    public void clearFilesBeforeStart(){
        System.out.println("Removing Screen Shots of Last test");
        File file = new File("ScreenShotsDir");
        String[] allFiles;
        if(file.isDirectory()) {
            allFiles = file.list();
            for(int i = 0; i <allFiles.length; i ++){
                File someFile = new File(file,allFiles[i]);
                someFile.delete();
            }
            System.out.println("Removed old Test's Screen Shots");
        }
    }

    public void captureScreen(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String timeStamp = dateFormat.format(date); // 2029/12/30 00:00:59
        System.out.println("Saving screenshot at "+timeStamp+"\n");

        screenCount++;
        String folderName = "ScreenShotsDir";
        new File(folderName).mkdir();
        try{
            File screen = ((TakesScreenshot) DeviceSetUp.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screen, new File(folderName,"Screen" + screenCount + ".png"));

        } catch (Exception e){
            System.out.println("Caught Exception while taking screen shot -- \n\n\n\n"+e.getCause().getMessage()+"\n");
        }

    }

}
