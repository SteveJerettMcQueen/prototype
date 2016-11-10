/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service;

import com.sjm.financialapplication.model.AppUser;


/**
 *
 * @author sm6668
 */
public interface AppUserService {//Same as DAO but for service class

    AppUser getAppUserById(String userID);

    void update(AppUser au);

    void save(AppUser au);
    
    boolean validateAppUser(String userID, String password);
}
