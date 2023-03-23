CREATE TABLE product
(
    id       INTEGER PRIMARY KEY,
    name     TEXT    NOT NULL,
    price    NUMERIC NOT NULL
);

CREATE TABLE discount_card
(
    number              VARCHAR(50) PRIMARY KEY,
    discount_percentage INTEGER NOT NULL
);


INSERT INTO product (id, name, price)
VALUES
    (1, 'Product 1', 200),
    (2, 'Product 2', 200),
    (3, 'Product 3', 333),
    (4, 'Product 4', 400),
    (5, 'Product 5', 500);

INSERT INTO discount_card (number, discount_percentage)
VALUES
    ('card1', 10),
    ('card2', 20),
    ('card3', 30),
    ('card-1234', 30);