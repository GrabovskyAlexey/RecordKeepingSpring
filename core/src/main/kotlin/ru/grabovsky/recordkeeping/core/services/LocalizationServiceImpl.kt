package ru.grabovsky.recordkeeping.core.services

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.core.services.interfaces.LocalizationService

@Service
class LocalizationServiceImpl(
    private val messageSource: MessageSource
) : LocalizationService {
    override fun getMessage(key: String, args: Array<Any>?): String {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale())
    }

    override fun getErrorMessage(key: String, args: Array<Any>?): String {
        return messageSource.getMessage("Errors.$key", args, LocaleContextHolder.getLocale())
    }
}