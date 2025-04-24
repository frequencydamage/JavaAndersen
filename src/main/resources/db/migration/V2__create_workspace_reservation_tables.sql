CREATE TABLE IF NOT EXISTS workspace (
    id INTEGER PRIMARY KEY,
    price DOUBLE PRECISION NOT NULL,
    workspace_type VARCHAR(255) NOT NULL,
    is_available BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS reservation (
    reservation_id INTEGER PRIMARY KEY,
    workspace_id INTEGER NOT NULL UNIQUE,
    CONSTRAINT fk_workspace
        FOREIGN KEY (workspace_id)
        REFERENCES workspace(id)
        ON DELETE CASCADE
);