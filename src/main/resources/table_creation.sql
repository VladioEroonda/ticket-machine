DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS ticket_message;
DROP TABLE IF EXISTS role_list;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_info
(
    user_id       SERIAL,
    user_name     VARCHAR(40)  NOT NULL,
    user_surname  VARCHAR(40)  NOT NULL,
    user_email    VARCHAR(50) UNIQUE,
    user_password VARCHAR(300) NOT NULL,
    user_enabled  BOOLEAN,
    user_phone    VARCHAR      NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE roles_list
(
    role_id   SERIAL,
    role_name VARCHAR(40) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE user_roles
(
    user_id INTEGER,
    role_id INTEGER,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id),
    FOREIGN KEY (role_id) REFERENCES roles_list (role_id)
);

CREATE TABLE ticket_message
(
    ticket_message_id   SERIAL,
    ticket_message_text VARCHAR(5000) NOT NULL,
    PRIMARY KEY (ticket_message_id)
);

CREATE TABLE tickets
(
    ticket_id            SERIAL,
    ticket_creation_time TIMESTAMP    NOT NULL,
    ticket_closing_time  TIMESTAMP,
    ticket_client_id     INTEGER      NOT NULL,
    ticket_engineer_id   INTEGER,
    ticket_status        INTEGER      NOT NULL,
    ticket_subject       VARCHAR(200) NOT NULL,
    ticket_message_id    INTEGER,
    PRIMARY KEY (ticket_id),
    FOREIGN KEY (ticket_client_id) REFERENCES user_info (user_id),
    FOREIGN KEY (ticket_engineer_id) REFERENCES user_info (user_id),
    FOREIGN KEY (ticket_message_id) REFERENCES ticket_message (ticket_message_id)
);

CREATE TABLE ticket_comments(
    comment_id SERIAL,
    ticket_id NOT NULL,
    comment_message VARCHAR (2000),
    PRIMARY KEY (comment_id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
);


-- INSERT INTO users (user_name, user_surname, user_email, user_password, user_role, user_phone)
-- VALUES ('Vlad', 'Eroonder', 'vladio@site.com', 'vlad1234', 'CLIENT', '123456789');