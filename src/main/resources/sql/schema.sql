create database ReadyMySuit;
use ReadyMySuit;

create table user(
                     email varchar(30) primary key,
                     userName varchar (30)not null,
                     password varchar (8)not null
);

create table tailor(
                       tailorId varchar (30) primary key,
                       firstName varchar(30) not null,
                       lastName varchar (30) ,
                       tailorNIC varchar (15),
                       address varchar (50) not null,
                       phoneNumber varchar (10)not null,
                       salary decimal(8,2)

);

create table machine (
                         machineId varchar (30) primary key,
                         tailorId varchar (30),
                         type varchar (30),
                         date date ,
                         avail enum('Yes','No'),
                         constraint foreign key (tailorId) references tailor(tailorId)on Delete Cascade on Update Cascade
);

create table customer(
                         customerId varchar (30) primary key,
                         firstName varchar (30) not null,
                         lastName varchar (30),
                         address varchar (30),
                         phoneNumber varchar (10)
);

create table coat(
                     coatId varchar (30)primary key,
                     type varchar (30),
                     color varchar (30),
                     avail enum('Yes','No') default 'YES',
                     mfgDate date,
                     unitPrice varchar (4),
                     size varchar (4)
);


create table rentalbond(
                           bondId varchar (30) primary key,
                           type varchar(30),
                           note varchar (50)
);
create table rent(
                     rentId varchar (30)primary key,
                     customerId varchar (30) not null,
                     rebtalBond varchar(30),
                     constraint foreign key (customerId) references customer(customerId)on Delete Cascade on Update Cascade
);

create table rentalCoatDetail(
                                 coatId varchar (30) not null,
                                 rentId varchar (30)not null,
                                 unitPrice double not null,
                                 rentDate date,
                                 returnDate date,
                                 constraint foreign key (coatId) references coat(coatId)on Delete Cascade on Update Cascade,
                                 constraint foreign key (rentId) references rent(rentId)on Delete Cascade on Update Cascade
);

create table orders(
                       orderId varchar (30) primary key,
                       date date ,
                       customerId varchar (30) not null,
                       tailorId varchar (30)not null,
                       fullAmount decimal(8,2),
                       advance decimal(8,2),
                       balance decimal(8,2),
                       constraint foreign key (customerId) references customer(customerId)on Delete Cascade on Update Cascade,
                       constraint foreign key (tailorId) references tailor(tailorId)on Delete Cascade on Update Cascade
);


create table item(
                     itemId varchar (30) primary key,
                     type varchar (30) not null
);

create table orderItem (
                           orderId varchar (30) not null,
                           itemId varchar (30) not null,
                           qty int (100) default 1,
                           note varchar (100),
                           constraint foreign key (orderId) references orders(orderId)on Delete Cascade on Update Cascade,
                           constraint foreign key (itemId) references item(itemId)on Delete Cascade on Update Cascade
);

create table fabric(
                       fabricId varchar (30) primary key,
                       name varchar (30) not null,
                       rollQty int,
                       type varchar(30),
                       colour varchar(30)
);

create table itemFabricsDetail (
                                   itemId varchar (30) not null,
                                   fabricId varchar (30) not null,
                                   usages decimal (5,2) not null,
                                   constraint foreign key (itemId) references item(itemId)on Delete Cascade on Update Cascade,
                                   constraint foreign key (fabricId) references fabric(fabricId)on Delete Cascade on Update Cascade
);

create table shirtMeasurements(
                                  smId varchar (30) primary key,
                                  customerId varchar (30) not null,
                                  date date,
                                  length decimal (5,2),
                                  chest decimal (5,2),
                                  shoulder decimal (5,2),
                                  sleeveLength decimal (5,2),
                                  collar decimal (5,2),
                                  cuff decimal (5,2),
                                  waist decimal (5,2),
                                  constraint foreign key (customerId) references customer(customerId)on Delete Cascade on Update Cascade
);

create table trouserMeasurements(
                                    trmId varchar (30) primary key,
                                    customerId varchar (30) not null,
                                    date date,
                                    length decimal (5,2),
                                    waist decimal (5,2),
                                    seat decimal (5,2),
                                    halfSeat decimal (5,2),
                                    knee decimal (5,2),
                                    bottom decimal (5,2),
                                    crotch decimal (5,2),
                                    constraint foreign key (customerId) references customer(customerId)on Delete Cascade on Update Cascade

);

create table coatMeasurements(
                                 cmId varchar (30) primary key,
                                 customerId varchar (30) not null,
                                 date date,
                                 length  decimal (5,2),
                                 chest  decimal (5,2),
                                 shoulder  decimal (5,2),
                                 sleeveLength  decimal (5,2),
                                 collar  decimal (5,2),
                                 waist  decimal (5,2),
                                 neck  decimal (5,2),
                                 elbow decimal (5,2),
                                 constraint foreign key (customerId) references customer(customerId)on Delete Cascade on Update Cascade

);


insert into rentalbond values ("1","NIC","Keep customer's NIC as rental bond");
insert into rentalbond values  ("2","Driving Licence","Kepp customer's Driving Licence as rental bond");
insert into rentalbond values  ("3","PassPort","Keep customer's Pass Port as rental bond");
insert into rentalbond values  ("4","Rs.5000","Keep customer's 5000 as rental bond");




