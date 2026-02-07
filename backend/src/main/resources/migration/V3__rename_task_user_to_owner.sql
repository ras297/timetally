ALTER TABLE task
    RENAME COLUMN user_id TO owner_id;

ALTER TABLE task
    DROP CONSTRAINT fk_task_user,
    ADD CONSTRAINT fk_task_owner
        FOREIGN KEY (owner_id) REFERENCES app_user (id);

ALTER INDEX idx_task_user_id
    RENAME TO idx_task_owner_id;
