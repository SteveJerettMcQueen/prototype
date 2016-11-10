/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.util;

/**
 *
 * @author Steve
 */
public class LogicUtils {

    //XOR where boolean "isTrue" is always true
    public static boolean isValidCombo(boolean isTrue, boolean i2, boolean i3) {
        return isTrue && ((!i2 && i3) || (i2 && !i3));
    }

    //AND where both conditions must be true
    public static boolean isValidCombo(boolean isTrue, boolean isTrue2) {
        return (isTrue && isTrue2);
    }

    //Boolean "isTrue" is always true
    public static boolean isValidCombo2(boolean isTrue, boolean i2, boolean i3) {
        return isTrue && (((!i2 && i3) || (i2 && !i3)) || (i2 && i3));
    }

}
