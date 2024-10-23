DROP SCHEMA IF EXISTS slaughterhouse CASCADE;
CREATE SCHEMA slaughterhouse;
SET SCHEMA 'slaughterhouse';
DROP TABLE if exists Animal;

CREATE TABLE Animal (
id SERIAL PRIMARY KEY,
type VARCHAR(50) NOT NULL,
weight DOUBLE PRECISION NOT NULL
);


-- Dummy data for testing animal table
insert into animal(id, type, weight) values(1, 'CHICKEN', 20);
insert into animal(id, type, weight) values(2, 'COW', 400);
insert into animal(id, type, weight) values(3, 'PIG', 200);