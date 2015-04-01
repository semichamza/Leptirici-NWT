INSERT INTO `pms`.`users` (`id`, `active`, `passwordHash`, `username`)
VALUES (1, TRUE, '098f6bcd4621d373cade4e832627b4f6', 'admin');
INSERT INTO `pms`.`users` (`id`, `active`, `passwordHash`, `username`)
VALUES (2, TRUE, '098f6bcd4621d373cade4e832627b4f6', 'User');

INSERT INTO `pms`.`projects` (`id`, `name`) VALUES ('1', 'Hello world');

INSERT INTO `pms`.`tasks` (`id`, `name`, `project_id`, `user_id`) VALUES ('1', 'Prvi task', '1', '1');

INSERT INTO `pms`.`projects` (`id`, `description`, `name`) VALUES ('2', 'Opis2', 'Projekat2');
INSERT INTO `pms`.`projects` (`id`, `description`, `name`) VALUES ('3', 'Opis3', 'Projekat3');

INSERT INTO `pms`.`tasks` (`id`, `name`, `project_id`, `user_id`) VALUES ('2', 'Drugi task', '1', '1');
INSERT INTO `pms`.`tasks` (`id`, `name`, `project_id`, `user_id`) VALUES ('3', 'Treci task', '1', '1');
INSERT INTO `pms`.`tasks` (`id`, `name`, `project_id`, `user_id`) VALUES ('4', 'Cetvrti task', '1', '1');


INSERT INTO `pms`.`comments` (`id`, `text`, `time_posted`) VALUES ('1', 'Komentar 1', sysdate());
INSERT INTO `pms`.`comments` (`id`, `text`, `time_posted`) VALUES ('2', 'Komentar 2', sysdate());

INSERT INTO `pms`.`tasks_comments` (`task_id`, `comment_id`) VALUES (1, 1);
INSERT INTO `pms`.`tasks_comments` (`task_id`, `comment_id`) VALUES (1, 2);

INSERT INTO `pms`.`projects_users` (`project_id`, `user_id`) VALUES (1, 2);

