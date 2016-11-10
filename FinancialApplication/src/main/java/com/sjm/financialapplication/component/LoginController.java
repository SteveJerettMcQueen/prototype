/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.concurrency.AppExecutorService;
import com.sjm.financialapplication.concurrency.AccountownerWorkers.AccountownerLoginService;
import com.sjm.financialapplication.view.Views;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sjm.financialapplication.concurrency.ViewWorkers.AccountownerViewer;

/**
 *
 * @author sm6668
 */
@Component
public class LoginController implements Initializable {

    @Autowired
    private AppExecutorService aes;

    @Autowired
    private AccountownerLoginService aols;

    @Autowired
    private AccountownerViewer aov;

    @Autowired
    private ViewContainerController vcc;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField userIdTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private Button loginBtn;

    @FXML
    private Label messageLabel;

    @FXML
    private ProgressIndicator progressIndicator;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aols.setExecutor(aes.getExecutorServicePool());
        aols.setOnSucceeded(e -> aolsSucceeded());

        aov.setExecutor(aes.getExecutorServicePool());
        aov.setOnSucceeded(e -> aovSucceeded());

        loginBtn.disableProperty().bind(aols.runningProperty());
        userIdTF.disableProperty().bind(aols.runningProperty());
        passwordTF.disableProperty().bind(aols.runningProperty());

        progressIndicator.progressProperty().bind(aols.progressProperty());
        progressIndicator.visibleProperty().bind(aols.runningProperty());

        messageLabel.visibleProperty().bind(aols.runningProperty());
        messageLabel.textProperty().bind(aols.messageProperty());
    }

    @FXML
    public void login(ActionEvent event) {
        if (!userIdTF.getText().isEmpty() && !passwordTF.getText().isEmpty()) {
            aols.setUserId(userIdTF.getText());
            aols.setPassword(passwordTF.getText());
            aols.start();
        } else {

            //Invalid Combo
        }
    }

    private void aolsSucceeded() {
        if (aols.getValue()) {
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.close();
            messageLabel.setAlignment(Pos.CENTER);
            messageLabel.setMaxHeight(1.7976931348623157E308);
            messageLabel.setMaxWidth(1.7976931348623157E308);
            progressIndicator.setMaxSize(150, 150);
            borderPane.getChildren().remove(anchorPane);
            borderPane.setPrefSize(400, 500);
            borderPane.setTop(messageLabel);
            borderPane.setCenter(progressIndicator);
            stage.centerOnScreen();
            stage.show();
            initAccountownerView(userIdTF.getText());
        } else {
            aols.reset();
        }
    }

    private void initAccountownerView(String userId) {
        progressIndicator.progressProperty().unbind();
        progressIndicator.visibleProperty().unbind();
        messageLabel.visibleProperty().unbind();
        messageLabel.textProperty().unbind();

        progressIndicator.progressProperty().bind(aov.progressProperty());
        progressIndicator.visibleProperty().bind(aov.runningProperty());
        messageLabel.visibleProperty().bind(aov.runningProperty());
        messageLabel.textProperty().bind(aov.messageProperty());

        aov.setUserId(userId);
        aov.start();
    }

    private void aovSucceeded() {
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
        aov.reset();
        borderPane.getChildren().remove(progressIndicator);
        borderPane.getChildren().remove(messageLabel);
        borderPane.setCenter(buildViewContainer());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight() - 50);
        stage.setResizable(true);
        stage.show();
    }

    private BorderPane buildViewContainer() {
        vcc.loadView(Views.VIEW2);
        vcc.loadView(Views.VIEW3);
        vcc.loadView(Views.VIEW4);
        vcc.loadView(Views.VIEW7);
        vcc.setNavigationButton("Home", Views.VIEW2);
        vcc.setNavigationButton("Profile", Views.VIEW3);
        vcc.setNavigationButton("Finances", Views.VIEW4);
        vcc.setNavigationButton("Account", Views.VIEW7);
        vcc.setView(Views.VIEW2);
        return vcc.getContainer();
    }

}
