--liquibase formatted sql

--changeset ipolunichev:1
INSERT INTO users(id,
                  birth_date,
                  firstname,
                  lastname,
                  position,
                  role,
                  surname,
                  username)
VALUES (1,
        '2020-01-01 10:10:10',
        'Igor',
        'Polunichev',
        'SERVICE_ENGINEER',
        'ADMIN',
        'Alexandrovich',
        'polusha');

--changeset ipolunichev:2
INSERT INTO machines(id, operating_time, serial_number, type, year_of_release)
VALUES (1,
        22134,
        '20128',
        'PR776',
        2020);

--changeset ipolunichev:3
INSERT INTO company(id, inn, kpp, name_company)
VALUES(1,
       '1004001744',
       '100401001',
       'SEVER_STAL');