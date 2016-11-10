/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.concurrency.AppExecutorService;
import com.sjm.financialapplication.concurrency.AppUserWorkers.AppUserUpdater;
import com.sjm.financialapplication.concurrency.ViewWorkers.AccountownerViewer;
import com.sjm.financialapplication.presenter.DataPresenter;
import com.sjm.financialapplication.model.AppUser;
import com.sjm.financialapplication.presenter.AppUserFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Steve
 */
@Component
public class AppUserAccountController implements Initializable {

    @Autowired
    private AppExecutorService aes;

    @Autowired
    private AppUserUpdater au;

    @Autowired
    private AccountownerViewer aov;

    @Autowired
    private DataPresenter dataPresenter;
    
    @FXML
    private TableView<AppUserFX> appUserTableView;

    @FXML
    private TableColumn<AppUserFX, String> userIdCol, passwordCol;

    @FXML
    private TableColumn<AppUserFX, String> startDateCol;

    @FXML
    private ProgressBar progressBar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        au.setExecutor(aes.getExecutorServicePool());
        au.setOnSucceeded(e -> auSucceeded());

        aov.setExecutor(aes.getExecutorServicePool());
        aov.setOnSucceeded(e -> aovSucceeded());

        appUserTableView.itemsProperty().bind(dataPresenter.appUsersFXProperty());
        userIdCol.setCellValueFactory(p -> p.getValue().appUserIdProperty());
        userIdCol.setCellFactory(TextFieldTableCell.<AppUserFX>forTableColumn());

        passwordCol.setCellValueFactory(p -> p.getValue().passwordProperty());
        passwordCol.setCellFactory(TextFieldTableCell.<AppUserFX>forTableColumn());

        startDateCol.setCellValueFactory(p -> p.getValue().startDateProperty());

        progressBar.progressProperty().bind(au.progressProperty());
        progressBar.visibleProperty().bind(au.runningProperty());
    }

    @FXML
    private void updateUserId(TableColumn.CellEditEvent<AppUserFX, String> event) {
//        int index = event.getTablePosition().getRow();
//        AppUserFX aufx = event.getTableView().getItems().get(index);
//        AppUser appUser = aufx.getAppUser();
//        appUser.setAppUserId(event.getNewValue());
//        update(appUser);
//        event.consume();
    }

    @FXML
    private void updatePassword(TableColumn.CellEditEvent<AppUserFX, String> event) {
        int index = event.getTablePosition().getRow();
        AppUserFX aufx = event.getTableView().getItems().get(index);
        AppUser appUser = aufx.getAppUser();
        appUser.setPassword(event.getNewValue());
        update(appUser);
        event.consume();
    }

    private void auSucceeded() {
        au.reset();
        resetAccountownerView();
    }

    private void aovSucceeded() {
        aov.reset();
    }

    private void update(AppUser au) {
        this.au.setAppUser(au);
        this.au.start();
    }

    private void resetAccountownerView() {
        aov.start();
    }
}
