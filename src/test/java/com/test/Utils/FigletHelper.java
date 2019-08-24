/**
 * @author: https://github.com/eaccmk
 * */

package com.test.Utils;

import com.github.lalyos.jfiglet.FigletFont;

public class FigletHelper {
    public void figletPrint(String text) {
        String asciiArt = null;
        try {
            asciiArt = FigletFont.convertOneLine(text);
        } catch (Exception e) {
            e.getCause().getMessage();
        }
    }

}
