/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.dao;

import com.sjm.financialapplication.model.AccountGoal;
import java.util.List;

/**
 *
 * @author Steve
 */
public interface AccountGoalDAO {

    List<AccountGoal> getAccountGoalsById(int accountId, int start, int max);

    void delete(AccountGoal ag);

    void save(AccountGoal ag);

    void update(AccountGoal ag);

}
