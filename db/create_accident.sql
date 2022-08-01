CREATE TABLE IF NOT EXISTS accidents (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    text TEXT,
    address VARCHAR(500),
    type_id INT NOT NULL REFERENCES types(id)
);