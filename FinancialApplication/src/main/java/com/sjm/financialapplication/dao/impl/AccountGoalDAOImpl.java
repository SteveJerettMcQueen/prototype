/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao.impl;

import com.sjm.financialapplication.dao.AccountGoalDAO;
import com.sjm.financialapplication.model.AccountGoal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Steve
 */
@Repository("accountGoalDAO")
public class AccountGoalDAOImpl implements AccountGoalDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<AccountGoal> getAccountGoalsById(int accountId, int start, int max) {
        Criteria criteria = getCurrentSession().createCriteria(AccountGoal.class);
        criteria.add(Restrictions.eq("account.accountId", accountId));
        criteria.setFirstResult(start);
        criteria.setMaxResults(max);
        return criteria.list();

    }

    @Override
    public void delete(AccountGoal ag) {
        getCurrentSession().delete(ag);
    }

    @Override
    public void save(AccountGoal ag) {
        getCurrentSession().save(ag);
    }

    @Override
    public void update(AccountGoal ag) {
        getCurrentSession().update(ag);
    }

}
