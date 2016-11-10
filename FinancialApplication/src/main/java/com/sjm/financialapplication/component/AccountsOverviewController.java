/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.presenter.AccountFX;
import com.sjm.financialapplication.presenter.DataPresenter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Steve
 */
@Component
public class AccountsOverviewController implements Initializable {

    @Autowired
    private DataPresenter dataPresenter;

    @FXML
    private BorderPane acntPane;

    @FXML
    private ListView<AccountFX> acntListView;

    @FXML
    private Label totalAcntBalLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acntListView.itemsProperty().bind(dataPresenter.accountownerAccountsFXProperty());
        totalAcntBalLabel.textProperty().bindBidirectional(dataPresenter.aggTotalProperty(),
                new NumberStringConverter());
    }

    protected BorderPane getAccountPane() {
        return acntPane;
    }

    protected ListView getAccountListView() {
        return acntListView;
    }

    protected Label getTotalAccountBalanceLabel() {
        return totalAcntBalLabel;
    }
}
