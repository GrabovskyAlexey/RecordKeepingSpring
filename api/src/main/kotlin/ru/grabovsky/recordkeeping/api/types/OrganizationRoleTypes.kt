package ru.grabovsky.recordkeeping.api.types

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 17:49
 */
enum class OrganizationRoleTypes(val desc: String) {
    ROLE_ORGANIZATION_USER("Пользователь организации"),
    ROLE_ORGANIZATION_MANAGER("Менеджер организации"),
    ROLE_ORGANIZATION_ADMIN("Администратор организации")
}