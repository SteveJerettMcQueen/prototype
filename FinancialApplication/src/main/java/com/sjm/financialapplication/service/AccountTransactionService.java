/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.service;

import com.sjm.financialapplication.model.AccountTransaction;
import java.util.List;

/**
 *
 * @author Steve
 */
public interface AccountTransactionService {

    List<AccountTransaction> getAccountTransactionsById(int accountId, int start, int max);

    Number totalAccountTransactions(int accountId);

    void delete(AccountTransaction at);

    void save(AccountTransaction at);

    void update(AccountTransaction at);
}
