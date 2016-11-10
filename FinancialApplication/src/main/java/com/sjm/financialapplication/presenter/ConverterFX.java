/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

import com.sjm.financialapplication.model.AccountTransaction;
import com.sjm.financialapplication.model.Accountowner;
import com.sjm.financialapplication.model.AccountGoal;
import com.sjm.financialapplication.model.Account;
import com.sjm.financialapplication.model.AppUser;
import com.sjm.financialapplication.main.AppIds;
import com.sjm.financialapplication.service.AccountGoalService;
import com.sjm.financialapplication.service.AccountService;
import com.sjm.financialapplication.service.AccountTransactionService;
import com.sjm.financialapplication.service.AccountownerService;
import com.sjm.financialapplication.service.AppUserService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sm6668
 */
@Component
public class ConverterFX {

    @Autowired
    private AppIds aids;

    @Autowired
    private AppUserService aus;

    @Autowired
    private AccountownerService aos;

    @Autowired
    private AccountService as;

    @Autowired
    private AccountGoalService ags;

    @Autowired
    private AccountTransactionService ats;

    public ObservableList<AppUserFX> convertAppUsers() {
        AppUser au = aus.getAppUserById(aids.getUserId());
        if (au != null) {
            AppUserFX afx = new AppUserFX();
            afx.setAppUserInfo(au);
            return FXCollections.observableArrayList(afx);
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    public AccountownerFX convertAccountowner() {
        Accountowner ao = aos.getAccountownerById(aids.getUserId());
        if (ao != null) {
            AccountownerFX aofx = new AccountownerFX();
            aofx.setAccountownerInfo(ao);
            return aofx;
        } else {
            return new AccountownerFX();
        }
    }

    public AccountFX convertAccount() {
        Account a = as.getAccountById(aids.getAccountId());
        if (a != null) {
            AccountFX afx = new AccountFX();
            afx.setAccountInfo(a);
            return afx;
        } else {
            return new AccountFX();
        }
    }

    public ObservableList<AccountFX> convertAccountownerAccounts() {
        Accountowner ao = aos.getAccountownerById(aids.getUserId());
        List<Account> list = new ArrayList(ao.getAccounts());

        if (!list.isEmpty()) {
            ObservableList<AccountFX> obsList = FXCollections.observableArrayList();
            list.stream().map((a) -> {
                AccountFX afx = new AccountFX();
                afx.setAccountInfo(a);
                return afx;
            }).forEach((afx) -> {
                obsList.add(afx);
            });

            return obsList;

        } else {

            return FXCollections.emptyObservableList();

        }
    }

    public ObservableList<PieChart.Data> convertAggPieChartBalance() {
        List<Object[]> list = aos.getAggBalance(aids.getUserId());
        if (!list.isEmpty()) {
            ObservableList<PieChart.Data> obsList = FXCollections.observableArrayList();
            list.stream().forEach((data) -> {
                String name = (String) data[0];
                double amount = ((BigDecimal) data[1]).doubleValue();
                obsList.add(new PieChart.Data(name, amount));
            });

            return obsList;

        } else {

            return FXCollections.emptyObservableList();

        }

    }

    public ObservableList<XYChart.Series<String, Number>> convertAggBarChartBalance() {
        List<Object[]> list = aos.getAggBalance(aids.getUserId());
        if (!list.isEmpty()) {
            ObservableList<XYChart.Series<String, Number>> obsList = FXCollections.observableArrayList();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            ObservableList<XYChart.Data<String, Number>> seriesData = FXCollections.observableArrayList();

            list.stream().forEach((data) -> {
                String xAxis = (String) data[0];
                Number yAxis = ((BigDecimal) data[1]).doubleValue();
                seriesData.add(new XYChart.Data(xAxis, yAxis));
            });

            series.setData(seriesData);
            obsList.setAll(series);
            return obsList;

        } else {

            return FXCollections.emptyObservableList();

        }

    }

    public Number aggTotal() {
        return aos.getAggTotal(aids.getUserId());
    }

    public ObservableList<Object[]> findCredits() {
        int id = aids.getAccountId();
        if (!as.findCredits(id).isEmpty()) {
            return FXCollections.observableArrayList(
                    as.findCredits(id));
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    public Number sumCredits() {
        return as.sumCredits(aids.getAccountId());
    }

    public Number avgCredits() {
        return as.avgCredits(aids.getAccountId());
    }

    public ObservableList<Object[]> findDebits() {
        int id = aids.getAccountId();
        if (!as.findDebits(id).isEmpty()) {
            return FXCollections.observableArrayList(
                    as.findDebits(id));
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    public Number sumDebits() {
        return as.sumDebits(aids.getAccountId());
    }

    public Number avgDebits() {
        return as.avgDebits(aids.getAccountId());
    }

    public ObservableList<XYChart.Series<String, Number>> convertAccountAmounts(Date startDate, Date endDate) {
        return convertList(as.getAccountAmounts(aids.getAccountId(), startDate, endDate));
    }

    public ObservableList<XYChart.Series<String, Number>> convertAccountBalance(Date startDate, Date endDate) {
        return convertList(as.getAccountBalances(aids.getAccountId(), startDate, endDate));
    }

    public ObservableList<XYChart.Series<Number, String>> convertAccountCategories() {
        List<Object[]> list = as.getAccountCategories(aids.getAccountId());
        if (!list.isEmpty()) {
            ObservableList<XYChart.Series<Number, String>> obsList = FXCollections.observableArrayList();
            XYChart.Series<Number, String> series = new XYChart.Series<>();
            ObservableList<XYChart.Data<Number, String>> seriesData = FXCollections.observableArrayList();

            list.stream().forEach((data) -> {
                Number xAxis = ((BigDecimal) data[0]).doubleValue();
                String yAxis = (String) data[1];

                XYChart.Data xyData = new XYChart.Data(xAxis, yAxis);
                seriesData.add(xyData);
            });

            series.setData(seriesData);
            obsList.setAll(series);
            return obsList;

        } else {

            return FXCollections.emptyObservableList();

        }
    }

    public AccountGoalFX convertAccountGoal() {
        List<AccountGoal> list = ags.getAccountGoalsById(aids.getAccountId(), 0, 1);
        if (!list.isEmpty()) {
            AccountGoal ag = list.get(0);
            AccountGoalFX agfx = new AccountGoalFX();
            agfx.setAccountGoalInfo(ag);
            return agfx;
        } else {
            return new AccountGoalFX();
        }

    }

    public ObservableList<AccountGoalFX> convertAccountGoals(int start, int max) {
        List<AccountGoal> list = ags.getAccountGoalsById(aids.getAccountId(), start, max);
        if (!list.isEmpty()) {
            ObservableList<AccountGoalFX> obsList = FXCollections.observableArrayList();
            list.stream().map((ag) -> {
                AccountGoalFX agfx = new AccountGoalFX();
                agfx.setAccountGoalInfo(ag);
                return agfx;
            }).forEach((agfx) -> {
                obsList.add(agfx);
            });

            return obsList;

        } else {

            return FXCollections.emptyObservableList();

        }
    }

    public Number totalAccountTransactions() {
        return ats.totalAccountTransactions(aids.getAccountId());
    }

    public ObservableList<AccountTransactionFX> convertAccountTransactions(int start, int max) {
        List<AccountTransaction> list = ats.getAccountTransactionsById(aids.getAccountId(), start, max);
        if (!list.isEmpty()) {
            ObservableList<AccountTransactionFX> obsList = FXCollections.observableArrayList();
            list.stream().map((at) -> {
                AccountTransactionFX atfx = new AccountTransactionFX();
                atfx.setAccountTransactionInfo(at);
                return atfx;
            }).forEach((atfx) -> {
                obsList.add(atfx);
            });

            return obsList;

        } else {

            return FXCollections.emptyObservableList();

        }

    }

    private ObservableList<XYChart.Series<String, Number>> convertList(List<Object[]> list) {
        if (!list.isEmpty()) {
            ObservableList<XYChart.Series<String, Number>> obsList = FXCollections.observableArrayList();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            ObservableList<XYChart.Data<String, Number>> seriesData = FXCollections.observableArrayList();

            list.stream().forEach((data) -> {
                Timestamp stamp = (Timestamp) data[1];
                String xAxis = stamp.toString();
                double yAxis = ((BigDecimal) data[0]).doubleValue();

                /*
                 Event Handling for chart data/Add elsewhere
                 */
                XYChart.Data xyData = new XYChart.Data(xAxis, yAxis);

                Circle cir = new Circle(2, Paint.valueOf("red"));
                Tooltip tip = new Tooltip();
                tip.setText("Amount: " + xyData.getYValue().toString());
                Tooltip.install(cir, tip);
                xyData.setNode(cir);

                seriesData.add(xyData);

            });

            series.setData(seriesData);
            obsList.setAll(series);
            return obsList;

        } else {

            return FXCollections.emptyObservableList();

        }

    }

}
