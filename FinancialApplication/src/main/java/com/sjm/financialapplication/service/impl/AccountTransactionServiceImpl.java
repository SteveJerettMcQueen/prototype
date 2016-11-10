/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service.impl;

import com.sjm.financialapplication.dao.AccountTransactionDAO;
import com.sjm.financialapplication.model.AccountTransaction;
import com.sjm.financialapplication.service.AccountTransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Steve
 */
@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Autowired
    private AccountTransactionDAO accountTransactionDAO;

    @Override
    @Transactional
    public List<AccountTransaction> getAccountTransactionsById(int accountId, int start, int max) {
        return accountTransactionDAO.getAccountTransactionsById(accountId, start, max);
    }

    @Override
    @Transactional
    public Number totalAccountTransactions(int accountId) {
        return accountTransactionDAO.totalAccountTransactions(accountId);
    }

    @Override
    @Transactional
    public void delete(AccountTransaction at) {
        accountTransactionDAO.delete(at);
    }

    @Override
    @Transactional
    public void save(AccountTransaction at) {
        accountTransactionDAO.save(at);
    }

    @Override
    @Transactional
    public void update(AccountTransaction at) {
        accountTransactionDAO.update(at);
    }

}
