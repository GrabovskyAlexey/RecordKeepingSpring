package ru.grabovsky.recordkeeping.api.notification

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 06.01.2024
 */
enum class EmailType(val subject: String, val template: String) {
    ACTIVATE("Необходимо активировать учетную запись", "activation"),
    RESET_PASSWORD("Вам отправлено письмо для сброса пароля", "reset_password"),
    CHANGE_PASSWORD("Пароль успешно изменен", "change_password"),
    NEW_PASSWORD("Новый пароль для вашей учетной записи", "new_password"),
    INVITE("Вам отправлено приглашение", "invite");
}