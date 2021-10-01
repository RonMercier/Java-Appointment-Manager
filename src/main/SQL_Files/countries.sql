-- auto-generated definition
create table countries
(
    Country_ID      int(10) auto_increment
        primary key,
    Country         varchar(50)                         not null,
    Create_Date     datetime  default CURRENT_TIMESTAMP null,
    Created_By      varchar(50)                         null,
    Last_Update     timestamp default CURRENT_TIMESTAMP not null,
    Last_Updated_By varchar(50)                         null
)
    charset = utf8mb4;