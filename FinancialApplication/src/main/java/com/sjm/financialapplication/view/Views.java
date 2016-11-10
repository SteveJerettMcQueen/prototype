package com.sjm.financialapplication.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Steve
 */
public enum Views {

    VIEW1("fxml/Login.fxml"),
    VIEW2("fxml/Home.fxml"),
    VIEW3("fxml/Profile.fxml"),
    VIEW4("fxml/Finances.fxml"),
    VIEW5("fxml/FinancesOverview.fxml"),
    VIEW6("fxml/AccountDetailPane.fxml"),
    VIEW7("fxml/AppUserAccount.fxml"),
    CHART1("fxml/charts/AggBarChart.fxml"),
    CHART2("fxml/charts/AggPieChart.fxml"),
    CONTAINER("fxml/custom/ViewContainer.fxml");

    private final String location;

    private Views(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}
