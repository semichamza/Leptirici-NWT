-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema pms
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pms
-- -----------------------------------------------------
# CREATE SCHEMA IF NOT EXISTS `pms` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
# USE `pms` ;

-- -----------------------------------------------------
-- Table `Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Users` (
  `id`            INT          NOT NULL,
  `username`      VARCHAR(30)  NOT NULL,
  `password_hash` VARCHAR(255) NULL,
  `active`        TINYINT(1)   NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Projects` (
  `id`          INT         NOT NULL,
  `name`        VARCHAR(50) NULL,
  `description` TEXT(2000)  NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tasks` (
  `id`         INT          NOT NULL,
  `name`       VARCHAR(255) NOT NULL,
  `opis`       TEXT(2000)   NULL,
  `project_id` INT          NULL,
  `user_id`    INT          NULL,
  `estimation` INT          NULL,
  PRIMARY KEY (`id`),
  INDEX `task_project_fk_idx` (`project_id` ASC),
  INDEX `task_user_fk_idx` (`user_id` ASC),
  CONSTRAINT `task_user_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `task_project_fk`
  FOREIGN KEY (`project_id`)
  REFERENCES `Projects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Comments` (
  `id`      INT  NOT NULL,
  `text`    TEXT NULL,
  `task_id` INT  NOT NULL,
  `user_id` INT  NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `comment_user_fk_idx` (`user_id` ASC),
  INDEX `comment_task_fk_idx` (`task_id` ASC),
  CONSTRAINT `comment_user_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comment_task_fk`
  FOREIGN KEY (`task_id`)
  REFERENCES `Tasks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Users_Projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Users_Projects` (
  `user_id`    INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`project_id`, `user_id`),
  INDEX `user_fk_idx` (`user_id` ASC),
  CONSTRAINT `project_fk`
  FOREIGN KEY (`project_id`)
  REFERENCES `Projects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Logs` (
  `id`         INT NOT NULL,
  `time`       INT NOT NULL,
  `comment_id` INT NULL,
  `task_id`    INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `log_comment_fk_idx` (`comment_id` ASC),
  INDEX `log_task_fk_idx` (`task_id` ASC),
  CONSTRAINT `log_comment_fk`
  FOREIGN KEY (`comment_id`)
  REFERENCES `Comments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `log_task_fk`
  FOREIGN KEY (`task_id`)
  REFERENCES `Tasks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
