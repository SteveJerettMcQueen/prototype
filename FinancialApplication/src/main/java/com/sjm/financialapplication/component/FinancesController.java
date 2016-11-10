/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.view.ViewService;
import com.sjm.financialapplication.presenter.DataPresenter;
import com.sjm.financialapplication.other.AccountCell;
import com.sjm.financialapplication.view.Views;
import com.sjm.financialapplication.presenter.AccountFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sjm.financialapplication.concurrency.AppExecutorService;
import com.sjm.financialapplication.concurrency.ViewWorkers.AccountViewer;
import com.sjm.financialapplication.model.Account;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Steve
 */
@Component
public class FinancesController implements Initializable {

    private final ArrayList<Parent> pages = new ArrayList(2);

    @Autowired
    private ViewService viewService;

    @Autowired
    private AppExecutorService aes;

    @Autowired
    private AccountViewer av;

    @Autowired
    private DataPresenter dataPresenter;

    @Autowired
    private ProgressPaneController ppc;

    @Autowired
    private TablePagerController tpc;

    @FXML
    private ToolBar leftToolBar;

    @FXML
    private ListView<AccountFX> acntListView;

    @FXML
    private BorderPane acntListPane;

    @FXML
    private Pagination pagination;

    @FXML
    private Button actionBtn;

    @FXML
    private Label topLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        av.setExecutor(aes.getExecutorServicePool());

        addPage(Views.VIEW5);
        addPage(Views.VIEW6);
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount(pages.size() - 1);
        pagination.setPageFactory((Integer pageIndex) -> pages.get(pageIndex));
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        pagination.currentPageIndexProperty().addListener(currentPageListener());

        acntListView.setCellFactory((ListView<AccountFX> list) -> new AccountCell());
        acntListView.getSelectionModel().selectedItemProperty().addListener(itemListener());
        acntListView.itemsProperty().bind(dataPresenter.accountownerAccountsFXProperty());

        actionBtn.addEventHandler(ActionEvent.ACTION, hideHandler);

        leftToolBar.disableProperty().bind(av.runningProperty());
        ppc.getProgressPane().visibleProperty().bind(av.runningProperty());
        ppc.getProgressIndicator().progressProperty().bind(av.progressProperty());
        ppc.getMessageLabel().textProperty().bind(av.messageProperty());

        acntListPane.disableProperty().bind(av.runningProperty());

        acntListPane.setBackground(new Background(
                new BackgroundFill(Paint.valueOf("rgba(255, 255, 255, 0.9)"),
                        CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    private void viewAccount(MouseEvent event) {
        if (event.getClickCount() == 2) {
            actionBtn.fire();

            ppc.getProgressPane().visibleProperty().unbind();
            ppc.getProgressIndicator().progressProperty().unbind();
            ppc.getMessageLabel().textProperty().unbind();

            ppc.getProgressPane().visibleProperty().bind(av.runningProperty());
            ppc.getProgressIndicator().progressProperty().bind(av.progressProperty());
            ppc.getMessageLabel().textProperty().bind(av.messageProperty());

            av.setOnFailed(e -> System.out.println("Account View Failed!"));
            av.setOnSucceeded(e -> avSucceeded());
            av.start();
        }
        event.consume();
    }

    @FXML
    private void closeAcntListPane(MouseEvent event) {
        actionBtn.fire();
        event.consume();
    }

    private boolean addPage(Views view) {
        viewService.loadView(view);
        return pages.add((Parent) viewService.getView(view));
    }

    private void avSucceeded() {
        pagination.setPageCount(pages.size());
        pagination.setCurrentPageIndex(1);
        topLabel.setText(acntListView.getSelectionModel().getSelectedItem().getAccount().getAccountName());
        av.reset();

        tpc.setPageNumber(1);//Start at page 1
        tpc.getTablePagerService().start();
    }

    private ChangeListener<Number> currentPageListener() {
        return (observ, oldVal, newVal) -> {
            int accountPage = 1;
            int currentPage = newVal.intValue();

            if (currentPage == accountPage) {
                topLabel.setVisible(true);
            } else {
                topLabel.setVisible(false);
            }

        };
    }

    private ChangeListener<AccountFX> itemListener() {
        return (observ, oldVal, newVal) -> {
            if (newVal != null) {
                Account a = newVal.getAccount();
                av.setAccountId(a.getAccountId());
            }
        };
    }

    private final EventHandler<ActionEvent> showHandler = (ActionEvent t) -> {
        KeyValue kvFade = new KeyValue(acntListPane.opacityProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(75), kvFade);
        Timeline hide = new Timeline(kf);
        hide.setOnFinished(e -> setShow());
        hide.play();
    };

    private final EventHandler<ActionEvent> hideHandler = (ActionEvent t) -> {
        acntListPane.setVisible(true);
        KeyValue kvFade = new KeyValue(acntListPane.opacityProperty(), 1);
        KeyFrame kf = new KeyFrame(Duration.millis(75), kvFade);
        Timeline show = new Timeline(kf);
        show.setOnFinished(e -> setHide());
        show.play();
    };

    private void setShow() {
        acntListPane.setVisible(false);
        actionBtn.removeEventHandler(ActionEvent.ACTION, showHandler);
        actionBtn.addEventHandler(ActionEvent.ACTION, hideHandler);
    }

    private void setHide() {
        acntListPane.setVisible(true);
        actionBtn.removeEventHandler(ActionEvent.ACTION, hideHandler);
        actionBtn.addEventHandler(ActionEvent.ACTION, showHandler);
    }

}
