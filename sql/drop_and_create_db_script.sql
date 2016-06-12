SET foreign_key_checks = 0;

drop table if exists users;
CREATE  TABLE users (
  username VARCHAR(45) NOT NULL,
  password VARCHAR(60) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  enabled BIT NOT NULL DEFAULT 1,
  last_login DATETIME,
  PRIMARY KEY (username));

drop table if exists user_roles;
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

drop table if exists user_details;
CREATE TABLE user_details (
  username varchar(45) NOT NULL,
  given_name varchar(35) NOT NULL,
  family_name varchar(35) NOT NULL,
  date_of_birth DATE NOT NULL,
  address varchar(200) NOT NULL,
  phone varchar(30) NOT NULL,
  email varchar(255) NOT NULL,
  PRIMARY KEY (username),
  CONSTRAINT FOREIGN KEY (username) REFERENCES users (username));

drop table if exists accounts;
CREATE TABLE accounts (
  account_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  balance DECIMAL(13, 4) NOT NULL DEFAULT 0,
  PRIMARY KEY (account_id),
  KEY (username),
  CONSTRAINT FOREIGN KEY (username) REFERENCES users (username));

drop table if exists transactions;
CREATE TABLE transactions (
  transaction_id int(11) NOT NULL AUTO_INCREMENT,
  sender_account_id int(11) NOT NULL,
  recipient_account_id int(11) NOT NULL,
  transaction_time DATETIME NOT NULL,
  amount int(11) NOT NULL,
  PRIMARY KEY (transaction_id),
  CONSTRAINT FOREIGN KEY (sender_account_id) REFERENCES accounts (account_id),
  CONSTRAINT FOREIGN KEY (recipient_account_id) REFERENCES accounts (account_id));

SET foreign_key_checks = 1;
