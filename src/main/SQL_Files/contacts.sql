-- auto-generated definition
create table contacts
(
    Contact_ID   int(10) auto_increment
        primary key,
    Contact_Name varchar(50) not null,
    Email        varchar(50) not null
)
    charset = utf8mb4;