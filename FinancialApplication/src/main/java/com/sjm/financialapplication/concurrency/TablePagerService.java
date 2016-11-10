/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.concurrency;

import com.sjm.financialapplication.presenter.ConverterFX;
import com.sjm.financialapplication.presenter.DataPresenter;
import static com.sjm.financialapplication.util.ThreadUtils.process;
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
public class TablePagerService extends Service<Void> {

    @Autowired
    private DataPresenter dp;

    @Autowired
    private ConverterFX cfx;

    private final SimpleIntegerProperty startProperty, maxProperty;

    public TablePagerService() {
        startProperty = new SimpleIntegerProperty(0);
        maxProperty = new SimpleIntegerProperty(25);
    }

    public final SimpleIntegerProperty startProperty() {
        return startProperty;
    }

    public final SimpleIntegerProperty maxProperty() {
        return maxProperty;
    }

    @Override
    protected Task createTask() {
        return new Task<Void>() {

            @Override
            protected Void call() {
                updateMessage("Retrieving data .....");
                process(250);
                dp.setAccountTransactionsFX(cfx.convertAccountTransactions(startProperty().get(), maxProperty().get()));
                dp.setTotalAccountTransactions(cfx.totalAccountTransactions().intValue());
                return null;
            }
        };
    }
}
