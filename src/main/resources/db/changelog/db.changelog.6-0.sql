-- liquibase formatted sql

-- changeset vbabiak:6

-- Add admin user.
-- hashed password is admin

INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$12$RdABiL9H/RNZbWbRsSx/QeB3zgQ9dcTxgCmHdrKydNB4M7SjKcsma', 'ADMIN');
