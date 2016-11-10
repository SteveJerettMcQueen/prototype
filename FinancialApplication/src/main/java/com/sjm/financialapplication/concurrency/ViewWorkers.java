/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.concurrency;

import com.sjm.financialapplication.main.AppIds;
import com.sjm.financialapplication.presenter.ConverterFX;
import com.sjm.financialapplication.presenter.DataPresenter;
import static com.sjm.financialapplication.util.ThreadUtils.process;
import static com.sjm.financialapplication.util.TimeUtils.getCurrentDate;
import static com.sjm.financialapplication.util.TimeUtils.getFirstDate;
import static com.sjm.financialapplication.util.TimeUtils.parse;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sm6668
 */
@Component
public class ViewWorkers {

    @Autowired
    private AppIds aids;

    @Autowired
    private DataPresenter dp;

    @Autowired
    private ConverterFX cfx;

    @Component
    public class AccountownerViewer extends Service<Void> {

        private String userId;

        @Autowired
        public AccountownerViewer() {
        }

        public AccountownerViewer(String id) {
            this.userId = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String id) {
            this.userId = id;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                public Void call() {
                    updateMessage("Setting user information .....");
                    process(1000);
                    dp.setAppUsersFX(cfx.convertAppUsers());
                    dp.setAccountownerFX(cfx.convertAccountowner());
                    dp.setAccountownerAccountsFX(cfx.convertAccountownerAccounts());
                    dp.setAggPieChartBalance(cfx.convertAggPieChartBalance());
                    dp.setAggBarChartBalance(cfx.convertAggBarChartBalance());
                    dp.setAggTotal(cfx.aggTotal());
                    return null;
                }
            };
        }

    };

    @Component
    public class AccountViewer extends Service<Void> {

        private int accountId;
        private final SimpleIntegerProperty startProperty, maxProperty;
        private LocalDate startDate, endDate;

        @Autowired
        public AccountViewer() {
            startProperty = new SimpleIntegerProperty(0);
            maxProperty = new SimpleIntegerProperty(25);
            startDate = getFirstDate();
            endDate = getCurrentDate();
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int id) {
            this.accountId = id;
        }

        public SimpleIntegerProperty startProperty() {
            return startProperty;
        }

        public void setStart(int value) {
            startProperty.set(value);
        }

        public SimpleIntegerProperty maxProperty() {
            return maxProperty;
        }

        public void setMax(int max) {
            maxProperty.set(max);
        }

        public final LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate ld) {
            startDate = ld;
        }

        public final LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate ld) {
            endDate = ld;
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() {
                    updateMessage("Setting account information .....");
                    process(200);
                    aids.setAccountId(accountId);//Setting for later globally
                    Platform.runLater(() -> updateView());
                    return null;
                }

                private void updateView() {
                    dp.setAccountFX(cfx.convertAccount());
                    dp.setAccountAmounts(cfx.convertAccountAmounts(parse(startDate), parse(endDate)));
                    dp.setAccountBalance(cfx.convertAccountBalance(parse(startDate), parse(endDate)));
                    dp.setAccountCategories(cfx.convertAccountCategories());
                    dp.setAccountGoalsFX(cfx.convertAccountGoals(startProperty.get(), maxProperty.get()));
                    dp.setAccountTransactionsFX(cfx.convertAccountTransactions(startProperty.get(), maxProperty.get()));
                    dp.setTotalAccountTransactions(cfx.totalAccountTransactions().intValue());
                    dp.setAggPieChartBalance(cfx.convertAggPieChartBalance());
                    dp.setAggBarChartBalance(cfx.convertAggBarChartBalance());
                    dp.setAggTotal(cfx.aggTotal());
                }

            };
        }
    }

    @Component
    public class ChartViewer extends Service<Void> {

        private LocalDate startDate, endDate;

        @Autowired
        public ChartViewer() {
            startDate = getFirstDate();
            endDate = getCurrentDate();
        }

        public final LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate ld) {
            startDate = ld;
        }

        public final LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate ld) {
            endDate = ld;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() {
                    updateMessage("Getting yearly data .....");
                    process(200);
                    Platform.runLater(() -> updateView());
                    return null;
                }

                private void updateView() {
                    dp.setAccountAmounts(cfx.convertAccountAmounts(parse(startDate), parse(endDate)));
                    dp.setAccountBalance(cfx.convertAccountBalance(parse(startDate), parse(endDate)));
                }

            };
        }

    }

}
