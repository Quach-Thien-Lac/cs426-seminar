package com.example.sanguosuoclient.data.session

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

class SessionManager(
    private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session_prefs")

    private val TOKEN_KEY = stringPreferencesKey(name = "token")
    private val USER_ID = stringPreferencesKey(name = "user_id")

    private val _session = MutableStateFlow<UserSession?>(null)
    val session: StateFlow<UserSession?> = _session.asStateFlow()

    suspend fun restoreSession() {
        val prefs = context.dataStore.data.first()
        val token = prefs[TOKEN_KEY]
        val userId = prefs[USER_ID]
        if (token != null && userId != null) {
            _session.value = UserSession(userId = userId, token = token)
        }
    }

    suspend fun login(session: UserSession) {
        _session.value = session

        context.dataStore.edit{ prefs ->
            prefs[TOKEN_KEY] = session.token
            prefs[USER_ID] = session.userId
        }
    }

    suspend fun logout() {
        context.dataStore.edit {
            it.clear()
        }
        _session.value = null
    }

    fun getToken(): String? {
        return _session.value?.let { "Bearer ${it.token}" }
    }
}