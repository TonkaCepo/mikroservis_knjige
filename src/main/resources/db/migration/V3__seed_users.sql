-- V3__seed_users.sql (H2) - idempotentno

-- roles
MERGE INTO roles (name) KEY(name) VALUES ('ADMIN');
MERGE INTO roles (name) KEY(name) VALUES ('USER');

-- users
-- BCrypt hash za "admin123"
MERGE INTO users (username, password_hash, enabled) KEY(username)
VALUES ('admin', '$2a$10$NjhHs4JOayo6ttSPmn6Pm.FDZZJ9D5Jrwp1.DDB2wkVrj6pXD9tme', TRUE);

-- BCrypt hash za "user123"
MERGE INTO users (username, password_hash, enabled) KEY(username)
VALUES ('user', '$2a$10$bIaXF2JSDX.7RW9550N5lulO98IzRY.B904rrOMbPa.mn0VuU61M.', TRUE);

-- user_roles (spoji po username i rolename)
MERGE INTO user_roles (user_id, role_id) KEY(user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name = 'ADMIN'
WHERE u.username = 'admin';

MERGE INTO user_roles (user_id, role_id) KEY(user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name = 'USER'
WHERE u.username = 'user';
