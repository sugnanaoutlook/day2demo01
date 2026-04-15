-- Run this script once to prepare the database

CREATE DATABASE IF NOT EXISTS test01;

USE test01;

CREATE TABLE IF NOT EXISTS employees (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100)   NOT NULL,
    department VARCHAR(100)   NOT NULL,
    salary     DECIMAL(10, 2) NOT NULL
);
