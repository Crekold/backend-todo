-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-09-25 02:03:28.992

-- tables
-- Table: etiquetas
CREATE TABLE etiquetas (
    id_etiqueta int  NOT NULL,
    etiqueta varchar(100)  NOT NULL,
    CONSTRAINT etiquetas_pk PRIMARY KEY (id_etiqueta)
);

-- Table: tasks
CREATE TABLE tasks (
    id_task int  NOT NULL,
    name_task varchar(200)  NOT NULL,
    due_date date  NOT NULL,
    status varchar(30)  NOT NULL,
    users_id_user int  NOT NULL,
    etiquetas_id_etiqueta int  NOT NULL,
    CONSTRAINT tasks_pk PRIMARY KEY (id_task)
);

-- Table: users
CREATE TABLE users (
    id_user int  NOT NULL,
    user varchar(100)  NOT NULL,
    password varchar(100)  NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id_user)
);

-- foreign keys
-- Reference: tasks_etiquetas (table: tasks)
ALTER TABLE tasks ADD CONSTRAINT tasks_etiquetas FOREIGN KEY tasks_etiquetas (etiquetas_id_etiqueta)
    REFERENCES etiquetas (id_etiqueta);

-- Reference: tasks_users (table: tasks)
ALTER TABLE tasks ADD CONSTRAINT tasks_users FOREIGN KEY tasks_users (users_id_user)
    REFERENCES users (id_user);

-- End of file.

