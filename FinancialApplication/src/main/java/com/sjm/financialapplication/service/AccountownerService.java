/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service;

import com.sjm.financialapplication.model.Accountowner;
import java.util.List;

/**
 *
 * @author sm6668
 */
public interface AccountownerService {

    Accountowner getAccountownerById(String userId);

    List<Object[]> getAggBalance(String userId);

    Number getAggTotal(String userId);

    int updateAccountownerId(String userId, String nv);

    void update(Accountowner a);

    void save(Accountowner a);
}
