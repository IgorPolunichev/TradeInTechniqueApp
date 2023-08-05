--liquibase formatted sql

--changeset ipolunichev:1
ALTER TABLE part
    ADD COLUMN ident_number VARCHAR(255);

DROP TABLE part_ident_number;





