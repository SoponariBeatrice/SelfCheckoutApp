CREATE TABLE roles
(
    id_roles int         NOT NULL AUTO_INCREMENT,
    name     varchar(45) NOT NULL,

    PRIMARY KEY (id_roles)

);


INSERT INTO `selfcheckout`.`roles` (`id_roles`, `name`)
VALUES ('1', 'ROLE_USER');
INSERT INTO `selfcheckout`.`roles` (`id_roles`, `name`)
VALUES ('2', 'ROLE_ADMIN');
