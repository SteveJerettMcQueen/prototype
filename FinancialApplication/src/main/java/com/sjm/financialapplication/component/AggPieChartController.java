/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.presenter.DataPresenter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author sm6668
 */
@Component
public class AggPieChartController implements Initializable {

    @Autowired
    private DataPresenter dataPresenter;

    @FXML
    private PieChart aggPieChart;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aggPieChart.dataProperty().bind(dataPresenter.aggPieChartBalanceProperty());
    }

}
