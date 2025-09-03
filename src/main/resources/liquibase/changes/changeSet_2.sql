-- liquibase formatted sql

-- changeSet enot:04.09.2025_2

CREATE TABLE userRecommendations(
    id SERIAL PRIMARY KEY,
    user_id UUID,
    first_name TEXT,
    last_name TEXT
    );