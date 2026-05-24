package com.example.sampleandroidapp

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID

// 1. Create a top-level extension property to initialize DataStore exactly once
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_prefs")

object DeviceIdManager {
    private const val TAG = "DeviceIdManager"
    // 2. Define a type-safe Preference Key instead of a raw String
    private val PREF_UNIQUE_ID = stringPreferencesKey("pref_unique_id")

    /**
     * Fetches the unique ID. Because DataStore is asynchronous,
     * this must be a 'suspend' function run inside a Coroutine scope.
     */
    suspend fun getAppInstanceId(context: Context): String {
        // 3. Read the current value using a Kotlin Flow
        val existingId = context.dataStore.data
            .map { preferences -> preferences[PREF_UNIQUE_ID] }
            .first() // Takes the first emitted value from the disk and stops collecting

        // 4. If it exists, return it immediately
        if (existingId != null) {
            Log.d(TAG, "Found existing ID: $existingId")
            return existingId
        }

        // 5. If it's null, generate a new UUID
        val newId = UUID.randomUUID().toString()

        // 6. Save the new ID to disk asynchronously via the edit function
        context.dataStore.edit { preferences ->
            preferences[PREF_UNIQUE_ID] = newId
        }

        return newId
    }
}