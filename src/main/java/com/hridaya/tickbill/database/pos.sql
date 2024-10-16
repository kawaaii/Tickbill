USE pos;

CREATE TABLE role
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) UNIQUE NOT NULL
);
INSERT INTO role (role_name)
VALUES ('admin'),
       ('employee');

CREATE TABLE user
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(255) UNIQUE NOT NULL,
    role_id  INT,
    password VARCHAR(255),
    FOREIGN KEY (role_id) REFERENCES role (id)
);
INSERT INTO user (name, role_id, password)
VALUES ('admin', 1, 'admin123');

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
    invoice_id             INT PRIMARY KEY AUTO_INCREMENT,
    customer_name          VARCHAR(255)   NOT NULL,
    total_bill             DECIMAL(10, 2) NOT NULL,
    status                 VARCHAR(10)    NOT NULL,
    due                    DECIMAL(10, 2) NOT NULL
);

CREATE TABLE invoice_ids
(
    i_id INT PRIMARY KEY AUTO_INCREMENT,
    invoice_id  INT  NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (invoice_id) REFERENCES sales(invoice_id)
);


