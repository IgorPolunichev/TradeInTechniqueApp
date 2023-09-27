--liquibase formatted sql

--changeset ipolunichev:1

ALTER TABLE act_works
    MODIFY end_lunch TIME
, MODIFY start_lunch TIME;















