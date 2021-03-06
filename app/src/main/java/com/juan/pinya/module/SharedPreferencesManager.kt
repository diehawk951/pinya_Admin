package com.juan.pinya.module

import android.content.SharedPreferences

class SharedPreferencesManager(private val sharedPreferences: SharedPreferences) {

    var stuffId: String
        get() = getValue(STUFF_ID_KEY, "")
        set(value) = setValue(STUFF_ID_KEY, value)

    var password: String
        get() = getValue(PASSWORD_KEY, "")
        set(value) = setValue(PASSWORD_KEY, value)

    var name: String
        get() = getValue(NAME_KEY, "")
        set(value) = setValue(NAME_KEY, value)

    var carId: String
        get() = getValue(CAR_ID_KEY, "")
        set(value) = setValue(CAR_ID_KEY, value)

    var isFirstLogin: Boolean
        get() = getValue(IS_FIRST_LOGIN_KEY, true)
        set(value) = setValue(IS_FIRST_LOGIN_KEY, value)

    private fun <T> setValue(key: String, value: T) {
        when (value) {
            is String -> {
                sharedPreferences.edit().putString(key, value).apply()
            }
            is Int -> {
                sharedPreferences.edit().putInt(key, value).apply()
            }
            is Boolean -> {
                sharedPreferences.edit().putBoolean(key, value).apply()
            }
        }
    }

    private inline fun <reified T> getValue(key: String, defValue: T): T {
        val value = when (defValue) {
            is String -> {
                sharedPreferences.getString(key, defValue) as? T
            }
            is Int -> {
                sharedPreferences.getInt(key, defValue) as? T
            }
            is Boolean -> {
                sharedPreferences.getBoolean(key, defValue) as? T
            }
            else -> {
                defValue
            }
        }
        return value ?: defValue
    }

    companion object {
        private const val STUFF_ID_KEY = "stuffId"
        private const val PASSWORD_KEY = "password"
        private const val NAME_KEY = "name"
        private const val IS_FIRST_LOGIN_KEY = "isFirstLogin"
        private const val CAR_ID_KEY = "carId"
    }
}