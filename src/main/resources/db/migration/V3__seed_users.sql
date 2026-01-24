-- Passwords are BCrypt hashes
-- user / user123
INSERT INTO users(username, password_hash, enabled) VALUES
('user',  '$2a$10$4YtS7oJQqO6ZPqQm2cJw9eYk4zH1m7J6VvV4Y8gCqzEw9Yg8o3g8G', TRUE);

-- admin / admin123
INSERT INTO users(username, password_hash, enabled) VALUES
('admin', '$2a$10$5O3b0cVnJ0p0wF7oEo2K0uG5m9hQGd7y5Zc8z2p8VnXbYw2oYkQvC', TRUE);

-- map roles
INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id FROM users u, roles r
WHERE u.username='user' AND r.name='USER';

INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id FROM users u, roles r
WHERE u.username='admin' AND r.name='ADMIN';
