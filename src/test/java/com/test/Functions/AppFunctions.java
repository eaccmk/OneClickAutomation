/**
 * @author: https://github.com/eaccmk
 * */

package com.test.Functions;

import com.test.AppObjects.ObjectRepository;
import com.test.CommanLibrary.Element;
import com.test.Functions.NonAppFunctions;
import com.test.Utils.JavaUtils;

public class AppFunctions {

    private ObjectRepository objectRepo = new ObjectRepository();
    private Element action = new Element();
    private JavaUtils javaUtils = new JavaUtils();
    private NonAppFunctions nonAppFunctions = new NonAppFunctions();

    public void add(String val_1, String val_2) {
        nonAppFunctions.captureScreen();
        if (action.isElementDisplayed(objectRepo.delete)) {
            System.out.println("This means app is launched and we can start testing ");
            nonAppFunctions.captureScreen();
            try {
                action.tapItem(objectRepo.delete);
                action.tapItem(objectRepo.one);
                action.tapItem(objectRepo.one);
                action.tapItem(objectRepo.one);
                action.tapItem(objectRepo.delete);
            } catch (NullPointerException e) {
                e.getMessage();
            }
            }
        else{
                javaUtils.letMeSleepForSeconds(5);
                if (action.isElementDisplayed(objectRepo.delete)) {
                    nonAppFunctions.captureScreen();
                    System.out.println("Yes the element is Dsiplayed after 5 seconds ");
                }
            }

            action.tapItem(objectRepo.one);
            action.tapItem(objectRepo.delete);
            action.tapItem(objectRepo.one);
            action.tapItem(objectRepo.delete);


        }
    public void del(){
        nonAppFunctions.captureScreen();
        action.tapItem(objectRepo.delete);
    }

    }