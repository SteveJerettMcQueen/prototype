/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author sm6668
 */
@Component
public class ViewNavigatorController implements Initializable {

    @FXML
    private HBox navBox;

    @FXML
    private ProgressBar progressBar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    protected final HBox getNavBox() {
        return navBox;
    }

    protected final ProgressBar getProgressBar() {
        return progressBar;
    }

}
