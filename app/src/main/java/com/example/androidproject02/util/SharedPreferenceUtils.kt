package com.example.androidproject02.util

import android.content.SharedPreferences
import android.content.Context
import com.example.androidproject02.MainApplication

private const val ACESS_TOKEN = "access_token"
private const val EXPIRES_IN = "expires_in"


object SharedPreferenceUtils {

    fun saveToken(accessToken: String, expiresIn: Int) {
        with (getSharedPreferences().edit()){
            putString(ACESS_TOKEN, accessToken)
            putInt(EXPIRES_IN, ((System.currentTimeMillis() / 1000) + expiresIn).toInt())
            commit()
        }

    }

    fun getAccessToken(): String? {

        var accessToken: String? = null

        with(getSharedPreferences()){
            if(contains(ACESS_TOKEN) && contains(EXPIRES_IN)){
                val expiresIn = getInt(EXPIRES_IN, 0)
                if(expiresIn > (System.currentTimeMillis() / 1000)){
                    accessToken = getString(ACESS_TOKEN, "")
                }
            }

        }

        return accessToken
    }

    private fun getSharedPreferences(): SharedPreferences {
        val context = MainApplication.getApplicationContext()
        return context.getSharedPreferences("main", Context.MODE_PRIVATE)
    }
}