/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

import com.sjm.financialapplication.model.AccountTransaction;
import static com.sjm.financialapplication.util.TimeUtils.parse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Steve
 */
public class AccountTransactionFX {

    private AccountTransaction accountTransaction;
    private final SimpleObjectProperty<Integer> transId;
    private final SimpleObjectProperty<Date> transDate;
    private final SimpleObjectProperty<Category> category;
    private final SimpleObjectProperty<String> description, credit, debit, balance;
    private final SimpleObjectProperty<TransactionType> type;

    public AccountTransactionFX() {
        transId = new SimpleObjectProperty();
        transDate = new SimpleObjectProperty();
        category = new SimpleObjectProperty();
        description = new SimpleObjectProperty();
        type = new SimpleObjectProperty();
        credit = new SimpleObjectProperty();
        debit = new SimpleObjectProperty();
        balance = new SimpleObjectProperty();
    }

    public AccountTransaction getAccountTransaction() {
        return accountTransaction;
    }

    public void setAccountTransaction(AccountTransaction at) {
        this.accountTransaction = at;
    }

    public SimpleObjectProperty<Integer> transIdProperty() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId.set(transId);
    }

    public SimpleObjectProperty<Date> transDateProperty() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate.set(transDate);
    }

    public SimpleObjectProperty<Category> categoryProperty() {
        return category;
    }

    public void setCategory(Category category) {
        this.category.set(category);
    }

    public SimpleObjectProperty<String> descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public SimpleObjectProperty<TransactionType> typeProperty() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type.set(type);
    }

    public SimpleObjectProperty<String> creditProperty() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit.set(credit);
    }

    public SimpleObjectProperty<String> debitProperty() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit.set(debit);
    }

    public SimpleObjectProperty<String> balanceProperty() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }

    public void setAccountTransactionInfo(AccountTransaction at) {
        setAccountTransaction(at);
        setTransId(at.getTransId());
        setTransDate(at.getTransDate());
        setCategory(Category.valueOf(at.getCategory()));
        setDescription(at.getDescription());
        setType(TransactionType.valueOf(at.getTransType()));

        BigDecimal amount = at.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) == 1) {
            setCredit(amount.toString());
        } else {
            setDebit(amount.toString());
        }

        setBalance(at.getCurBalance().toString());

    }

}
