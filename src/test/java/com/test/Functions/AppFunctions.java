package com.test.AppFunctions;

import com.test.AppObjects.ObjectRepository;
import com.test.CommanLibrary.Element;
import com.test.Utils.JavaUtils;

public class AppFunctions {

    private ObjectRepository objectRepo = new ObjectRepository();
    private Element action = new Element();
    private JavaUtils javaUtils = new JavaUtils();

    public void add() {

        if (action.isElementDisplayed(objectRepo.delete)) {
            System.out.println("Yes the element is Dsiplayed ");
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
                    System.out.println("Yes the element is Dsiplayed after 5 seconds ");
                }
            }

            action.tapItem(objectRepo.one);
            action.tapItem(objectRepo.delete);
            action.tapItem(objectRepo.one);
            action.tapItem(objectRepo.delete);


        }

    }