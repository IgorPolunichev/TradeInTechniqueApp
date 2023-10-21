--liquibase formatted sql

--changeset ipolunichev:1

ALTER TABLE acts
ADD COLUMN path_files TEXT;














