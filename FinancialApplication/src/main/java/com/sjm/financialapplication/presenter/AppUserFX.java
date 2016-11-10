/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

import com.sjm.financialapplication.model.AppUser;
import static com.sjm.financialapplication.util.TimeUtils.defaultFormat;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Steve
 */
public class AppUserFX {

    private AppUser au;
    private final SimpleStringProperty appUserId, password, startDate;

    public AppUserFX() {
        appUserId = new SimpleStringProperty();
        password = new SimpleStringProperty();
        startDate = new SimpleStringProperty();
    }

    public AppUser getAppUser() {
        return au;
    }

    public void setAppUser(AppUser au) {
        this.au = au;
    }

    public SimpleStringProperty appUserIdProperty() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId.set(appUserId);
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public SimpleStringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public void setAppUserInfo(AppUser au) {
        setAppUser(au);
        setAppUserId(au.getAppUserId());
        setPassword(au.getPassword());
        setStartDate(defaultFormat(au.getStartDate()));
    }

}
