/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

import com.sjm.financialapplication.model.Account;
import static com.sjm.financialapplication.util.TimeUtils.defaultFormat;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Steve
 */
public class AccountFX {

    private Account account;
    private final SimpleObjectProperty<String> accountName, accountInitBal, accountCurBal, startDate;
//    private final SimpleObjectProperty<Number> numOfTransactions, numOfCredits, numOfDebits;
//    private final SimpleObjectProperty<Boolean> hasGoal;

    public AccountFX() {
        accountName = new SimpleObjectProperty("");
        accountInitBal = new SimpleObjectProperty("");
        accountCurBal = new SimpleObjectProperty("");
        startDate = new SimpleObjectProperty("");
//        numOfTransactions = new SimpleObjectProperty();
//        numOfCredits = new SimpleObjectProperty();
//        numOfDebits = new SimpleObjectProperty();
//        hasGoal = new SimpleObjectProperty(false);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account a) {
        account = a;
    }

    public SimpleObjectProperty<String> accountNameProperty() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }

    public SimpleObjectProperty<String> accountInitBalProperty() {
        return accountInitBal;
    }

    public void setAccountInitBal(String accountInitBal) {
        this.accountInitBal.set(accountInitBal);
    }

    public SimpleObjectProperty<String> accountCurBalProperty() {
        return accountCurBal;
    }

    public void setAccountCurBal(String accountCurBal) {
        this.accountCurBal.set(accountCurBal);
    }

    public SimpleObjectProperty<String> startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

//    public SimpleObjectProperty<Number> numOfTransactionsProperty() {
//        return numOfTransactions;
//    }
//
//    public void setNumOfTransactions(int numOfTransactions) {
//        this.numOfTransactions.set(numOfTransactions);
//    }
//
//    public SimpleObjectProperty<Number> numOfCreditsProperty() {
//        return numOfCredits;
//    }
//
//    public void setNumOfCredits(int numOfCredits) {
//        this.numOfCredits.set(numOfCredits);
//    }
//
//    public SimpleObjectProperty<Number> numOfDebitsProperty() {
//        return numOfDebits;
//    }
//
//    public void setNumOfDebits(int numOfDebits) {
//        this.numOfDebits.set(numOfDebits);
//    }
//
//    public SimpleObjectProperty<Boolean> hasGoalProperty() {
//        return hasGoal;
//    }
//
//    public void setHasGoal(boolean value) {
//        hasGoal.set(value);
//    }

    public void setAccountInfo(Account a) {
        setAccount(a);
        setAccountName(a.getAccountName());
        setAccountCurBal(a.getAccountCurBal().toString());
        setAccountInitBal(a.getAccountInitBal().toString());
        setStartDate(defaultFormat(a.getStartDate()));
    }
}
