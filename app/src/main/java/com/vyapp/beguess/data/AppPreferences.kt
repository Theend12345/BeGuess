package com.vyapp.beguess.data

import android.content.Context
import android.content.SharedPreferences
import com.vyapp.beguess.domain.model.UserRegisterDomain

private const val APP_PREFS = "APP_PREFERENCES"
private const val USERNAME = "USER_name"
private const val PASSWORD = "PASSword"
private const val DEFAULT = ""

class AppPreferences(context: Context) {

    var data: SharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)

    fun saveUser(username: String, password: String) {
        data.edit().putString(USERNAME, username).apply()
        data.edit().putString(PASSWORD, password).apply()
    }

    fun getUser(): UserRegisterDomain {

        val username = data.getString(USERNAME, DEFAULT) ?: DEFAULT
        val password = data.getString(PASSWORD, DEFAULT) ?: DEFAULT

        return UserRegisterDomain(username, password)

    }

    fun leaveUser() {
        data.edit().putString(USERNAME, DEFAULT).apply()
        data.edit().putString(PASSWORD, DEFAULT).apply()
    }

}