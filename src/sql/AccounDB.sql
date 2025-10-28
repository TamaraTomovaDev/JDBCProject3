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
    FOREIGN KEY (accountUsername) REFERENCES account(username)
);

-- Vul de account-tabel
INSERT INTO account (username, password) VALUES
('alice', 'pass123'),
('bob', 'secure456'),
('carol', 'mypassword'),
('dave', 'abc123'),
('eve', 'qwerty'),
('frank', 'letmein'),
('grace', 'password1'),
('heidi', 'trustno1'),
('ivan', '123456'),
('judy', 'adminpass');

-- Vul de userdetail-tabel
INSERT INTO userdetail (firstname, lastname, email, accountUsername) VALUES
('Alice', 'Anderson', 'alice@example.com', 'alice'),
('Bob', 'Brown', 'bob@example.com', 'bob'),
('Carol', 'Clark', 'carol@example.com', 'carol'),
('Dave', 'Davis', 'dave@example.com', 'dave'),
('Eve', 'Evans', 'eve@example.com', 'eve'),
('Frank', 'Foster', 'frank@example.com', 'frank'),
('Grace', 'Green', 'grace@example.com', 'grace'),
('Heidi', 'Hill', 'heidi@example.com', 'heidi'),
('Ivan', 'Iverson', 'ivan@example.com', 'ivan'),
('Judy', 'Jones', 'judy@example.com', 'judy');
