/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao.impl;

import com.sjm.financialapplication.dao.AccountDAO;
import com.sjm.financialapplication.model.Account;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Steve
 */
@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Query getNamedQuery(int accountId, String name) {
        return getCurrentSession().getNamedQuery(name)
                .setParameter("accountId", accountId);
    }

    @Override
    public Account getAccountById(int accountId) {
        return (Account) getCurrentSession().get(Account.class, accountId);
    }

    @Override
    public List<Object[]> findCredits(int accountId) {
        return (List<Object[]>) getNamedQuery(
                accountId, "findCredits").list();
    }

    @Override
    public Number sumCredits(int accountId) {
        return (Number) getNamedQuery(
                accountId, "sumCredits").uniqueResult();
    }

    @Override
    public Number avgCredits(int accountId) {
        return (Number) getNamedQuery(
                accountId, "avgCredits").uniqueResult();
    }

    @Override
    public List<Object[]> findDebits(int accountId) {
        return (List<Object[]>) getNamedQuery(
                accountId, "findDebits").list();
    }

    @Override
    public Number sumDebits(int accountId) {
        return (Number) getNamedQuery(
                accountId, "sumDebits").uniqueResult();
    }

    @Override
    public Number avgDebits(int accountId) {
        return (Number) getNamedQuery(
                accountId, "avgDebits").uniqueResult();
    }

    @Override
    public List<Object[]> getAccountAmounts(int accountId, Date startDate, Date endDate) {
        Query query = getNamedQuery(accountId, "GetAccountAmounts");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getAccountBalances(int accountId, Date startDate, Date endDate) {
        Query query = getNamedQuery(accountId, "getAccountBalances");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getAccountCategories(int accountId) {
        Query query = getNamedQuery(accountId, "GetAccountCategories");
        return (List<Object[]>) query.list();
    }

    @Override
    public int updateAccountBalanceById(int accountId, BigDecimal accountCurBal) {
        Query query = getCurrentSession().getNamedQuery("UpdateAccountBalance");
        query.setParameter("accountCurBal", accountCurBal);
        query.setParameter("accountId", accountId);
        return query.executeUpdate();

    }

    @Override
    public void delete(Account a) {
        getCurrentSession().delete(a);

    }

    @Override
    public void save(Account a) {
        getCurrentSession().save(a);

    }

    @Override
    public void update(Account a) {
        getCurrentSession().update(a);
    }
}
