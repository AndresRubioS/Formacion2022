package com.example.data.domain

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProfileRepository(private val context: Context) {

    private val Context.storeData: DataStore<Preferences> by preferencesDataStore(name = "ProfileSettings")
    private val KEY_USER_NAME = stringPreferencesKey("KEY_USER_NAME")
    private val KEY_USER_SURNAME = stringPreferencesKey("KEY_USER_SURNAME")

    fun getUserSurname(): Flow<String> {
        return context.storeData.data.map { preferences->
            preferences[KEY_USER_SURNAME]?:""
        }
    }

    fun getUserName(): Flow<String> {
        return context.storeData.data.map { preferences->
            preferences[KEY_USER_NAME]?:""
        }
    }

    suspend fun saveUserName(name: String) {
        withContext(Dispatchers.IO) {
            context.storeData.edit { preferences->
                preferences[KEY_USER_NAME] = name

            }
        }
    }

    suspend fun saveUserSurname(surname: String) {
        withContext(Dispatchers.IO) {
            context.storeData.edit { preferences->
                preferences[KEY_USER_SURNAME] = surname

            }
        }
    }
}