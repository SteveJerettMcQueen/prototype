/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.concurrency.AppExecutorService;
import com.sjm.financialapplication.presenter.DataPresenter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sjm.financialapplication.concurrency.ViewWorkers.ChartViewer;
import java.time.LocalDate;
import java.time.Month;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author sm6668
 */
@Component
public class AccountChartsController implements Initializable {

    private final NumberAxis xAxis = new NumberAxis();
    private final CategoryAxis yAxis = new CategoryAxis();
    private final BarChart<Number, String> barChart = new BarChart(xAxis, yAxis);

    @Autowired
    private AppExecutorService aes;

    @Autowired
    private ChartViewer cv;

    @Autowired
    private DataPresenter dataPresenter;

    @Autowired
    private ProgressPaneController ppc;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private AreaChart<String, Number> areaChart;

    @FXML
    private ComboBox<Integer> yearCB;

    @FXML
    private TitledPane catPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cv.setExecutor(aes.getExecutorServicePool());
        cv.setOnSucceeded(e -> cv.reset());

        xAxis.setVisible(false);
        xAxis.setTickLabelRotation(90);
        xAxis.setTickLabelsVisible(false);

        barChart.setAnimated(false);
        barChart.setLegendVisible(false);

        lineChart.getXAxis().setVisible(false);
        lineChart.getXAxis().setTickLabelsVisible(false);

        areaChart.getXAxis().setVisible(false);
        areaChart.getXAxis().setTickLabelsVisible(false);

        lineChart.dataProperty().bind(dataPresenter.accountBalanceProperty());
        areaChart.dataProperty().bind(dataPresenter.accountAmountsProperty());
        barChart.dataProperty().bind(dataPresenter.accountCategoriesProperty());

        yearCB.setItems(dataPresenter.getYears());
        yearCB.setValue(LocalDate.now().getYear());

    }

    @FXML
    private void showData(ActionEvent event) {
        ppc.getProgressPane().visibleProperty().unbind();
        ppc.getProgressIndicator().progressProperty().unbind();
        ppc.getMessageLabel().textProperty().unbind();

        ppc.getProgressPane().visibleProperty().bind(cv.runningProperty());
        ppc.getProgressIndicator().progressProperty().bind(cv.progressProperty());
        ppc.getMessageLabel().textProperty().bind(cv.messageProperty());

        LocalDate startDate = LocalDate.now()
                .withMonth(Month.JANUARY.getValue())
                .withYear(yearCB.getValue())
                .withDayOfMonth(1);

        LocalDate endDate = LocalDate.now()
                .withMonth(LocalDate.now().getMonthValue())
                .withYear(yearCB.getValue())
                .withDayOfMonth(LocalDate.now().getDayOfMonth());

        cv.setStartDate(startDate);
        cv.setEndDate(endDate);
        cv.start();
        event.consume();
    }

    @FXML
    private void addBarChart(ActionEvent event) {
        catPane.setContent(barChart);
        event.consume();
    }

    private Month getCurrentMonth(int value) {
        Month[] months = Month.values();
        for (Month month : months) {
            if (month.getValue() == value) {
                return month;
            }
        }
        return null;
    }

}
