-- liquibase formatted sql

-- changeset grabovsky.alexey:init_db_scheme
CREATE TYPE direction AS ENUM (
    'IN',
    'OUT',
    'SPECIAL'
    );

CREATE TABLE organizations
(
    id         bigserial    NOT NULL,
    name       varchar(250) NOT NULL,
    enabled    boolean      NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    CONSTRAINT organization_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE organizations IS 'Таблица организации';

CREATE TABLE users
(
    id              bigserial           NOT NULL,
    username        varchar(250) UNIQUE NOT NULL,
    email           varchar(250) UNIQUE NOT NULL,
    password        varchar(250)        NOT NULL,
    enabled         boolean             NOT NULL,
    activated       boolean             NOT NULL,
    activation_code varchar(250)        NOT NULL,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE users IS 'Таблица пользователей';

CREATE TABLE contractors
(
    id         bigserial    NOT NULL,
    name       varchar(100) NOT NULL,
    organization_id bigint       NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    CONSTRAINT contractors_pkey PRIMARY KEY (id),
    CONSTRAINT fk_contractors_organization_id FOREIGN KEY (organization_id) REFERENCES organizations (id)
);
COMMENT ON TABLE contractors IS 'Контрагенты';

CREATE TABLE employees
(
    id         bigserial    NOT NULL,
    name       varchar(100) NOT NULL,
    organization_id bigint       NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT fk_employee_organization_id FOREIGN KEY (organization_id) REFERENCES organizations (id)
);
COMMENT ON TABLE employees IS 'Сотрудники организации';

CREATE TABLE invites
(
    id          bigserial    NOT NULL,
    email       varchar(100) NOT NULL,
    invite_code varchar(64)  NOT NULL,
    organization_id  bigint       NOT NULL,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp,
    CONSTRAINT invite_pkey PRIMARY KEY (id),
    CONSTRAINT fk_organization_invite FOREIGN KEY (organization_id) REFERENCES organizations (id)
);
COMMENT ON TABLE invites IS 'Приглашения';

CREATE TABLE projects
(
    id          bigserial   NOT NULL,
    short_name  varchar(15) NOT NULL,
    full_name   varchar(250),
    description text,
    organization_id  bigint      NOT NULL,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp,
    CONSTRAINT projects_pkey PRIMARY KEY (id),
    CONSTRAINT fk_projects_organization_id FOREIGN KEY (organization_id) REFERENCES organizations (id)
);
COMMENT ON TABLE projects IS 'Проекты организации';

CREATE TABLE records
(
    id            bigserial    NOT NULL,
    direction     direction    NOT NULL,
    mail_number   varchar(50)  NOT NULL,
    reg_date      date         NOT NULL,
    subject       varchar(250) NOT NULL,
    reply_id      bigint,
    reply_to_id   bigint,
    mail_date     date         NOT NULL,
    description   text,
    color         varchar(7) default '#FFFFFF',
    project_id    bigint,
    employee_id   bigint,
    contractor_id bigint,
    organization_id    bigint       NOT NULL,
    author_id     bigint       NOT NULL,
    created_at    timestamp  default current_timestamp,
    updated_at    timestamp  default current_timestamp,
    CONSTRAINT records_pkey PRIMARY KEY (id),
    CONSTRAINT fk_records_reply_id FOREIGN KEY (reply_id) REFERENCES records (id),
    CONSTRAINT fk_records_reply_to_id FOREIGN KEY (reply_to_id) REFERENCES records (id),
    CONSTRAINT fk_records_organization_id FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT fk_records_contractors_id FOREIGN KEY (contractor_id) REFERENCES contractors (id),
    CONSTRAINT fk_records_employee_id FOREIGN KEY (employee_id) REFERENCES employees (id),
    CONSTRAINT fk_records_project_id FOREIGN KEY (project_id) REFERENCES projects (id),
    CONSTRAINT fk_records_author_id FOREIGN KEY (author_id) REFERENCES users (id)
);
COMMENT ON TABLE records IS 'Таблица для хранения записей';

CREATE TABLE files
(
    id         bigserial    NOT NULL,
    record_id  bigint       NOT NULL,
    path       varchar(250) NOT NULL,
    filename   varchar(250) NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    CONSTRAINT files_pkey PRIMARY KEY (id),
    CONSTRAINT fk_files_record_id FOREIGN KEY (record_id) REFERENCES records (id)
);
COMMENT ON TABLE files IS 'Связанные с записью файлы';

CREATE TABLE roles
(
    id          bigserial    NOT NULL,
    name        varchar(250) NOT NULL,
    description varchar(250) NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE roles IS 'Таблица для ролей пользователей';

CREATE TABLE organization_roles
(
    id          bigserial    NOT NULL,
    name        varchar(250) NOT NULL,
    description varchar(250) NOT NULL,
    created_at  timestamp DEFAULT (current_timestamp),
    updated_at  timestamp DEFAULT (current_timestamp),
    CONSTRAINT organization_roles_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE organization_roles IS 'Таблица для ролей организаций';

CREATE TABLE authorities
(
    id          bigserial    NOT NULL,
    type        varchar(50)  NOT NULL,
    description varchar(250) NOT NULL,
    CONSTRAINT authorities_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE authorities IS 'Таблица прав для пользователей и ролей';

CREATE TABLE user_info
(
    user_id    bigint NOT NULL,
    name       varchar(250),
    surname    varchar(250),
    phone      varchar(20),
    city       varchar(250),
    birthday   date,
    reg_date   date   NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    CONSTRAINT fk_user_details_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);
COMMENT ON TABLE user_info IS 'Данные пользователя';

CREATE TABLE users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_role_user_id FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_user_role_id FOREIGN KEY (user_id) REFERENCES users (id)
);
COMMENT ON TABLE users_roles IS 'ManyToMany для связи пользователей и ролей';

CREATE TABLE roles_authorities
(
    authority_id bigint NOT NULL,
    role_id      bigint NOT NULL,
    CONSTRAINT roles_authorities_pkey PRIMARY KEY (authority_id, role_id),
    CONSTRAINT fk_role_authority_id FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_authority_role_id FOREIGN KEY (authority_id) REFERENCES authorities (id)
);
COMMENT ON TABLE roles_authorities IS 'ManyToMany для связи ролей и прав';

CREATE TABLE organization_roles_authorities
(
    authority_id    bigint NOT NULL,
    organization_role_id bigint NOT NULL,
    CONSTRAINT organization_roles_authorities_pkey PRIMARY KEY (authority_id, organization_role_id),
    CONSTRAINT fk_organization_role_authority_id FOREIGN KEY (organization_role_id) REFERENCES organization_roles (id),
    CONSTRAINT fk_authority_organization_role_id FOREIGN KEY (authority_id) REFERENCES authorities (id)
);
COMMENT ON TABLE organization_roles_authorities IS 'ManyToMany для связи ролей и прав организации';

CREATE TABLE user_actions
(
    id          bigserial    NOT NULL,
    user_id     bigint       NOT NULL,
    created_at  timestamp default current_timestamp,
    action      varchar(255) NOT NULL,
    description varchar      NOT NULL,
    CONSTRAINT user_logs_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_logs_id FOREIGN KEY (user_id) REFERENCES users (id)
);
COMMENT ON TABLE user_actions IS 'Логирование действий пользователей';

CREATE TABLE users_organizations
(
    user_id    bigint NOT NULL,
    organization_id bigint NOT NULL,
    CONSTRAINT users_organizations_pkey PRIMARY KEY (user_id, organization_id),
    CONSTRAINT fk_organization_user_id FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT fk_user_organization_id FOREIGN KEY (user_id) REFERENCES users (id)
);
COMMENT ON TABLE users_organizations IS 'ManyToMany для связи пользователей и организаций';

CREATE TABLE organizations_users_roles
(
    organization_id      bigint,
    user_id         bigint,
    organization_role_id bigint,
    CONSTRAINT organizations_users_roles_pkey PRIMARY KEY (organization_id, user_id, organization_role_id),
    CONSTRAINT fk_organizations_users_roles_organization_id FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT fk_organizations_users_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_organizations_users_roles_role_id FOREIGN KEY (organization_role_id) REFERENCES organization_roles (id)
);
COMMENT ON TABLE organizations_users_roles IS 'ManyToMany для связи пользователей, ролей и организаций';