-- liquibase formatted sql

-- changeset vbabiak:1

-- Set schema to blog_service_java

SET search_path TO blog_service_java;

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
    user_id BIGINT REFERENCES users (id)
);

CREATE TABLE reactions (
    id SERIAL PRIMARY KEY,
    type VARCHAR(32),
    post_id BIGINT REFERENCES posts (id),
    user_id BIGINT REFERENCES users (id)
);