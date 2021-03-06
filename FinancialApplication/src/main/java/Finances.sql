/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Steve
 * Created: Feb 9, 2016
 */

-- DROP TABLE ACCOUNT_GOAL;
-- DROP TABLE ACCOUNT_TRANSACTION;
-- DROP TABLE ACCOUNT;
-- DROP TABLE ACCOUNTOWNER;
-- DROP TABLE APP_USER;
 
CREATE TABLE APP_USER (
    APP_USER_ID VARCHAR(50) NOT NULL, 
    PASSWORD VARCHAR(30) NOT NULL,
    START_DATE DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY(APP_USER_ID)
);

CREATE TABLE ACCOUNTOWNER (
    APP_USER_ID VARCHAR(50) NOT NULL,
    FIRST_NAME VARCHAR(30) NOT NULL,
    LAST_NAME VARCHAR(30) NOT NULL,
    GENDER CHAR(6) NOT NULL,
    CITY VARCHAR(30),
    STATE_RESIDENCE VARCHAR(4),
    ZIP VARCHAR(30),
    STREET VARCHAR(30),
    PRIMARY KEY(APP_USER_ID)
);

--Treated as a weak entity type
CREATE TABLE ACCOUNT (
    APP_USER_ID VARCHAR(50) NOT NULL,
    ACCOUNT_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    ACCOUNT_NAME VARCHAR(30),
    ACCOUNT_INIT_BAL DECIMAL(7,2),
    ACCOUNT_CUR_BAL DECIMAL(7,2),
    START_DATE DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY(ACCOUNT_ID)
);

ALTER TABLE ACCOUNT
    ADD FOREIGN KEY(APP_USER_ID)
    REFERENCES ACCOUNTOWNER(APP_USER_ID)
    ON DELETE CASCADE;
  
--Treated as a weak entity type
CREATE TABLE ACCOUNT_TRANSACTION (
     ACCOUNT_ID INTEGER NOT NULL,
     TRANS_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
     TRANS_DATE DATE NOT NULL DEFAULT CURRENT_DATE,
     CATEGORY VARCHAR(50),
     DESCRIPTION VARCHAR(100),
     TRANS_TYPE VARCHAR(20) NOT NULL,
     AMOUNT DECIMAL(12, 2) NOT NULL,
     CUR_BALANCE DECIMAL(12, 2),
     ENTRY TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY(TRANS_ID)
 );
  
ALTER TABLE ACCOUNT_TRANSACTION
    ADD FOREIGN KEY(ACCOUNT_ID)
    REFERENCES ACCOUNT(ACCOUNT_ID)
    ON DELETE CASCADE; 
  
--Treated as a weak entity type
CREATE TABLE ACCOUNT_GOAL (
    ACCOUNT_ID INTEGER NOT NULL,
    GOAL_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    GOAL_DATE DATE NOT NULL,
    TARGET_AMOUNT DECIMAL(12, 2),
    MIN_AMOUNT DECIMAL(12, 2),
    PRIMARY KEY(GOAL_ID)
 );
  
ALTER TABLE ACCOUNT_GOAL
    ADD FOREIGN KEY(ACCOUNT_ID)
    REFERENCES ACCOUNT(ACCOUNT_ID)
    ON DELETE CASCADE;

CREATE TABLE CATEGORY (
    CATEGORY_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, 
    CATEGORY_NAME VARCHAR(30) NOT NULL,
    PRIMARY KEY(CATEGORY_ID)
);