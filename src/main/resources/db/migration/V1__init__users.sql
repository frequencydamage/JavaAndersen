CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password, user_type)
VALUES
    ('admin', '$2a$10$aAcjXzVnW5DEuhAcINDkAOGYl.m8XTpDgePIsQ77Npj.ZwHaEiJy6', 'ADMIN'),
    ('customer', '$2a$10$eA52qy3Hq5HWnKVToNwJ2uwLYybhMWkP.7yM20/7lTpbaqqKLkqu2', 'CUSTOMER');