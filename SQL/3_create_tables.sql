CREATE TABLE faculty
(
    id serial PRIMARY KEY,
    name character varying(50) NOT NULL
);

CREATE TABLE "group"
(
    id serial PRIMARY KEY,
    number integer NOT NULL,
    name character varying(50) NOT NULL,
    faculty integer REFERENCES faculty(id)
);

CREATE TABLE student
(
    id serial PRIMARY KEY,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birth_day date NOT NULL,
    "group" integer REFERENCES "group"(id)
);

CREATE TABLE course
(
    id serial PRIMARY KEY,
    name character varying(50) NOT NULL,
    description character varying (150)NOT NULL,
    faculty integer REFERENCES faculty(id)
);

CREATE TABLE teacher
(
    id serial PRIMARY KEY,
    first_name character varying(50)NOT NULL,
    last_name character varying(50)NOT NULL,
    course integer REFERENCES course(id),
    faculty integer REFERENCES faculty(id)
);

CREATE TABLE classroom
(
    id serial PRIMARY KEY,
    number integer UNIQUE NOT NULL,
    capacity integer NOT NULL
);

CREATE TABLE schedule
(
    id serial PRIMARY KEY,
    "date" date NOT NULL,
    "time" time NOT NULL,
    "group" integer REFERENCES "group"(id),
    teacher integer REFERENCES teacher(id),
    course integer REFERENCES course(id),
    classroom integer REFERENCES classroom(id)
);  
