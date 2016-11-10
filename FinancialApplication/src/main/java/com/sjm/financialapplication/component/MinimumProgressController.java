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
import javafx.scene.shape.Circle;
import org.springframework.stereotype.Component;

/**
 *
 * @author sm6668
 */
@Component
public class MinimumProgressController implements Initializable {

    @FXML
    private ProgressBar leftMinProgBar, rightMinProgBar;

    @FXML
    private HBox leftMinProgBox, rightMinProgBox;

    @FXML
    private Label leftPercLabel, rightPercLabel;

    @FXML
    private Circle circIndicator;

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
    private void showLeftMinProgress(MouseEvent event) {
        leftMinProgBox.setVisible(true);
        double perc = leftMinProgBar.getProgress() * 100;
        setPercentage(leftPercLabel, perc);
        event.consume();
    }

    @FXML
    private void hideLeftMinProgress(MouseEvent event) {
        leftMinProgBox.setVisible(false);
        event.consume();
    }

    @FXML
    private void showRightMinProgress(MouseEvent event) {
        rightMinProgBox.setVisible(true);
        double perc = rightMinProgBar.getProgress() * 100;
        setPercentage(rightPercLabel, perc);
        event.consume();
    }

    @FXML
    private void hideRightMinProgress(MouseEvent event) {
        rightMinProgBox.setVisible(false);
        event.consume();
    }

    private void setPercentage(Label label, double percentage) {
        if (percentage != 100) {
            String percStrg = new DecimalFormat("#.##").format(percentage);
            label.setText(percStrg);
        } else {
            //Set circle indicator to some value
        }

    }

    protected ProgressBar getLeftMinProgressBar() {
        return leftMinProgBar;
    }

    protected ProgressBar getRightMinProgressBar() {
        return rightMinProgBar;
    }

    protected HBox getLeftMinProgressBox() {
        return leftMinProgBox;
    }

    protected HBox getRightMinProgressBox() {
        return rightMinProgBox;
    }

    protected Circle getCircleIndicator() {
        return circIndicator;
    }

}
