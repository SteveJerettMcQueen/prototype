/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.view.Views;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Steve
 */
@Component
public class FinancesOverviewController implements Initializable {

    @Autowired
    private AccountsOverviewController aoc;

    @Autowired
    private CarouselPaneController cpc;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cpc.setPage(0, Views.CHART1);
        cpc.setPage(1, Views.CHART2);
    }
}
