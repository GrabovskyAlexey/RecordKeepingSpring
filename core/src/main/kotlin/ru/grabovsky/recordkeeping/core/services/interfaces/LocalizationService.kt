package ru.grabovsky.recordkeeping.core.services.interfaces

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 23.02.2024 14:46
 */

interface LocalizationService {
    fun getMessage(key: String, args: Array<Any>? = null): String
    fun getErrorMessage(key: String, args: Array<Any>? = null): String
}