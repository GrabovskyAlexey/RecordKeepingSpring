CREATE TYPE "direction" AS ENUM (
    'IN',
    'OUT',
    'SPECIAL'
    );

CREATE TABLE "companies"
(
    "id"         bigserial    NOT NULL,
    "name"       varchar(250) NOT NULL,
    "enabled"    boolean      NOT NULL,
    "created_at" timestamp default current_timestamp,
    "updated_at" timestamp default current_timestamp,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE "companies" IS 'Таблица компании';

CREATE TABLE "users"
(
    "id"         bigserial           NOT NULL,
    "username"   varchar(250) UNIQUE NOT NULL,
    "email"      varchar(250) UNIQUE NOT NULL,
    "password"   varchar(250)        NOT NULL,
    "enabled"    boolean             NOT NULL,
    "activated"  boolean             NOT NULL,
    "company_id" bigint,
    "created_at" timestamp default current_timestamp,
    "updated_at" timestamp default current_timestamp,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT fk_users_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);
COMMENT ON TABLE "users" IS 'Таблица пользователей';

CREATE TABLE "contractors"
(
    "id"         bigserial    NOT NULL,
    "name"       varchar(250) NOT NULL,
    "company_id" bigint       NOT NULL,
    "created_at" timestamp default current_timestamp,
    "updated_at" timestamp default current_timestamp,
    CONSTRAINT contractors_pkey PRIMARY KEY (id),
    CONSTRAINT fk_contractors_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);
COMMENT ON TABLE "contractors" IS 'Контрагенты';

CREATE TABLE "employees"
(
    "id"         bigserial    NOT NULL,
    "name"       varchar(250) NOT NULL,
    "company_id" bigint       NOT NULL,
    "created_at" timestamp default current_timestamp,
    "updated_at" timestamp default current_timestamp,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT fk_employee_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);
COMMENT ON TABLE "employees" IS 'Сотрудники компании';

CREATE TABLE "invites"
(
    "id"          bigserial    NOT NULL,
    "email"       varchar(100) NOT NULL,
    "invite_code" varchar(64)  NOT NULL,
    "company_id"  bigint       NOT NULL,
    "created_at"  timestamp default current_timestamp,
    "updated_at"  timestamp default current_timestamp,
    CONSTRAINT invite_pkey PRIMARY KEY (id),
    CONSTRAINT fk_company_invite FOREIGN KEY (company_id) REFERENCES companies (id)
);
COMMENT ON TABLE "invites" IS 'Приглашения';

CREATE TABLE "projects"
(
    "id"         bigserial    NOT NULL,
    "name"       varchar(250) NOT NULL,
    "company_id" bigint       NOT NULL,
    "created_at" timestamp default current_timestamp,
    "updated_at" timestamp default current_timestamp,
    CONSTRAINT projects_pkey PRIMARY KEY (id),
    CONSTRAINT fk_projects_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);
COMMENT ON TABLE "projects" IS 'Проекты компании';

CREATE TABLE "records"
(
    "id"            bigserial    NOT NULL,
    "direction"     direction    NOT NULL,
    "mail_number"   varchar(250) NOT NULL,
    "reg_date"      date         NOT NULL,
    "title"         varchar(250) NOT NULL,
    "reply"         varchar(250),
    "reply_to"      varchar(250),
    "mail_date"     date         NOT NULL,
    "description"   varchar,
    "color"         varchar(7) default '#FFFFFF',
    "project_id"    bigint,
    "employee_id"   bigint,
    "contractor_id" bigint,
    "company_id"    bigint       NOT NULL,
    "author_id"     bigint       NOT NULL,
    "created_at"    timestamp  default current_timestamp,
    "updated_at"    timestamp  default current_timestamp,
    CONSTRAINT records_pkey PRIMARY KEY (id),
    CONSTRAINT fk_records_company_id FOREIGN KEY (company_id) REFERENCES companies (id),
    CONSTRAINT fk_records_contractors_id FOREIGN KEY (contractor_id) REFERENCES contractors (id),
    CONSTRAINT fk_records_employee_id FOREIGN KEY (employee_id) REFERENCES employees (id),
    CONSTRAINT fk_records_project_id FOREIGN KEY (project_id) REFERENCES projects (id),
    CONSTRAINT fk_records_author_id FOREIGN KEY (author_id) REFERENCES users (id)
);
COMMENT ON TABLE "records" IS 'Таблица для хранения записей';

CREATE TABLE "files"
(
    "id"         bigserial    NOT NULL,
    "record_id"  bigint       NOT NULL,
    "path"       varchar(250) NOT NULL,
    "filename"   varchar(250) NOT NULL,
    "created_at" timestamp default current_timestamp,
    "updated_at" timestamp default current_timestamp,
    CONSTRAINT files_pkey PRIMARY KEY (id),
    CONSTRAINT fk_files_record_id FOREIGN KEY (record_id) REFERENCES records (id)
);
COMMENT ON TABLE "files" IS 'Связанные с записью файлы';

CREATE TABLE "roles"
(
    "id"          bigserial    NOT NULL,
    "role"        varchar(250) NOT NULL,
    "description" varchar(250) NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE "roles" IS 'Таблица для ролей позльзователей';

CREATE TABLE "authorities"
(
    "id"          bigserial    NOT NULL,
    "authority"   varchar(250) NOT NULL,
    "description" varchar(250) NOT NULL,
    CONSTRAINT authorities_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE "authorities" IS 'Таблица прав для пользователей и ролей';

CREATE TABLE "user_info"
(
    "user_id"         bigint       NOT NULL,
    "name"            varchar(250) NOT NULL,
    "surname"         varchar(250) NOT NULL,
    "phone"           varchar(250),
    "city"            varchar(250),
    "birthday"        date,
    "reg_date"        date,
    "activation_code" varchar(250) NOT NULL,
    "created_at"      timestamp default current_timestamp,
    "updated_at"      timestamp default current_timestamp,
    CONSTRAINT fk_user_details_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);
COMMENT ON TABLE "user_info" IS 'Данные пользователя';

CREATE TABLE "users_roles"
(
    "user_id" bigint NOT NULL,
    "role_id" bigint NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_role_user_id FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_user_role_id FOREIGN KEY (user_id) REFERENCES users (id)
);
COMMENT ON TABLE "users_roles" IS 'ManyToMany для связи пользователей и ролей';

CREATE TABLE "roles_authorities"
(
    "authority_id" bigint NOT NULL,
    "role_id"      bigint NOT NULL,
    CONSTRAINT roles_authorities_pkey PRIMARY KEY (authority_id, role_id),
    CONSTRAINT fk_role_authority_id FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_authority_role_id FOREIGN KEY (authority_id) REFERENCES authorities (id)
);
COMMENT ON TABLE "roles_authorities" IS 'ManyToMany для связи ролей и прав';

CREATE TABLE "user_logs"
(
    "id"          bigserial    NOT NULL,
    "user_id"     bigint       NOT NULL,
    "created_at"  timestamp default current_timestamp,
    "action"      varchar(255) NOT NULL,
    "description" varchar      NOT NULL,
    CONSTRAINT user_logs_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_logs_id FOREIGN KEY (user_id) REFERENCES users (id)
);
COMMENT ON TABLE "user_logs" IS 'Логирование действий пользователей';