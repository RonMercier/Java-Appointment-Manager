-- auto-generated definition
create table customers
(
    Customer_ID     int(10) auto_increment
        primary key,
    Customer_Name   varchar(50)                         not null,
    Address         varchar(100)                        not null,
    Postal_Code     varchar(50)                         not null,
    Phone           varchar(50)                         not null,
    Create_Date     datetime  default CURRENT_TIMESTAMP null,
    Created_By      varchar(50)                         null,
    Last_Update     timestamp default CURRENT_TIMESTAMP not null,
    Last_Updated_By varchar(50)                         null,
    Division_ID     int(10)                             not null,
    constraint customers_ibfk_1
        foreign key (Division_ID) references first_level_divisions (Division_ID)
)
    charset = utf8mb4;

create index Division_ID
    on customers (Division_ID);