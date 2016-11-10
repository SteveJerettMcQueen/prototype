/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.component;

import com.sjm.financialapplication.concurrency.AccountGoalWorkers.AccountGoalDeleter;
import com.sjm.financialapplication.concurrency.AccountGoalWorkers.AccountGoalSaver;
import com.sjm.financialapplication.concurrency.AppExecutorService;
import com.sjm.financialapplication.concurrency.ViewWorkers.AccountViewer;
import com.sjm.financialapplication.model.Account;
import com.sjm.financialapplication.model.AccountGoal;
import com.sjm.financialapplication.other.TimeCellCallbacks.DayCellCallbacks.Future;
import com.sjm.financialapplication.presenter.AccountFX;
import com.sjm.financialapplication.presenter.AccountGoalFX;
import com.sjm.financialapplication.presenter.DataPresenter;
import static com.sjm.financialapplication.util.LogicUtils.isValidCombo2;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Steve
 */
@Component
public class GoalsController implements Initializable {

    private ObjectProperty<AccountFX> afxProperty
            = new SimpleObjectProperty();

    private ObjectProperty<ObservableList<AccountGoalFX>> agsfxProperty
            = new SimpleObjectProperty();

    @Autowired
    private AppExecutorService aes;

    @Autowired
    private AccountViewer av;

    @Autowired
    private AccountGoalDeleter agd;

    @Autowired
    private AccountGoalSaver ags;

    @Autowired
    private DataPresenter dataPresenter;

    @Autowired
    private TargetProgressController tpc;

    @Autowired
    private MinimumProgressController minpc;

    @Autowired
    private MessagePaneController mpc;

    @FXML
    private GridPane setGoalPane, removeGoalPane;

    @FXML
    private DatePicker expDatePicker;

    @FXML
    private TextField targetTF, minTF;

    @FXML
    private TableView<AccountGoalFX> goalTableView;

    @FXML
    private TableColumn<AccountGoalFX, String> expDateCol;

    @FXML
    private TableColumn<AccountGoalFX, String> minCol, targetCol;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setExecutors(aes.getExecutorServicePool());
        agd.setOnSucceeded(e -> agdSucceeded());
        ags.setOnSucceeded(e -> agsSucceeded());

        afxProperty.bind(dataPresenter.accountFXProperty());
        agsfxProperty.bind(dataPresenter.accountGoalsFXProperty());

        targProgBinding.addListener(targProgListener());
        minProgBinding.addListener(minProgListener());

        tpc.getTargetProgressBar().progressProperty().bind(targProgBinding);
        minpc.getLeftMinProgressBar().progressProperty().bind(minProgBinding);
        minpc.getRightMinProgressBar().progressProperty().bind(minDiffProgBinding);

        setGoalPane.disableProperty().bind(hasGoalBinding);
        removeGoalPane.disableProperty().bind(setGoalPane.disableProperty().not());

        expDatePicker.setDayCellFactory(new Future());

        goalTableView.itemsProperty().bind(agsfxProperty);
        expDateCol.setCellValueFactory(p -> p.getValue().goalDateProperty());
        minCol.setCellValueFactory(p -> p.getValue().minAmountProperty());
        targetCol.setCellValueFactory(p -> p.getValue().targetAmountProperty());

    }

    @FXML
    private void setGoal(ActionEvent event) {
        if (isValidCombo2(
                !expDatePicker.getEditor().getText().isEmpty(),
                !minTF.getText().isEmpty(),
                !targetTF.getText().isEmpty()
        )) {

            LocalDate localDate = expDatePicker.getValue();
            Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            BigDecimal min = new BigDecimal(minTF.getText());
            BigDecimal target = new BigDecimal(targetTF.getText());

            Account a = afxProperty.get().getAccount();
            AccountGoal ag = new AccountGoal();

            ag.setAccount(a);
            ag.setGoalDate(date);
            ag.setMinAmount(min);
            ag.setTargetAmount(target);

            save(ag);

        } else {

            //Incorrect Combo
        }

        expDatePicker.getEditor().clear();
        targetTF.clear();
        minTF.clear();
        event.consume();
    }

    @FXML
    private void removeGoal(ActionEvent event) {
        AccountGoalFX agfx = goalTableView.getItems().get(0);
        AccountGoal ag = agfx.getAccountGoal();
        delete(ag);
        event.consume();
    }

    protected DatePicker getExpDatePicker() {
        return expDatePicker;
    }

    protected TextField getTargetTF() {
        return targetTF;
    }

    protected TextField getMinTF() {
        return minTF;
    }

    private void setExecutors(ExecutorService es) {
        av.setExecutor(es);
        agd.setExecutor(es);
        ags.setExecutor(es);
    }

    private void agdSucceeded() {
        agd.reset();
        resetAccountView();
    }

    private void agsSucceeded() {
        expDatePicker.getEditor().clear();
        minTF.clear();
        targetTF.clear();
        ags.reset();
        resetAccountView();
    }

    private void delete(AccountGoal ag) {
        resetUIComponents(agd);
        agd.setAccountGoal(ag);
        agd.start();
    }

    private void save(AccountGoal ag) {
        resetUIComponents(ags);
        ags.setAccountGoal(ag);
        ags.start();
    }

    private void resetAccountView() {
        av.setOnSucceeded(e -> av.reset());
        av.start();
    }

    private void resetUIComponents(Service s) {
        unbindAll();
        bindAll(s);
    }

    private void unbindAll() {
        mpc.getMessagePane().visibleProperty().unbind();
        mpc.getMessageLabel().textProperty().unbind();
    }

    private void bindAll(Service s) {
        mpc.getMessagePane().visibleProperty().bind(s.runningProperty());
        mpc.getMessageLabel().textProperty().bind(s.messageProperty());
    }

    /*
    ** Bindings and Listeners
     */
    private final BooleanBinding hasGoalBinding = new BooleanBinding() {
        {
            bind(agsfxProperty);
        }

        ;
        
        @Override
        protected boolean computeValue() {
            ObservableList<AccountGoalFX> goalsList = agsfxProperty.get();
            return !(goalsList == null || goalsList.isEmpty());
        }
    };

    private final DoubleBinding targProgBinding = new DoubleBinding() {
        {
            bind(afxProperty, agsfxProperty);
        }

        ;
        
        @Override
        protected double computeValue() {
            AccountFX afx = afxProperty.get();
            ObservableList<AccountGoalFX> goalsList = agsfxProperty.get();
            if (goalsList == null || goalsList.isEmpty()) {
                return 0.0;
            } else {
                Account a = afx.getAccount();
                AccountGoal ag = goalsList.get(0).getAccountGoal();

                double curBal = a.getAccountCurBal().doubleValue();
                double target = ag.getTargetAmount().doubleValue();
                double percent = ((curBal / target));

                return percent;
            }
        }
    };

    private final DoubleBinding minProgBinding = new DoubleBinding() {
        {
            bind(afxProperty, agsfxProperty);
        }

        ;
        
        @Override
        protected double computeValue() {
            AccountFX afx = afxProperty.get();
            ObservableList<AccountGoalFX> goalsList = agsfxProperty.get();
            if (goalsList == null || goalsList.isEmpty()) {
                return 0.0;
            } else {
                Account a = afx.getAccount();
                AccountGoal ag = goalsList.get(0).getAccountGoal();

                double curBal = a.getAccountCurBal().doubleValue();
                double min = ag.getMinAmount().doubleValue();
                double decimal = (min / curBal);

                return decimal;
            }
        }
    };

    private final DoubleBinding minDiffProgBinding = new DoubleBinding() {
        {
            bind(afxProperty, agsfxProperty);
        }

        ;
        
        @Override
        protected double computeValue() {
            AccountFX afx = afxProperty.get();
            ObservableList<AccountGoalFX> goalsList = agsfxProperty.get();
            if (goalsList == null || goalsList.isEmpty()) {
                return 0.0;
            } else {
                Account a = afx.getAccount();
                AccountGoal ag = goalsList.get(0).getAccountGoal();

                double curBal = a.getAccountCurBal().doubleValue();
                double min = ag.getMinAmount().doubleValue();
                double decimal = (((curBal - min) / curBal));

                return decimal;
            }
        }
    };

    private ChangeListener<Number> targProgListener() {
        return (observ, oldval, newVal) -> {

        };
    }

    private ChangeListener<Number> minProgListener() {
        return (observ, oldval, newVal) -> {

        };
    }
}
