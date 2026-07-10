CREATE DATABASE Sanguosuo;
USE Sanguosuo;

CREATE TABLE UserStatus (
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

CREATE TABLE UserRole (
	user_role_id TINYINT AUTO_INCREMENT,
	user_role_code VARCHAR(20),
	user_role_name VARCHAR(50) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_UserRole_user_role_id
		PRIMARY KEY (user_role_id),
	CONSTRAINT UK_UserRole_user_role_code
		UNIQUE (user_role_code),
    CONSTRAINT CHK_UserRole_user_role_code_uppercase
        CHECK (CAST(user_role_code AS BINARY) = UPPER(user_role_code))
);

CREATE TABLE SkillTag (
	skill_tag_id TINYINT AUTO_INCREMENT,
	skill_tag_code VARCHAR(20),
	skill_tag_name VARCHAR(50) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_SkillTag_skill_tag_id
		PRIMARY KEY (skill_tag_id),
	CONSTRAINT UK_SkillTag_skill_tag_code
		UNIQUE (skill_tag_code),
    CONSTRAINT CHK_SkillTag_skill_tag_code_uppercase
        CHECK (CAST(skill_tag_code AS BINARY) = UPPER(skill_tag_code))
);