-- liquibase formatted sql

-- changeset grabovsky.alexey:add_users
INSERT INTO users (email, password, username, enabled, activated, activation_code)
VALUES ('admin@test.ru', '$2a$12$2IfNzxSnMcn/rpSKT9EvuOMnIR.x6ZHtXUul6ykKsEjAB8pOTOMaO', 'admin', true, true,
        'temp'), -- pass: admin
       ('user@test.ru', '$2a$12$0lCh0ZBnMJzs0gnluRi1q.00DLal0ILpBWg7xUPlfYv7aKdMQUvPW', 'user', true, true,
        'temp'); -- pass: user;

-- changeset grabovsky.alexey:add_company
INSERT INTO companies (name, enabled)
VALUES ('Тестовая компания', true);


-- changeset grabovsky.alexey:add_application_roles
INSERT INTO roles (name, description)
VALUES ('ROLE_UNACTIVATED_USER', 'Зарегистрированный пользователь не подтвердивший адрес электронной почты'),   -- 1
       ('ROLE_ACTIVATED_USER', 'Зарегистрированный пользователь подтвердивший адрес электронной почты'),        -- 2
       ('ROLE_APP_ADMIN', 'Администратор приложения');                                                          -- 3

-- changeset grabovsky.alexey:add_authorities
INSERT INTO authorities (type, description)
VALUES ('EDIT_PROFILE', 'Редактирование профиля'),
       ('REGISTER_COMPANY', 'Регистрация организации'),
       ('ADD_RECORD', 'Добавление записей'),
       ('ADD_PROJECT', 'Добавление проектов'),
       ('EDIT_PROJECT', 'Изменение проектов'),
       ('ADD_EMPLOYEE', 'Добавление сотрудников'),
       ('EDIT_EMPLOYEE', 'Изменение сотрудников'),
       ('ADD_CONTRACTOR', 'Добавление контрагентов'),
       ('EDIT_CONTRACTOR', 'Редактирование контрагентов'),
       ('EDIT_RECORD', 'Редактирование записей'),
       ('INVITE_USER', 'Приглашение пользователй в организацию'),
       ('ADD_USER', 'Добавление пользователей в организацию'),
       ('EDIT_COMPANY', 'Изменение организации'),
       ('EDIT_USER_RIGHT', 'Изменение прав пользователей организации'),
       ('REMOVE_USER_FROM_COMPANY', 'Открепление пользователей от организации'),
       ('REMOVE_PROJECT', 'Удаление проектов'),
       ('REMOVE_EMPLOYEE', 'Удаление сотрудников'),
       ('REMOVE_CONTRACTOR', 'Удаление контрагентов'),
       ('REMOVE_RECORD', 'Удаление записей');

-- changeset grabovsky.alexey:add_company_roles
INSERT INTO company_roles (name, description)
VALUES ('ROLE_COMPANY_USER', 'Пользователь организации'),        -- 1
       ('ROLE_COMPANY_MANAGER', 'Менеджер организации'),         -- 2
       ('ROLE_COMPANY_ADMIN', 'Администратор организации');      -- 3

-- changeset grabovsky.alexey:link_roles_and_authorities
INSERT INTO roles_authorities (role_id, authority_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (3, 5),
       (3, 6),
       (3, 7),
       (3, 8),
       (3, 9),
       (3, 10),
       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 15),
       (3, 16),
       (3, 17),
       (3, 18),
       (3, 19);

-- changeset grabovsky.alexey:link_users_and_application_roles
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 3),
       (2, 2);

-- changeset grabovsky.alexey:link_company_roles_and_authorities
INSERT INTO company_roles_authorities (company_role_id, authority_id)
VALUES (1, 3),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (2, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (2, 11),
       (2, 12),
       (3, 3),
       (3, 4),
       (3, 5),
       (3, 6),
       (3, 7),
       (3, 8),
       (3, 9),
       (3, 10),
       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 15),
       (3, 16),
       (3, 17),
       (3, 18),
       (3, 19);

-- changeset grabovsky.alexey:link_user_company_roles_and_company
INSERT INTO companies_users_roles (company_id, user_id, company_role_id) VALUES (1, 2, 3);

-- changeset grabovsky.alexey:link_user_and_company
INSERT INTO users_companies (user_id, company_id) VALUES (2, 1);