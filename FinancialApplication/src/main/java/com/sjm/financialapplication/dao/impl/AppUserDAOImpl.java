/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao.impl;

import com.sjm.financialapplication.dao.AppUserDAO;
import com.sjm.financialapplication.model.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sm6668
 */
@Repository("appUserDAO")
public class AppUserDAOImpl implements AppUserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public AppUser getAppUserById(String userId) {
        return (AppUser) getCurrentSession().get(
                AppUser.class, userId);
    }

    @Override
    public void update(AppUser au) {
        getCurrentSession().update(au);
    }

    @Override
    public void save(AppUser au) {
        getCurrentSession().save(au);
    }
    
    @Override
    public boolean validateAppUser(String userId, String password) {
        AppUser appUser = (AppUser) getCurrentSession().get(AppUser.class, userId);
        if (appUser != null) {
            return appUser.getPassword().equals(password);
        } else {
            return false;
        }
    }
}
