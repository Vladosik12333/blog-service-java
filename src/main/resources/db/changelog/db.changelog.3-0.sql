-- liquibase formatted sql

-- changeset vbabiak:3

-- Modify tables. Delete is_removed.

ALTER TABLE users
    DROP COLUMN is_removed;

ALTER TABLE posts
    DROP COLUMN is_removed;

ALTER TABLE reactions
    DROP COLUMN is_removed;

-- Modify table users. Add role.

ALTER TABLE users
    ADD COLUMN role VARCHAR(64);

-- Update users data to set new column role

UPDATE users
SET role = 'USER';

UPDATE users
SET role = 'ADMIN'
WHERE id = 2 OR id = 7;
