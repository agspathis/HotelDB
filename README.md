Hotel Database Manager
======================

Jim Stanev

Aristotelis Spathis

Description
===========

Language: Java

Database server: Mysql-server

Program with gui and database support.

* Make reservation
* Check for free rooms
* Check out
* Check in
* Payment
* Statistics
* Query engine

Installation
============

In order to work you need to install:
Mysql server (mysql-server)

After installation some configurations must be made from terminal.

1)login into mysql

		mysql -u root -p

2)create create schema

		database hotelDB; 

3)load sql file

		source hotelDB.sql;

if everything is ok you can check the database 

		show databases;

4)make user and grant privileges

		CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
		USE hotelDB;
		GRANT ALL ON hotelDB.* TO 'admin'@'localhost';

5)If you follow the instructions with user admin and password admin you will
not need to change database.properties file.

6)run 

		java -jar HotelDB.jar

Dependencies
============

Java library dependencies:
MySQL Connector/J driver

Database
========

The database tables are:

* Customer
* Room
* Rental
* Discount
* Offer
* Service
* Trade

![Schema](https://github.com/mitkof6/HotelDB/blob/master/db_schema.png)
