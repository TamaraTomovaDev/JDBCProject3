CREATE DATABASE accountdb;
USE accountdb;

CREATE TABLE account(
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE userdetail(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    email VARCHAR(100),
    accountUsername VARCHAR(50),
    FOREIGN KEY (accountUsername) REFERENCES account(username) ON DELETE CASCADE
);