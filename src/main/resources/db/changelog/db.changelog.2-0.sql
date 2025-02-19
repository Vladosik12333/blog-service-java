-- liquibase formatted sql

-- changeset vbabiak:2

-- Set schema to blog_service_java

SET search_path TO blog_service_java;

-- Modify tables. Add is_removed, created_at and modified_at columns

ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW() NOT NULL;
ALTER TABLE users
    ADD COLUMN modified_at TIMESTAMP DEFAULT NOW() NOT NULL;
ALTER TABLE users
    ADD COLUMN is_removed BOOLEAN DEFAULT FALSE NOT NULL;

ALTER TABLE posts
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW() NOT NULL;
ALTER TABLE posts
    ADD COLUMN modified_at TIMESTAMP DEFAULT NOW() NOT NULL;
ALTER TABLE posts
    ADD COLUMN is_removed BOOLEAN DEFAULT FALSE NOT NULL;

ALTER TABLE reactions
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW() NOT NULL;
ALTER TABLE reactions
    ADD COLUMN modified_at TIMESTAMP DEFAULT NOW() NOT NULL;
ALTER TABLE reactions
    ADD COLUMN is_removed BOOLEAN DEFAULT FALSE NOT NULL;

-- Insert data

INSERT INTO users (username, password, first_name, last_name)
VALUES ('jsmith', 'password123', 'John', 'Smith'),
       ('amiller', 'securepass', 'Alice', 'Miller'),
       ('rwilliams', 'p@ssw0rd!', 'Robert', 'Williams'),
       ('esanchez', 'safepass123', 'Elena', 'Sanchez'),
       ('mjohnson', 'pass1234', 'Michael', 'Johnson'),
       ('lclark', 'strongpassword', 'Laura', 'Clark'),
       ('tkim', 'kim12345', 'Thomas', 'Kim'),
       ('jwilson', 'wilsonpass', 'Jessica', 'Wilson'),
       ('gdavis', 'davispass', 'George', 'Davis'),
       ('klee', 'leepassword', 'Karen', 'Lee');

INSERT INTO posts (title, description, user_id)
VALUES ('My First Post',
        'This is my very first post on the platform! I am excited to share my thoughts, ideas, and experiences with the community. Looking forward to connecting with everyone!',
        1),
       ('Tips for Learning SQL',
        'Learning SQL can seem daunting at first, but with the right approach, it becomes much easier. In this post, I will cover essential topics such as writing queries, understanding joins, and optimizing database performance.',
        2),
       ('Weekend Adventures',
        'This past weekend, I went on an amazing hiking trip to the mountains. The views were breathtaking, and I had the chance to disconnect from technology and enjoy nature. Here’s a detailed account of the trip and some photos!',
        3);


INSERT INTO reactions (type, post_id, user_id)
VALUES ('LIKE', 1, 2),
       ('INSIGHTFUL', 2, 1),
       ('DISLIKE', 3, 4),
       ('LIKE', 1, 3),
       ('LIKE', 2, 4),
       ('DISLIKE', 1, 5),
       ('INSIGHTFUL', 3, 2),
       ('LIKE', 2, 3),
       ('DISLIKE', 3, 5),
       ('INSIGHTFUL', 1, 4),
       ('LIKE', 3, 1),
       ('DISLIKE', 2, 5),
       ('INSIGHTFUL', 2, 3),
       ('LIKE', 3, 4),
       ('LIKE', 1, 5),
       ('DISLIKE', 2, 1),
       ('INSIGHTFUL', 3, 2),
       ('DISLIKE', 1, 3),
       ('LIKE', 2, 4),
       ('INSIGHTFUL', 3, 5);
