create table terrorists
(
    id               int8 not null,
    address          varchar(255),
    date_of_birthday timestamp ,
    middle_name      varchar(255),
    name             varchar(255) not null ,
    passport_number  int8 not null ,
    status           boolean,
    surname          varchar(255) not null ,
    primary key (id)
);
