--liquibase formatted sql

--changeset ipolunichev:1
ALTER TABLE users
    ADD COLUMN created_at DATETIME,
    ADD COLUMN modified_at DATETIME,
    ADD COLUMN created_by VARCHAR(255),
    ADD COLUMN last_modified_by VARCHAR(255);

