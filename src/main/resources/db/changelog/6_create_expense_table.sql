--liquibase formatted sql

--changeset kempegowdafs:13
CREATE TABLE user_expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    expense_type BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    expense_type_subscription_id VARCHAR(100),
    amount NUMERIC(20,2),
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    FOREIGN KEY (expense_type) REFERENCES expense_type(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (expense_type_subscription_id) REFERENCES user_expense_type_subscription(id)
);