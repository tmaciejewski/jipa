insert into train values(1, 'train lorem');
insert into train values(2, 'train ipsum');

insert into station values(1000, 'station 1');
insert into station values(1001, 'station 2');
insert into station values(1002, 'station 3');
insert into station values(1003, 'station 4');
insert into station values(1004, 'station 5');

insert into schedule values(100, '2017-10-10', 1, 0);

insert into schedule_info values(100, 1, 1000, '2017-10-10T11:00:00', 1, '2017-10-10T11:01:00', 0);
insert into schedule_info values(100, 2, 1001, '2017-10-10T11:10:00', 1, '2017-10-10T11:12:00', 2);
insert into schedule_info values(100, 3, 1002, '2017-10-10T11:14:00', 10, '2017-10-10T11:21:00', 0);
insert into schedule_info values(100, 4, 1003, '2017-10-10T12:10:00', 1, '2017-10-10T12:51:00', 40);
insert into schedule_info values(100, 5, 1004, '2017-10-11T01:00:00', -13, '2017-10-11T01:05:00', 0);

insert into schedule values(101, '2017-10-11', 1, 1);

insert into schedule_info values(101, 1, 1000, '2017-10-11T11:00:00', 1, '2017-10-11T11:01:00', 0);
insert into schedule_info values(101, 2, 1001, '2017-10-11T11:10:00', 1, '2017-10-11T11:12:00', 2);
insert into schedule_info values(101, 3, 1002, '2017-10-11T11:14:00', 10, '2017-10-11T11:21:00', 0);
insert into schedule_info values(101, 4, 1003, '2017-10-11T12:10:00', 1, '2017-10-11T12:51:00', 40);

insert into schedule values(102, '2017-10-12', 2, 1);
insert into schedule_info values(102, 1, 1003, '2017-10-12T11:10:00', 1, '2017-10-12T12:51:00', 40);
insert into schedule_info values(102, 2, 1002, '2017-10-12T12:14:00', 10, '2017-10-12T11:21:00', 0);
insert into schedule_info values(102, 3, 1001, '2017-10-12T13:10:00', 1, '2017-10-12T11:12:00', 2);
