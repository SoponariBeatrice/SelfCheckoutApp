CREATE TABLE users (
                      id_user int NOT NULL AUTO_INCREMENT,
                      first_name varchar(45) NOT NULL,
                      last_name varchar(45) NOT NULL,
                      username varchar(45) NOT NULL UNIQUE,
                      email varchar(45) NOT NULL UNIQUE ,
                      password varchar(255) NOT NULL,
                      PRIMARY KEY (id_user)
);
