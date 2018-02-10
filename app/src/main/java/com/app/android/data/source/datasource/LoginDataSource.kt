package com.app.android.data.source.datasource

import com.app.android.data.source.remote.request.RegisterRequestBody
import com.app.android.data.source.remote.response.RegisterResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
interface LoginDataSource {

    fun createUser(key: String, body: RegisterRequestBody): Single<RegisterResponse>
}
