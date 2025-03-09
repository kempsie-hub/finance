--changeset kempegowdafs:8  (this is the changeset that inserts data into the tables)
INSERT INTO users (email, username, name, password) VALUES
('nkempegowda@gmail.com', 'kempegowda',
'Kempegowda', '$2a$10$sChM5TQUXRr5g.ywpRlBgOKwQ9Noximibk4CXoHIU/DE2mqq0nNxa');

--changeset kempegowdafs:9  (this is the changeset that inserts data into the tables)
INSERT INTO users (email, username, name, password) VALUES
('mehaboob@gmail.com', 'mehaboob',
'Mehaboob', '$2a$10$7RWjYhS.3qEn5jQ8Arc0DudvJ5amgEAA3EnK9Bii7AsAAM.xGWotC');

--changeset kempegowdafs:10  (this is the changeset that inserts data into the tables)
INSERT INTO users (email, username, name, password) VALUES
('anoop@gmail.com', 'anoop',
'anoop', '$2a$10$wuFwwWIPDpeOBXDB9KxqkOFh6WYSPr8sOB.iBYxEMn3LHRS05CKTO');

--changeset kempegowdafs:11  (this is the changeset that inserts data into the tables)
INSERT INTO users (email, username, name, password) VALUES
('lokesh@gmail.com', 'lokesh',
'lokesh', '$2a$10$tt/CIUCSAaa3bNUrQwGFiudUQCejJefMHLBBlW.WQNL/0TLRHHVSC');

--changeset kempegowdafs:12
INSERT INTO ROLES (name) VALUES ('USER');

--changeset kempegowdafs:13
INSERT INTO ROLES (name) VALUES ('ADMIN');

--changeset kempegowdafs:14
INSERT INTO user_roles (user_id, role_id) VALUES
(select id from users where email = 'nkempegowda@gmail.com' , select id from roles where name = 'ADMIN');

--changeset kempegowdafs:15
INSERT INTO user_roles (user_id, role_id) VALUES
(select id from users where email = 'anoop@gmail.com' , select id from roles where name = 'ADMIN');

--changeset kempegowdafs:16
INSERT INTO user_roles (user_id, role_id) VALUES
(select id from users where email = 'mehaboob@gmail.com' , select id from roles where name = 'USER');

--changeset kempegowdafs:17
INSERT INTO user_roles (user_id, role_id) VALUES
(select id from users where email = 'lokesh@gmail.com' , select id from roles where name = 'USER');