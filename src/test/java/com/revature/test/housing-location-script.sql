truncate housing_location, training_location restart identity cascade;

insert into training_location values(1,'Existing Location');
insert into housing_location values(1,'13452 BB Downs','','Tampa','Florida','34116','The Standard',1);
insert into housing_location values(3,'13452 BB Downs','','Tampa','Florida','34116','Crossing Swords',1);