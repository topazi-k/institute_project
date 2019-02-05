CREATE TABLE faculties
(
    faculty_id serial PRIMARY KEY,
    faculty_name character varying(50) NOT NULL
);

CREATE TABLE groups
(
    group_id serial PRIMARY KEY,
    group_number integer NOT NULL,
    group_name character varying(50) NOT NULL,
    faculty_id integer REFERENCES faculties(faculty_id)
);

CREATE TABLE students
(
    student_id serial PRIMARY KEY,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birth_date date NOT NULL,
    group_id integer REFERENCES groups(group_id)
);

CREATE TABLE courses
(
    course_id serial PRIMARY KEY,
    course_name character varying(50) NOT NULL,
    descriprion character varying (150)NOT NULL,
    faculty_id integer REFERENCES faculties(faculty_id)
);

CREATE TABLE teachers
(
    teacher_id serial PRIMARY KEY,
    first_name character varying(50)NOT NULL,
    last_name character varying(50)NOT NULL,
    course_id integer REFERENCES courses(course_id),
    faculty_id integer REFERENCES faculties(faculty_id)
);

CREATE TABLE classrooms
(
    classroom_id serial PRIMARY KEY,
    classroom_number integer UNIQUE NOT NULL,
    capacity integer NOT NULL
);

CREATE TABLE schedule
(
    lecture_id serial PRIMARY KEY,
    lecture_date date NOT NULL,
    lecture_time time NOT NULL,
    group_id integer REFERENCES groups(group_id),
    teacher_id integer REFERENCES teachers(teacher_id),
    course_id integer REFERENCES courses(course_id),
    classroom_id integer REFERENCES classrooms(classroom_id)
);
    



    