CREATE TABLE weather.users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(120) NOT NULL,
    password VARCHAR(120) NOT NULL
);