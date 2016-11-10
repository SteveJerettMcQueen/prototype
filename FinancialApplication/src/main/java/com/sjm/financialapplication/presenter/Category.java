/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.presenter;

/**
 *
 * @author sm6668
 */
public enum Category {

    AUTOMOTIVE("Automotive"),
    BILLS_UTILITIES("Bills & Utilities"),
    BUSINESS_SERVICES("Business Services"),
    CLOTHING("Clothing"),
    DEBT("Debt"),
    EDUCATION("Education"),
    ELECTRONICS("Electronics"),
    ENTERTAINMENT("Entertainment"),
    FEES_CHARGES("Fees & Charges"),
    FINANCIAL("Financial"),
    FOOD_DINING("Food & Dining"),
    GIFTS_DONATIONS("Gifts & Donations"),
    HEALTH_FITNESS("Health & Fitness"),
    HOME("Home"),
    INCOME("Income"),
    KIDS("Kids"),
    MEDICAL("Medical"),
    MISCELLANEOUS("Miscellaneous"),
    PERSONAL_CARE("Personal Care"),
    PETS("Pets"),
    TAXES("Taxes"),
    TRANSFER("Transfer"),
    TRANSPORTATION("Transportation"),
    TRAVEL("Travel"),
    UNCATEGORIZED("Uncategorized");

    private final String s;

    private Category(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

}
