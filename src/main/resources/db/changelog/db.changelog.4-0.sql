-- liquibase formatted sql

-- changeset vbabiak:3

-- Set schema to blog_service_java

SET search_path TO blog_service_java;

-- Modify table users. Update the length of password

ALTER TABLE users
    DROP COLUMN password;

ALTER TABLE users
    ADD COLUMN password VARCHAR(264) NOT NULL DEFAULT '';