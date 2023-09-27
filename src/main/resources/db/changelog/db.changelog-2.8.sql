--liquibase formatted sql

--changeset ipolunichev:1

ALTER TABLE acts
    ADD COLUMN cars          VARCHAR(12),
    ADD COLUMN license_plate VARCHAR(10),
    ADD COLUMN place_of_work VARCHAR(100),
    ADD COLUMN act_pay       VARCHAR(100);















