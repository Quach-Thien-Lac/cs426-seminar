CREATE DATABASE Sanguosuo;
USE Sanguosuo;

/******************************************
************** FUCKASS ENTITY *************
******************************************/
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

CREATE TABLE HeroFaction (
	hero_faction_id TINYINT AUTO_INCREMENT,
	hero_faction_code VARCHAR(20),
	hero_faction_name VARCHAR(50) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_HeroFaction_hero_faction_id
		PRIMARY KEY (hero_faction_id),
	CONSTRAINT UK_HeroFaction_hero_faction_code
		UNIQUE (hero_faction_code),
    CONSTRAINT CHK_HeroFaction_hero_faction_code_uppercase
        CHECK (CAST(hero_faction_code AS BINARY) = UPPER(hero_faction_code))
);

/******************************************
*************** BASED ENTITY **************
******************************************/
CREATE TABLE HeroSkill (
	skill_id VARCHAR(10),
	skill_name VARCHAR(20) CHARACTER SET UTF8MB4,
	skill_description VARCHAR(1000) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_HeroSkill_skill_id
		PRIMARY KEY (skill_id),

	-- example: SHU009_1
	CONSTRAINT CHK_HeroSkill_skill_id_correct_format
		CHECK (skill_id REGEXP '^[A-Z0-9_]+$')
);

CREATE TABLE RevisionText (
	text_id BINARY(28),
	text_content TEXT,

	CONSTRAINT PK_Text_text_id
		PRIMARY KEY (text_id),

	CONSTRAINT CHK_Text_check_SHA2
		CHECK (SHA2(text_content, 224) = text_id)
);

CREATE TABLE `User` (
	user_id BINARY(8),
	user_name VARCHAR(50) CHARACTER SET UTF8MB4,
	user_email VARCHAR(255) CHARACTER SET UTF8MB4 NOT NULL,
	user_phone BINARY(10) NOT NULL,
	user_status_id TINYINT NOT NULL,
	user_role_id TINYINT NOT NULL,

	CONSTRAINT PK_User_user_id
		PRIMARY KEY (user_id),

	CONSTRAINT UK_User_user_email
		UNIQUE (user_email),
	CONSTRAINT UK_User_user_phone
		UNIQUE (user_phone),

	CONSTRAINT FK_User_user_phone
		FOREIGN KEY (user_status_id) REFERENCES UserStatus (user_status_id),
	CONSTRAINT FK_User_user_email
		FOREIGN KEY (user_role_id) REFERENCES UserRole (user_role_id),
	
	-- user_id must be exactly 8 digits long and does not start with 0
	CONSTRAINT CHK_User_user_id_correct_format
		CHECK (LENGTH(user_id) = 8 AND user_id REGEXP '^[1-9][0-9]*$'),
	-- user_phone must be exactly 10 digits long and start with 0
	CONSTRAINT CHK_User_user_phone_correct_format
		CHECK (LENGTH(user_phone) = 10 AND user_phone REGEXP '^0[0-9]*$'),
	-- you know i am not gonna bother with understanding email regex
	CONSTRAINT CHK_User_user_email_correct_format
		CHECK (user_email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

CREATE TABLE Revision (
	revision_id BINARY(18),
	revision_time DATETIME NOT NULL,
	revision_text_id BINARY(28) NOT NULL,
	revision_author_id BINARY(8) NOT NULL,

	CONSTRAINT PK_Revision_revision_id
		PRIMARY KEY (revision_id),
	
	CONSTRAINT FK_Revision_revision_text_id
		FOREIGN KEY (revision_text_id) REFERENCES RevisionText (text_id),
	CONSTRAINT FK_Revision_revision_author_id
		FOREIGN KEY (revision_author_id) REFERENCES `User` (user_id)
);

CREATE TABLE Article (
	article_id BINARY(6),
	article_title VARCHAR(50) CHARACTER SET UTF8MB4,
	article_rev_id BINARY(18) NOT NULL,

	CONSTRAINT PK_Article_article_id
		PRIMARY KEY (article_id),
	
	CONSTRAINT FK_Article_article_rev_id
		FOREIGN KEY (article_rev_id) REFERENCES Revision (revision_id)
);

CREATE TABLE Image (
	image_id VARCHAR(10),
	image_blob BLOB,

	CONSTRAINT PK_Image_image_id
		PRIMARY KEY (image_id),

	CONSTRAINT CHK_Image_image_id_correct_format
		CHECK (image_id REGEXP '^[A-Z0-9_]+$');
);

CREATE TABLE Hero (
	hero_id VARCHAR(10),
	hero_name VARCHAR(80) CHARACTER SET UTF8MB4,
	hero_image_id VARCHAR(10),
	hero_faction_id TINYINT NOT NULL,
	hero_hp FLOAT(1) NOT NULL,
	hero_epithet VARCHAR(100) CHARACTER SET UTF8MB4,
	hero_quote VARCHAR(200) CHARACTER SET UTF8MB4,
	hero_has_tradeoff BIT NOT NULL,
	hero_skill_1_id VARCHAR(10),
	hero_skill_2_id VARCHAR(10),
	hero_skill_3_id VARCHAR(10),

	CONSTRAINT PK_Hero_hero_id
		PRIMARY KEY (hero_id),

	CONSTRAINT FK_Hero_hero_image_id
		FOREIGN KEY (hero_image_id) REFERENCES Image (image_id),
	CONSTRAINT FK_Hero_hero_faction_id
		FOREIGN KEY (hero_faction_id) REFERENCES HeroFaction (hero_faction_id),
	CONSTRAINT FK_HeroSkill_hero_skill_1_id
		FOREIGN KEY (hero_skill_1_id) REFERENCES HeroSkill (skill_id),
	CONSTRAINT FK_HeroSkill_hero_skill_2_id
		FOREIGN KEY (hero_skill_2_id) REFERENCES HeroSkill (skill_id),
	CONSTRAINT FK_HeroSkill_hero_skill_3_id
		FOREIGN KEY (hero_skill_3_id) REFERENCES HeroSkill (skill_id),

	CONSTRAINT CHK_Hero_hero_id_correct_format
		CHECK (hero_id REGEXP '^[A-Z0-9]+$');
);

/******************************************
************** BRIDGE ENTITIES ************
******************************************/
CREATE TABLE HeroSkillTag (
	skill_id VARCHAR(10),
	skill_tag_id TINYINT,

	CONSTRAINT PK_HeroSkillTag_sid_stid
		PRIMARY KEY (skill_id, skill_tag_id),
	
	CONSTRAINT FK_HeroSkillTag_skill_id
		FOREIGN KEY (skill_id) REFERENCES HeroSkill (skill_id),
	CONSTRAINT FK_HeroSkillTag_skill_tag_id
		FOREIGN KEY (skill_tag_id) REFERENCES SkillTag (skill_tag_id)
);

CREATE TABLE HeroCombo (
	hero_1_id VARCHAR(10),
	hero_2_id VARCHAR(10),

	CONSTRAINT PK_HeroCombo_h1id_h2id
		PRIMARY KEY (hero_1_id, hero_2_id),

	CONSTRAINT FK_HeroCombo_hero_1_id
		FOREIGN KEY (hero_1_id) REFERENCES Hero (hero_id),
	CONSTRAINT FK_HeroCombo_hero_2_id
		FOREIGN KEY (hero_2_id) REFERENCES Hero (hero_id)
);