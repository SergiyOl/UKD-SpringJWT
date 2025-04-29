-- Створюємо ролі
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

-- Створюємо користувачів
INSERT INTO users (id, username, email, password) VALUES (1, 'admin', 'admin@example.com', '$2a$10$3S8Of3Fbnfx1i3pm.AR8IOVdzvFIJ96nWPpSaS5WOwEY4Jr9clwnW'); -- пароль: admin123
INSERT INTO users (id, username, email, password) VALUES (2, 'user', 'user@example.com', '$2a$10$grGL9oT9IWAvbLTrz6JQN.clAd2fMs8VD1eDrrfXePefcBHVx72UG'); -- пароль: user123

-- Прив'язуємо ролі до користувачів
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- admin має ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2); -- admin має ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- user має тільки ROLE_USER

