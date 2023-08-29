create table clients
(
    id  bigint primary key GENERATED ALWAYS AS IDENTITY,
    bank_account_number varchar(255)

);

create table limits
(
    id  bigint primary key GENERATED ALWAYS AS IDENTITY,
    limit_sum bigint,
    remaining_month_limit bigint,
    limit_datetime TIMESTAMP,
    limit_currency_shortname varchar(255),
    limit_expense_category varchar(255),
    limit_сlient bigint

);

insert into clients (bank_account_number )
values ('0000000001'),
       ('0000000002'),
       ('0000000003');