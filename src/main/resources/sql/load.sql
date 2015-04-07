INSERT INTO `pms`.`user_roles` (`name`) VALUES ('Administrator');
INSERT INTO `pms`.`user_roles` (`name`) VALUES ('Normal');

INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `user_role_id`) VALUES (TRUE, 'Sinan', 'Saki?', '098f6bcd4621d373cade4e832627b4f6', 'admin', '1');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `user_role_id`) VALUES (TRUE, 'Jasmin', 'Kaldžija', '098f6bcd4621d373cade4e832627b4f6', 'jkaldzija', '2');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `user_role_id`) VALUES (TRUE, 'Alem', 'Zeki?', '098f6bcd4621d373cade4e832627b4f6', 'azekic', '2');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `user_role_id`) VALUES (TRUE, 'Hamza', 'Semi?', '098f6bcd4621d373cade4e832627b4f6', 'hsemic', '2');
INSERT INTO `pms`.`users` (`active`, `firstName`, `lastName`, `passwordHash`, `username`, `user_role_id`) VALUES (TRUE, 'Almin', '?iva', '098f6bcd4621d373cade4e832627b4f6', 'aciva', '2');

INSERT INTO `pms`.`project_roles` (`name`) VALUES ('Owner');
INSERT INTO `pms`.`project_roles` (`name`) VALUES ('Member');

INSERT INTO `pms`.`projects` (`description`, `name`, `user_id`) VALUES ('Testni projekat', 'Hello world', 1);
INSERT INTO `pms`.`projects` (`description`, `name`, `user_id`) VALUES ('Projekat sa predmeta NWT', 'PMS-NWT', 2);

INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `project_role_id`) VALUES ('1', '1', '1');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `project_role_id`) VALUES ('2', '2', '1');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `project_role_id`) VALUES ('3', '2', '2');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `project_role_id`) VALUES ('4', '2', '2');
INSERT INTO `pms`.`projects_users` (`user_id`, `project_id`, `project_role_id`) VALUES ('5', '2', '2');

INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Hello world task', 'Hello world', '1', '1');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('MySQL + Hibernate + Java EE7 + Angular', 'Konfiguracija okruženja', '2', '4');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Kreirati REST servise za osnovne entitete', 'REST servisi', '2', '4');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Napisati dokumentaciju za REST servise', 'Dokumentacija API-ja', '2', '5');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Napraviti početni Angular View/Controller', 'Konfiguracija Angular frameworka', '2', '3');

INSERT INTO `comments` (`id`, `text`, `time_posted`) VALUES ('1', 'Hello world', NOW());

INSERT INTO `tasks_comments` (`task_id`, `comment_id`) VALUES ('1', '1');