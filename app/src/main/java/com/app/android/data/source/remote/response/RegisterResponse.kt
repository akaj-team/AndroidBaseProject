package com.app.android.data.source.remote.response

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/10/18.
 */
class RegisterResponse(val kind: String,
                       val idToken: String,
                       val email: String,
                       val refreshToken: String,
                       val expiresIn: Int,
                       val localId: String)
