package com.example.agetech.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.agetech.model.Login
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserDataStore @Inject constructor(private val context: Context) {

    private val gson = Gson()

    suspend fun saveUser(login: Login) {
        context.dataStore.edit {
            val json = gson.toJson(login)
            it[USER_KEY] = json
        }
    }

    suspend fun getUser(): Login? {
        val json = context.dataStore.data.first()[USER_KEY]
        return if (json != null) {
            gson.fromJson(json, Login::class.java)
        } else {
            null
        }
    }

    companion object {
        private val USER_KEY = stringPreferencesKey("user_key")
    }
}