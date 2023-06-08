CREATE TABLE USERS (
  ID INT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  FIRST_NAME VARCHAR(255) NOT NULL,
  LAST_NAME VARCHAR(255) NOT NULL,
  EMAIL VARCHAR(255) NOT NULL UNIQUE,
  BALANCE DECIMAL(10,2) NOT NULL DEFAULT '0.00',
  PASSWORD VARCHAR(255) NOT NULL
);


CREATE TABLE RELATIONS (
  ID_USER INT NOT NULL,
  ID_FRIEND INT NOT NULL,
  PRIMARY KEY (ID_USER,ID_FRIEND),
  FOREIGN KEY (ID_USER) REFERENCES USERS(ID),
  FOREIGN KEY (ID_FRIEND) REFERENCES USERS(ID)
);

CREATE TABLE TRANSACTIONS(
  ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ID_USER_SENDER  INT NOT NULL,
  ID_USER_RECEIVER INT NOT NULL,
  AMOUNT DECIMAL(10,2) NOT NULL,
  DATE DATETIME DEFAULT CURRENT_TIMESTAMP,
  FEE DECIMAL(10,2),
  FOREIGN KEY (ID_USER_SENDER) REFERENCES USERS(ID),
  FOREIGN KEY (ID_USER_RECEIVER) REFERENCES USERS(ID)
);

CREATE TABLE BANK_ACOUNT (
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
ID_USER INT NOT NULL,
AMOUNT DECIMAL (10,2),
IBAN VARCHAR(255) NOT NULL,
FOREIGN KEY (ID_USER) REFERENCES USERS(ID)
);