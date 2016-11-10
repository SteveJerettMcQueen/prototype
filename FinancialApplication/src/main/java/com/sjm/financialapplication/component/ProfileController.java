/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.presenter.DataPresenter;
import com.sjm.financialapplication.presenter.AccountownerFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Steve
 */
@Component
public class ProfileController implements Initializable {

    private AccountownerFX aofx = new AccountownerFX();
    
    @Autowired
    private DataPresenter dataPresenter;

    @FXML
    private Label label0, label1, label2, label3,
            label4, label5, label6;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aofx = dataPresenter.accountownerFXProperty().get();
        label0.textProperty().bind(aofx.getFirstNameProperty());
        label1.textProperty().bind(aofx.getLastNameProperty());
        label2.textProperty().bind(aofx.getGenderProperty());
        label3.textProperty().bind(aofx.getCityProperty());
        label4.textProperty().bind(aofx.getStateResidenceProperty());
        label5.textProperty().bind(aofx.getStreetProperty());
        label6.textProperty().bind(aofx.getZipProperty());
    }

}
