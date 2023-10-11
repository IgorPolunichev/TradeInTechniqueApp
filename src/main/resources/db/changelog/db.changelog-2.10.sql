--liquibase formatted sql

--changeset ipolunichev:1

CREATE TABLE IF NOT EXISTS act_parts
    (
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        act_id  BIGINT,
        part_id BIGINT,
        count INT,
        owner VARCHAR(32),
        FOREIGN KEY (act_id) REFERENCES acts (id),
        FOREIGN KEY (part_id) REFERENCES part (id)

);














