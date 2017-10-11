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
            "([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])+@([0-9a-zA-Z]"
            + "[-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z])");

    private static final Pattern DOB_PATTERN = Pattern.compile(
            "[0-9]{2}/[0-9]{2}/[0-9]{4}");

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
