/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service.impl;

import com.sjm.financialapplication.dao.AccountownerDAO;
import com.sjm.financialapplication.model.Accountowner;
import com.sjm.financialapplication.service.AccountownerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sm6668
 */
@Service
public class AccountownerServiceImpl implements AccountownerService {

    @Autowired
    private AccountownerDAO accountownerDAO;

    @Override
    @Transactional
    public Accountowner getAccountownerById(String userId) {
        return accountownerDAO.getAccountownerById(userId);
    }

    @Override
    @Transactional
    public List<Object[]> getAggBalance(String userId) {
        return accountownerDAO.getAggBalance(userId);
    }

    @Override
    @Transactional
    public Number getAggTotal(String userId) {
        return accountownerDAO.getAggTotal(userId);
    }

    @Override
    @Transactional
    public int updateAccountownerId(String userId, String nv) {
        return accountownerDAO.updateAccountownerId(userId, nv);
    }

    @Override
    @Transactional
    public void update(Accountowner a) {
        accountownerDAO.update(a);
    }

    @Override
    @Transactional
    public void save(Accountowner a) {
        accountownerDAO.save(a);
    }

}
