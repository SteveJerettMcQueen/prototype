/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Steve
 */
@Component
public class TargetProgressController implements Initializable {

    @FXML
    private ProgressBar targProgBar;

    @FXML
    private HBox targProgBox;

    @FXML
    private Label targPercLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void showProgress(MouseEvent event) {
        targProgBox.setVisible(true);
        double perc = targProgBar.getProgress() * 100;

        if (perc != 100) {
            String percStrg = new DecimalFormat("#.##").format(perc);
            targPercLabel.setText(percStrg);
        } else {
            //Set to check mark as completed!
            System.out.println("TargetReached!");
        }

        event.consume();
    }

    @FXML
    private void hideProgress(MouseEvent event) {
        targProgBox.setVisible(false);
        event.consume();
    }

    protected ProgressBar getTargetProgressBar() {
        return targProgBar;
    }

    protected HBox getTargetProgressBox() {
        return targProgBox;
    }

    protected Label getTargetPercentLabel() {
        return targPercLabel;
    }
}
