package com.app.aisheildme.bytrix.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.app.aisheildme.bytrix.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "aishieldme_session")

@Singleton
class SessionStore @Inject constructor(
    private val context: Context,
    private val json: Json,
) {
    private object Keys {
        val USER = stringPreferencesKey("user")
        val API_TOKEN = stringPreferencesKey("api_token")
        val REMEMBERED_EMAIL = stringPreferencesKey("remembered_email")
        val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
    }

    private val preferences: Flow<Preferences> = context.dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }

    val user: Flow<User?> = preferences.map { prefs ->
        prefs[Keys.USER]?.let { raw ->
            runCatching { json.decodeFromString<User>(raw) }.getOrNull()
        }
    }

    suspend fun currentUser(): User? = user.first()

    suspend fun apiToken(): String? = preferences.first()[Keys.API_TOKEN]

    suspend fun setUser(user: User) {
        context.dataStore.edit { it[Keys.USER] = json.encodeToString(user) }
    }

    suspend fun setApiToken(token: String) {
        context.dataStore.edit { it[Keys.API_TOKEN] = token }
    }

    suspend fun rememberedEmail(): String? =
        preferences.first()[Keys.REMEMBERED_EMAIL]?.takeIf { it.isNotEmpty() }

    suspend fun setRememberedEmail(email: String?) {
        context.dataStore.edit { prefs ->
            if (email.isNullOrEmpty()) prefs.remove(Keys.REMEMBERED_EMAIL)
            else prefs[Keys.REMEMBERED_EMAIL] = email
        }
    }

    suspend fun isBiometricEnabled(): Boolean =
        preferences.first()[Keys.BIOMETRIC_ENABLED] ?: false

    suspend fun setBiometricEnabled(enabled: Boolean) {
        context.dataStore.edit { it[Keys.BIOMETRIC_ENABLED] = enabled }
    }

    suspend fun clear() {
        context.dataStore.edit { prefs ->
            prefs.remove(Keys.USER)
            prefs.remove(Keys.API_TOKEN)
        }
    }
}
