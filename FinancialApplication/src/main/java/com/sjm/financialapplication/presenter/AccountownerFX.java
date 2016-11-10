/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

import com.sjm.financialapplication.model.Accountowner;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Steve
 */
public class AccountownerFX {

    private Accountowner accountowner;
    private final SimpleStringProperty firstName, lastName, gender, city,
            stateResidence, zip, street, numOfAccounts;

    public AccountownerFX() {
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        gender = new SimpleStringProperty("");
        city = new SimpleStringProperty("");
        stateResidence = new SimpleStringProperty("");
        zip = new SimpleStringProperty("");
        street = new SimpleStringProperty("");
        numOfAccounts = new SimpleStringProperty("");
    }

    public Accountowner getAccountowner() {
        return accountowner;
    }

    public void setAccountowner(Accountowner ao) {
        accountowner = ao;
    }

    public SimpleStringProperty getFirstNameProperty() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public SimpleStringProperty getLastNameProperty() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public SimpleStringProperty getGenderProperty() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public SimpleStringProperty getCityProperty() {
        return this.city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public SimpleStringProperty getStateResidenceProperty() {
        return this.stateResidence;
    }

    public void setStateResidence(String stateResidence) {
        this.stateResidence.set(stateResidence);
    }

    public SimpleStringProperty getZipProperty() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip.set(zip);
    }

    public SimpleStringProperty getStreetProperty() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public SimpleStringProperty getNumOfAccountsProperty() {
        return this.numOfAccounts;
    }

    public void setNumOfAccounts(String numOfAccounts) {
        this.numOfAccounts.set(numOfAccounts);
    }

    public void setAccountownerInfo(Accountowner ao) {
        setAccountowner(ao);
        setFirstName(ao.getFirstName());
        setLastName(ao.getLastName());
        setGender(ao.getGender());
        setCity(ao.getCity());
        setStateResidence(ao.getStateResidence());
        setZip(ao.getZip());
        setStreet(ao.getStreet());
    }
}
