--liquibase formatted sql

--changeset ipolunichev:1

ALTER TABLE machines_aud
    ADD COLUMN subtype VARCHAR(12);











