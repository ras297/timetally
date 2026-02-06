CREATE TABLE task
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    type        VARCHAR(15)    NOT NULL,
    status      VARCHAR(15)  NOT NULL,
    user_id     BIGINT       NOT NULL,
    create_date TIMESTAMP    NOT NULL DEFAULT NOW(),
    modify_date TIMESTAMP    NOT NULL DEFAULT NOW(),
    version     INT          NOT NULL DEFAULT 0,
    CONSTRAINT fk_task_user FOREIGN KEY (user_id) REFERENCES "app_user" (id)
);

CREATE INDEX idx_task_user_id ON task(user_id);
