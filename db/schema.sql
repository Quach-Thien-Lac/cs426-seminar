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

CREATE TABLE CardType (
	card_type_id TINYINT AUTO_INCREMENT,
	card_type_code VARCHAR(20),
	card_type_name VARCHAR(50) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_CardType_card_type_id
		PRIMARY KEY (card_type_id),
	CONSTRAINT UK_CardType_card_type_code
		UNIQUE (card_type_code),
    CONSTRAINT CHK_CardType_card_type_code_uppercase
        CHECK (CAST(card_type_code AS BINARY) = UPPER(card_type_code))
);

CREATE TABLE CardSuit (
	card_suit_id TINYINT AUTO_INCREMENT,
	card_suit_code VARCHAR(20),
	card_suit_name VARCHAR(50) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_CardSuit_card_suit_id
		PRIMARY KEY (card_suit_id),
	CONSTRAINT UK_CardSuit_card_suit_code
		UNIQUE (card_suit_code),
    CONSTRAINT CHK_CardSuit_card_suit_code_uppercase
        CHECK (CAST(card_suit_code AS BINARY) = UPPER(card_suit_code))
);

CREATE TABLE HeroGender (
	hero_gender_id TINYINT AUTO_INCREMENT,
	hero_gender_code VARCHAR(20),
	hero_gender_name VARCHAR(50) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_HeroGender_hero_gender_id
		PRIMARY KEY (hero_gender_id),
	CONSTRAINT UK_HeroGender_hero_gender_code
		UNIQUE (hero_gender_code),
    CONSTRAINT CHK_HeroGender_hero_gender_code_uppercase
        CHECK (CAST(hero_gender_code AS BINARY) = UPPER(hero_gender_code))
);

CREATE TABLE HeroRole (
	hero_role_id TINYINT AUTO_INCREMENT,
	hero_role_code VARCHAR(20),
	hero_role_name VARCHAR(50) CHARACTER SET UTF8MB4,

	CONSTRAINT PK_HeroRole_hero_role_id
		PRIMARY KEY (hero_role_id),
	CONSTRAINT UK_HeroRole_hero_role_code
		UNIQUE (hero_role_code),
    CONSTRAINT CHK_HeroRole_hero_role_code_uppercase
        CHECK (CAST(hero_role_code AS BINARY) = UPPER(hero_role_code))
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
	text_id CHAR(28),
	text_content TEXT,

	CONSTRAINT PK_Text_text_id
		PRIMARY KEY (text_id),

	CONSTRAINT CHK_Text_check_SHA2
		CHECK (SHA2(text_content, 224) = text_id)
);

CREATE TABLE `User` (
	user_id CHAR(8),
	user_name VARCHAR(50) CHARACTER SET UTF8MB4,
	user_email VARCHAR(255) CHARACTER SET UTF8MB4 NOT NULL,
	user_phone CHAR(10) NOT NULL,
	user_username VARCHAR(20) NOT NULL,
	user_password_hash CHAR(60) NOT NULL,
	user_registration_time DATETIME NOT NULL,
	user_login_time DATETIME,
	user_status_id TINYINT NOT NULL,
	user_role_id TINYINT NOT NULL,

	CONSTRAINT PK_User_user_id
		PRIMARY KEY (user_id),

	CONSTRAINT UK_User_user_email
		UNIQUE (user_email),
	CONSTRAINT UK_User_user_phone
		UNIQUE (user_phone),
	CONSTRAINT UK_User_user_username
		UNIQUE (user_username),

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
		CHECK (user_email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
	-- user_password_hash must follow bcrypt hash format
	CONSTRAINT CHK_User_user_password_hash_correct_format
		CHECK (user_password_hash REGEXP '^\\$2[abxy]\\$(0[4-9]|[12][0-9]|3[01])\\$[./A-Za-z0-9]{53}$'),
	-- user_name must only be alphanumeric with underscore
	CONSTRAINT CHK_User_user_username_correct_format
		CHECK (user_username REGEXP '^[0-9a-zA-Z_]*$')
	
);

CREATE TABLE Revision (
	revision_id CHAR(18),
	revision_time DATETIME NOT NULL,
	revision_text_id CHAR(28) NOT NULL,
	revision_author_id CHAR(8) NOT NULL,

	CONSTRAINT PK_Revision_revision_id
		PRIMARY KEY (revision_id),
	
	CONSTRAINT FK_Revision_revision_text_id
		FOREIGN KEY (revision_text_id) REFERENCES RevisionText (text_id),
	CONSTRAINT FK_Revision_revision_author_id
		FOREIGN KEY (revision_author_id) REFERENCES `User` (user_id)
);

CREATE TABLE Article (
	article_id CHAR(6),
	article_title VARCHAR(50) CHARACTER SET UTF8MB4,
	article_rev_id CHAR(18) NOT NULL,

	CONSTRAINT PK_Article_article_id
		PRIMARY KEY (article_id),
	
	CONSTRAINT FK_Article_article_rev_id
		FOREIGN KEY (article_rev_id) REFERENCES Revision (revision_id)
);

CREATE TABLE Image (
	image_id VARCHAR(10),
	image_directory VARCHAR(255) NOT NULL,

	CONSTRAINT PK_Image_image_id
		PRIMARY KEY (image_id),

	CONSTRAINT CHK_Image_image_id_correct_format
		CHECK (image_id REGEXP '^[A-Z0-9_]+$')
);

CREATE TABLE Hero (
	hero_id VARCHAR(10),
	hero_name VARCHAR(80) CHARACTER SET UTF8MB4,
	hero_gender_id TINYINT NOT NULL,
	hero_image_id VARCHAR(10),
	hero_hp FLOAT(1) NOT NULL,
	hero_epithet VARCHAR(100) CHARACTER SET UTF8MB4,
	hero_quote VARCHAR(200) CHARACTER SET UTF8MB4,
	hero_has_tradeoff BIT NOT NULL,
	hero_complexity TINYINT,
	hero_skill_1_id VARCHAR(10),
	hero_skill_2_id VARCHAR(10),
	hero_skill_3_id VARCHAR(10),

	CONSTRAINT PK_Hero_hero_id
		PRIMARY KEY (hero_id),

	CONSTRAINT FK_Hero_hero_image_id
		FOREIGN KEY (hero_image_id) REFERENCES Image (image_id),
	CONSTRAINT FK_Hero_hero_gender_id
		FOREIGN KEY (hero_gender_id) REFERENCES HeroGender (hero_gender_id),
	CONSTRAINT FK_HeroSkill_hero_skill_1_id
		FOREIGN KEY (hero_skill_1_id) REFERENCES HeroSkill (skill_id),
	CONSTRAINT FK_HeroSkill_hero_skill_2_id
		FOREIGN KEY (hero_skill_2_id) REFERENCES HeroSkill (skill_id),
	CONSTRAINT FK_HeroSkill_hero_skill_3_id
		FOREIGN KEY (hero_skill_3_id) REFERENCES HeroSkill (skill_id),

	CONSTRAINT CHK_Hero_hero_id_correct_format
		CHECK (hero_id REGEXP '^[A-Z0-9]+$')
);

CREATE TABLE Session (
	session_token CHAR(60),
	session_user_id CHAR(8) NOT NULL,
	session_creation_time DATETIME NOT NULL,

	CONSTRAINT PK_Session_session_token
		PRIMARY KEY (session_token),

	CONSTRAINT FK_Session_session_user_id
		FOREIGN KEY (session_user_id) REFERENCES `User` (user_id)
);

CREATE TABLE Card (
	card_id CHAR(4),
	card_name VARCHAR(70) CHARACTER SET UTF8MB4 NOT NULL,
	card_description VARCHAR(100) CHARACTER SET UTF8MB4,
	card_type_id TINYINT NOT NULL,

	CONSTRAINT PK_Card_card_id
		PRIMARY KEY (card_id),

	CONSTRAINT FK_Card_card_type_id
		FOREIGN KEY (card_type_id) REFERENCES CardType (card_type_id),

	-- card_id must be in the format XYYY, where X is an uppercase letter, and Y is any number
	CONSTRAINT CHK_Card_card_id_correct_format
		CHECK (LENGTH(card_id) = 4 AND card_id REGEXP '^[A-Z][0-9]{3}$')
);

CREATE TABLE BasicCard (
	basic_card_id CHAR(4),

	CONSTRAINT PK_BasicCard_basic_card_id
		PRIMARY KEY (basic_card_id),
	
	CONSTRAINT FK_BasicCard_basic_card_id
		FOREIGN KEY (basic_card_id) REFERENCES Card (card_id)
);

CREATE TABLE ToolCard (
	tool_card_id CHAR(4),
	tool_card_is_time BIT NOT NULL,
	tool_card_is_reroll BIT NOT NULL,
	tool_card_is_tradable BIT NOT NULL,

	CONSTRAINT PK_ToolCard_tool_card_id
		PRIMARY KEY (tool_card_id),

	CONSTRAINT FK_ToolCard_tool_card_id
		FOREIGN KEY (tool_card_id) REFERENCES Card (card_id)
);

CREATE TABLE WeaponCard (
	weapon_card_id CHAR(4),
	weapon_card_range TINYINT NOT NULL,
	weapon_card_is_tradable BIT NOT NULL,
	weapon_card_is_giftable BIT NOT NULL,

	CONSTRAINT PK_WeaponCard_weapon_card_id
		PRIMARY KEY (weapon_card_id),
	
	CONSTRAINT FK_WeaponCard_weapon_card_id
		FOREIGN KEY (weapon_card_id) REFERENCES Card (card_id)
);

CREATE TABLE ArmorCard (
	armor_card_id CHAR(4),
	armor_card_is_tradable BIT NOT NULL,
	armor_card_is_giftable BIT NOT NULL,

	CONSTRAINT PK_ArmorCard_armor_card_id
		PRIMARY KEY (armor_card_id),
	
	CONSTRAINT FK_ArmorCard_armor_card_id
		FOREIGN KEY (armor_card_id) REFERENCES Card (card_id)
);

CREATE TABLE HorseCard (
	horse_card_id CHAR(4),
	horse_card_is_plus BIT NOT NULL,
	Horse_card_is_minus BIT NOT NULL,
	horse_card_is_tradable BIT NOT NULL,
	horse_card_is_giftable BIT NOT NULL,

	CONSTRAINT PK_HorseCard_horse_card_id
		PRIMARY KEY (horse_card_id),
	
	CONSTRAINT FK_HorseCard_horse_card_id
		FOREIGN KEY (horse_card_id) REFERENCES Card (card_id)
);

CREATE TABLE TreasureCard (
	treasure_card_id CHAR(4),
	treasure_card_is_tradable BIT NOT NULL,
	treasure_card_is_giftable BIT NOT NULL,

	CONSTRAINT PK_TreasureCard_treasure_card_id
		PRIMARY KEY (treasure_card_id),
	
	CONSTRAINT FK_TreasureCard_treasure_card_id
		FOREIGN KEY (treasure_card_id) REFERENCES Card (card_id)
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

CREATE TABLE HeroBelongsFaction (
	hero_id VARCHAR(10),
	hero_faction_id TINYINT,

	CONSTRAINT PK_HeroFaction_hid_hfid
		PRIMARY KEY (hero_id, hero_faction_id),

	CONSTRAINT FK_HeroFaction_hero_id
		FOREIGN KEY (hero_id) REFERENCES Hero (hero_id),
	CONSTRAINT FK_HeroFaction_hero_faction_id
		FOREIGN KEY (hero_faction_id) REFERENCES HeroFaction (hero_faction_id)
);

CREATE TABLE CardSuitAndRank (
	card_id CHAR(4),
	card_suit_id TINYINT,
	card_rank TINYINT,

	CONSTRAINT PK_CardSuitAndRank_cid_csid_cr
		PRIMARY KEY (card_id, card_suit_id, card_rank),
	
	CONSTRAINT FK_CardSuitAndRank_card_suit_id
		FOREIGN KEY (card_suit_id) REFERENCES CardSuit (card_suit_id),
	
	CONSTRAINT CHK_CardSuitAndRank_valid_rank
		CHECK (card_rank <= 13 AND card_rank >= 1)
);

CREATE TABLE HeroBelongsRole (
	hero_id VARCHAR(10),
	hero_role_id TINYINT,

	CONSTRAINT PK_HeroBelongsRole_hid_hrid
		PRIMARY KEY (hero_id, hero_role_id),
	
	CONSTRAINT FK_HeroBelongsRole_hero_id
		FOREIGN KEY (hero_id) REFERENCES Hero (hero_id),
	CONSTRAINT FK_HeroBelongsRole_hero_role_id
		FOREIGN KEY (hero_role_id) REFERENCES HeroRole (hero_role_id)
);

CREATE TABLE HeroSaves (
	user_id CHAR(8),
	hero_id VARCHAR(10),

	CONSTRAINT PK_HeroSaves_uid_hid
		PRIMARY KEY (user_id, hero_id),
	
	CONSTRAINT FK_HeroSaves_user_id
		FOREIGN KEY (user_id) REFERENCES `User` (user_id),
	CONSTRAINT FK_HeroSaves_hero_id
		FOREIGN KEY (hero_id) REFERENCES Hero (hero_id)
);
