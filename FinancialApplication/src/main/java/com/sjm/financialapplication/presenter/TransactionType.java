/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

/**
 *
 * @author sm6668
 */
public enum TransactionType {

    DEPOSIT("Deposit"),
    FEE("Fee"),
    INTEREST("Interest"),
    PAYROLL("Payroll"),
    WITHDRAWAL("Withdrawal");

    private final String s;

    private TransactionType(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

}
