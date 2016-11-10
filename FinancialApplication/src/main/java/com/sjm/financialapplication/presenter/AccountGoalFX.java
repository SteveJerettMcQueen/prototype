/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

import com.sjm.financialapplication.model.AccountGoal;
import static com.sjm.financialapplication.util.TimeUtils.defaultFormat;
import java.util.Date;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Steve
 */
public class AccountGoalFX {

    private AccountGoal accountGoal;
    private final SimpleObjectProperty<Integer> goalId;
    private final SimpleObjectProperty<String> goalDate;
    private final SimpleObjectProperty<String> targetAmount, minAmount;

    public AccountGoalFX() {
        goalId = new SimpleObjectProperty();
        goalDate = new SimpleObjectProperty();
        targetAmount = new SimpleObjectProperty();
        minAmount = new SimpleObjectProperty();
    }

    public AccountGoal getAccountGoal() {
        return accountGoal;
    }

    public void setAccountGoal(AccountGoal ag) {
        this.accountGoal = ag;
    }

    public SimpleObjectProperty<Integer> goalIdProperty() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId.set(goalId);
    }

    public SimpleObjectProperty<String> goalDateProperty() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate.set(goalDate);
    }

    public SimpleObjectProperty<String> targetAmountProperty() {
        return targetAmount;
    }

    public void setTargetAmount(String amount) {
        targetAmount.set(amount);
    }

    public SimpleObjectProperty<String> minAmountProperty() {
        return minAmount;
    }

    public void setMinAmount(String amount) {
        minAmount.set(amount);
    }

    public void setAccountGoalInfo(AccountGoal ag) {
        setAccountGoal(ag);
        setGoalId(ag.getGoalId());
        setGoalDate(defaultFormat(ag.getGoalDate()));
        setTargetAmount(ag.getTargetAmount().toString());
        setMinAmount((ag.getMinAmount().toString()));
        
    }
}
