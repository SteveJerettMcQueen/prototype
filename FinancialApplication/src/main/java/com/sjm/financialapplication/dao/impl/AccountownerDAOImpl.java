/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao.impl;

import com.sjm.financialapplication.dao.AccountownerDAO;
import com.sjm.financialapplication.model.Accountowner;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sm6668
 */
@Repository("accountownerDAO")
public class AccountownerDAOImpl implements AccountownerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Accountowner getAccountownerById(String userId) {
        return (Accountowner) getCurrentSession().get(Accountowner.class, userId);
    }

    @Override
    public List<Object[]> getAggBalance(String userId) {
        Query query = getCurrentSession().getNamedQuery("GetAggBalance");
        query.setParameter("appUserId", userId);
        return (List<Object[]>) query.list();

    }

    @Override
    public Number getAggTotal(String userId) {
        Query query = getCurrentSession().getNamedQuery("GetAggTotal");
        query.setParameter("appUserId", userId);
        return (Number) query.uniqueResult();
    }

    @Override
    public int updateAccountownerId(String userId, String nv) {
        Query query = getCurrentSession().getNamedQuery("UpdateAccountownerId");
        query.setParameter("appUserId", userId);
        query.setParameter("nv", nv);
        return query.executeUpdate();
    }

    @Override
    public void update(Accountowner ao) {
        getCurrentSession().update(ao);
    }

    @Override
    public void save(Accountowner ao) {
        getCurrentSession().save(ao);
    }

}
