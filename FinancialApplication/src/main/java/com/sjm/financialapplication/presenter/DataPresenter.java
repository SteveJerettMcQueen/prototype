/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

import java.time.LocalDate;
import java.time.Month;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import org.springframework.stereotype.Component;

/**
 *
 * @author Steve
 */
@Component
public class DataPresenter {

    private final ObservableList<Category> categories
            = FXCollections.observableArrayList(Category.values());

    private final ObservableList<TransactionType> transTypes
            = FXCollections.observableArrayList(TransactionType.values());

    private final ObservableList<Month> months
            = FXCollections.observableArrayList(Month.values());

    private final ObservableList<Integer> years
            = FXCollections.observableArrayList(getE3());

    private final ObjectProperty<ObservableList<AppUserFX>> appUsersFXProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<AccountownerFX> accountownerFXProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<AccountFX> accountFXProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<AccountFX>> accountownerAccountsFXProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<PieChart.Data>> aggPieChartBalanceProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<XYChart.Series<String, Number>>> aggBarChartBalanceProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<Number> aggTotalProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<Object[]>> findCreditsProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<Number> sumCreditsProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<Number> avgCreditsProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<Object[]>> findDebitsProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<Number> sumDebitsProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<Number> avgDebitsProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<XYChart.Series<String, Number>>> accountAmountsProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<XYChart.Series<String, Number>>> accountBalanceProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<XYChart.Series<Number, String>>> accountCategoriesProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<AccountGoalFX> accountGoalFXProperty
            = new SimpleObjectProperty();

    private final ObjectProperty<ObservableList<AccountGoalFX>> accountGoalsFXProperty
            = new SimpleObjectProperty();

    private final SimpleIntegerProperty totalAccountTransactionsProperty
            = new SimpleIntegerProperty();

    private final ObjectProperty<ObservableList<AccountTransactionFX>> accountTransactionsFXProperty
            = new SimpleObjectProperty();

    public final ObservableList<Category> getCategories() {
        return categories;
    }

    public final ObservableList<TransactionType> getTransTypes() {
        return transTypes;
    }

    public final ObservableList<Month> getMonths() {
        return months;
    }

    public final ObservableList<Integer> getYears() {
        return years;
    }

    public final ObjectProperty<ObservableList<AppUserFX>> appUsersFXProperty() {
        return appUsersFXProperty;
    }

    public final void setAppUsersFX(ObservableList<AppUserFX> value) {
        appUsersFXProperty.set(value);
    }

    public final ObjectProperty<AccountownerFX> accountownerFXProperty() {
        return accountownerFXProperty;
    }

    public final void setAccountownerFX(AccountownerFX value) {
        accountownerFXProperty.set(value);
    }

    public final ObjectProperty<AccountFX> accountFXProperty() {
        return accountFXProperty;
    }

    public final void setAccountFX(AccountFX value) {
        accountFXProperty.set(value);
    }

    public final ObjectProperty<ObservableList<AccountFX>> accountownerAccountsFXProperty() {
        return accountownerAccountsFXProperty;
    }

    public final void setAccountownerAccountsFX(ObservableList<AccountFX> value) {
        accountownerAccountsFXProperty.set(value);
    }

    public final ObjectProperty<ObservableList<PieChart.Data>> aggPieChartBalanceProperty() {
        return aggPieChartBalanceProperty;
    }

    public final void setAggPieChartBalance(ObservableList<PieChart.Data> value) {
        aggPieChartBalanceProperty.set(value);
    }

    public final ObjectProperty<ObservableList<XYChart.Series<String, Number>>> aggBarChartBalanceProperty() {
        return aggBarChartBalanceProperty;
    }

    public final void setAggBarChartBalance(ObservableList<XYChart.Series<String, Number>> value) {
        aggBarChartBalanceProperty.set(value);
    }

    public final ObjectProperty<Number> aggTotalProperty() {
        return aggTotalProperty;
    }

    public final void setAggTotal(Number value) {
        aggTotalProperty.set(value);
    }

    public final ObjectProperty<ObservableList<Object[]>> findCreditsProperty() {
        return findCreditsProperty;
    }

    public final void setFindCredits(ObservableList<Object[]> value) {
        findCreditsProperty.set(value);
    }

    public final ObjectProperty<Number> sumCreditsProperty() {
        return sumCreditsProperty;
    }

    public final void setSumCredits(Number value) {
        sumCreditsProperty.set(value);
    }

    public final ObjectProperty<Number> avgCreditsProperty() {
        return avgCreditsProperty;
    }

    public final void setAvgCredits(Number value) {
        avgCreditsProperty.set(value);
    }

    public final ObjectProperty<ObservableList<Object[]>> findDebitsProperty() {
        return findDebitsProperty;
    }

    public final void setFindDebits(ObservableList<Object[]> value) {
        findDebitsProperty.set(value);
    }

    public final ObjectProperty<Number> sumDebitsProperty() {
        return sumDebitsProperty;
    }

    public final void setSumDebits(Number value) {
        sumDebitsProperty.set(value);
    }

    public final ObjectProperty<Number> avgDebitsProperty() {
        return avgDebitsProperty;
    }

    public final void setAvgDebits(Number value) {
        avgDebitsProperty.set(value);
    }

    public final ObjectProperty<ObservableList<XYChart.Series<String, Number>>> accountAmountsProperty() {
        return accountAmountsProperty;
    }

    public final void setAccountAmounts(ObservableList<XYChart.Series<String, Number>> value) {
        accountAmountsProperty.set(value);
    }

    public final ObjectProperty<ObservableList<XYChart.Series<String, Number>>> accountBalanceProperty() {
        return accountBalanceProperty;
    }

    public final void setAccountBalance(ObservableList<XYChart.Series<String, Number>> value) {
        accountBalanceProperty.set(value);
    }

    public final ObjectProperty<ObservableList<XYChart.Series<Number, String>>> accountCategoriesProperty() {
        return accountCategoriesProperty;
    }

    public final void setAccountCategories(ObservableList<XYChart.Series<Number, String>> value) {
        accountCategoriesProperty.set(value);
    }

    public final ObjectProperty<AccountGoalFX> accountGoalFXProperty() {
        return accountGoalFXProperty;
    }

    public final void setAccountGoal(AccountGoalFX value) {
        accountGoalFXProperty.set(value);
    }

    public final ObjectProperty<ObservableList<AccountGoalFX>> accountGoalsFXProperty() {
        return accountGoalsFXProperty;
    }

    public final void setAccountGoalsFX(ObservableList<AccountGoalFX> value) {
        accountGoalsFXProperty.set(value);
    }

    public final SimpleIntegerProperty totalAccountTransactionsProperty() {
        return totalAccountTransactionsProperty;
    }

    public final void setTotalAccountTransactions(int value) {
        totalAccountTransactionsProperty.set(value);
    }

    public final ObjectProperty<ObservableList<AccountTransactionFX>> accountTransactionsFXProperty() {
        return accountTransactionsFXProperty;
    }

    public final void setAccountTransactionsFX(ObservableList<AccountTransactionFX> value) {
        accountTransactionsFXProperty.set(value);
    }

    private ObservableList<Integer> getE3() {
        ObservableList<Integer> y = FXCollections.observableArrayList();
        for (int i = LocalDate.now().getYear(); i >= 1900; i--) {
            y.add(i);
        }
        return y;
    }

}
