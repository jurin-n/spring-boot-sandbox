DROP TABLE csv_content IF EXISTS;

CREATE TABLE csv_content  (
    content_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(20),
    description VARCHAR(256)
);