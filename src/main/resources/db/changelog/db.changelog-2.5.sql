--liquibase formatted sql

--changeset ipolunichev:1

ALTER TABLE machines
    ADD COLUMN subtype VARCHAR(12);











