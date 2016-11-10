/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao;

import com.sjm.financialapplication.model.Account;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Steve
 */
public interface AccountDAO {

    Account getAccountById(int accountId);

    List<Object[]> findCredits(int accountId);

    Number sumCredits(int accountId);

    Number avgCredits(int accountId);

    List<Object[]> findDebits(int accountId);

    Number sumDebits(int accountId);

    Number avgDebits(int accountId);

    List<Object[]> getAccountAmounts(int accountId, Date startDate, Date endDate);

    List<Object[]> getAccountBalances(int accountId, Date startDate, Date endDate);

    List<Object[]> getAccountCategories(int accountId);

    int updateAccountBalanceById(int accountId, BigDecimal accountCurBal);

    void delete(Account a);

    void save(Account a);

    void update(Account a);

}
