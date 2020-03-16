-- CREATE DATABASE rabbit;
-- GRANT CONNECT ON DATABASE rabbit TO root;
-- GRANT USAGE ON SCHEMA public TO root;
-- GRANT ALL PRIVILEGES ON DATABASE rabbit to root;
-- GRANT ALL PRIVILEGES ON DATABASE rabbit to root;

CREATE TABLE employee(
    employeeName varchar(100) NOT NULL,
    employeeId varchar(11) NOT NULL ,
    employeeAddress varchar(100) DEFAULT NULL,
    employeeEmail varchar(100) DEFAULT NULL,
    PRIMARY KEY (employeeId)
);