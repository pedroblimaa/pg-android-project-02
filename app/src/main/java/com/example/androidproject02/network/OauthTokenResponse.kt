package com.example.androidproject02.network

import com.squareup.moshi.Json

class OauthTokenResponse (

    @Json(name = "access_token")
    val acessToken: String,

    @Json(name = "expires_in")
    val expiresIn: Int,
)