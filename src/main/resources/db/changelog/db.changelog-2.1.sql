--liquibase formatted sql

--changeset ipolunichev:1
ALTER TABLE  machines
    ADD COLUMN created_at DATETIME;

ALTER TABLE machines
    ADD COLUMN modified_at DATETIME;

ALTER TABLE machines
    ADD COLUMN created_by VARCHAR(255);

ALTER TABLE machines
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE users
    Add COLUMN counter_acts BIGINT DEFAULT 0;

ALTER TABLE  users
    ADD COLUMN password VARCHAR(128) DEFAULT '{noop}123';

