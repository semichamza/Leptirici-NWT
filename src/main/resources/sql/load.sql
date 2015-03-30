INSERT INTO `pms`.`users` (`active`, `passwordHash`, `username`)
VALUES (TRUE, '098f6bcd4621d373cade4e832627b4f6', 'admin');

INSERT INTO `pms`.`projects` (`id`, `name`) VALUES ('1', 'Hello world');

INSERT INTO `pms`.`tasks` (`id`, `name`, `project_id`, `user_id`) VALUES ('1', 'Prvi task', '1', '1');
