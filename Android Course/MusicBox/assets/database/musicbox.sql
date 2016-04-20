drop table if exists "catalogue";
create table "catalogue" ("_id" integer primary key  autoincrement  not null , "name" varchar(20));
insert into "catalogue" values(0,'默认');
drop table if exists "musicinfo";
create table "musicinfo" ("_id" integer primary key  autoincrement  not null,"catalogue_id" integer references "catalogue"("_id"), "name" varchar(20) , "path" varchar(140));	