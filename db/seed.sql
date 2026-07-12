USE Sanguosuo;

INSERT INTO UserStatus (user_status_code, user_status_name) VALUES
	('ACTIVE', 'Active'),
	('INACTIVE', 'Inactive'),
	('DISABLED', 'Disabled');

INSERT INTO UserRole (user_role_code, user_role_name) VALUES
	('VIEWER', 'Viewer'),
	('EDITOR', 'Editor'),
	('ADMIN', 'Administrator');