-- liquibase formatted sql

-- changeset vbabiak:1

-- Drop tables before creating them

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS posts;

DROP TABLE IF EXISTS reactions;

-- Create tables

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(32) NOT NULL,
    first_name VARCHAR(128),
    last_name VARCHAR(128)
);

CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    description TEXT,
    user_id BIGINT REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE reactions (
    id SERIAL PRIMARY KEY,
    type VARCHAR(32),
    post_id BIGINT REFERENCES posts (id) ON DELETE CASCADE ,
    user_id BIGINT REFERENCES users (id) ON DELETE CASCADE
);