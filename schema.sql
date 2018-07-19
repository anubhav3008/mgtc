create table users(
  id VARCHAR(10) not null,
  name varchar(50) not null ,
  is_active boolean default true
);

create table meetings(
  id serial primary key ,
  timing timestamp,
  ttm_name varchar(50),
  ttm_id varchar(10),
  grammarian_name varchar(50),
  grammarian_id varchar(10),
  ah_counter_name varchar(50),
  ah_counter_id varchar(10),
  tmod_name varchar(50),
  tmod_id varchar(10),
  timer_name varchar(50),
  timer_id varchar(10),
  ge_name varchar(50),
  ge_id varchar(10),
  theme varchar(150),
  venue varchar(150)
);

create table speeches(
    id serial primary key ,
    meeting_id integer,
    project_name varchar(50),
    speaker_name varchar(50),
    speaker_id varchar(10),
    evaluator_name varchar(50),
    evaluator_id varchar(10),
    "date" date,
    time_min integer,
    time_max integer
  );


create table toastmasters_goals(
  id serial primary key ,
  user_id varchar(10),
  user_name varchar(50),
  project_name varchar(50),
  date date
);


