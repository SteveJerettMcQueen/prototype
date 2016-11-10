/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao.impl;

import com.sjm.financialapplication.dao.AccountTransactionDAO;
import com.sjm.financialapplication.model.AccountTransaction;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Steve
 */
@Repository("accountTransactionDAO")
public class AccountTransactionDAOImpl implements AccountTransactionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<AccountTransaction> getAccountTransactionsById(int accountId, int start, int max) {
        Criteria crit = getCurrentSession().createCriteria(AccountTransaction.class);
        crit.add(Restrictions.eq("account.accountId", accountId));
        crit.addOrder(Order.desc("entry"));
        crit.setFirstResult(start);
        crit.setMaxResults(max);
        return crit.list();
    }

    @Override
    public Number totalAccountTransactions(int accountId) {
        Criteria crit = getCurrentSession().createCriteria(AccountTransaction.class);
        crit.add(Restrictions.eq("account.accountId", accountId));
        crit.setProjection(Projections.rowCount());
        return (Number) crit.uniqueResult();
    }

    @Override
    public void delete(AccountTransaction at) {
        getCurrentSession().delete(at);
    }

    @Override
    public void save(AccountTransaction at) {
        getCurrentSession().save(at);
    }

    @Override
    public void update(AccountTransaction at) {
        getCurrentSession().update(at);
    }
}
