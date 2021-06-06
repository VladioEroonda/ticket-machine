DROP TABLE IF EXISTS reg_confirmation_token;
DROP TABLE IF EXISTS user_info;

DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS ticket_message;
DROP TABLE IF EXISTS role_list;
DROP TABLE IF EXISTS ticket_comments;

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
    ticket_id INTEGER NOT NULL,
    comment_author_id INTEGER NOT NULL,
    comment_date TIMESTAMP NOT NULL,
    comment_message VARCHAR (2000),
    PRIMARY KEY (comment_id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),
    FOREIGN KEY (comment_author_id) REFERENCES user_info(user_id)
);

CREATE TABLE reg_confirmation_token(
                                       token_id SERIAL,
                                       token varchar(300) NOT NULL,
                                       token_created TIMESTAMP NOT NULL,
                                       token_expired TIMESTAMP NOT NULL,
                                       token_confirmed TIMESTAMP,
                                       token_user_id INTEGER NOT NULL,
                                       PRIMARY KEY (token_id),
                                       FOREIGN KEY(token_user_id) REFERENCES user_info(user_id)
);

CREATE TABLE reset_password_confirmation_token(
                                       token_id SERIAL,
                                       token varchar(300) NOT NULL,
                                       token_created TIMESTAMP NOT NULL,
                                       token_expired TIMESTAMP NOT NULL,
                                       token_confirmed TIMESTAMP,
                                       token_user_id INTEGER NOT NULL,
                                       PRIMARY KEY (token_id),
                                       FOREIGN KEY(token_user_id) REFERENCES user_info(user_id)
);

-- INSERT INTO roles_list (role_name) VALUE (ROLE_USER);
-- INSERT INTO roles_list (role_name) VALUE (ROLE_ENGINEER);
-- INSERT INTO roles_list (role_name) VALUE (ROLE_ADMIN);