INSERT INTO users (active,blocked,deleted, firstName, lastName, passwordHash, username, userRole,email) VALUES (TRUE,FALSE ,FALSE ,'Sinan', 'Sakic', '098f6bcd4621d373cade4e832627b4f6', 'admin', 'ADMINISTRATOR','test7@test.com');
INSERT INTO users (active,blocked,deleted, firstName, lastName, passwordHash, username, userRole,email) VALUES (TRUE,FALSE ,FALSE , 'Nihad', 'Borovina', '098f6bcd4621d373cade4e832627b4f6', 'nborovina', 'NORMAL','test2@test.com');
INSERT INTO users (active,blocked,deleted, firstName, lastName, passwordHash, username, userRole,email) VALUES (TRUE,FALSE ,FALSE , 'Jasmin', 'Kaldzija', '098f6bcd4621d373cade4e832627b4f6', 'jkaldzija', 'NORMAL','test3@test.com');
INSERT INTO users (active,blocked,deleted, firstName, lastName, passwordHash, username, userRole,email) VALUES (TRUE,FALSE ,FALSE , 'Hamza', 'Semic', '098f6bcd4621d373cade4e832627b4f6', 'hsemic', 'NORMAL','test4@test.com');
INSERT INTO users (active,blocked,deleted, firstName, lastName, passwordHash, username, userRole,email) VALUES (TRUE,FALSE ,FALSE , 'Sylvester', 'Stallone', '098f6bcd4621d373cade4e832627b4f6', 'sstallone', 'NORMAL','test5@test.com');
INSERT INTO users (active,blocked,deleted, firstName, lastName, passwordHash, username, userRole,email) VALUES (TRUE,FALSE ,FALSE , 'Mesa', 'Selimovic', '098f6bcd4621d373cade4e832627b4f6', 'mselimovic', 'NORMAL','test6@test.com');

INSERT INTO projects (description, name) VALUES ('Testni projekat', 'Hello world');
INSERT INTO projects (description, name) VALUES ('Projekat sa predmeta NSI', 'PMS-NSI');
INSERT INTO projects (description, name) VALUES ('Testni 3', 'Testni 3');

INSERT INTO projects_users (user_id, project_id, projectRole) VALUES ('1', '1', 'OWNER');
INSERT INTO projects_users (user_id, project_id, projectRole) VALUES ('1', '2', 'MEMBER');
INSERT INTO projects_users (user_id, project_id, projectRole) VALUES ('2', '2', 'OWNER');
INSERT INTO projects_users (user_id, project_id, projectRole) VALUES ('3', '2', 'MEMBER');
INSERT INTO projects_users (user_id, project_id, projectRole) VALUES ('4', '2', 'MEMBER');
INSERT INTO projects_users (user_id, project_id, projectRole) VALUES ('5', '2', 'MEMBER');

INSERT INTO tasks (description, name, project_id, user_id,taskStatus,dueDate,taskPriority) VALUES ('Hello world task', 'Hello world', '1', '1','OPEN', '2016-07-20','HIGH');
INSERT INTO tasks (description, name, project_id, user_id,taskStatus,dueDate,taskPriority) VALUES ('MySQL + Hibernate + Java EE7 + Angular', 'Konfiguracija okruzenja', '2', '4','OPEN', '2016-02-01','HIGH');
-- INSERT INTO tasks (description, name, project_id, user_id) VALUES ('Kreirati REST servise za osnovne entitete', 'REST servisi', '2', '4');
INSERT INTO tasks (description, name, project_id, user_id,taskStatus,dueDate,taskPriority) VALUES ('Napisati dokumentaciju za REST servise', 'Dokumentacija API-ja', '2', '5','OPEN', '2016-04-11','LOW');
INSERT INTO tasks (description, name, project_id, user_id,taskStatus,dueDate,taskPriority) VALUES ('Napraviti pocetni Angular View/Controller', 'Konfiguracija Angular frameworka', '2', '3','OPEN', '2017-04-19','NORMAL');

INSERT INTO comments (id, text, time_posted, task_id, user_id) VALUES ('1', 'Hello world', NOW(), 1, 1);