/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service.impl;

import com.sjm.financialapplication.dao.AccountGoalDAO;
import com.sjm.financialapplication.model.AccountGoal;
import com.sjm.financialapplication.service.AccountGoalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Steve
 */
@Service
public class AccountGoalServiceImpl implements AccountGoalService {

    @Autowired
    private AccountGoalDAO accountGoalDAO;

    @Override
    @Transactional
    public List<AccountGoal> getAccountGoalsById(int accountId, int start, int max) {
        return accountGoalDAO.getAccountGoalsById(accountId, start, max);
    }

    @Override
    @Transactional
    public void delete(AccountGoal ag) {
        accountGoalDAO.delete(ag);
    }

    @Override
    @Transactional
    public void save(AccountGoal ag) {
        accountGoalDAO.save(ag);
    }

    @Override
    @Transactional
    public void update(AccountGoal ag) {
        accountGoalDAO.update(ag);
    }

}
