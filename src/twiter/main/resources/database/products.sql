CREATE TABLE IF NOT EXISTS tweets (
    id       VARCHAR(36)     NOT NULL PRIMARY KEY,
    text     VARCHAR(255) NOT NULL,
    user VARCHAR(255) NOT NULL,
    locale VARCHAR(255) NOT NULL,
    validation INT NOT NULL
);

CREATE TABLE IF NOT EXISTS tags (
    id       VARCHAR(36)     NOT NULL PRIMARY KEY,
    used INT NOT NULL
);

