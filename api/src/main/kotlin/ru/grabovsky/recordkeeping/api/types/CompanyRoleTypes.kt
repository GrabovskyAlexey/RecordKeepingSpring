package ru.grabovsky.recordkeeping.api.types

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 17:49
 */
enum class CompanyRoleTypes(val desc: String) {
    ROLE_COMPANY_USER("Пользователь организации"),
    ROLE_COMPANY_MANAGER("Менеджер организации"),
    ROLE_COMPANY_ADMIN("Администратор организации")
}