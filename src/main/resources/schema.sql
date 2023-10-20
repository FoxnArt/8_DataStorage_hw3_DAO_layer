create table CUSTOMERS
(
    id           int primary key auto_increment,
    name         varchar(50) not null,
    surname      varchar(50) not null,
    age          int         not null,
    phone_number varchar(60) not null
);

create table ORDERS
(
    id           int primary key auto_increment,
    date         date         not null,
    customer_id  int          not null,
    product_name varchar(255) not null,
    amount       int          not null
);

alter table ORDERS
    add constraint fk_customer_id foreign key (customer_id) references CUSTOMERS (id);