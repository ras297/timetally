CREATE TYPE task_status AS ENUM (
    'PENDING',
    'IN_PROGRESS',
    'COMPLETED',
    'CANCELLED',
    'OVERDUE'
    );

CREATE TYPE task_type AS ENUM (
    'WORK',
    'STUDY',
    'PERSONAL',
    'HEALTH',
    'SLEEP',
    'MEALS',
    'COMMUTE',
    'ERRANDS',
    'SOCIAL',
    'ENTERTAINMENT',
    'HOBBY',
    'ADMIN',
    'FINANCE',
    'UNPLANNED',
    'IDLE',
    'OTHER'
    );

CREATE TABLE task
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    type        task_type    NOT NULL,
    status      task_status  NOT NULL,
    user_id     BIGINT       NOT NULL,
    create_date TIMESTAMP    NOT NULL DEFAULT NOW(),
    modify_date TIMESTAMP    NOT NULL DEFAULT NOW(),
    version     INT          NOT NULL DEFAULT 0,
    CONSTRAINT fk_task_user FOREIGN KEY (user_id) REFERENCES "app_user" (id)
);

CREATE INDEX idx_task_user_id ON task(user_id);
