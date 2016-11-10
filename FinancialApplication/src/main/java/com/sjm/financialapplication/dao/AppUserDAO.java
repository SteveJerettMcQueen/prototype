/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao;

import com.sjm.financialapplication.model.AppUser;

/**
 *
 * @author sm6668
 */
public interface AppUserDAO {

    AppUser getAppUserById(String userId);
    
    void update(AppUser au);

    void save(AppUser au);
        
    boolean validateAppUser(String userId, String password);
    
    
}
