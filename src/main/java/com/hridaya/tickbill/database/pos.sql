USE pos;

CREATE TABLE user
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    username   CHAR(20),
    password   VARCHAR(50),
    role       VARCHAR(10),
    address    VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    phone_no   VARCHAR(15)  NOT NULL
);

INSERT INTO user (first_name, last_name, username, password, role, address, email, phone_no)
VALUES ('admin',
        'admin',
        'admin',
        'admin123',
        'admin',
        'earth',
        'admin@123.com',
        '1234567890');

CREATE TABLE inventory
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(255) UNIQUE NOT NULL,
    rate     DECIMAL(10, 2)      NOT NULL,
    quantity INT UNSIGNED        NOT NULL DEFAULT 0
);
INSERT INTO inventory (name, rate, quantity)
VALUES ('French Fries', 19.99, 150),
       ('Fried Chicken', 29.50, 75),
       ('Burger', 15.00, 200),
       ('Steak', 45.00, 10);

CREATE TABLE sales
(
    invoice_id    INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255)   NOT NULL,
    total_bill    DECIMAL(10, 2) NOT NULL,
    status        VARCHAR(10)    NOT NULL,
    due           DECIMAL(10, 2) NOT NULL
);

CREATE TABLE invoice_ids
(
    i_id       INT PRIMARY KEY AUTO_INCREMENT,
    invoice_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (invoice_id) REFERENCES sales (invoice_id)
);


