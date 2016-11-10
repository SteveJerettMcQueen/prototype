/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service.impl;

import com.sjm.financialapplication.dao.AppUserDAO;
import com.sjm.financialapplication.model.AppUser;
import com.sjm.financialapplication.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sm6668
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserDAO appUserDAO;

    @Override
    @Transactional
    public AppUser getAppUserById(String userID) {
        return appUserDAO.getAppUserById(userID);
    }

    @Override
    @Transactional
    public void update(AppUser au) {
        appUserDAO.update(au);
    }

    @Override
    @Transactional
    public void save(AppUser au) {
        appUserDAO.save(au);
    }

    @Override
    @Transactional
    public boolean validateAppUser(String userID, String password) {
        return appUserDAO.validateAppUser(userID, password);
    }
}
