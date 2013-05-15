Hotel Database Manager

STANEV DIMITAR
SPATHIS ARISTOTELIS

Description
-----------

Language: Java
Database server: Mysql-server

Program with gui and database support.

+make reservation
+check for free rooms
+check out
+check in
+payment
+statistics
+query engine

Installation
-----------

In order to work you need to install:
Mysql server (mysql-server)

After installation some configurations must be made from terminal.

1)login mysql -u root -p

2)create database hotelDB; (create schema)

3)source hotelDB.sql; (load sql file)

if everything is ok you can check the database (show databases;)

4)make user and grant privileges

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
USE hotelDB;
GRANT ALL ON hotelDB.* TO 'admin'@'localhost';

5)If you follow the instructions with user admin and password admin you will
not need to change database.properties file.

6)run java -jar HotelDB.jar

Dependencies
------------

Java library dependencies:
MySQL Connector/J driver

Database
--------

The database tables are:

-Customer
-Room
-Rental
-Discount
-Offer
-Service
-Trade
