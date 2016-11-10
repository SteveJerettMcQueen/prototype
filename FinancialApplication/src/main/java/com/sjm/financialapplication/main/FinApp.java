/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.main;

import com.sjm.financialapplication.view.Views;
import com.sjm.financialapplication.view.ViewService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javafx.scene.Parent;

/**
 *
 * @author sm6668
 */
public class FinApp extends Application {

    private ApplicationContext context = null;
    private ViewService viewService = null;

    @Override
    public void init() throws Exception {
        context = new ClassPathXmlApplicationContext("spring.cfg.xml");
        viewService = (ViewService) context.getBean("viewService");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        goToLogin(primaryStage);
    }

    private void goToLogin(Stage stage) {
        viewService.loadView(Views.VIEW1);
        viewService.loadView(Views.CONTAINER);
        Scene scene = new Scene((Parent) viewService.getView(Views.VIEW1));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

}
