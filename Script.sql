

create database testdb;
use testdb;

drop table if exists User;
CREATE TABLE if not exists User(
    email VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL,
    address_street_num VARCHAR(4),
    address_street VARCHAR(30),
    address_city VARCHAR(20),
    address_state VARCHAR(2),
    address_zip_code VARCHAR(5),
    cash_bal DECIMAL(13,2) DEFAULT 0,
    PPS_bal DECIMAL(13,2) DEFAULT 0,
    userid int NOT NULL AUTO_INCREMENT, 
    PRIMARY KEY (userid)
  );
    
drop table if exists Addresses;
 CREATE TABLE if not exists Addresses(
    address_street_num VARCHAR(4),
    address_street VARCHAR(30),
    address_city VARCHAR(20),
    address_state VARCHAR(2),
    address_zip_code VARCHAR(5),
    username VARCHAR(50) NOT NULL,
    userid int NOT NULL,
    CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES User(userid),
    address_id int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(address_id)
);

drop table if exists NFT_Ledger;
CREATE TABLE if not exists NFT_Ledger(
    nftName VARCHAR(150) NOT NULL,
    listingPrice DECIMAL(13,2) DEFAULT 0,
    listingTime VARCHAR(100),
    uploadNFT varchar(500) NOT NULL,
    nftDescription VARCHAR(100),
    nftOwner VARCHAR(50) NOT NULL,
    NFTid int NOT NULL auto_increment,
    PRIMARY KEY (NFTid)
    );

    
drop table if exists Transaction_History;
CREATE TABLE if not exists Transaction_History(
	NFTid int NOT NULL,
    CONSTRAINT fk_TH_NFTId FOREIGN KEY(NFTid) REFERENCES NFT_Ledger(NFTid),
    creator_id VARCHAR(80),
    customer_id VARCHAR(80),
    transaction_date DATETIME,
    transactionId int NOT NULL auto_increment,
    PRIMARY KEY (transactionId)
    );
    
drop table if exists Transfer_History;
CREATE TABLE if not exists Transfer_History(
    NFTid int NOT NULL,
    CONSTRAINT fk_TrH_NFTId FOREIGN KEY(NFTid) REFERENCES NFT_Ledger(NFTid),
    current_owner VARCHAR(60),
    previous_owner VARCHAR(60),
    transfer_date DATETIME,
    transferId int NOT NULL auto_increment,
    PRIMARY KEY (transferId)
)
