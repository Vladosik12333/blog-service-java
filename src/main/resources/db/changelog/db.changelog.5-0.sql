-- liquibase formatted sql

-- changeset vbabiak:5

-- Modify table users. Delete is_removed.

ALTER TABLE users
    DROP COLUMN role;

ALTER TABLE users
    ADD COLUMN role VARCHAR(64) NOT NULL DEFAULT 'USER';
