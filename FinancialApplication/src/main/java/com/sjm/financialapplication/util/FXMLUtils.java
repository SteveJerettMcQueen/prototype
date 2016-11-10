/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.util;

import com.sjm.financialapplication.view.ViewImpl;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Steve
 */
public class FXMLUtils {

    public static Object loadFXML(String resource) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    FXMLUtils.class.getClassLoader().
                    getResource(resource));

            return loader.load();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}
