INSERT INTO `users` (`active`, `passwordHash`, `username`) VALUES (TRUE, '098f6bcd4621d373cade4e832627b4f6', 'admin');
INSERT INTO `users` (`active`, `passwordHash`, `username`) VALUES (TRUE, '098f6bcd4621d373cade4e832627b4f6', 'azekic');
INSERT INTO `users` (`active`, `passwordHash`, `username`) VALUES (TRUE, '098f6bcd4621d373cade4e832627b4f6', 'jkaldzija');
INSERT INTO `users` (`active`, `passwordHash`, `username`) VALUES (TRUE, '098f6bcd4621d373cade4e832627b4f6', 'aciva');
INSERT INTO `users` (`active`, `passwordHash`, `username`) VALUES (TRUE, '098f6bcd4621d373cade4e832627b4f6', 'hsemic');

INSERT INTO `projects` (`description`,`name`,`owner_id`) VALUES ('Testni projekat','Hello world',1);
INSERT INTO `projects` (`description`,`name`,`owner_id`) VALUES ('Projekat sa predmeta NWT','PMS-NWT',1);

INSERT INTO `projects_users` (`project_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `projects_users` (`project_id`, `user_id`) VALUES ('2', '2');
INSERT INTO `projects_users` (`project_id`, `user_id`) VALUES ('2', '3');
INSERT INTO `projects_users` (`project_id`, `user_id`) VALUES ('2', '4');
INSERT INTO `projects_users` (`project_id`, `user_id`) VALUES ('2', '5');

INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Hello world task', 'Hello world', '1', '1');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('MySQL + Hibernate + Java EE7 + Angular', 'Konfiguracija okruženja', '2', '4');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Kreirati REST servise za osnovne entitete', 'REST servisi', '2', '4');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Napisati dokumentaciju za REST servise', 'Dokumentacija API-ja', '2', '5');
INSERT INTO `tasks` (`description`, `name`, `project_id`, `user_id`) VALUES ('Napraviti početni Angular View/Controller', 'Konfiguracija Angular frameworka', '2', '3');

INSERT INTO `comments` (`id`, `text`, `time_posted`) VALUES ('1', 'Hello world', NOW());

INSERT INTO `tasks_comments` (`task_id`, `comment_id`) VALUES ('1', '1');