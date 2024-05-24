SELECT for_test.customer.name
     , for_test.account.description
     , AVG(for_test.fin_transaction.amount) as avg
from for_test.fin_transaction
         right join  for_test.account On for_test.fin_transaction.account_id = for_test.account.id
         right join for_test.customer ON for_test.account.customer_id = for_test.customer.id
group by for_test.customer.name, for_test.account.description
;

create database for_test;

create table  if not exists for_test.customer
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10),
    Address VARCHAR(10)
);

create table  if not exists for_test.fin_transaction
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transactDate DATE,
    amount BIGINT,
    account_id BIGINT,
    description VARCHAR(10),
    FOREIGN KEY (account_id) references for_test.account (id)
);


create table if not exists for_test.account (
                                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                                acc_number BIGINT,
                                                description varchar(10),
                                                customer_id BIGINT,
                                                FOREIGN KEY (customer_id) REFERENCES for_test.customer (id)

);

INSERT INTO for_test.customer (
    id, name, Address
) VALUE (1,'igor', 'www'),
    (2,'vlada', 'rrr'),
    (3,'dima', 'ggg'),
    (4, 'harold', 'hhh');

INSERT INTO for_test.account (
    acc_number,description,customer_id
) VALUE (1, 'www', 1),
    (2, 'rrr',1),
    (3, 'ggg',1),
    ( 4, 'hhh',3);

INSERT INTO for_test.fin_transaction (
    id,transactDate, amount, account_id, description
) VALUE
    (1,'25.05.2004', 100, 1, 'ggggg'),
    (2,'25.05.2004', 1300, 1, 'ggggg'),
    (3,'25.05.2004', 13000, 2, 'ggggg'),
    (4,'25.05.2004', 100, 3, 'ggggg'),
    (5,'25.05.2004', 440, 1, 'ggggg'),
    (6,'25.05.2004', 100, 3, 'ggggg')
;


