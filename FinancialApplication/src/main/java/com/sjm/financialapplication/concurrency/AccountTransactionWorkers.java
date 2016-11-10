/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.concurrency;

import com.sjm.financialapplication.model.AccountTransaction;
import com.sjm.financialapplication.main.AppIds;
import com.sjm.financialapplication.service.AccountService;
import com.sjm.financialapplication.service.AccountTransactionService;
import static com.sjm.financialapplication.util.ThreadUtils.process;
import java.math.BigDecimal;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sm6668
 */
@Component
public class AccountTransactionWorkers {

    @Autowired
    private AppIds aids;

    @Autowired
    private AccountService as;

    @Autowired
    private AccountTransactionService ats;

    @Component
    public class AccountTransactionDeleter extends Service<Void> {

        private AccountTransaction at;

        @Autowired
        public AccountTransactionDeleter() {

        }

        public final AccountTransaction getAccountTransaction() {
            return at;
        }

        public final void setAccountTransaction(AccountTransaction at) {
            this.at = at;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() {
                    updateMessage("Deleting account transaction .....");
                    process(250);
                    ats.delete(at);

                    updateMessage("Updating account balance .....");
                    process(250);
                    as.updateAccountBalanceById(aids.getAccountId(), getPrevBal());

                    updateMessage("Account transaction deleted!");
                    updateMessage("Account balance updated!");
                    process(250);
                    return null;
                }

                private BigDecimal getPrevBal() {
                    BigDecimal bal = at.getCurBalance();
                    BigDecimal amount = at.getAmount();
                    int value = amount.compareTo(BigDecimal.ZERO);
                    switch (value) {
                        case 1:
                            return bal.subtract(amount);
                        case -1:
                            return bal.add(amount.multiply(
                                    BigDecimal.valueOf(-1.0)));
                        default:
                            return BigDecimal.ZERO;
                    }
                }

            };
        }

    }

    @Component
    public class AccountTransactionSaver extends Service<Void> {

        private AccountTransaction at;

        @Autowired
        private AccountTransactionSaver() {

        }

        public final AccountTransaction getAccountTransaction() {
            return at;
        }

        public final void setAccountTransaction(AccountTransaction at) {
            this.at = at;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() {
                    updateMessage("Saving account transaction .....");
                    process(250);
                    ats.save(at);

                    updateMessage("Updating account balance .....");
                    process(250);
                    as.updateAccountBalanceById(aids.getAccountId(), at.getCurBalance());

                    updateMessage("Account transaction saved!");
                    updateMessage("Account balance updated!");
                    process(250);
                    return null;
                }

            };
        }

    }

    @Component
    public class AccountTransactionUpdater extends Service<Void> {

        private AccountTransaction at;

        @Autowired
        public AccountTransactionUpdater() {

        }

        public final AccountTransaction getAccountTransaction() {
            return at;
        }

        public final void setAccountTransaction(AccountTransaction at) {
            this.at = at;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() {
                    updateMessage("Updating account transaction .....");
                    process(500);
                    ats.update(at);

                    updateMessage("Account transaction updated!");
                    process(250);
                    return null;
                }
            };
        }

    }

}
