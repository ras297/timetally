CREATE TABLE app_user
(
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(255),
    create_date TIMESTAMP NOT NULL,
    modify_date TIMESTAMP NOT NULL,
    version     INTEGER   NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_app_user_username ON app_user (username);
