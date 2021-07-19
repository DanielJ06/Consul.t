package com.ioasys.diversidade.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ioasys.diversidade.domain.models.DataStoreUser
import com.ioasys.diversidade.utils.Constants.Companion.ACCESS_TOKEN
import com.ioasys.diversidade.utils.Constants.Companion.PREFERENCES_NAME
import com.ioasys.diversidade.utils.Constants.Companion.USER_ID
import com.ioasys.diversidade.utils.Constants.Companion.USER_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferencesKeys {
        val userId = stringPreferencesKey(USER_ID)
        val userName = stringPreferencesKey(USER_NAME)
        val accessToken = stringPreferencesKey(ACCESS_TOKEN)
    }

    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun saveUserInfo(userId: String, userName: String, accessToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userId] = userId
            preferences[PreferencesKeys.userName]= userName
            preferences[PreferencesKeys.accessToken] = accessToken
        }
    }

    suspend fun logout() {
        dataStore.edit {
            it.remove(PreferencesKeys.userId)
            it.remove(PreferencesKeys.userName)
            it.remove(PreferencesKeys.accessToken)
        }
    }

    val readUserInfo: Flow<DataStoreUser> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val savedUserId = preferences[PreferencesKeys.userId] ?: ""
            val savedUserName = preferences[PreferencesKeys.userName] ?: ""
            val savedAccessToken = preferences[PreferencesKeys.accessToken] ?: ""

            DataStoreUser(
                savedUserId,
                savedUserName,
                savedAccessToken
            )
        }

}