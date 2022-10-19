use testdb;
drop table if exists User;
CREATE TABLE if not exists User(
    email VARCHAR(50) NOT NULL,
    firstName VARCHAR(10) NOT NULL,
    lastName VARCHAR(10) NOT NULL,
    password VARCHAR(20) NOT NULL,
    birthday DATE NOT NULL,
    address_street_num VARCHAR(4),
    address_street VARCHAR(30),
    address_city VARCHAR(20),
    address_state VARCHAR(2),
    address_zip_code VARCHAR(5),
    cash_bal DECIMAL(13,2) DEFAULT 0,
    PPS_bal DECIMAL(13,2) DEFAULT 0,
    username VARCHAR(20) NOT NULL,
    userid VARCHAR(10) NOT NULL auto_increment,
    PRIMARY KEY (userid)
    insert into User(email, firstName, lastName, password, birthday, address_street_num, address_street, address_city, address_state, address_zip_code, cash_bal, PPS_bal)

)
drop table if exists Addresses;
CREATE TABLE if not exists Addresses(
    address_street_num VARCHAR(4),
    address_street VARCHAR(30),
    address_city VARCHAR(20),
    address_state VARCHAR(2),
    address_zip_code VARCHAR(5),
    FOREIGN KEY(username) REFERENCES User(username)
    address_id VARCHAR(5) NOT NULL auto_increment,
    PRIMARY KEY(address_id)

)
drop table if exists NFT_Ledger;
CREATE TABLE if not exists NFT_Ledger(
    NFT_name VARCHAR(50) NOT NULL,
    price DECIMAL(13,2) DEFAULT 0,
    image FILENAME NOT NULL,
    description VARCHAR(100),
    FOREIGN KEY(creater) REFERENCES USER(username)
    NFTid VARCHAR(5) NOT NULL auto_increment,
    PRIMARY KEY (NFTid)
    
)
