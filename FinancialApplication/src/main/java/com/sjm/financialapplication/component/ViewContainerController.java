/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.view.ViewService;
import com.sjm.financialapplication.view.Views;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author sm6668
 */
@Component
public class ViewContainerController implements Initializable {

    @Autowired
    private ViewService vs;

    @Autowired
    private ViewNavigatorController vnc;

    @FXML
    private BorderPane container, placeHolder;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    protected final int getViewSize() {
        return vs.getSize();
    }

    protected final BorderPane getContainer() {
        return container;
    }

    protected final HBox getNavBox() {
        return vnc.getNavBox();
    }

    protected final boolean loadView(Views view) {
        return vs.loadView(view);
    }

    protected final boolean removeView(Views view) {
        container.getChildren().remove(1);//Nodes will not stack
        container.setCenter(placeHolder);
        return vs.removeView(view);
    }

    protected Node getContainerView() {
        return container.getCenter();
    }

    protected final void setView(Views view) {
        container.getChildren().remove(placeHolder);
        container.setCenter((Parent) vs.getView(view));
    }

    protected final void setVisibleViewNavigator(boolean value) {
        vnc.getNavBox().setVisible(value);
    }

    protected final ObservableList<Node> getNavigationButtons() {
        return vnc.getNavBox().getChildren();
    }

    protected final void setNavigationButton(String name, Views view) {
        ObservableList<Node> navButtons = vnc.getNavBox().getChildren();
        if (getViewSize() != navButtons.size()) {
            Button button = new Button(name);
            button.setUserData(view);
            button.setPrefSize(100, 15);//Temporary
            button.setOnAction(e -> nextView((Views) button.getUserData()));
            navButtons.add(button);
        }
    }

    private void nextView(Views view) {
        HBox navBox = vnc.getNavBox();
        ProgressBar bar = vnc.getProgressBar();
        Parent currentView = (Parent) getContainerView();

        bar.setVisible(true);
        navBox.setDisable(true);

        KeyValue kv = new KeyValue(bar.progressProperty(), 1.0);
        KeyValue kv2 = new KeyValue(currentView.opacityProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv, kv2);

        Timeline timeline = new Timeline(kf);
        timeline.setOnFinished(e -> {
            bar.setVisible(false);
            bar.setProgress(0);
            navBox.setDisable(false);
            currentView.setOpacity(1);//Reset state
            fadeIn(view);//Fade in nextView
        });
        timeline.play();
    }

    private void fadeIn(Views view) {
        container.setVisible(false);
        setView(view);
        getContainerView().setOpacity(0);
        container.setVisible(true);

        new Timeline(new KeyFrame(Duration.millis(1000),
                new KeyValue(getContainerView().opacityProperty(), 1.0))).play();

    }

}
