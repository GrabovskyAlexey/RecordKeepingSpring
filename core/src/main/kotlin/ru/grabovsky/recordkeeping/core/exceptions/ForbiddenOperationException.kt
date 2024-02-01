package ru.grabovsky.recordkeeping.core.exceptions

open class ForbiddenOperationException(message: String = "Отсутствуют необходимые права доступа") : RuntimeException(message)
