CREATE DATABASE Sanguosuo;
USE Sanguosuo;

CREATE SCHEMA lookup_table;

CREATE TABLE lookup_table.UserStatus (
	user_status_id TINYINT AUTO_INCREMENT,
	user_status_code VARCHAR(20),
	user_status_name VARCHAR(50) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_UserStatus_user_status_id
		PRIMARY KEY (user_status_id),
	CONSTRAINT UK_UserStatus_user_status_code
		UNIQUE (user_status_code),
    CONSTRAINT CHK_UserStatus_user_status_code_uppercase
        CHECK (CAST(user_status_code AS BINARY) = UPPER(user_status_code))
);