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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author sm6668
 */
@Component
public class MessagePaneController implements Initializable {

    @FXML
    private BorderPane messagePane;

    @FXML
    private HBox messageBox;

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
        messageBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("rgba(0, 0, 0, 0.1)"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected final BorderPane getMessagePane() {
        return messagePane;
    }

    protected final HBox getMessageBox() {
        return messageBox;
    }

    protected final Label getMessageLabel() {
        return messageLabel;
    }

}
