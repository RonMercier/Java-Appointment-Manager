-- auto-generated definition
create table users
(
    User_ID         int(10) auto_increment
        primary key,
    User_Name       varchar(50)                         null,
    Password        text                                null,
    Create_Date     datetime  default CURRENT_TIMESTAMP null,
    Created_By      varchar(50)                         null,
    Last_Update     timestamp default CURRENT_TIMESTAMP not null,
    Last_Updated_By varchar(50)                         null,
    constraint User_Name
        unique (User_Name)
)
    charset = utf8mb4;