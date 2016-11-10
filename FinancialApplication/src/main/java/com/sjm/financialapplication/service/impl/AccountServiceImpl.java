/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service.impl;

import com.sjm.financialapplication.dao.AccountDAO;
import com.sjm.financialapplication.model.Account;
import com.sjm.financialapplication.service.AccountService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Steve
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    @Transactional
    public Account getAccountById(int accountID) {
        return accountDAO.getAccountById(accountID);
    }

    @Override
    @Transactional
    public List<Object[]> findCredits(int accountId) {
        return accountDAO.findCredits(accountId);
    }

    @Override
    @Transactional
    public Number sumCredits(int accountId) {
        return accountDAO.sumCredits(accountId);
    }

    @Override
    @Transactional
    public Number avgCredits(int accountId) {
        return accountDAO.avgCredits(accountId);
    }

    @Override
    @Transactional
    public List<Object[]> findDebits(int accountId) {
        return accountDAO.findDebits(accountId);
    }

    @Override
    @Transactional
    public Number sumDebits(int accountId) {
        return accountDAO.sumDebits(accountId);
    }

    @Override
    @Transactional
    public Number avgDebits(int accountId) {
        return accountDAO.avgDebits(accountId);
    }

    @Override
    @Transactional
    public List<Object[]> getAccountAmounts(int accountId, Date startDate, Date endDate) {
        return accountDAO.getAccountAmounts(accountId, startDate, endDate);
    }

    @Override
    @Transactional
    public List<Object[]> getAccountBalances(int accountId, Date startDate, Date endDate) {
        return accountDAO.getAccountBalances(accountId, startDate, endDate);
    }

    @Override
    @Transactional
    public List<Object[]> getAccountCategories(int accountId) {
        return accountDAO.getAccountCategories(accountId);
    }

    @Override
    @Transactional
    public int updateAccountBalanceById(int accountId, BigDecimal ab) {
        return accountDAO.updateAccountBalanceById(accountId, ab);
    }

    @Override
    @Transactional
    public void delete(Account a) {
        accountDAO.delete(a);
    }

    @Override
    @Transactional
    public void save(Account a) {
        accountDAO.save(a);
    }

    @Override
    @Transactional
    public void update(Account a) {
        accountDAO.update(a);
    }

}
