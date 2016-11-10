/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.concurrency;

import com.sjm.financialapplication.model.Account;
import com.sjm.financialapplication.service.AccountService;
import static com.sjm.financialapplication.util.ThreadUtils.process;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sm6668
 */
@Component
public class AccountWorkers {

    @Autowired
    private AccountService as;

    @Component
    public class AccountDeleter extends Service<Void> {

        private Account a;

        @Autowired
        public AccountDeleter() {

        }

        public final Account getAccount() {
            return a;
        }

        public final void setAccount(Account a) {
            this.a = a;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                public Void call() {
                    updateMessage("Deleting account .....");
                    process(500);
                    as.delete(a);

                    updateMessage("Account deleted!");
                    process(250);
                    return null;
                }

            };
        }
    }

    @Component
    public class AccountSaver extends Service<Void> {

        private Account a;

        @Autowired
        public AccountSaver() {

        }

        public final Account getAccount() {
            return a;
        }

        public final void setAccount(Account a) {
            this.a = a;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                public Void call() {
                    updateMessage("Saving account .....");
                    process(500);
                    as.save(a);

                    updateMessage("Account saved!");
                    process(250);
                    return null;

                }

            };
        }

    }

    @Component
    public class AccountUpdater extends Service<Void> {

        private Account a;

        @Autowired
        public AccountUpdater() {

        }

        public final Account getAccount() {
            return a;
        }

        public final void setAccount(Account a) {
            this.a = a;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                public Void call() {
                    updateMessage("Updating account .....");
                    process(500);
                    as.update(a);

                    updateMessage("Account updated!");
                    process(250);
                    return null;
                }

            };
        }

    }

}
