
-- MYSQL
create user 'reporting'@'localhost' identified by 'reporting';
GRANT ALL PRIVILEGES ON *.* TO 'reporting'@'localhost'  WITH GRANT OPTION;
CREATE DATABASE reporting;

-------------------------------
-- ORACLE

-- windows
CREATE TABLESPACE reporting
   DATAFILE 'D:\oracle\app\oradata\orcl\reporting.dbf' SIZE 100M REUSE
   AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED;
   
CREATE TEMPORARY TABLESPACE reportingtemp
  TEMPFILE 'D:\oracle\app\oradata\orcl\reportingtemp.dbf'
    SIZE 64M
    AUTOEXTEND ON
    MAXSIZE UNLIMITED;

-- linux
CREATE TABLESPACE reporting
   DATAFILE '/opt/oracle/oradata/orcl/reporting.dbf' SIZE 100M REUSE
   AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED;
   
CREATE TEMPORARY TABLESPACE reportingtemp
  TEMPFILE '/opt/oracle/oradata/orcl/reportingtemp.dbf'
    SIZE 64M
    AUTOEXTEND ON
    MAXSIZE UNLIMITED;
	
-- user
CREATE USER reporting
  IDENTIFIED BY reporting
  DEFAULT TABLESPACE reporting
  TEMPORARY TABLESPACE reportingtemp;
  
GRANT all privileges to reporting;