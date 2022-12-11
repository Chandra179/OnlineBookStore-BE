CREATE TABLE book
(
    id               UUID NOT NULL,
    title            VARCHAR(255),
    CONSTRAINT pk_book PRIMARY KEY (id)
);

CREATE TABLE author
(
    id               UUID NOT NULL,
    full_name        VARCHAR(255),
    CONSTRAINT pk_author PRIMARY KEY (id)
);