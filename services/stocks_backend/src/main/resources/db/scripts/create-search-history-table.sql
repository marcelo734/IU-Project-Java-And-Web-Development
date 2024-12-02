-- liquibase formatted sql
-- changeset marcelo.gontijo:enable-generate-uuid-extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- changeset marcelo.gontijo:1.0-create-search-history-table
CREATE TABLE stocks_search_history (
    id VARCHAR(128) not null primary key default uuid_generate_v4(),
    symbol VARCHAR(5) not null unique,
    name VARCHAR(100) not null,
    date TIMESTAMP not null default CURRENT_TIMESTAMP
)