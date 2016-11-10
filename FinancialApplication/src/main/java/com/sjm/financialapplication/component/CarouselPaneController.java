/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.view.ViewService;
import com.sjm.financialapplication.view.Views;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Steve
 */
@Component
public class CarouselPaneController implements Initializable {

    private final int pageMax = 2;
    private final ArrayList<Parent> pages = new ArrayList(pageMax);

    @Autowired
    private ViewService viewService;

    @FXML
    private BorderPane carouselPane;

    @FXML
    private Pagination pagination;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initPages();
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount(pages.size() - 1);
        pagination.setPageFactory((Integer pageIndex) -> pages.get(pageIndex));
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        pagination.currentPageIndexProperty().addListener(currentPageListener());
    }

    public Parent setPage(int pageNumber, Views view) {
        viewService.loadView(view);
        return pages.set(pageNumber, (Parent) viewService.getView(view));
    }

    protected BorderPane getCarouselPane() {
        return carouselPane;
    }

    protected Pagination getPagination() {
        return pagination;
    }

    private void initPages() {
        for (int i = 0; i <= pageMax; i++) {
            pages.add(new BorderPane(new Label("No Content: Page " + i + 1)));
        }
    }

    private ChangeListener<Number> currentPageListener() {
        return (observ, oldVal, newVal) -> {
            //TODO
        };
    }

//    private final EventHandler<ActionEvent> cycleHandler = (ActionEvent t) -> {
//        KeyValue kvFor = new KeyValue(pagination.currentPageIndexProperty(), pageMax);
//        KeyFrame kf = new KeyFrame(Duration.seconds(10), kvFor);
//        Timeline cycle = new Timeline(kf);
//        cycle.setDelay(Duration.seconds(10));
//        cycle.setCycleCount(10);
//        cycle.setRate(.5);
//        cycle.play();
//    };

}
