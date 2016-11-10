/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.model;

import com.sjm.financialapplication.exception.InsufficientFundsException;
import com.sjm.financialapplication.exception.InvalidAmountException;
import java.math.BigDecimal;
import org.junit.Test;

/**
 *
 * @author Steve
 */
public class AccountTransactionTest {

//    @Test(expected = InsufficientFundsException.class)
//    public void testSetAmount() {
//        AccountTransaction at = new AccountTransaction();
//        at.setCurBalance(BigDecimal.valueOf(1000));
//        at.setAmount(BigDecimal.valueOf(-1001));
//    }

    @Test(expected = InvalidAmountException.class)
    public void testSetCurrentBalance() {
        AccountTransaction at = new AccountTransaction();
        at.setCurBalance(BigDecimal.valueOf(-1));
    }
}
