--liquibase formatted sql

--changeset ipolunichev:1

ALTER TABLE acts
   RENAME COLUMN cars TO car;















