/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import org.springframework.stereotype.Component;

/**
 *
 * @author Steve
 */
@Component
public class ProgressPaneController implements Initializable {

    @FXML
    private BorderPane progressPane;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Label messageLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("rgba(0, 0, 0, 0.1)"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected final BorderPane getProgressPane() {
        return progressPane;
    }

    protected final ProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    protected final Label getMessageLabel() {
        return messageLabel;
    }
}
