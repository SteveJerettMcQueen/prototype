/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.main;

import com.sun.javafx.application.LauncherImpl;

/**
 *
 * @author Steve
 */
public class AppMain {

    public static void main(String[] args) {
        LauncherImpl.launchApplication(
                FinApp.class, AppPreloader.class, args);
    }
}
