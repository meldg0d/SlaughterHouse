DROP SCHEMA IF EXISTS slaughterhouse CASCADE;
CREATE SCHEMA slaughterhouse;
SET search_path TO slaughterhouse;

DROP TABLE IF EXISTS Animal;

CREATE TABLE Animal (
                        id SERIAL PRIMARY KEY,
                        type VARCHAR(50) NOT NULL,
                        weight DOUBLE PRECISION NOT NULL
);

-- Dummy data for testing animal table
INSERT INTO Animal (id, type, weight) VALUES (1, 'CHICKEN', 20);
INSERT INTO Animal (id, type, weight) VALUES (2, 'COW', 400);
INSERT INTO Animal (id, type, weight) VALUES (3, 'PIG', 200);
