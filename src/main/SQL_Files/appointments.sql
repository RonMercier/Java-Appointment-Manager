-- auto-generated definition
create table appointments
(
    Appointment_ID  int(10) auto_increment
        primary key,
    Title           varchar(50)                         not null,
    Description     varchar(50)                         null,
    Location        varchar(50)                         not null,
    Type            varchar(50)                         null,
    Start           datetime  default CURRENT_TIMESTAMP null,
    End             datetime  default CURRENT_TIMESTAMP null,
    Create_Date     datetime  default CURRENT_TIMESTAMP null,
    Created_By      varchar(50)                         null,
    Last_Update     timestamp default CURRENT_TIMESTAMP not null,
    Last_Updated_By varchar(50)                         null,
    Customer_ID     int(10)                             null,
    User_ID         int(10)                             null,
    Contact_ID      int(10)                             null,
    constraint appointments_ibfk_1
        foreign key (Customer_ID) references customers (Customer_ID),
    constraint appointments_ibfk_2
        foreign key (User_ID) references users (User_ID),
    constraint appointments_ibfk_3
        foreign key (Contact_ID) references contacts (Contact_ID)
)
    charset = utf8mb4;

create index Contact_ID
    on appointments (Contact_ID);

create index Customer_ID
    on appointments (Customer_ID);

create index User_ID
    on appointments (User_ID);