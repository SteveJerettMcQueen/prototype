/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.concurrency;

import com.sjm.financialapplication.model.AppUser;
import com.sjm.financialapplication.main.AppIds;
import com.sjm.financialapplication.service.AccountownerService;
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
public class AppUserWorkers {

    @Autowired
    private AppUserService aus;

    @Component
    public class AppUserUpdater extends Service<Void> {

        private AppUser au;

        @Autowired
        public AppUserUpdater() {
        }

        public final AppUser getAppUser() {
            return au;
        }

        public final void setAppUser(AppUser au) {
            this.au = au;
        }

        @Override
        protected Task createTask() {
            return new Task<Void>() {

                @Override
                public Void call() {
                    updateMessage("Updating changes .....");
                    process(200);
                    aus.update(au);
                    //TODO...
                    //Change userId in accountowner relation
                    updateMessage("Changes updated!");
                    return null;
                }

            };
        }

    }

}
