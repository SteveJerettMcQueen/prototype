package com.sjm.financialapplication.model;
// Generated Jul 26, 2016 6:39:04 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * AccountGoal generated by hbm2java
 */
public class AccountGoal  implements java.io.Serializable {


     private int goalId;
     private Date goalDate;
     private BigDecimal targetAmount;
     private BigDecimal minAmount;
     private Account account;

    public AccountGoal() {
    }

	
    public AccountGoal(Date goalDate, Account account) {
        this.goalDate = goalDate;
        this.account = account;
    }
    public AccountGoal(Date goalDate, BigDecimal targetAmount, BigDecimal minAmount, Account account) {
       this.goalDate = goalDate;
       this.targetAmount = targetAmount;
       this.minAmount = minAmount;
       this.account = account;
    }
   
    public int getGoalId() {
        return this.goalId;
    }
    
    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }
    public Date getGoalDate() {
        return this.goalDate;
    }
    
    public void setGoalDate(Date goalDate) {
        this.goalDate = goalDate;
    }
    public BigDecimal getTargetAmount() {
        return this.targetAmount;
    }
    
    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }
    public BigDecimal getMinAmount() {
        return this.minAmount;
    }
    
    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }
    public Account getAccount() {
        return this.account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }




}


