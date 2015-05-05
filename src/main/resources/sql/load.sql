INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `userRole`) VALUES (TRUE, 'Sinan', 'Saki?', '098f6bcd4621d373cade4e832627b4f6', 'admin', 'ADMINISTRATOR');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `userRole`) VALUES (TRUE, 'Jasmin', 'Kaldžija', '098f6bcd4621d373cade4e832627b4f6', 'jkaldzija', 'NORMAL');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `userRole`) VALUES (TRUE, 'Alem', 'Zeki?', '098f6bcd4621d373cade4e832627b4f6', 'azekic', 'NORMAL');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `userRole`) VALUES (TRUE, 'Hamza', 'Semi?', '098f6bcd4621d373cade4e832627b4f6', 'hsemic', 'NORMAL');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `userRole`) VALUES (TRUE, 'Almin', '?iva', '098f6bcd4621d373cade4e832627b4f6', 'aciva', 'NORMAL');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `userRole`) VALUES (TRUE, 'TEST', 'TEST', '098f6bcd4621d373cade4e832627b4f6', 'test', 'NORMAL');

INSERT INTO `pms`.`projects` (`description`, `name`) VALUES ('Testni projekat', 'Hello world');
INSERT INTO `pms`.`projects` (`description`, `name`) VALUES ('Projekat sa predmeta NWT', 'PMS-NWT');
INSERT INTO `pms`.`projects` (`description`, `name`) VALUES ('Testni 3', 'Testni 3');

INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `projectRole`) VALUES ('1', '1', 'OWNER');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `projectRole`) VALUES ('1', '2', 'OWNER');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `projectRole`) VALUES ('2', '2', 'OWNER');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `projectRole`) VALUES ('3', '2', 'MEMBER');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `projectRole`) VALUES ('4', '2', 'MEMBER');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `projectRole`) VALUES ('5', '2', 'MEMBER');

INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Hello world task', 'Hello world', '1', '1');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('MySQL + Hibernate + Java EE7 + Angular', 'Konfiguracija okruženja', '2', '4');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Kreirati REST servise za osnovne entitete', 'REST servisi', '2', '4');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Napisati dokumentaciju za REST servise', 'Dokumentacija API-ja', '2', '5');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Napraviti početni Angular View/Controller', 'Konfiguracija Angular frameworka', '2', '3');

INSERT INTO `comments` (`id`, `text`, `time_posted`, `task_id`, `user_id`) VALUES ('1', 'Hello world', NOW(), 1, 1);