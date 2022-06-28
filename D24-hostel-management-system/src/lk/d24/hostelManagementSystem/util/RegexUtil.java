package lk.d24.hostelManagementSystem.util;

import java.util.regex.Pattern;

/**
 * author  Yasith C Bandara
 * created 6/18/2022 - 9:56 PM
 * project D24-hostel-management-system
 */

public class RegexUtil {
    public static Pattern qty = Pattern.compile("^[0-9]{1,6}$");
    public static Pattern discount = Pattern.compile("^[0-100]$");
    public static Pattern price = Pattern.compile("^[0-9]*(.[0-9]{2})?$");
    public static Pattern name = Pattern.compile("^[A-z ]{3,30}$");
    public static Pattern postalCode = Pattern.compile("^[0-9]{5}$");
}
