/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.concurrency;

import com.sjm.financialapplication.main.AppIds;
import com.sjm.financialapplication.service.AppUserService;
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
public class AccountownerWorkers {

    @Autowired
    private AppIds aids;

    @Autowired
    private AppUserService aus;

    @Component
    public class AccountownerLoginService extends Service<Boolean> {

        private String userId, password;

        @Autowired
        public AccountownerLoginService() {
        }

        public final String getUserId() {
            return userId;
        }

        public final void setUserId(String userId) {
            this.userId = userId;
        }

        public final String getPassword() {
            return password;
        }

        public final void setPassword(String password) {
            this.password = password;
        }

        @Override
        protected Task createTask() {
            return new Task<Boolean>() {

                @Override
                protected Boolean call() {
                    updateMessage("Validating .....");
                    process(700);
                    if (aus.validateAppUser(userId, password)) {
                        aids.setUserId(userId);//Set user id for later globally
                        return true;
                    } else {
                        updateMessage("Incorrect username or password");
                        process(250);
                        return false;
                    }

                }
            };
        }

    }

}
