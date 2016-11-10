/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.other;

import com.sjm.financialapplication.presenter.AccountFX;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 *
 * @author Steve
 */
public class AccountCell extends ListCell<AccountFX> {

    private final Label label;

    public AccountCell() {
        label = new Label();
    }

    @Override
    public void updateItem(AccountFX item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            
            label.setText(item.getAccount().getAccountName());
            setGraphic(label);
        } else {
            setText(null);
            setGraphic(null);
        }

    }

}
