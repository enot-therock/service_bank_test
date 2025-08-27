-- liquibase formatted sql

-- changeSet enot: 22.08.2025_01

CREATE TABLE recommendation (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    text TEXT NOT NULL
);