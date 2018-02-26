package com.app.android.data.source.remote.request

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/10/18.
 */
class RegisterRequestBody(val email: String,
                          val password: String,
                          val returnSecureToken: Boolean = true)
