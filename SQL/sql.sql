-- Project Name : noname
-- Date/Time    : 2019/02/08 19:59:44
-- Author       : likeit_student
-- RDBMS Type   : Oracle Database
-- Application  : A5:SQL Mk-2

/*
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
*/

-- user
--* RestoreFromTempTable
create table user (
  id int(255) auto_increment not null
  , login_id varchar(255) not null
  , login_password varchar(255) not null
  , name varchar(255) not null
  , email varchar(255) not null
  , tel int(255) not null
  , address varchar(255) not null
  , birthday date not nullA
  , create_date date not null
  , constraint user_PKC primary key (id)
) ;

alter table user add constraint login_id
  unique (login_id) ;

alter table user add constraint email
  unique (email) ;

alter table user add constraint tel
  unique (tel) ;

-- buy
--* RestoreFromTempTable
create table buy (
  id int(10) auto_increment not null
  , total_price int(10) not null
  , create_date datetime not null
  , user_id int(10) not null
  , delivery_id int(10) not null
  , constraint buy_PKC primary key (id)
) ;

-- buy_detail
--* RestoreFromTempTable
create table buy_detail (
  id int(10) auto_increment not null
  , buy_id int(10) not null
  , meal_id int(10)
  , item_id int(10)
  , num int(10) not null
  , constraint buy_detail_PKC primary key (id)
) ;

-- delivery
--* RestoreFromTempTable
create table delivery (
  id int(10) not null
  , name varchar(10) not null
  , price int(10) not null
  , constraint delivery_PKC primary key (id)
) ;

-- item
--* RestoreFromTempTable
create table item (
  id int(10) not null
  , name varchar(30) not null
  , price int(10) not null
  , team int(10) not null
  , num varchar(10) not null
  , constraint item_PKC primary key (id)
) ;

-- meal
--* RestoreFromTempTable
create table meal (
  id int(10) not null
  , name varchar(20) not null
  , price int(10) not null
  , file_name varchar(20) not null
  , meal_balance int(10) not null
  , constraint meal_PKC primary key (id)
) ;

alter table meal add constraint name
  unique (name) ;

-- meal_balance
--* RestoreFromTempTable
create table meal_balance (
  id int(10) not null
  , detail varchar(20) not null
  , constraint meal_balance_PKC primary key (id)
) ;

alter table meal_balance add constraint detail
  unique (detail) ;

comment on table user is 'user';
comment on column user.id is 'id';
comment on column user.login_id is 'login_id';
comment on column user.login_password is 'login_password';
comment on column user.name is 'name';
comment on column user.email is 'email';
comment on column user.tel is 'tel';
comment on column user.address is 'address';
comment on column user.birthday is 'birthday';
comment on column user.create_date is 'create_date';

comment on table buy is 'buy';
comment on column buy.id is 'id';
comment on column buy.total_price is 'total_price';
comment on column buy.create_date is 'create_date';
comment on column buy.user_id is 'user_id';
comment on column buy.delivery_id is 'delivery_id';

comment on table buy_detail is 'buy_detail';
comment on column buy_detail.id is 'id';
comment on column buy_detail.buy_id is 'buy_id';
comment on column buy_detail.meal_id is 'meal_id';
comment on column buy_detail.item_id is 'item_id';
comment on column buy_detail.num is 'num';

comment on table delivery is 'delivery';
comment on column delivery.id is 'id';
comment on column delivery.name is 'name';
comment on column delivery.price is 'price';

comment on table item is 'item';
comment on column item.id is 'id';
comment on column item.name is 'name';
comment on column item.price is 'price';
comment on column item.team is 'team';
comment on column item.num is 'num';

comment on table meal is 'meal';
comment on column meal.id is 'id';
comment on column meal.name is 'name';
comment on column meal.price is 'price';
comment on column meal.file_name is 'file_name';
comment on column meal.meal_balance is 'meal_balance';

comment on table meal_balance is 'meal_balance';
comment on column meal_balance.id is 'id';
comment on column meal_balance.detail is 'detail';
