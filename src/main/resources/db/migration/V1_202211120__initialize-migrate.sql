CREATE TABLE book
(
    id          UUID NOT NULL,
    title            VARCHAR(255),
    CONSTRAINT pk_book PRIMARY KEY (id)
);

CREATE TABLE author
(
    id        UUID NOT NULL,
    full_name        VARCHAR(255),
    CONSTRAINT pk_author PRIMARY KEY (id)
);

CREATE TABLE book_author
(
    book_id UUID REFERENCES book(id),
    author_id UUID REFERENCES author(id),
    PRIMARY KEY (book_id, author_id)
);