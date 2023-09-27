--liquibase formatted sql

--changeset ipolunichev:1
ALTER TABLE part
ADD CONSTRAINT Ident_UNIQUE UNIQUE (ident_number);

ALTER TABLE part
DROP CONSTRAINT name;








