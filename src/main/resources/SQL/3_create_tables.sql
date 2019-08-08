CREATE TABLE faculty
(
    id serial PRIMARY KEY,
    name character varying(50) NOT NULL
);

CREATE TABLE groups
(
    id serial PRIMARY KEY,
    number integer NOT NULL,
    name character varying(50) NOT NULL,
    faculty integer REFERENCES faculty(id)
	on delete set null
);

CREATE TABLE student
(
    id serial PRIMARY KEY,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birth_day date NOT NULL,
    group_id integer REFERENCES groups(id)
	on delete set null
);

CREATE TABLE course
(
    id serial PRIMARY KEY,
    name character varying(50) NOT NULL,
    description character varying (150)NOT NULL,
    faculty integer REFERENCES faculty(id)
	on delete set null
);

CREATE TABLE teacher
(
    id serial PRIMARY KEY,
    first_name character varying(50)NOT NULL,
    last_name character varying(50)NOT NULL,
    course integer REFERENCES course(id)
	on delete set null,
    faculty integer REFERENCES faculty(id)
	on delete set null
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
    lecture_date date NOT NULL,
    lecture_time time NOT NULL,
    group_id integer REFERENCES groups(id)
	on delete set null,
    teacher integer REFERENCES teacher(id)
	on delete set null,
    course integer REFERENCES course(id)
	on delete set null,
    classroom integer REFERENCES classroom(id)
	on delete set null
);  
