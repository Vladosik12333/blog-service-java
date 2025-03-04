-- liquibase formatted sql

-- changeset vbabiak:3

-- Set schema to blog_service_java

SET search_path TO blog_service_java;

-- Modify table users. Delete is_removed.

ALTER TABLE users
    DROP COLUMN role;

ALTER TABLE users
    ADD COLUMN role VARCHAR(64) NOT NULL DEFAULT 'USER';
