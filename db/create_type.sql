CREATE TABLE IF NOT EXISTS types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO types(name) VALUES ('Две машины');
INSERT INTO types(name) VALUES ('Машина и человек');
INSERT INTO types(name) VALUES ('Машина и велосипед');