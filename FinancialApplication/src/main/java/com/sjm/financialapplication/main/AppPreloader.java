/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.main;

import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author Steve
 */
public class AppPreloader extends Preloader {

    private final Label message;
    private final ProgressIndicator progressIndicator;
    private Stage preloaderStage;

    public AppPreloader() {
        message = new Label();
        progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxSize(100, 100);
    }

    private Scene createPreloaderScene() {
        BorderPane p = new BorderPane();
        message.setText("Loading Application Resources");
        p.setTop(message);
        p.setCenter(progressIndicator);
        return new Scene(p, 400, 400);
    }

    @Override
    public void start(Stage stage) throws Exception {
        preloaderStage = stage;
        preloaderStage.setResizable(false);
        preloaderStage.initStyle(StageStyle.TRANSPARENT);
        preloaderStage.setScene(createPreloaderScene());
        preloaderStage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        //TODO
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (null != evt.getType()) {
            switch (evt.getType()) {
                case BEFORE_LOAD:
                    break;
                case BEFORE_INIT:
                    break;
                case BEFORE_START:
                    preloaderStage.close();
                    break;
                default:
                    break;
            }
        }
    }
}
