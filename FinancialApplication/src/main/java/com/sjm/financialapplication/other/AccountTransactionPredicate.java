/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.other;

import com.sjm.financialapplication.presenter.AccountTransactionFX;
import java.util.function.Predicate;

/**
 *
 * @author Steve
 */
public class AccountTransactionPredicate implements Predicate<AccountTransactionFX> {

    private String input;

    public AccountTransactionPredicate() {
        input = null;
    }

    public AccountTransactionPredicate(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public boolean test(AccountTransactionFX atfx) {

        String userInput = input.toLowerCase();
        if (userInput == null || userInput.isEmpty()) {
            return true;
        }

        String transId = Integer.toString(atfx.transIdProperty().get());
        String transDate = atfx.transDateProperty().get().toString();
        String category = atfx.categoryProperty().get().toString().toLowerCase();
        String desc = atfx.descriptionProperty().get().toLowerCase();
        String type = atfx.typeProperty().get().toString().toLowerCase();

        return transId.contains(userInput)
                || transDate.contains(userInput)
                || category.contains(userInput)
                || desc.contains(userInput)
                || type.contains(userInput);
    }
}
