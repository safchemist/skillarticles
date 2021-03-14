package ru.skillbranch.skillarticles.data.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import ru.skillbranch.skillarticles.App
import ru.skillbranch.skillarticles.data.models.AppSettings

@SuppressLint("RestrictedApi")
class PrefManager {
    internal val preferences : SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(App.applicationContext()) }

    fun clearAll() {
        preferences.edit().clear().apply()
    }

    fun getAppSettings() : LiveData<AppSettings> {
        return MutableLiveData(AppSettings(
            isBigText = preferences.getBoolean("is_big", false),
            isDarkMode = preferences.getBoolean("is_dark", false)
        ))
    }

    fun isAuth() : MutableLiveData<Boolean> {
        return MutableLiveData(preferences.getBoolean("is_auth", false))
    }

    fun setAuth() {
        preferences.edit().putBoolean("is_auth", true).apply()
    }

    fun setAppSettings(appSettings: AppSettings) {
        with(preferences.edit()) {
            putBoolean("is_big", appSettings.isBigText)
            putBoolean("is_dark", appSettings.isDarkMode)
            apply()
        }
    }
}