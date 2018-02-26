package com.app.android.data.source.remote

import com.app.android.data.source.datasource.LoginDataSource
import com.app.android.data.source.remote.network.ApiClient
import com.app.android.data.source.remote.network.ApiService
import com.app.android.data.source.remote.request.RegisterRequestBody
import com.app.android.data.source.remote.response.RegisterResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
class LoginRemoteDataSource(private val apiService: ApiService) : LoginDataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun createUser(key: String, body: RegisterRequestBody): Single<RegisterResponse> = apiService.createUser(key, body)

}
