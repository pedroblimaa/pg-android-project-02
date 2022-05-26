package com.example.androidproject02.network


import android.util.Log
import com.example.androidproject02.util.SharedPreferenceUtils
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

private const val TAG = "OauthTokenAuthenticator"

class OauthTokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = retrieveNewToken()
        SharedPreferenceUtils.saveToken(token.acessToken, token.expiresIn)

        return response.request().newBuilder()
            .header("Authorization", "Bearer ${token.acessToken}")
            .build()
    }

    private fun retrieveNewToken(): OauthTokenResponse {
        Log.i(TAG, "Retrieving new token")
        return SalesApi.retrofitService.getToken(
            "Basic c2llY29sYTptYXRpbGRl",
            "password",
            "pedropb2@mail.com.br",
            "pass123"
        ).execute().body()!!
    }
}