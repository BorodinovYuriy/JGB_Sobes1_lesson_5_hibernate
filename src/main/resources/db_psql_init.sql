DROP DATABASE IF EXISTS sobes_lesson5;
DROP schema IF EXISTS sobes_lesson5;
create schema sobes_lesson5;


DROP table IF EXISTS sobes_lesson5.Student;
create table sobes_lesson5.Student(
    id      bigserial primary key,
    name    varchar(100),
    mark    int
);

insert into sobes_lesson5.Student(name, mark) values ('Student_0', 0);
select * from sobes_lesson5.Student;
delete from sobes_lesson5.Student;