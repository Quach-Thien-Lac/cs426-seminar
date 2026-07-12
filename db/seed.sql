USE Sanguosuo;

INSERT INTO UserStatus (user_status_code, user_status_name) VALUES
	('ACTIVE', 'Active'),
	('INACTIVE', 'Inactive'),
	('DISABLED', 'Disabled');

INSERT INTO UserRole (user_role_code, user_role_name) VALUES
	('VIEWER', 'Viewer'),
	('EDITOR', 'Editor'),
	('ADMIN', 'Administrator');

INSERT INTO SkillTag (skill_tag_code, skill_tag_name) VALUES
	('CTK', 'Chủ Tướng Kỹ'),
	('PTK', 'Phó Tướng Kỹ'),
	('TDK', 'Toả Định Kỹ'),
	('HDK', 'Hạn Định Kỹ'),
	('TPK', 'Trận Pháp Kỹ'),
	('GM', 'Giảm 0.5 Máu');

INSERT INTO HeroFaction (hero_faction_code, hero_faction_name) VALUES
	('WEI', 'Nguỵ'),
	('SHU', 'Thục'),
	('WU', 'Ngô'),
	('QUN', 'Quần');