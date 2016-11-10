/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.other;

import com.sjm.financialapplication.presenter.AccountTransactionFX;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.control.TableRow;

/**
 *
 * @author Steve
 */
public class AccountTransactionRow extends TableRow<AccountTransactionFX> {

    //The pseudo classes 'credit' and 'debit' that were defined in the css file.
    private final PseudoClass credit;
    private final PseudoClass debit;

    public AccountTransactionRow() {
        credit = PseudoClass.getPseudoClass("credit");
        debit = PseudoClass.getPseudoClass("debit");
        this.itemProperty().addListener(rowItemListener());
    }

    @Override
    public void updateItem(AccountTransactionFX item, boolean empty) {
        if (item != null) {

        }
    }

    private ChangeListener<AccountTransactionFX> rowItemListener() {
        return (observ, oldVal, newVal) -> {
            if (newVal != null) {

                boolean isCredit = (Double.parseDouble(newVal.creditProperty().get()) >= 0);
                boolean isDebit = (Double.parseDouble(newVal.debitProperty().get()) < 0);

                newVal.creditProperty().addListener(numberListener());
                this.pseudoClassStateChanged(credit, isCredit);
                this.pseudoClassStateChanged(debit, isDebit);

            } else {

                this.pseudoClassStateChanged(credit, false);
                this.pseudoClassStateChanged(debit, false);

            }
        };
    }

    private ChangeListener<String> numberListener() {
        return (observ, oldVal, newVal) -> {
            boolean isCredit = Double.parseDouble(newVal) >= 0;
            boolean isDebit = Double.parseDouble(newVal) < 0;

            this.pseudoClassStateChanged(credit, isCredit);
            this.pseudoClassStateChanged(debit, isDebit);
        };
    }
}
