/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Adam
 */
public class RegexHelper {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
                    + "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b"
                    + "\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\""
                    + ")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:"
                    + "[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01"
                    + "0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]"
                    + "?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1"
                    + "f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e"
                    + "-\\x7f])+)\\])");

    /*
    private static final Pattern DOB_PATTERN = Pattern.compile(
            "[0-9]{2}/[0-9]{2}/[0-9]{4}");
*/
    
    private static final Pattern DOB_PATTERN = Pattern.compile(
            "[0-3]{1}[0-9]{1}\\/[0-1]{1}[0-9]{1}\\/[0-9]{4}");
    
    private static final Pattern NAME_PATTERN = Pattern.compile(
            "(([A-Z][a-z]+)\\s*)*([A-Z][a-z]*)");

    public static boolean TestName(String name) {

        Matcher m = NAME_PATTERN.matcher(name);
        return m.matches();
    }

    public static boolean TestEmail(String name) {
        Matcher m = EMAIL_PATTERN.matcher(name);
        return m.matches();
    }

    public static boolean TestDOB(String name) {
        Matcher m = DOB_PATTERN.matcher(name);
        return m.matches();
    }

}
