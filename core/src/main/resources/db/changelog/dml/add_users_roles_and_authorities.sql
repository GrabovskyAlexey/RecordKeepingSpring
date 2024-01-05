-- liquibase formatted sql

-- changeset grabovsky.alexey:add user and roles
INSERT INTO users (email, password, username, enabled, activated)
VALUES ('newtest@test.ru', '$2a$12$0lCh0ZBnMJzs0gnluRi1q.00DLal0ILpBWg7xUPlfYv7aKdMQUvPW', 'user', true, true);
INSERT INTO roles (name, description)
VALUES ('ROLE_UNACTIVATED_USER', 'Пользователь не подтвердивший адрес электронной почты'),
       ('ROLE_REGISTERED_USER', 'Зарегистрированный пользователь'),
       ('ROLE_COMPANY_USER', 'Пользователь организации'),
       ('ROLE_COMPANY_MANAGER', 'Менеджер организации'),
       ('ROLE_COMPANY_ADMIN', 'Администратор организации'),
       ('ROLE_APP_ADMIN', 'Администратор приложения');
INSERT INTO authorities (name, description)
VALUES ('editProfile', 'Редактирование профиля'),
       ('registerCompany', 'Регистрация организации'),
       ('addRecord', 'Добавление записей'),
       ('addProject', 'Добавление проектов'),
       ('editProject', 'Изменение проектов'),
       ('addEmployee', 'Добавление сотрудников'),
       ('editEmployee', 'Изменение сотрудников'),
       ('addContractors', 'Добавление контрагентов'),
       ('editContractors', 'Редактирование контрагентов'),
       ('editRecord', 'Редактирование записей'),
       ('inviteUser', 'Приглашение пользователй в организацию'),
       ('addUser', 'Добавление пользователей в организацию'),
       ('editCompany', 'Изменение организации'),
       ('editUserRights', 'Изменение прав пользователей организации'),
       ('removeUserFromCompany', 'Открепление пользователей от организации'),
       ('removeUserFromCompany', 'Открепление пользователей от организации'),
       ('removeProject', 'Удаление проектов'),
       ('removeEmployee', 'Удаление сотрудников'),
       ('removeContractor', 'Удаление контрагентов'),
       ('removeRecord', 'Удаление записей');
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 6);

INSERT INTO roles_authorities (role_id, authority_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 3),
       (4, 1),
       (4, 3),
       (4, 4),
       (4, 5),
       (4, 6),
       (4, 7),
       (4, 8),
       (4, 9),
       (4, 10),
       (4, 11),
       (4, 12),
       (5, 1),
       (5, 3),
       (5, 4),
       (5, 5),
       (5, 6),
       (5, 7),
       (5, 8),
       (5, 9),
       (5, 10),
       (5, 11),
       (5, 12),
       (5, 13),
       (5, 14),
       (5, 15),
       (5, 16),
       (5, 17),
       (5, 18),
       (5, 19),
       (5, 20),
       (6, 1),
       (6, 2),
       (6, 3),
       (6, 4),
       (6, 5),
       (6, 6),
       (6, 7),
       (6, 8),
       (6, 9),
       (6, 10),
       (6, 11),
       (6, 12),
       (6, 13),
       (6, 14),
       (6, 15),
       (6, 16),
       (6, 17),
       (6, 18),
       (6, 19),
       (6, 20);