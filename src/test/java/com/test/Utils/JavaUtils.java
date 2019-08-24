/**
 * @author: https://github.com/eaccmk
 * */

package com.test.Utils;


public class JavaUtils {

    public void printThis(String stringToBePrinted){
        System.out.println("\n"+stringToBePrinted+"\n");
    }

    public void letMeSleepForSeconds(int secondsSleepValue){
        int milliSeconds = secondsSleepValue/100;
        try{
            Thread.sleep(milliSeconds);
        }catch(Exception e){
            printThis("Got Exception while waiting in letMeSleepForSeconds() ");
        }
        System.out.println("\n Sleeping for "+secondsSleepValue+" Seconds .. \n");
    }
}
