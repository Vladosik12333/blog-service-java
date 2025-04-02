-- liquibase formatted sql

-- changeset vbabiak:4

-- Modify table users. Update the length of password

ALTER TABLE users
    DROP COLUMN password;

ALTER TABLE users
    ADD COLUMN password VARCHAR(264) NOT NULL DEFAULT '';