/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.concurrency;

import com.sjm.financialapplication.model.AccountGoal;
import com.sjm.financialapplication.service.AccountGoalService;
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
public class AccountGoalWorkers {

    @Autowired
    private AccountGoalService ags;

    @Component
    public class AccountGoalDeleter extends Service<Void> {

        private AccountGoal ag;

        @Autowired
        public AccountGoalDeleter() {

        }

        public final AccountGoal getAccountGoal() {
            return ag;
        }

        public final void setAccountGoal(AccountGoal ag) {
            this.ag = ag;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() {
                    updateMessage("Deleting account goal .....");
                    process(500);
                    ags.delete(ag);

                    updateMessage("Account goal deleted!");
                    process(250);
                    return null;
                }
            };
        }

    }

    @Component
    public class AccountGoalSaver extends Service<Void> {

        private AccountGoal ag;

        @Autowired
        public AccountGoalSaver() {

        }

        public final AccountGoal getAccountGoal() {
            return ag;
        }

        public final void setAccountGoal(AccountGoal ag) {
            this.ag = ag;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() {
                    updateMessage("Saving account goal .....");
                    process(500);
                    ags.save(ag);

                    updateMessage("Account goal saved!");
                    process(250);
                    return null;
                }
            };
        }

    }

    @Component
    public class AccountGoalUpdater extends Service<Void> {

        private AccountGoal ag;

        @Autowired
        public AccountGoalUpdater() {

        }

        public final AccountGoal getAccountGoal() {
            return ag;
        }

        public final void setAccountGoal(AccountGoal ag) {
            this.ag = ag;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() {
                    updateMessage("Updating account goal .....");
                    process(500);
                    ags.update(ag);

                    updateMessage("Account goal updated!");
                    process(250);
                    return null;
                }
            };
        }

    }
}
