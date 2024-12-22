CREATE DATABASE pos;
USE pos;

CREATE TABLE users
(
    user_id      INT PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(255)                                         NOT NULL,
    last_name    VARCHAR(255)                                         NOT NULL,
    user_role    ENUM ('admin', 'employee')      DEFAULT 'employee'   NOT NULL,
    user_address VARCHAR(255)                                         NOT NULL,
    user_email   VARCHAR(100) UNIQUE                                  NOT NULL,
    phone_no     VARCHAR(15) UNIQUE                                   NOT NULL,
    status       ENUM ('verified', 'unverified') DEFAULT 'unverified' NOT NULL,
    username     VARCHAR(50) UNIQUE,
    password     VARCHAR(255)
);

INSERT INTO users (first_name, last_name, username, password, status, user_role, user_address, user_email, phone_no)
VALUES ('admin', 'admin', 'admin', 'admin123', 'verified', 'admin', 'earth', 'admin@123.com', '1234567890');

CREATE TABLE products
(
    product_id       INT PRIMARY KEY AUTO_INCREMENT,
    product_name     VARCHAR(255) UNIQUE NOT NULL,
    product_rate     DECIMAL(10, 2)      NOT NULL,
    product_quantity INT UNSIGNED        NOT NULL DEFAULT 0
);

INSERT INTO products (product_name, product_rate, product_quantity)
VALUES ('French Fries', 19.99, 150),
       ('Fried Chicken', 29.50, 75),
       ('Burger', 15.00, 200),
       ('Steak', 45.00, 10);

-- For invoice section
CREATE TABLE sales
(
    invoice_id    INT PRIMARY KEY AUTO_INCREMENT,
    user_id       INT                                        NOT NULL,
    customer_name VARCHAR(255)                               NOT NULL,
    total_bill    DECIMAL(10, 2)                             NOT NULL,
    status        ENUM ('Pending', 'Paid', 'Due', 'Partial') NOT NULL DEFAULT 'Pending',
    due           DECIMAL(10, 2)                             NOT NULL,
    created_at    TIMESTAMP                                           DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- For generating invoice
CREATE TABLE sales_history
(
    sales_id         INT PRIMARY KEY AUTO_INCREMENT,
    invoice_id       INT            NOT NULL,
    user_id          INT            NOT NULL,
    customer_name    VARCHAR(255)   NOT NULL,
    SN               INT            NOT NULL,
    product_name     VARCHAR(255)   NOT NULL,
    product_rate     DECIMAL(10, 2) NOT NULL,
    product_quantity INT            NOT NULL CHECK (product_quantity > 0),
    product_price    DECIMAL(10, 2) NOT NULL,
    total_bill       DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES sales (invoice_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);


-- Per user invoice
CREATE VIEW user_invoice AS
SELECT s.invoice_id,
       s.customer_name,
       s.status,
       s.total_bill,
       s.due,
       u.user_id,
       CONCAT(u.first_name, ' ', u.last_name)                             AS user_name,
       sh.product_name,
       sh.product_rate,
       ROW_NUMBER() OVER (PARTITION BY s.invoice_id ORDER BY sh.sales_id) AS SN,
       sh.product_quantity,
       sh.product_price
FROM sales s
         JOIN users u ON s.user_id = u.user_id
         JOIN sales_history sh ON s.invoice_id = sh.invoice_id
ORDER BY s.invoice_id, sh.SN;
