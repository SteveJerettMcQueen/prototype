/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.util;

/**
 *
 * @author sm6668
 */
public class ThreadUtils {

    public static void process(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

}
