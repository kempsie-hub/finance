--changeset kempegowdafs:8  (this is the changeset that inserts data into the tables)
INSERT INTO users (email, username, name, password) VALUES
('kempegowdanagaraj14@gmail.com', 'kempegowda',
    'Kempegowda', '$2a$10$sChM5TQUXRr5g.ywpRlBgOKwQ9Noximibk4CXoHIU/DE2mqq0nNxa');

--changeset kempegowdafs:9
INSERT INTO ROLES (name) VALUES ('USER');

--changeset kempegowdafs:10
INSERT INTO ROLES (name) VALUES ('ADMIN');

--changeset kempegowdafs:11
INSERT INTO user_roles (user_id, role_id) VALUES
(select id from users where email = 'kempegowdanagaraj14@gmail.com' , select id from roles where name = 'ADMIN');
