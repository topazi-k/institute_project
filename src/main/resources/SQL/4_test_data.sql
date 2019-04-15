INSERT INTO faculty
    (id,name)
VALUES
    (1,'PSYCHOLOGY'),
    (2,'COMPUTER SCIENCE');

INSERT INTO "group"
    (id,number,name,faculty)
VALUES
    (1,111,'P-18',1),
    (2,112,'P-17',1),
    (3,221,'CS-18',2);

INSERT INTO student
    (id,first_name,last_name,birth_day,"group")
VALUES
    (1,'Ivan','Ivanov','2001-09-28',1),
    (2,'Petr','Petrov','2001-09-28',1),
    (3,'Anatolii','Anatatoliev','2001-09-28',1),
    (4,'Mihail','Svetlov','2001-09-28',2),
    (5,'Fedor','Fedorov','2001-09-28',2),
    (6,'Grigori','Grigoriev','2001-09-28',2),
    (7,'Ibragim','Ibragimov','2001-09-28',3),
    (8,'Andrey','Andreev','2001-09-28',3),
    (9,'Anatolii','Anatatoliv','2001-09-28',3);

INSERT INTO course
    (id,name,description,faculty)
VALUES
    (1,'COURSE 1','SUPER-COURSE',1),
    (2,'COURSE 2','SUPER-COURSE',1),
    (3,'COURSE 3','SIMPLE-COURSE',2),
    (4,'COURSE 4','SIMPLE-COURSE',2);

INSERT INTO teacher
    (id,first_name,last_name,course,faculty)
VALUES
    (1,'Filimon','Filimonov',1,1),
    (2,'Arsenii','Arseniev',2,1),
    (3,'Alex','Alexandrov',3,2),
    (4,'Nikolai','Nikolaev',4,2);

INSERT INTO classroom
    (id,number,capacity)
VALUES
    (1,11,30),
    (2,12,40);

INSERT INTO schedule
    (id,"date","time","group",teacher,course,classroom)
VALUES
    (1,'2001-09-28','04:00:00',1,1,1,1),
    (2,'2001-09-27','04:00:00',1,1,1,1),
    (3,'2001-09-26','04:00:00',1,1,1,1),
    (4,'2001-09-25','04:00:00',1,1,1,1);