package com.ioasys.diversidade.dataLocal.dataSource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ioasys.diversidade.data.local.dataSource.DataStoreDataSource
import com.ioasys.diversidade.domain.models.DataStoreUser
import com.ioasys.diversidade.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(Constants.PREFERENCES_NAME)

class DataStoreDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStoreDataSource {

    private object PreferencesKeys {
        val userId = stringPreferencesKey(Constants.USER_ID)
        val userName = stringPreferencesKey(Constants.USER_NAME)
        val accessToken = stringPreferencesKey(Constants.ACCESS_TOKEN)
    }

    private val dataStore: DataStore<Preferences> = context.dataStore

    override suspend fun saveUserInfo(userId: String, userName: String, accessToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userId] = userId
            preferences[PreferencesKeys.userName] = userName
            preferences[PreferencesKeys.accessToken] = accessToken
        }
    }

    override suspend fun logout() {
        dataStore.edit {
            it.remove(PreferencesKeys.userId)
            it.remove(PreferencesKeys.userName)
            it.remove(PreferencesKeys.accessToken)
        }
    }

    override val readUserInfo: Flow<DataStoreUser> = dataStore.data
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