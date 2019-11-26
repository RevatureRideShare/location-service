create table training_location(
training_location_id numeric primary key,
training_location_name text not null
);

create table housing_location(
location_id numeric primary key,
address_1 text not null,
address_2 text,
city varchar(50) not null,
state varchar(50) not null,
zip_code varchar(10) not null,
housing_location_name text not null,
training_location_id numeric references training_location(training_location_id)
);

create sequence training_location_id_seq;
create sequence location_id_seq;

--drop sequence training_location_id_seq;
--drop sequence location_id_seq;

insert into training_location values(3,'Existing Location');