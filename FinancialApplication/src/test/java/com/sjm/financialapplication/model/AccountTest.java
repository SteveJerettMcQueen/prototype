/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.model;

import com.sjm.financialapplication.exception.InvalidAmountException;
import java.math.BigDecimal;
import org.junit.Test;

/**
 *
 * @author Steve
 */
public class AccountTest {

    @Test(expected = InvalidAmountException.class)
    public void testSetAccountInitialBalance() {
        Account a = new Account();
        a.setAccountInitBal(BigDecimal.valueOf(-1));
    }

    @Test(expected = InvalidAmountException.class)
    public void testSetAccountCurrentBalance() {
        Account a = new Account();
        a.setAccountCurBal(BigDecimal.valueOf(-1));
    }
}
