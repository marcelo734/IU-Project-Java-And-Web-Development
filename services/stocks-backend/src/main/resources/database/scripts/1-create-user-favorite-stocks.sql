CREATE TABLE user_favorite_stocks (
    id INT NOT NULL,
    symbol VARCHAR(3) NOT NULL,
    created_at timestamp default CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
)
