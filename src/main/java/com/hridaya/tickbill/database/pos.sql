CREATE DATABASE pos;
USE pos;

-- User table creation
CREATE TABLE user
(
    user_id      INT PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(255)               NOT NULL,
    last_name    VARCHAR(255)               NOT NULL,
    username     VARCHAR(50) UNIQUE,
    password     VARCHAR(255),
    user_role    ENUM ('admin', 'employee') NOT NULL,
    user_address VARCHAR(255)               NOT NULL,
    user_email   VARCHAR(100) UNIQUE        NOT NULL,
    phone_no     VARCHAR(15)                NOT NULL
);

-- Insert an admin user
INSERT INTO user (first_name, last_name, username, password, user_role, user_address, user_email, phone_no)
VALUES ('admin', 'admin', 'admin', 'admin123', 'admin', 'earth', 'admin@123.com', '1234567890');

-- Inventory table creation
CREATE TABLE inventory
(
    product_id       INT PRIMARY KEY AUTO_INCREMENT,
    product_name     VARCHAR(255) UNIQUE NOT NULL,
    product_rate     DECIMAL(10, 2)      NOT NULL,
    product_quantity INT UNSIGNED        NOT NULL DEFAULT 0
);

-- Insert inventory items
INSERT INTO inventory (product_name, product_rate, product_quantity)
VALUES ('French Fries', 19.99, 150),
       ('Fried Chicken', 29.50, 75),
       ('Burger', 15.00, 200),
       ('Steak', 45.00, 10);

-- Sales table creation
CREATE TABLE sales
(
    invoice_id    INT PRIMARY KEY AUTO_INCREMENT,
    user_id       INT                                        NOT NULL,
    customer_name VARCHAR(255)                               NOT NULL,
    total_bill    DECIMAL(10, 2)                             NOT NULL,
    status        ENUM ('Pending', 'Paid', 'Due', 'Partial') NOT NULL DEFAULT 'Pending',
    due           DECIMAL(10, 2)                             NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

-- Invoice IDs table creation
CREATE TABLE invoice_ids
(
    i_id       INT PRIMARY KEY AUTO_INCREMENT,
    invoice_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (invoice_id) REFERENCES sales (invoice_id) ON DELETE CASCADE
);

-- Sales history table creation
CREATE TABLE sales_history
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    user_id          INT            NOT NULL,
    invoice_id       INT            NOT NULL,
    customer_name    VARCHAR(255)   NOT NULL,
    product_name     VARCHAR(255)   NOT NULL,
    product_rate     DECIMAL(10, 2) NOT NULL,
    product_quantity INT            NOT NULL CHECK (product_quantity > 0),
    product_price    DOUBLE         NOT NULL,
    total_bill       DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (invoice_id) REFERENCES sales (invoice_id) ON DELETE CASCADE,
    FOREIGN KEY (product_name) REFERENCES inventory (product_name) ON DELETE CASCADE
);

-- Create indexes for performance
CREATE INDEX idx_product_rate ON sales_history (product_rate);
CREATE INDEX idx_product_quantity ON sales_history (product_quantity);

-- User invoice table creation
CREATE TABLE user_invoice
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    invoice_id       INT                                        NOT NULL,
    customer_name    VARCHAR(255)                               NOT NULL,
    status           ENUM ('Pending', 'Paid', 'Due', 'Partial') NOT NULL DEFAULT 'Pending',
    total_bill       DECIMAL(10, 2)                             NOT NULL,
    due              DECIMAL(10, 2)                             NOT NULL,
    user_id          INT                                        NOT NULL,
    product_name     VARCHAR(255)                               NOT NULL,
    product_rate     DECIMAL(10, 2)                             NOT NULL,
    product_quantity INT                                        NOT NULL,
    product_price    DOUBLE                                     NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (invoice_id) REFERENCES sales (invoice_id) ON DELETE CASCADE,
    FOREIGN KEY (product_name) REFERENCES inventory (product_name) ON DELETE CASCADE
);
