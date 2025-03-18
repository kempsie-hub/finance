--liquibase formatted sql

--changeset kempegowdafs:12
CREATE TABLE user_expense_type_subscription (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    expense_type BIGINT,
    user_id BIGINT,
    subscription_id VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    FOREIGN KEY (expense_type) REFERENCES expense_type(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT user_expense_type_subscription_unique UNIQUE (expense_type, user_id, subscription_id)
);