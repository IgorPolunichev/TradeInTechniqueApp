--liquibase formatted sql

--changeset ipolunichev:1
CREATE TABLE IF NOT EXISTS location_company
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    city     VARCHAR(255) NOT NULL,
    house    VARCHAR(255) NOT NULL,
    street   VARCHAR(255) NOT NULL,
    zip_code VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS company
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    inn                 VARCHAR(32),
    kpp                 VARCHAR(32),
    name_company        VARCHAR(255),
    location_company_id BIGINT,
    FOREIGN KEY (location_company_id) REFERENCES location_company (id)
);

CREATE TABLE IF NOT EXISTS machines
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    operating_time  INT,
    serial_number   VARCHAR(10),
    type            VARCHAR(10),
    year_of_release INT,
    company_id      BIGINT,
    FOREIGN KEY (company_id) REFERENCES company (id)

);
--changeset ipolunichev:4

CREATE TABLE IF NOT EXISTS cars
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    model         VARCHAR(32),
    license_plate VARCHAR(32)

);
CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    birth_date DATE,
    firstname  VARCHAR(255),
    lastname   VARCHAR(255),
    position   VARCHAR(255),
    role       VARCHAR(255),
    surname    VARCHAR(255),
    username   VARCHAR(255) UNIQUE NOT NULL,
    car_id     BIGINT,
    FOREIGN KEY (car_id) REFERENCES cars (id)
);



--changeset ipolunichev:5
CREATE TABLE IF NOT EXISTS acts
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    date               DATE,
    number             VARCHAR(255),
    company_id         BIGINT,
    number_application varchar(32),
    FOREIGN KEY (company_id) REFERENCES company (id)
);
--changeset ipolunichev:6
CREATE TABLE IF NOT EXISTS act_user
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    act_id  BIGINT,
    user_id BIGINT,
    FOREIGN KEY (act_id) REFERENCES acts (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
--changeset ipolunichev:7
CREATE TABLE IF NOT EXISTS part
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS part_ident_number
(
    part_id      BIGINT       NOT NULL,
    ident_number VARCHAR(255) null,
    FOREIGN KEY (part_id) REFERENCES part (id)
);

--changeset ipolunichev:8
CREATE TABLE IF NOT EXISTS revision
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    timestamp BIGINT NULL
);
--changeset ipolunichev:9
CREATE TABLE IF NOT EXISTS machines_aud
(
    id              BIGINT       NOT NULL,
    rev             BIGINT       NOT NULL,
    revtype         tinyint      null,
    operating_time  INT          null,
    serial_number   VARCHAR(255) null,
    year_of_release INT          null,
    company_id      BIGINT       null,
    type            VARCHAR(255) null,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) references revision (id)

);

--changeset ipolunichev:10
CREATE TABLE IF NOT EXISTS act_works
(
    act_id           BIGINT NOT NULL,
    work_date        DATE,
    start_work       TIME,
    end_work         TIME,
    work_description VARCHAR(256),
    PRIMARY KEY (act_id, work_date, start_work, end_work),
    FOREIGN KEY (act_id) REFERENCES acts (id)
);




