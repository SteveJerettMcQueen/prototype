/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao.impl;

import com.sjm.financialapplication.dao.CategoryDAO;
import com.sjm.financialapplication.presenter.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sm6668
 */
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void delete(Category cat) {
        getCurrentSession().delete(cat);
    }

    @Override
    public void update(Category cat) {
        getCurrentSession().update(cat);
    }

    @Override
    public void save(Category cat) {
        getCurrentSession().save(cat);
    }

}
