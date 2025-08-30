-- liquibase formatted sql

-- changeSet enot:30.08.2025_1

CREATE TABLE rule(
    id SERIAL PRIMARY KEY,
    query TEXT NOT NULL,
    arguments TEXT NOT NULL,
    negate BOOLEAN
    );

CREATE TABLE recommendation(
    id SERIAL PRIMARY KEY,
    product_id SERIAL,
    product_name TEXT NOT NULL,
    product_text TEXT NOT NULL
    );
